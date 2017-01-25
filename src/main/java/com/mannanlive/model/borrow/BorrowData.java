package com.mannanlive.model.borrow;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({"type", "id", "attributes"})
public class BorrowData {
    @ApiModelProperty(required = true, example = "borrow", readOnly = true)
    private String type = "borrow";

    @ApiModelProperty(required = true, example = "123", readOnly = true, position = 1)
    private String id;

    @ApiModelProperty(required = true, readOnly = true, position = 2)
    private BorrowAttributes attributes = new BorrowAttributes();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BorrowAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(BorrowAttributes attributes) {
        this.attributes = attributes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
