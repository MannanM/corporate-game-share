package com.mannanlive.configuration;

import com.mannanlive.entity.Game;
import com.mannanlive.repository.GameRepository;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    private DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);

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

    private void upsertGame(Game game) {
        Game entity = repository.findByNameAndConsole(game.getName(), game.getConsole());
        if (entity != null) {
            game.setId(entity.getId());
        }
        repository.save(game);
    }

    private void processRow(String console, Element row) {
        Elements cells = row.select("td");
        if (cells.size() >= NA_DATE_COLUMN) {
            Game game = extractGameData(cells);
            game.setConsole(console);
            upsertGame(game);
        }
    }

    private Game extractGameData(Elements cells) {
        //todo: nicer way to do this
        Element[] tds = cells.toArray(new Element[10]);
        Game game = new Game();
        game.setName(tds[0].text());
        game.setGenres(format("%s,", tds[1].text()));
        game.setDeveloper(tds[2].text());
        game.setPublisher(tds[3].text());
        game.setExclusive(isExclusive(tds[4].text()));
        game.setNaReleaseDate(getReleaseDate(tds[NA_DATE_COLUMN]));
        return game;
    }

    //todo: pull this out to a wiki td date translator class?
    private Date getReleaseDate(Element td) {
        try {
            if (td.children().size() == 2) {
                //<span class="sortkey" style="display:none;speak:none">000000002015-04-22-0000</span>
                //<span style="white-space:nowrap">April 22, 2015</span>
                return format.parse(correctPartialDates(td));
            }
        } catch (ParseException e) {
            System.out.println(td.text());
        }
        return null;
    }

    private String correctPartialDates(Element td) {
        String text = td.child(1).text();
        if (text.length() == 4) {
            //2017
            return format("December 31, %s", text);
        }
        if (text.length() == 8) {
            //Jul 2017
            return format("%s 1, %s", text.substring(0, 3), text.substring(4, 7));
        }
        return text;
    }

    private boolean isExclusive(String text) {
        return "Yes".equals(text) || "Console".equals(text) || "Sony".equals(text) || "Microsoft".equals(text);
    }
}
