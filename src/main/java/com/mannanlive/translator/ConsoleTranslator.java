package com.mannanlive.translator;

import com.mannanlive.entity.ConsoleEntity;
import com.mannanlive.model.console.Console;
import org.springframework.stereotype.Component;

@Component
public class ConsoleTranslator {
    public Console translate(ConsoleEntity entity) {
        Console console = new Console();
        console.getData().setId(entity.getId());
        console.getData().getAttributes().setName(entity.getConsole());
        console.getData().getAttributes().setDeveloper(entity.getDeveloper());
        console.getData().getAttributes().setManufacturer(entity.getManufacturer());
        return console;
    }
}