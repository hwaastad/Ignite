/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.cache;

import java.util.ArrayList;
import java.util.List;
import javax.cache.Cache;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import lombok.extern.java.Log;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.primefaces.extensions.model.fluidgrid.FluidGridItem;

/**
 *
 * @author helge
 */
@Log
public class CacheProducer {

    @Produces
    @ApplicationScoped
    public Cache<String, Object> getCache() {
        log.info("producing cache.....");
        CacheConfiguration<String, Object> cc = new CacheConfiguration<>("xxxcache");
        //cc.setIndexedTypes(String.class, FluidGridItem.class);
//         cc.setTypes(String.class,FluidGridItem.class);
        return Ignition.ignite().getOrCreateCache(cc);
    }
}
