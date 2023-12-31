package com.rio.messenger.service;

import com.rio.messenger.bo.UserBO;

public interface AuthService {

    void addUser(UserBO userBO) throws Exception;

    String authenticateUser(UserBO userBO) throws Exception;

    void logout(UserBO userBO) throws Exception;


}
