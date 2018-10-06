package com.farmershao.www.stockmember.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * RedisConfig: redis 环境配置
 *
 * @author ShaoYu
 * @since 2018/10/6 0006 下午 12:17
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {


}
