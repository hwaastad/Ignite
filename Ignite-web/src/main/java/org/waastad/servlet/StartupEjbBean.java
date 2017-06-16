/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.servlet;

import java.util.Arrays;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import lombok.extern.slf4j.Slf4j;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;

/**
 *
 * @author helge
 */
//@Singleton
//@Startup
@Slf4j
public class StartupEjbBean {

    @PostConstruct
    public void construct() {
        log.info("Initialized....Loader: {}", Thread.currentThread().getContextClassLoader());
        Ignition.setClientMode(true);

        IgniteConfiguration ic = new IgniteConfiguration();

        ic.setPeerClassLoadingEnabled(true);
        ic.setClassLoader(Thread.currentThread().getContextClassLoader());
        TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder(true);
        ipFinder.setAddresses(Arrays.asList("127.0.0.1:47500"));
        discoverySpi.setIpFinder(ipFinder);
        ic.setDiscoverySpi(discoverySpi);
        Ignition.start(ic);
    }

    @PreDestroy
    public void destroy() {
        log.info("Destroyed....");
        Ignition.stopAll(true);
    }
}
