package com.rio.messenger.util;


import com.rio.messenger.bo.UserBO;
import com.rio.messenger.cache.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthUtil {

    private JedisClient jedisClient;

    @Autowired
    public AuthUtil(JedisClient jedisClient){
        this.jedisClient = jedisClient;
    }

    public boolean isSessionActive(String username){
        Optional<String> session = jedisClient.getAndUpdateAuthExpiry(username,600);
        return session.isPresent();

    }

}
