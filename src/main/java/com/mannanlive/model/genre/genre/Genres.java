package com.mannanlive.model.genre;

import java.util.List;
import java.util.stream.Collectors;

public class Genres {
    //todo: add link and count meta
    private List<Genre> data;

    public Genres(List<String> data) {
        this.data = data.stream().map(Genre::new).collect(Collectors.toList());
    }

    public List<Genre> getData() {
        return data;
    }

    public void setData(List<Genre> data) {
        this.data = data;
    }
}
