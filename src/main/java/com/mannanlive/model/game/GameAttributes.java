package com.mannanlive.model.game;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.List;

@JsonPropertyOrder({"name", "console", "exclusive", "developer", "publisher", "releaseDate", "genres"})
public class GameAttributes {
    @ApiModelProperty(readOnly = true, required = true, example = "Destiny")
    private String name;

    @ApiModelProperty(readOnly = true, required = true, example = "PlayStation 4", position = 1)
    private String console;

    @ApiModelProperty(readOnly = true, required = true, example = "false", position = 2)
    private boolean exclusive;

    @ApiModelProperty(readOnly = true, required = true, example = "Bungie", position = 3)
    private String developer;

    @ApiModelProperty(readOnly = true, example = "Activision", position = 4)
    private String publisher;

    @JsonFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(readOnly = true, example = "2014-09-13", position = 5)
    private LocalDate releaseDate;

    @ApiModelProperty(readOnly = true, position = 6)
    private List<String> genres;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
