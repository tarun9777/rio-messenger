package com.rio.messenger.exception;

public class UserException extends MessengerException {

    public UserException( String errorMessage) {
        super(null, errorMessage);
    }
}
