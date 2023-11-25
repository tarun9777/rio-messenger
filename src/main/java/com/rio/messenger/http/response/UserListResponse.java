package com.rio.messenger.http.response;

import java.util.List;

public class UserListResponse extends StatusResponse {

    List<String> data;

    public UserListResponse(List<String> users) {
        super(ResponseType.SUCCESS);
        this.data = users;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
