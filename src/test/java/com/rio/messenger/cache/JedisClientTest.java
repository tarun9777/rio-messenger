package com.rio.messenger.cache;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

import java.util.Optional;

@SpringBootTest
public class JedisClientTest {

    @Autowired
    JedisClient jedisClient;
    @Autowired
    JedisClientFactory factory;
    Jedis jedis;

    @BeforeEach
    void setup(){
        jedis = factory.getJedisResource();
    }

    @AfterEach
    void shutDown(){
        jedis.close();
    }

    @Test
    void addToCacheTest_1(){
        String key = "abc";
        String val = "qw123kd";
        jedisClient.addToCache(key,val);
        String actual = jedis.get(key);
        Assertions.assertEquals(val,actual);
    }

    @Test
    void getAndUpdateExpiryTest_1(){
        String key = "123";
        String val = "qw123kd";
        jedis.setex(key,600,val);
        String actual = jedisClient.getAndUpdateExpiry(key,700).get();
        Assertions.assertEquals(val,actual);
    }

    @Test
    void getAndUpdateExpiryTest_2(){
        String key = "pqr";
        Optional<String> actual = jedisClient.getAndUpdateExpiry(key,700);
        Assertions.assertTrue(actual.isEmpty());
    }
}
