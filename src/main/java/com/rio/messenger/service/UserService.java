package com.rio.messenger.service;

import java.util.List;

public interface UserService {

    List<String> getUsers();

    boolean isUserExists(String username) ;
}
