/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.paragonwine.service;

import com.paragonwine.cdi.QuoteQualifier;
import javax.enterprise.inject.Produces;
import org.infinispan.cdi.ConfigureCache;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;

/**
 *
 * @author duncan
 */
public class QuoteCacheConfiguration {
    
    @ConfigureCache("my-cache-name")
    @QuoteQualifier
    @Produces
    Configuration myCacheConfiguration() {
        return new ConfigurationBuilder()
                .expiration()
                    .lifespan(20 * 60 * 1000)
                .build();
    }
}
