package com.rio.messenger.dao;

import com.rio.messenger.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {


    void save(User user);
    Optional<User> findById(String username);
    List<User> findAll();

}
