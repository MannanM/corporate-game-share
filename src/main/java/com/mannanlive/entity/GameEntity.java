package com.mannanlive.entity;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "game")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "console_id"})} )
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ElementCollection
    @CollectionTable
    private List<String> genres;
    private String developer;
    private String publisher;
    private boolean exclusive;
    private LocalDate releaseDate;

    @ManyToOne
    private ConsoleEntity console;

    public GameEntity() {
        //for hibernate
    }

    public GameEntity(String name, ConsoleEntity console) {
        this.name = name;
        this.console = console;
    }

    public GameEntity(long id) {
        this.id = id;
    }

    //todo: add wikipedia link, you can get this from the first column

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setConsole(ConsoleEntity console) {
        this.console = console;
    }

    public ConsoleEntity getConsole() {
        return console;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
}
