package com.rio.messenger.cache;

import com.rio.messenger.exception.MessengerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.time.Duration;
import java.util.Optional;

@Component
public class JedisClient {

    JedisClientFactory factory;

    @Autowired
    public JedisClient(JedisClientFactory factory){
        this.factory = factory;
    }

    public void addToCache(String key, String value){
        addToCache(key,value,600);
    }

    public void addToCache(String key, String value, long seconds){
        try(Jedis jedis = factory.getJedisResource()){
            jedis.setex(key,seconds,value);
        } catch (Exception e){
            throw new MessengerException("redis",e.getMessage());
        }
    }

    public Optional<String> getAndUpdateExpiry(String key, long seconds){
        try(Jedis jedis = factory.getJedisResource()){
            String val = jedis.get(key);
            if (val == null || val.isEmpty()){
                return Optional.empty();
            } else {
                jedis.setex(key,seconds,val);
            }
            return Optional.of(val);
        } catch (Exception e){
            throw new MessengerException("redis",e.getMessage());
        }
    }
}
