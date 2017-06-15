/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.servlet;

import java.util.Arrays;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import lombok.extern.java.Log;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;

/**
 *
 * @author helge
 */
@WebListener
@Log

public class StartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Initialized....");
        Ignition.setClientMode(true);

        IgniteConfiguration ic = new IgniteConfiguration();

        ic.setPeerClassLoadingEnabled(true);

        TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder(true);
        ipFinder.setAddresses(Arrays.asList("127.0.0.1:47500"));
        discoverySpi.setIpFinder(ipFinder);
        ic.setDiscoverySpi(discoverySpi);
        Ignition.start(ic);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("Destroyed....");
        Ignition.stopAll(true);
    }

}
