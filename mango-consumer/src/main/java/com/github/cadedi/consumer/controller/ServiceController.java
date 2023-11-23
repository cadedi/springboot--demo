package com.github.cadedi.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 获取所有名为mango-producer的服务
     * @return
     */
    @GetMapping("/services")
    public Object services(){
        return discoveryClient.getInstances("mango-producer");
    }

    /**
     * 从所有服务中选择一个服务(轮询)
     * @return
     */
    @GetMapping("/discover")
    public Object discover(){
        return loadBalancerClient.choose("mango-producer").getUri();
    }

}
