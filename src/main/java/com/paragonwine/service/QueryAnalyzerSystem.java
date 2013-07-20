/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paragonwine.service;

import com.paragonwine.cdi.OfferEvent;
import com.paragonwine.model.Offer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Observes;

/**
 *
 * @author duncan
 */
public class QueryAnalyzerSystem {
    
    public void onEvent(@Observes OfferEvent event) {
        for (Offer offer : event.getOffers()) {
            Logger.getLogger(QueryAnalyzerSystem.class.getName()).log(Level.WARNING, "ANALYZING {0}", offer);
        }
    }
}
