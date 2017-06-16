/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.cache.Cache;
import javax.cache.Caching;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.primefaces.extensions.model.fluidgrid.FluidGridItem;

/**
 *
 * @author helge
 */
@Path("/counter")
@Singleton
@Slf4j
public class Rest2Resource {

    @Inject
    private Cache<String, List> cache;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(String key) {
        log.info("getting from cache...");

        if (!cache.containsKey(key)) {
            log.info("Adding to cache");
            FluidGridItem item = new FluidGridItem(UUID.randomUUID());
            List<FluidGridItem> ll = new ArrayList<>();
            ll.add(item);
            cache.put(key, ll);
//            cache.put(key, Arrays.asList(ll.toArray()));
        }
        List<FluidGridItem> list = (List<FluidGridItem>) cache.get(key);

        log.info(String.format("Got %s from cache", list.toString()));
        return Response.ok(list).build();
    }
}
