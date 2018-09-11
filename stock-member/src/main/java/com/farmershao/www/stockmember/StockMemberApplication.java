package com.farmershao.www.stockmember;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan
@EnableAutoConfiguration
@Configuration
public class StockMemberApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockMemberApplication.class, args);
	}
}
