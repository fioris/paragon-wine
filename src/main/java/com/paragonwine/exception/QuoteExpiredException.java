/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paragonwine.exception;

/**
 *
 * @author duncan
 */
public class QuoteExpiredException extends Exception {

    /**
     * Creates a new instance of
     * <code>QuoteExpiredException</code> without detail message.
     */
    public QuoteExpiredException() {
    }

    /**
     * Constructs an instance of
     * <code>QuoteExpiredException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public QuoteExpiredException(String msg) {
        super(msg);
    }
}
