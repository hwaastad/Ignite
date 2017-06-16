/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.cache;

import java.util.List;
import javax.cache.Cache;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;

/**
 *
 * @author helge
 */
@Slf4j
public class CacheProducer {


    @Produces
    @ApplicationScoped
    public Cache<String, List> getCache() {
        log.info("producing cache.....Loader: {}", Thread.currentThread().getContextClassLoader());
        CacheConfiguration<String, List> cc = new CacheConfiguration<>("xxxcache");
        IgniteConfiguration configuration = Ignition.ignite().configuration();
        //Ignition.ignite().configuration().setClassLoader(Thread.currentThread().getContextClassLoader());
        return Ignition.ignite().getOrCreateCache(cc);
    }
}
