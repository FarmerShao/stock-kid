package com.farmershao.www.stockmember.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * redis + spring cache 配置
 */
@Configuration
@EnableCaching
public class RedisCacheConfig {

    /**
     * 配置RedisCacheManager
     * @param redisTemplate redisTemplate
     * @return   RedisCacheManager
     */
    @Bean
    public RedisCacheManager cacheManager(RedisTemplate<Object, Object> redisTemplate) {
        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
        //设置默认过期时间 60S
        cacheManager.setDefaultExpiration(60);
        return cacheManager;
    }

    /**
     * 自定义生成redis-key
     *
     * @return KeyGenerator的实现类
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> "member";
    }

}
