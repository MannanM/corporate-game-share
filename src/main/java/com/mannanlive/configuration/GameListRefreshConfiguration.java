package com.mannanlive.configuration;

import com.mannanlive.entity.GameEntity;
import com.mannanlive.repository.GameRepository;
import com.mannanlive.translator.WikiElementTranslator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

import static java.lang.String.format;

@Configuration
@EnableAsync
@EnableScheduling
public class GameListRefreshConfiguration {
    private static final String WIKI_PAGE = "https://en.wikipedia.org/wiki/List_of_%s_games";
    private static final String WIKI_TABLE_NAME = "softwarelist";
    private static final String PLAY_STATION_4 = "PlayStation_4";
    private static final String XBOX_ONE = "Xbox_One";
    private static final int NA_DATE_COLUMN = 7;

    @Autowired
    private GameRepository repository;
    private WikiElementTranslator translator = new WikiElementTranslator();

    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    public void refreshGames() {
        //todo: get consoles from console repository and pass in here. console.id, console.name, console.wiki_name
        refreshGamesForPlatform(PLAY_STATION_4);
        refreshGamesForPlatform(XBOX_ONE);
    }

    private void refreshGamesForPlatform(final String console) {
        try {
            Document document = Jsoup.connect(format(WIKI_PAGE, console)).get();
            Elements tableRows = document.select("#" + WIKI_TABLE_NAME + " tr");
            for (Element row : tableRows) {
                processRow(console, row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void upsertGame(GameEntity game) {
        GameEntity entity = repository.findByNameAndConsole(game.getName(), game.getConsole());
        if (entity != null) {
            game.setId(entity.getId());
        }
        repository.save(game);
    }

    private void processRow(String console, Element row) {
        Elements cells = row.select("td");
        if (cells.size() >= NA_DATE_COLUMN) {
            GameEntity game = extractGameData(cells);
            game.setConsole(console);
            upsertGame(game);
        }
    }

    private GameEntity extractGameData(Elements cells) {
        //todo: nicer way to do this
        Element[] tds = cells.toArray(new Element[10]);
        GameEntity game = new GameEntity();
        game.setName(tds[0].text());
        game.setGenres(translator.extractList(tds[1]));
        game.setDeveloper(tds[2].text());
        game.setPublisher(tds[3].text());
        game.setExclusive(isExclusive(tds[4].text()));
        game.setReleaseDate(translator.extractDate(tds[NA_DATE_COLUMN]));
        return game;
    }

    private boolean isExclusive(String text) {
        return "Yes".equals(text) || "Console".equals(text) || "Sony".equals(text) || "Microsoft".equals(text);
    }
}
