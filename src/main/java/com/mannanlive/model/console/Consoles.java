package com.mannanlive.model.console;

import java.util.List;

public class Consoles {
    public Consoles(List<Console> data) {
        this.data = data;
    }

    private List<Console> data;

    public List<Console> getData() {
        return data;
    }

    public void setData(List<Console> data) {
        this.data = data;
    }
}
