/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.service;

import java.net.URL;
import java.util.Collection;
import java.util.Properties;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.openejb.jee.WebApp;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.Configuration;
import org.apache.openejb.testing.EnableServices;
import org.apache.openejb.testing.Module;
import org.apache.openejb.testing.RandomPort;
import org.apache.openejb.testng.PropertiesBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.primefaces.extensions.model.fluidgrid.FluidGridItem;
import org.waastad.cache.CacheProducer;
import org.waastad.servlet.StartupListener;

/**
 *
 * @author helge
 */
@EnableServices("jax-rs")
@RunWith(ApplicationComposer.class)
public class RestResourceIT {

    @RandomPort("httpejbd")
    private URL url;

    @Module
    @Classes(value = {Rest2Resource.class, CacheProducer.class, StartupListener.class}, cdi = true)
    public WebApp app() {
        return new WebApp().addListener(StartupListener.class.getName());
    }

    
    @Configuration
    public Properties props(){
        Properties p = new Properties();
        p.put("openejb.log.factory", "slf4j");
        return p;
    }

    /**
     * Test of get method, of class Rest2Resource.
     */
    @Test
    public void testGet() throws Exception {
        System.out.println(url.toExternalForm());
        WebClient client = WebClient.create(url.toExternalForm()+"/openejb/counter");
        WebClient.getConfig(client).getInInterceptors().add(new LoggingInInterceptor());
        WebClient.getConfig(client).getOutInterceptors().add(new LoggingOutInterceptor());
        Response post = client.accept(MediaType.APPLICATION_JSON).post("xxx");
       client.post("xxx");
    }

}
