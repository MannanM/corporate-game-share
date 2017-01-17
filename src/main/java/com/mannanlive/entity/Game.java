package com.mannanlive.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "console"})} )
public class Game {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String genres;
    private String developer;
    private String publisher;
    private boolean exclusive;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date naReleaseDate;
    private String console;
    //todo: add wikipedia link, you can get this from the first column

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getGenres() {
        return genres;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public void setNaReleaseDate(Date naReleaseDate) {
        this.naReleaseDate = naReleaseDate;
    }

    public Date getNaReleaseDate() {
        return naReleaseDate;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public String getConsole() {
        return console;
    }
}
