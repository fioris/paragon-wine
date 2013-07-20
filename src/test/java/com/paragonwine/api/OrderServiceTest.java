/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paragonwine.api;

import com.paragonwine.cdi.OfferEvent;
import com.paragonwine.cdi.QuoteQualifier;
import com.paragonwine.exception.FulfillmentException;
import com.paragonwine.exception.QuoteExpiredException;
import com.paragonwine.model.Offer;
import com.paragonwine.model.Order;
import com.paragonwine.model.Quote;
import com.paragonwine.service.FulfillmentSystem;
import com.paragonwine.service.OrderSystem;
import com.paragonwine.service.QueryAnalyzerSystem;
import com.paragonwine.service.VeryFastQuoteCacheConfiguration;
import com.paragonwine.service.QuoteSystem;
import com.paragonwine.service.WarehouseSystem;
import com.paragonwine.web.UserBean;
import java.util.List;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.MavenResolverSystem;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 *
 * @author duncan
 */
@RunWith(Arquillian.class)
public class OrderServiceTest {

    @Inject
    OrderService orderService;
    
    @Inject
    UserBean userBean;
    
    @Deployment
    public static WebArchive createDeployment() {
        MavenResolverSystem resolver = Maven.resolver();
        JavaArchive[] infinispanCdi = resolver.loadPomFromFile("pom.xml").resolve("org.infinispan:infinispan-cdi").withTransitivity().as(JavaArchive.class);
        
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(UserBean.class, Order.class, Offer.class, Quote.class)
                .addClasses(OrderService.class, OrderSystem.class, OfferEvent.class)
                .addClasses(Warehouse.class, WarehouseSystem.class)
                .addClasses(QuoteStore.class, QuoteSystem.class, QuoteQualifier.class, VeryFastQuoteCacheConfiguration.class)
                .addClasses(FulfillmentService.class, FulfillmentSystem.class)
                .addClasses(FulfillmentException.class, QuoteExpiredException.class)
                .addClass(QueryAnalyzerSystem.class)
                .addAsLibraries(infinispanCdi)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .setManifest(new StringAsset("Manifest-Version: 1.0\r\nDependencies: org.infinispan, org.joda.time, javax.api\r\n"));
    }

    /**
     * Test of searchForProduct method, of class OrderService.
     */
    @Test
    public void testSearchForProduct() {
        userBean.setUserAuthToken("test@me.com");
        List<Offer> offers = orderService.searchForProduct("Cabernet Sauvignon");
        
        assertThat(offers, hasSize(1));
    }

    /**
     * Test of confirmOrder method, of class OrderService.
     */
    @Test
    public void testConfirmOrderWithinExpirationPeriod() throws InterruptedException {
        userBean.setUserAuthToken("test@me.com");
        List<Offer> offers = orderService.searchForProduct("Cabernet Sauvignon");
        
        Thread.sleep(2000);
        
        assertThat(orderService.confirmOrder(userBean.getUserAuthToken(), offers.get(0)), is(true));
    }
    
    /**
     * Test of confirmOrder method, of class OrderService.
     */
    @Test
    public void testConfirmOrderOutsideExpirationPeriods() throws InterruptedException {
        userBean.setUserAuthToken("test@me.com");
        List<Offer> offers = orderService.searchForProduct("Cabernet Sauvignon");
        
        Thread.sleep(6000);
        
        assertThat(orderService.confirmOrder(userBean.getUserAuthToken(), offers.get(0)), is(false));
    }
}