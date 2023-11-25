package com.rio.messenger.dao;

import com.rio.messenger.entity.User;

public interface UserDao {


    void save(User user);

    User findById(String username);

}
