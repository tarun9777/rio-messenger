package com.rio.messenger.service;


import com.rio.messenger.bo.HashedPasscode;
import com.rio.messenger.bo.UserBO;
import com.rio.messenger.dao.UserDao;
import com.rio.messenger.entity.User;
import com.rio.messenger.util.PasscodeHashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@Service
public class AuthServiceImpl implements AuthService {

    private final PasscodeHashUtil hashUtil;
    private final UserDao userDao;

    @Autowired
    public AuthServiceImpl(PasscodeHashUtil hashUtil, UserDao userDao){
        this.hashUtil = hashUtil;
        this.userDao = userDao;
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

}
