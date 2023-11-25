package com.rio.messenger.service;


import com.rio.messenger.bo.HashedPasscode;
import com.rio.messenger.bo.UserBO;
import com.rio.messenger.cache.JedisClient;
import com.rio.messenger.dao.UserDao;
import com.rio.messenger.entity.User;
import com.rio.messenger.exception.UserException;
import com.rio.messenger.util.PasscodeHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final PasscodeHashUtil hashUtil;
    private final UserDao userDao;
    private final JedisClient jedisClient;

    @Autowired
    public AuthServiceImpl(PasscodeHashUtil hashUtil, UserDao userDao, JedisClient jedisClient){
        this.hashUtil = hashUtil;
        this.userDao = userDao;
        this.jedisClient = jedisClient;
    }

    @Override
    @Transactional("MSG_TM")
    public void addUser(UserBO userBO) throws NoSuchAlgorithmException {
        HashedPasscode hashedPasscode = hashUtil.getPasscodeHash(userBO.getPasscode());
        User userEntity = User.UserEntityBuilder.getBuilder()
                .setUsername(userBO.getUsername())
                .setHash(hashedPasscode.getHash())
                .setSalt(hashedPasscode.getSalt())
                .build();
        userDao.save(userEntity);
    }

    @Override
    @Transactional("MSG_TM")
    public void authenticateUser(UserBO userBO) throws Exception {
        Optional<User> user = userDao.findById(userBO.getUsername());
        if (!user.isPresent()) throw new UserException("User not found");
        hashUtil.validatePasscode(user.get(),userBO);
        jedisClient.addToCache(user.get().getUsername(), Base64.getEncoder().encodeToString(user.get().getUsername().getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public void logout(UserBO userBO) throws Exception {
        jedisClient.deleteKey(userBO.getUsername());
    }
}
