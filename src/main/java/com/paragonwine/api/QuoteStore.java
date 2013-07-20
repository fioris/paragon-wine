/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paragonwine.api;

import com.paragonwine.exception.QuoteExpiredException;
import com.paragonwine.model.Offer;
import com.paragonwine.model.Quote;

/**
 *
 * @author duncan
 */
public interface QuoteStore {
    
    void put(String userAuthToken, Offer offer);
    
    Quote get(String userAuthToken, Offer offer) throws QuoteExpiredException;
    
}
