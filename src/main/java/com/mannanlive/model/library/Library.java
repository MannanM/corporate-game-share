package com.mannanlive.model.library;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<LibraryGameData> data = new ArrayList<>();

    public Library(List<LibraryGameData> data) {
        this.data = data;
    }

    public List<LibraryGameData> getData() {
        return data;
    }

    public void setData(List<LibraryGameData> data) {
        this.data = data;
    }
}
