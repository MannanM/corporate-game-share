package com.mannanlive.model.library;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({"type", "id", "attributes"})
public class LibraryGameData {
    @ApiModelProperty(required = true, example = "library-games", readOnly = true)
    private String type = "library-games";

    @ApiModelProperty(required = true, example = "123", readOnly = true, position = 1)
    private String id;

    @ApiModelProperty(required = true, readOnly = true, position = 2)
    private LibraryGameAttributes attributes = new LibraryGameAttributes();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LibraryGameAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(LibraryGameAttributes attributes) {
        this.attributes = attributes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
