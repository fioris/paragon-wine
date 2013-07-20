/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paragonwine.service;

import com.paragonwine.annotation.ServiceFacade;
import com.paragonwine.api.FulfillmentService;
import com.paragonwine.api.OrderService;
import com.paragonwine.api.Warehouse;
import com.paragonwine.exception.FulfillmentException;
import com.paragonwine.model.Offer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author duncan
 */
@ServiceFacade
@Stateless @LocalBean
public class OrderSystem implements OrderService {
    
    @Inject Warehouse warehouse;
    
    @Inject FulfillmentService fulfillmentService;
    
    @Override
    public List<Offer> searchForProduct(String query) {
        List<Offer> offers = warehouse.searchFor(query);
        return offers;
    }

    @Override
    public boolean confirmOrder(String userAuthToken, Offer offer) {
        Logger.getLogger(OrderSystem.class.getName()).log(Level.INFO, "confirming {0} by {1}", new Object[]{offer, userAuthToken});
        try {
            fulfillmentService.createOrder(userAuthToken, offer);
            return true;
        } catch (FulfillmentException ex) {
            Logger.getLogger(OrderSystem.class.getName()).log(Level.INFO, ex.getMessage());
            return false;
        }
    }
    
}
