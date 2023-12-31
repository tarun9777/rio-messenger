package com.rio.messenger.cache;

import com.rio.messenger.cache.config.JedisConfigBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;


/**
 * this class act as factory to create redis connections from redis pool
 */
@Component
public class JedisClientFactory {

    @Autowired
    JedisConfigBean config;


    public Jedis getJedisResource(){
        return config.getJedisPool().getResource();
    }

}
