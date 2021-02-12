package com.microservices.gateway;

import com.netflix.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class GatewayDiscoveryConfiguration {




    DiscoveryClientRouteDefinitionLocator definitionLocator  (ReactiveDiscoveryClient discoveryClient, DiscoveryLocatorProperties properties){
        return new DiscoveryClientRouteDefinitionLocator(discoveryClient, properties);
    }
}