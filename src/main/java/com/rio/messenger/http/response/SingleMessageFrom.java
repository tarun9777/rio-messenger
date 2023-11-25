package com.rio.messenger.http.response;

public class SingleMessageFrom {

    String user;
    String text;

    public SingleMessageFrom(String user, String text) {
        this.user = user;
        this.text = text;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
