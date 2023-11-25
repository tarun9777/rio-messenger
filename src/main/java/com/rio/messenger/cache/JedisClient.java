package com.rio.messenger.cache;

import com.rio.messenger.exception.MessengerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.time.Duration;
import java.util.Map;
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

    public void deleteKey(String key){
        try(Jedis jedis = factory.getJedisResource()){
            jedis.del(key);
        } catch (Exception e){
            throw new MessengerException("redis",e.getMessage());
        }
    }

//    public void addToCache(String key, Map<String,String> value, long seconds){
//        try(Jedis jedis = factory.getJedisResource()){
//            jedis.hset(key,value);
//            jedis.expire(key,seconds);
//        } catch (Exception e){
//            throw new MessengerException("redis",e.getMessage());
//        }
//    }
//
//    public Map<String,String> getUserFromCache(String key){
//        try(Jedis jedis = factory.getJedisResource()){
//            return jedis.ge
//        } catch (Exception e){
//            throw new MessengerException("redis",e.getMessage());
//        }
//    }

    public Optional<String> getAndUpdateAuthExpiry(String key, long seconds){
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
