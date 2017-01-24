package com.mannanlive.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResourceSummary {
    @ApiModelProperty(required = true, example = "games", readOnly = true)
    private String type = "resources";

    @ApiModelProperty(required = true, example = "123", readOnly = true, position = 1)
    private String id;

    @ApiModelProperty(required = true, readOnly = true, position = 2)
    private ResourceSummaryAttributes attributes = new ResourceSummaryAttributes();

    public ResourceSummary() {
        //for jackson
    }

    public ResourceSummary(String type, String id, String name) {
        this.type = type;
        this.id = id;
        attributes.setName(name);
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

    public ResourceSummaryAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ResourceSummaryAttributes attributes) {
        this.attributes = attributes;
    }
}
