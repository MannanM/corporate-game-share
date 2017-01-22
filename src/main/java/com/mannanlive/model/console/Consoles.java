package com.mannanlive.model.console;

import java.util.List;

public class Consoles {
    public Consoles(List<Console> consoles) {
        this.consoles = consoles;
    }

    private List<Console> consoles;

    public List<Console> getConsoles() {
        return consoles;
    }

    public void setConsoles(List<Console> consoles) {
        this.consoles = consoles;
    }
}
