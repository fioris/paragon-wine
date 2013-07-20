/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paragonwine.api;

import com.paragonwine.model.Offer;
import java.util.List;

/**
 *
 * @author duncan
 */
public interface OrderService {
    
    List<Offer> searchForProduct(String query);

    boolean confirmOrder(String userAuthToken, Offer offer);
    
}
