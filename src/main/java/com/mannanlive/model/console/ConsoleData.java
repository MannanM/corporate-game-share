package com.mannanlive.model.console;

import io.swagger.annotations.ApiModelProperty;

public class ConsoleData {
    @ApiModelProperty(required = true, example = "Playstation_4", readOnly = true, position = 1)
    private Long id;

    @ApiModelProperty(required = true, readOnly = true, position = 2)
    private ConsoleAttributes attributes = new ConsoleAttributes();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConsoleAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ConsoleAttributes attributes) {
        this.attributes = attributes;
    }
}
