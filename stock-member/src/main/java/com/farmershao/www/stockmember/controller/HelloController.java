package com.farmershao.www.stockmember.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * HelloController
 *
 * @author Shao Yu
 * @since 2018/10/17 18:11
 **/
@RestController
public class HelloController {

    private final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index(){
        ServiceInstance serviceInstance = discoveryClient.getLocalServiceInstance();
        logger.info("/hello, host:{}, service_id:{}", serviceInstance.getHost(), serviceInstance.getServiceId());
        return "hello world";
    }
}
