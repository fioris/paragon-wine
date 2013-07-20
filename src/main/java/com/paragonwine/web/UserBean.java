/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paragonwine.web;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author duncan
 */
@SessionScoped
public class UserBean implements Serializable {
    
    private String userAuthToken;

    public String getUserAuthToken() {
        return userAuthToken;
    }

    public void setUserAuthToken(String userAuthToken) {
        this.userAuthToken = userAuthToken;
    }
    
}
