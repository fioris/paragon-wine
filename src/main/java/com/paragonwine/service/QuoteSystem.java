/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paragonwine.service;

import com.paragonwine.api.QuoteStore;
import com.paragonwine.cdi.OfferEvent;
import com.paragonwine.cdi.QuoteQualifier;
import com.paragonwine.exception.QuoteExpiredException;
import com.paragonwine.model.Offer;
import com.paragonwine.model.Quote;
import com.paragonwine.web.UserBean;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import org.infinispan.Cache;
import org.joda.time.DateTime;
import org.joda.time.Duration;

/**
 *
 * @author duncan
 */
public class QuoteSystem implements QuoteStore {

    // these values should be dynamicly fetched from the app server (JNDI)
    public static final BigDecimal STANDARD_PROCESSING_CHARGE = new BigDecimal(5);
    public static final BigDecimal CASE_SIZE = new BigDecimal(12);
    
    @Inject
    @QuoteQualifier
    Cache<String, Quote> quotes;
    
    @Inject UserBean userBean;
    
    public void onOffer(@Observes OfferEvent event) {
        for (Offer offer : event.getOffers()) {
            put(userBean.getUserAuthToken(), offer);
            Logger.getLogger(QuoteSystem.class.getName()).log(Level.INFO, "put {0} into cache", offer);
        }
    }
    
    @Override
    public void put(String userAuthToken, Offer offer) {
        /**
         * Only insert offer into the cache if none exists, otherwise
         * the old offer would be overwritten with a new eviction time
         */
        quotes.putIfAbsent(createKey(userAuthToken, offer.getId()), new Quote(offer));
    }
    
    @Override
    public Quote get(String userAuthToken, Offer offer) throws QuoteExpiredException {
        Quote quote = quotes.get(createKey(userAuthToken, offer.getId()));
        
        if (quote == null) {
            throw new QuoteExpiredException("Quote not found");
        }
        /**
         * insert some business logic to get the correct processing-charge
         */
        long millis = new Duration(quote.getCreateDate(), new DateTime()).getMillis();
        if (millis < (2 * 60 *1000)) {
            quote.setProcessingCharge(BigDecimal.ZERO);
        } else {
            BigDecimal standardPrice = quote.getOffer().getPrice().multiply(CASE_SIZE);
            quote.setProcessingCharge(standardPrice.min(BigDecimal.TEN));
        }
        
        return quote;
    }
    
    private String createKey(String userAuthToken, UUID uuid) {
        return userAuthToken.concat(uuid.toString());
    }
}
