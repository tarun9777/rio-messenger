package com.rio.messenger.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @Column(name = "msg_id")
    private String id;
    @Column(name = "msg_from")
    private String from;
    @Column(name = "msg_to")
    private String to;
    @Column(name = "msg")
    private String msg;
    @Column(name = "time_sent")
    private long time;
    @Column(name = "is_read")
    private String read;

    public Message(){}

    public Message(String id, String from, String to, String msg, long time,String read) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.msg = msg;
        this.time = time;
        this.read = read;
    }

    public Message(String from, String to, String msg){
        this.from = from;
        this.to = to;
        this.msg = msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public static class MessageBuilder{

        private String id;

        private String from;

        private String to;

        private String msg;

        private long time;

        private String read;

        private MessageBuilder(){}

        public static MessageBuilder getBuilder(){
            return new MessageBuilder();
        }

        public MessageBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public MessageBuilder setFrom(String from) {
            this.from = from;
            return this;
        }

        public MessageBuilder setTo(String to) {
            this.to = to;
            return this;
        }

        public MessageBuilder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public MessageBuilder setTime(long time) {
            this.time = time;
            return this;
        }

        public MessageBuilder setRead(String read) {
            this.read = read;
            return this;
        }

        public Message build(){
            return new Message(id,from,to,msg,time,read);
        }
    }
}
