/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paragonwine.service;

import com.paragonwine.api.FulfillmentService;
import com.paragonwine.api.QuoteStore;
import com.paragonwine.exception.FulfillmentException;
import com.paragonwine.exception.QuoteExpiredException;
import com.paragonwine.model.Offer;
import com.paragonwine.model.Order;
import com.paragonwine.model.Quote;
import java.math.BigDecimal;
import javax.inject.Inject;

/**
 *
 * @author duncan
 */
public class FulfillmentSystem implements FulfillmentService {

    @Inject QuoteStore quoteStore;
    
    @Override
    public Order createOrder(String userAuthToken, Offer offer) throws FulfillmentException {
        try {
            Quote quote = quoteStore.get(userAuthToken, offer);
            return new Order(quote.getTotalPrice(), quote.getId(), userAuthToken);
        } catch (QuoteExpiredException ex) {
            //Do something or re-throw the error...
            throw new FulfillmentException(ex.getMessage());
        }
    }
    
}
