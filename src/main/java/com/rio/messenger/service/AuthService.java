package com.rio.messenger.service;

import com.rio.messenger.bo.UserBO;

public interface AuthService {

    void addUser(UserBO userBO) throws Exception;

    void authenticateUser(UserBO userBO) throws Exception;


}
