package com.mannanlive.model.console;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

@JsonPropertyOrder({"name", "shortName", "developer", "manufacturer"})
public class ConsoleAttributes {
    @ApiModelProperty(readOnly = true, required = true, example = "PlayStation 4", position = 1)
    private String name;
    @ApiModelProperty(readOnly = true, required = true, example = "PS4", position = 2)
    private String shortName;
    @ApiModelProperty(readOnly = true, required = true, example = "Sony Computer Entertainment", position = 3)
    private String developer;
    @ApiModelProperty(readOnly = true, required = true, example = "Sony", position = 4)
    private String manufacturer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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
