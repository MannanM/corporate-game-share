package com.mannanlive.model.usermodel;

import io.swagger.annotations.ApiModelProperty;

public class NewUserRequest {

    @ApiModelProperty(required = true, example = "john@example.com")
    private String email;

    @ApiModelProperty(required = true, example = "qwerty123")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
