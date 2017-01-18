package com.mannanlive.model.usermodel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({"type", "id", "attributes"})
public class UserData {
    @ApiModelProperty(required = true, example = "users", readOnly = true)
    private String type = "users";

    @ApiModelProperty(required = true, example = "123", readOnly = true, position = 1)
    private String id;

    @ApiModelProperty(required = true, readOnly = true, position = 2)
    private UserAttributes attributes = new UserAttributes();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(UserAttributes attributes) {
        this.attributes = attributes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
