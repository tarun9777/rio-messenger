package com.rio.messenger.http.response;

public class ErrorResponse extends StatusResponse {

    private String message;

    public ErrorResponse(String message) {
        super(ResponseType.ERROR);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
