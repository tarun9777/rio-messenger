package com.rio.messenger.util;

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

    public boolean isSessionActive(String username,String token){
        Optional<String> session = jedisClient.getFromCache(username);
        if (!session.isPresent()) return false;
        if(token.equals(session.get())){
            jedisClient.updateKeyExpiry(username,600);
            return true;
        }
        return false;
    }

}
