package com.rio.messenger.service;

import com.rio.messenger.bo.MessageBO;
import com.rio.messenger.bo.UserBO;
import com.rio.messenger.http.request.HistoryRequest;
import com.rio.messenger.http.response.MultipleMessagesFrom;
import com.rio.messenger.http.response.SingleMessageFrom;

import java.util.List;

public interface MessageService {

    void sendMessage(MessageBO messageBO);

    List<MultipleMessagesFrom> getUnread(UserBO userBO);

    List<SingleMessageFrom> getHistory(HistoryRequest request);


}
