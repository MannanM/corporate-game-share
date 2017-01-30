package com.mannanlive.model.game;

import com.mannanlive.model.console.Console;
import com.mannanlive.model.genre.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Games {
    private List<Game> data;

    public Games(List<Game> data) {
        this.data = data;
    }

    public List<Game> getData() {
        return data;
    }

    public void setData(List<Game> data) {
        this.data = data;
    }
}
