package com.rio.messenger.http.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class UnreadMessageResponse extends StatusResponse {

    String message;
    List<MultipleMessagesFrom> data;

    public UnreadMessageResponse(String message, List<MultipleMessagesFrom> data) {
        super(ResponseType.SUCCESS);
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MultipleMessagesFrom> getData() {
        return data;
    }

    public void setData(List<MultipleMessagesFrom> data) {
        this.data = data;
    }
}
