package com.rio.messenger.cache.config;

import com.rio.messenger.exception.MessengerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

@Component
public class JedisConfig {


    @Autowired
    Environment env;

    private JedisPool jedisPool;

    public JedisPool getJedisPool(){
        if (jedisPool != null) return jedisPool;
        String url = env.getProperty("redis.url");
        if (url == null || url.isEmpty()) throw new MessengerException("redis url not present");
        jedisPool = new JedisPool(buildPoolConfig(),url);
        return jedisPool;
    }


    private JedisPoolConfig buildPoolConfig() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }
}
