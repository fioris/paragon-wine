/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paragonwine.api;

import com.paragonwine.exception.FulfillmentException;
import com.paragonwine.model.Offer;
import com.paragonwine.model.Order;

/**
 *
 * @author duncan
 */
public interface FulfillmentService {
    
    Order createOrder(String userAuthToken, Offer offer) throws FulfillmentException;
    
}
