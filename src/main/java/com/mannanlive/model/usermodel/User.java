package com.mannanlive.model.usermodel;

public class User {
    private UserData data = new UserData();

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }
}
