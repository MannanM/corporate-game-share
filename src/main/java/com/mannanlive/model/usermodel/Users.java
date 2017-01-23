package com.mannanlive.model.usermodel;

import java.util.List;

public class Users {
    private List<UserData> data;

    public Users(List<UserData> data) {
        this.data = data;
    }

    public List<UserData> getData() {
        return data;
    }

    public void setData(List<UserData> data) {
        this.data = data;
    }
}
