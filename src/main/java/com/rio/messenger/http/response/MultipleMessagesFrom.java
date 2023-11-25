package com.rio.messenger.http.response;

import java.util.List;

public class MultipleMessagesFrom {

    private String username;
    private List<String> texts;


    public MultipleMessagesFrom(String username, List<String> texts) {
        this.username = username;
        this.texts = texts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getTexts() {
        return texts;
    }

    public void setTexts(List<String> texts) {
        this.texts = texts;
    }
}
