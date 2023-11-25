package com.rio.messenger.http.response;

public class StatusResponse {
    ResponseType status;

    public StatusResponse(ResponseType status){
        this.status = status;
    }

    public ResponseType getStatus() {
        return status;
    }

    public void setStatus(ResponseType status) {
        this.status = status;
    }
}
