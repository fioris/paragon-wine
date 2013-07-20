/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paragonwine.cdi;

import com.paragonwine.model.Offer;
import java.util.List;

/**
 *
 * @author duncan
 */
public class OfferEvent {
    
    List<Offer> offers;

    public OfferEvent(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Offer> getOffers() {
        return offers;
    }
    
}
