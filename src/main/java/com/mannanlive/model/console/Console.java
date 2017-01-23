package com.mannanlive.model.console;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({"type", "id", "attributes"})
public class Console {
    @ApiModelProperty(required = true, example = "1", readOnly = true, position = 1)
    private String id;

    @ApiModelProperty(required = true, example = "consoles", readOnly = true)
    private String type = "consoles";

    @ApiModelProperty(required = true, readOnly = true, position = 2)
    private ConsoleAttributes attributes = new ConsoleAttributes();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ConsoleAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ConsoleAttributes attributes) {
        this.attributes = attributes;
    }
}
