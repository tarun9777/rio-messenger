package com.rio.messenger.bo;

import java.io.Serializable;

public class UserBO implements Serializable {

    private String username;
    private String passcode;

    public UserBO(){}

    public UserBO(String username, String passcode) {
        this.username = username;
        this.passcode = passcode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasscode() {
        return passcode;
    }

    public void setPasscode(String passcode) {
        this.passcode = passcode;
    }
}
