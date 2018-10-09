package com.farmershao.www.stockmember.configuration;

import com.farmershao.www.stockmember.entity.po.User;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ShaoYu on 2018/10/9.
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisTestConfig {

    @Bean
    public User getUser(RedisProperties redisProperties){
        System.out.println(redisProperties.getHost());
        return new User();
    }
}
