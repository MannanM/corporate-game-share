package com.mannanlive.model.game;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({"type", "id", "attributes"})
public class GameData {
    @ApiModelProperty(required = true, example = "games", readOnly = true)
    private String type = "games";

    @ApiModelProperty(required = true, example = "123", readOnly = true, position = 1)
    private String id;

    @ApiModelProperty(required = true, readOnly = true, position = 2)
    private GameAttributes attributes = new GameAttributes();

    public GameAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(GameAttributes attributes) {
        this.attributes = attributes;
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
