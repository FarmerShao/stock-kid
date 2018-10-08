package com.farmershao.www.stockmember.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * RedisConfig: redis 环境配置
 *
 * @author ShaoYu
 * @since 2018/10/6 0006 下午 12:17
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {

    private String hostName;
    private int port;
    private int poolMaxIdle;
    private int poolMaxTotal;
    private long maxWaitMillis;

    /**
     * JedisPoolConfig 连接池
     * @return
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲数
        jedisPoolConfig.setMaxIdle(poolMaxIdle);
        // 连接池的最大数据库连接数
        jedisPoolConfig.setMaxTotal(poolMaxTotal);
        // 最大建立连接等待时间
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        return jedisPoolConfig;
    }

    //    /**
//     * redis 哨兵模式配置
//     * @return
//     */
//    @Bean
//    public RedisSentinelConfiguration sentinelConfiguration(){
//        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration();
//        //配置matser的名称
//        RedisNode redisNode = new RedisNode(hostName, port);
//        redisNode.setName("mymaster");
//        redisSentinelConfiguration.master(redisNode);
//        //配置redis的哨兵sentinel
//        RedisNode senRedisNode = new RedisNode(senHost1,senPort1);
//        Set<RedisNode> redisNodeSet = new HashSet<>();
//        redisNodeSet.add(senRedisNode);
//        redisSentinelConfiguration.setSentinels(redisNodeSet);
//        return redisSentinelConfiguration;
//    }

    /**
     * 配置工厂
     * @param jedisPoolConfig
     * @return
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory(JedisPoolConfig jedisPoolConfig) {
        return new JedisConnectionFactory(jedisPoolConfig);
    }

    /**
     * 实例化 RedisTemplate 对象
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
