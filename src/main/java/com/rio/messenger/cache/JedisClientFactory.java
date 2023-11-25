package com.rio.messenger.cache;

import com.rio.messenger.cache.config.JedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@Component
public class JedisClientFactory {

    @Autowired
    JedisConfig config;


    public Jedis getJedisResource(){
        return config.getJedisPool().getResource();
    }

}
