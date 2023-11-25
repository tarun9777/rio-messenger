package com.rio.messenger.http.request;

public class HistoryRequest {

    String friend;
    String user;

    public HistoryRequest(String friend, String user) {
        this.friend = friend;
        this.user = user;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
