package com.farmershao.www.stockmember;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @EnableDiscoveryClient 与 @EnableEurekaClient 的区别：
 * @EnableEurekaClient ：只是Eureka 的服务中心的客户端
 * @EnableDiscoveryClient ： 服务中心的客户端，不仅仅局限于Eureka。
 */
//@EnableDiscoveryClient
@SpringBootApplication
@ServletComponentScan
public class StockMemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockMemberApplication.class, args);
	}
}
