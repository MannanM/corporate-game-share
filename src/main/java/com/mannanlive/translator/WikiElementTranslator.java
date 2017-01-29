package com.mannanlive.translator;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.String.format;

public class WikiElementTranslator {
    private DateTimeFormatter monthDayYear = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
    private DateTimeFormatter shortMonthDayYear = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
    private static final String EMPTY_STRING = "";
    private static final String SEPARATOR = ",";

    public LocalDate extractDate(Element td) {
        if (td.children().size() == 2) {
            //<span class="sortkey" style="display:none;speak:none">000000002015-04-22-0000</span>
            //<span style="white-space:nowrap">April 22, 2015</span>
            String text = correctPartialDates(td);
            try {
                return LocalDate.parse(text, monthDayYear);
            } catch (DateTimeParseException ex) {
                return tryWithShortMonthPattern(text);
            }
        }
        return null;
    }

    public List<String> extractList(Element td) {
        String[] rawValues = td.text().toLowerCase().split(SEPARATOR);
        List<String> result = new ArrayList<>(rawValues.length);
        for (String rawValue : rawValues) {
            String trimmedResult = deduplicate(rawValue);
            if (!trimmedResult.isEmpty()) {
                result.add(trimmedResult);
            }
        }
        return result;
    }

    public String extractLink(Element td) {
        Elements href = td.select("a");
        if (!href.isEmpty()) {
            //<i><a href="/wiki/1001_Spikes" title="1001 Spikes">1001 Spikes</a></i>
            return href.attr("href");
        }
        return null;
    }

    //removes about 35 (15%) of the duplicate values
    //could probably remove more if anyone wants to look into it
    private String deduplicate(String genre) {
        String trimmedResult = genre.trim();
        trimmedResult = trimmedResult.replace("&", "and");
        trimmedResult = trimmedResult.replace("-", " ");
        trimmedResult = trimmedResult.replace("'", EMPTY_STRING);
        trimmedResult = trimmedResult.replace(" game", EMPTY_STRING);
        trimmedResult = trimmedResult.replace("platforming", "platform");
        trimmedResult = trimmedResult.replace("platformer", "platform");
        return trimmedResult;
    }

    private LocalDate tryWithShortMonthPattern(String text) {
        try {
            return LocalDate.parse(text, shortMonthDayYear);
        } catch (DateTimeParseException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private String correctPartialDates(Element td) {
        String text = td.child(1).text();
        if (text.length() == 4) {
            //2017
            return format("December 31, %s", text);
        }
        if (text.length() == 8) {
            //Jul 2017
            return format("%s 1, %s", text.substring(0, 3), text.substring(4, 8));
        }
        return text;
    }
}
