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
public class Order {
    
    private UUID id;
    private DateTime timestamp = new DateTime();
    private BigDecimal totalPrice;
    private String userAuthtoken;

    public Order(BigDecimal totalPrice, UUID id, String userAuthToken) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.userAuthtoken = userAuthToken;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public DateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(DateTime timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUserAuthtoken() {
        return userAuthtoken;
    }

    public void setUserAuthtoken(String userAuthtoken) {
        this.userAuthtoken = userAuthtoken;
    }
    
}
