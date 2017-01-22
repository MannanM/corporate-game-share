package com.mannanlive.entity;

import javax.persistence.*;

@Entity(name = "console")
@Table(name = "CONSOLE", uniqueConstraints = {@UniqueConstraint(columnNames = {"console"})} )
public class ConsoleEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String console;
    private String developer;
    private String manufacturer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
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
