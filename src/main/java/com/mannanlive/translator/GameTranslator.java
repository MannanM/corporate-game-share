package com.mannanlive.translator;

import com.mannanlive.entity.GameEntity;
import com.mannanlive.model.game.Game;
import org.springframework.stereotype.Service;

@Service
public class GameTranslator {
    public Game translate(GameEntity entity) {
        Game json = new Game();
        json.getData().setId(String.format("%d", entity.getId()));
        json.getData().getAttributes().setName(entity.getName());
        json.getData().getAttributes().setConsole(entity.getConsole().getName());
        json.getData().getAttributes().setDeveloper(entity.getDeveloper());
        json.getData().getAttributes().setExclusive(entity.isExclusive());
        json.getData().getAttributes().setPublisher(entity.getPublisher());
        json.getData().getAttributes().setReleaseDate(entity.getReleaseDate());
        json.getData().getAttributes().setGenres(entity.getGenres());
        json.getData().getAttributes().setImage(entity.getImageLink());
        return json;
    }
}
