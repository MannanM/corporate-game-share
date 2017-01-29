package com.mannanlive.service;

import com.mannanlive.entity.GameEntity;
import com.mannanlive.repository.GameRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static java.lang.String.format;

@Service
public class GameImageService {
    private static final String WIKI_PAGE = "https://en.wikipedia.org%s";
    private static final String INFOBOX_IMG = ".infobox img";

    @Autowired
    private GameRepository repository;

    @Async
    public void refreshGameImage(GameEntity gameEntity) {
        if (gameEntity.getImageLink() == null && gameEntity.getWikiLink() != null) {
            if (tryAndRetrieveImage(gameEntity)) {
                repository.save(gameEntity);
            }
        }
    }

    private boolean tryAndRetrieveImage(GameEntity gameEntity) {
        try {
            Document document = Jsoup.connect(format(WIKI_PAGE, gameEntity.getWikiLink())).get();
            Elements images = document.select(INFOBOX_IMG);
            if (!images.isEmpty()) {
                gameEntity.setImageLink(images.get(0).attr("src"));
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameEntity.setImageLink("N/A");
        return false;
    }
}
