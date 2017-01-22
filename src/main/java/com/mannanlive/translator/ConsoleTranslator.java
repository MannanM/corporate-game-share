package com.mannanlive.translator;

import com.mannanlive.entity.ConsoleEntity;
import com.mannanlive.model.console.Console;
import org.springframework.stereotype.Component;

@Component
public class ConsoleTranslator {
    public Console translate(ConsoleEntity entity) {
        Console console = new Console();
        console.setId(entity.getId().toString());
        console.getAttributes().setName(entity.getName());
        console.getAttributes().setShortName(entity.getShortName());
        console.getAttributes().setDeveloper(entity.getDeveloper());
        console.getAttributes().setManufacturer(entity.getManufacturer());
        return console;
    }
}