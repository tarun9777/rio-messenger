package com.rio.messenger.dao;

import com.rio.messenger.entity.Message;

import java.util.List;

public interface MessageDao {

    void save(Message message);

    List<Message> findByUsers(String from, String to);

    List<Message> getUnread(String from,String to);
}
