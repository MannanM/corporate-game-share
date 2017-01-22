package com.mannanlive.model.console;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({"name", "developer", "manufacturer"})
public class ConsoleAttributes {
    @ApiModelProperty(readOnly = true, required = true, example = "PlayStation_4")
    private String name;
    @ApiModelProperty(readOnly = true, required = true, example = "Sony Computer Entertainment")
    private String developer;
    @ApiModelProperty(readOnly = true, required = true, example = "Sony")
    private String manufacturer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
