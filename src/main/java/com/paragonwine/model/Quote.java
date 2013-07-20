/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paragonwine.model;

import java.math.BigDecimal;
import java.util.UUID;
import org.joda.time.DateTime;

/**
 *
 * @author duncan
 */
public class Quote {
    
    private Offer offer;
    private DateTime createDate = new DateTime();
    private BigDecimal totalPrice;
    private BigDecimal processingCharge = BigDecimal.TEN.multiply(BigDecimal.valueOf(2));

    public Quote(Offer offer) {
        this.offer = offer;
        this.totalPrice = offer.getPrice();
    }

    public UUID getId() {
        return offer.getId();
    }
    
    public DateTime getCreateDate() {
        return createDate;
    }

    public Offer getOffer() {
        return offer;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getProcessingCharge() {
        return processingCharge;
    }

    public void setProcessingCharge(BigDecimal processingCharge) {
        this.processingCharge = processingCharge;
        this.totalPrice = totalPrice.add(processingCharge);
    }

}
