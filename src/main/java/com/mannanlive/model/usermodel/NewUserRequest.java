package com.mannanlive.model.usermodel;

import io.swagger.annotations.ApiModelProperty;

public class NewUserRequest {

    @ApiModelProperty(required = true, example = "John Smith")
    private String name;

    @ApiModelProperty(required = true, example = "john@ex.com.au", position = 1)
    private String email;

    @ApiModelProperty(required = true, example = "qwerty123", position = 2)
    private String password;

    @ApiModelProperty(required = true, example = "1", position = 3)
    private Long console;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getConsole() {
        return console;
    }

    public void setConsole(Long console) {
        this.console = console;
    }
}
