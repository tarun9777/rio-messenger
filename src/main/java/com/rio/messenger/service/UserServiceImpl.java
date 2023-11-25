package com.rio.messenger.service;

import com.rio.messenger.dao.UserDao;
import com.rio.messenger.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService{

    UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao dao){
        this.userDao = dao;
    }
    @Override
    @Transactional("MSG_TM")
    public List<String> getUsers()  {
        List<User> users = userDao.findAll();
        return users.stream().map(User::getUsername).collect(Collectors.toList());
    }

    @Override
    public boolean isUserExists(String username) {
        Optional<User> user = userDao.findById(username);
        return user.isPresent();
    }
}
