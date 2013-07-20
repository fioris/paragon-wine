/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paragonwine.service;

import com.paragonwine.api.Warehouse;
import com.paragonwine.cdi.OfferEvent;
import com.paragonwine.model.Offer;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 *
 * @author duncan
 */
@Stateless @LocalBean
public class WarehouseSystem implements Warehouse {

    @Inject
    Event<OfferEvent> event;
    
    @Override
    public List<Offer> searchFor(String query) {
        Offer offer = new Offer(UUID.randomUUID(), "Cabernet", BigDecimal.TEN);
        
        List<Offer> result = Collections.singletonList(offer);
        event.fire(new OfferEvent(result));
        return result;
    }
    
}
