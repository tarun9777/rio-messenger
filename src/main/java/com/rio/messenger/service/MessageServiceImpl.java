package com.rio.messenger.service;

import com.rio.messenger.bo.MessageBO;
import com.rio.messenger.bo.UserBO;
import com.rio.messenger.dao.MessageDao;
import com.rio.messenger.entity.Message;
import com.rio.messenger.exception.UserException;
import com.rio.messenger.http.request.HistoryRequest;
import com.rio.messenger.http.response.MultipleMessagesFrom;
import com.rio.messenger.http.response.SingleMessageFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class MessageServiceImpl implements MessageService {

    private MessageDao messageDao;

    private UserService userService;

    @Autowired
    public MessageServiceImpl (MessageDao messageDao,UserService userService){
        this.messageDao = messageDao;
        this.userService = userService;
    }

    @Override
    @Transactional("MSG_TM")
    public void sendMessage(MessageBO messageBO) {
        if(!userService.isUserExists(messageBO.getTo())){
            throw new UserException("receiver does not exists");
        }
        Message message = Message.MessageBuilder.getBuilder()
                .setId(UUID.randomUUID().toString())
                .setFrom(messageBO.getFrom())
                .setMsg(messageBO.getText())
                .setTo(messageBO.getTo())
                .setTime(System.currentTimeMillis())
                .setRead('N')
                .build();
        messageDao.save(message);
    }

    @Override
    @Transactional("MSG_TM")
    public List<MultipleMessagesFrom> getUnread(UserBO userBO) {
        List<Message> messages = messageDao.getUnread(userBO.getUsername());
        if (messages.isEmpty()){
            return new ArrayList<>();
        }
        Map<String,List<Message>> map = messages.stream().collect(Collectors.groupingBy(Message::getFrom));
        List<MultipleMessagesFrom> messagesFromUsers = new ArrayList<>();
        for (Map.Entry<String,List<Message>> e : map.entrySet()){
            List<String> texts = e.getValue().stream().map(Message::getMsg).collect(Collectors.toList());
            messagesFromUsers.add(new MultipleMessagesFrom(e.getKey(),texts));
        }
        return messagesFromUsers;
    }

    @Override
    @Transactional("MSG_TM")
    public List<SingleMessageFrom> getHistory(HistoryRequest request) {
        List<Message> messages = messageDao.findByUsers(request.getUser(),request.getFriend());
        return messages.stream().map(m -> new SingleMessageFrom(m.getFrom(),m.getMsg())).collect(Collectors.toList());
    }
}
