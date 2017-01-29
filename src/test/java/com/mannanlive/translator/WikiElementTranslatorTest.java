package com.mannanlive.translator;

import org.jsoup.nodes.Element;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static junit.framework.TestCase.assertNull;
import static org.assertj.core.api.Assertions.assertThat;

public class WikiElementTranslatorTest {
    private WikiElementTranslator translator = new WikiElementTranslator();
    private Element td;

    @Before
    public void setUp() {
        td = new Element("td");
    }

    @Test
    public void extractDateIgnoresInvalidDates() throws Exception {
        td.text("TBA");
        assertNull(translator.extractDate(td));
    }

    @Test
    public void extractDateWithFullyQualifiedDate() throws Exception {
        td.appendChild(createSpanWithText("000000002015-04-22-0000"));
        td.appendChild(createSpanWithText("April 22, 2015"));
        assertThat(translator.extractDate(td)).isEqualByComparingTo(LocalDate.of(2015, Month.APRIL, 22));
    }

    @Test
    public void extractDateWithYearOnly() throws Exception {
        td.appendChild(createSpanWithText("000000002015-04-22-0000"));
        td.appendChild(createSpanWithText("2015"));
        assertThat(translator.extractDate(td)).isEqualByComparingTo(LocalDate.of(2015, Month.DECEMBER, 31));
    }

    @Test
    public void extractDateWithMonthAndYearOnly() throws Exception {
        td.appendChild(createSpanWithText("000000002015-04-22-0000"));
        td.appendChild(createSpanWithText("Jul 2015"));
        assertThat(translator.extractDate(td)).isEqualByComparingTo(LocalDate.of(2015, Month.JULY, 1));
    }

    //Todo: This scenario could be fixed
    @Test
    public void extractDateWithFullMonthAndYearOnlyDoesNotWork() throws Exception {
        td.appendChild(createSpanWithText("000000002015-04-22-0000"));
        td.appendChild(createSpanWithText("January 2015"));
        assertNull(translator.extractDate(td));
    }

    @Test
    public void testGetAttributesWorksAsExpected() throws Exception {
        Element italics = new Element("i");
        Element href = new Element("a");
        href.attr("href", "/wiki/1001_Spikes");
        italics.appendChild(href);

        td.appendChild(italics);
        assertThat(translator.extractLink(td)).isEqualTo("/wiki/1001_Spikes");
    }

    @Test
    public void testGetAttributesReturnsNullForGamesWithNoLink() throws Exception {
        td.appendChild(new Element("i"));
        assertNull(translator.extractLink(td));
    }

    private Element createSpanWithText(String text) {
        Element span = new Element("span");
        span.text(text);
        return span;
    }

}