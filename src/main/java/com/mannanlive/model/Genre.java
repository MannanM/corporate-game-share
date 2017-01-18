package com.mannanlive.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({"type", "id"})
public class Genre {
    @ApiModelProperty(required = true, example = "genres", readOnly = true)
    private String type = "genres";

    @ApiModelProperty(required = true, example = "action", readOnly = true, position = 1)
    private String id;

    public Genre(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
