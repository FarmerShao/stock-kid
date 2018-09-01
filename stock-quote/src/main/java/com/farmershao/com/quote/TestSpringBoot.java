package com.farmershao.com.quote;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class TestSpringBoot {


    @RequestMapping("/")
    String home() { return "Hello World!"; }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestSpringBoot.class, args);
    }

}





