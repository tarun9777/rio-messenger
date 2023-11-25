package com.rio.messenger.http.response;

public class MsgResponse {
    ResponseType status;

    public MsgResponse(ResponseType status){
        this.status = status;
    }

    public ResponseType getStatus() {
        return status;
    }

    public void setStatus(ResponseType status) {
        this.status = status;
    }
}
