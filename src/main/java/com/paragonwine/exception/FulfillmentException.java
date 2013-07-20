/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paragonwine.exception;

/**
 *
 * @author duncan
 */
public class FulfillmentException extends Exception {

    /**
     * Creates a new instance of
     * <code>FulfillmentException</code> without detail message.
     */
    public FulfillmentException() {
    }

    /**
     * Constructs an instance of
     * <code>FulfillmentException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public FulfillmentException(String msg) {
        super(msg);
    }
}
