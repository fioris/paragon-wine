/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paragonwine.web;

import com.paragonwine.model.Offer;
import com.paragonwine.service.OrderSystem;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author duncan
 */
@RequestScoped
public class OfferSearchBean {
    
    @Inject
    OrderSystem orderSystem;
    
    private String query;
    
    public void search() {
        List<Offer> offers = orderSystem.searchForProduct(query);
        
        
    }
}
