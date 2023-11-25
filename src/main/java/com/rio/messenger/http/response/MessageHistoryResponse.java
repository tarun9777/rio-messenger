package com.rio.messenger.http.response;

import java.util.List;

public class MessageHistoryResponse extends StatusResponse {

    List<SingleMessageFrom> texts;

    public MessageHistoryResponse(List<SingleMessageFrom> texts) {
        super(ResponseType.SUCCESS);
        this.texts = texts;
    }

    public List<SingleMessageFrom> getTexts() {
        return texts;
    }

    public void setTexts(List<SingleMessageFrom> texts) {
        this.texts = texts;
    }
}
