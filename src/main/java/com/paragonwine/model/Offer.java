/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paragonwine.model;

import java.math.BigDecimal;
import java.util.UUID;

/**
 *
 * @author duncan
 */
public class Offer {
 
    private UUID id;
    private String description;
    private BigDecimal price;

    public Offer() {
    }

    public Offer(UUID id, String description, BigDecimal price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Offer{" + "id=" + id + ", description=" + description + ", price=" + price + '}';
    }
    
}
