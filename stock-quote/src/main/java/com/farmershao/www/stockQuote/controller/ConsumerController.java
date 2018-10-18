package com.farmershao.www.stockQuote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by ShaoYu on 2018/10/18.
 */
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/consumer", method = RequestMethod.GET)
    public String helloConsumer(){
        return restTemplate.getForEntity("http://STOCK-MEMBER/hello", String.class).getBody();
    }
}
