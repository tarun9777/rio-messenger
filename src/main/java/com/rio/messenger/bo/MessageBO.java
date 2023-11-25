package com.rio.messenger.bo;

import java.io.Serializable;

public class MessageBO implements Serializable {

    private String from;
    private String to;
    private String text;

    public MessageBO(){}

    public MessageBO(String from, String to, String msg) {
        this.from = from;
        this.to = to;
        this.text = msg;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
