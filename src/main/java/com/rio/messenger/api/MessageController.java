package com.rio.messenger.api;


import com.rio.messenger.bo.MessageBO;
import com.rio.messenger.bo.UserBO;
import com.rio.messenger.http.request.HistoryRequest;
import com.rio.messenger.http.response.*;
import com.rio.messenger.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @PostMapping(value = "/send/text/user",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends StatusResponse> sendMessage(@RequestBody MessageBO messageBO){
        try {
            messageService.sendMessage(messageBO);
            return ResponseEntity.ok().body(new StatusResponse(ResponseType.SUCCESS));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ErrorResponse("Something went wrong"));
        }
    }

    @PostMapping(value = "/get/unread",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends StatusResponse> sendMessage(@RequestBody UserBO userBO){
        try {
            List<MultipleMessagesFrom> messagesFromUsers = messageService.getUnread(userBO);
            if(messagesFromUsers.isEmpty()) {
                return ResponseEntity.ok().body(new UnreadMessageResponse("No new messages",null));
            }
            return ResponseEntity.ok().body(new UnreadMessageResponse("You have messages(s)",messagesFromUsers));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ErrorResponse("Something went wrong"));
        }
    }

    @PostMapping(value = "/get/history",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends StatusResponse> sendMessage(@RequestBody HistoryRequest request){
        try {
            List<SingleMessageFrom> messagesFromUsers = messageService.getHistory(request);
            if(messagesFromUsers.isEmpty()) {
                return ResponseEntity.ok().body(new MessageHistoryResponse(null));
            }
            return ResponseEntity.ok().body(new MessageHistoryResponse(messagesFromUsers));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(new ErrorResponse("Something went wrong"));
        }
    }



}
