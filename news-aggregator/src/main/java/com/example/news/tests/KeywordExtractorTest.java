package com.example.news.tests;

import com.example.news.util.KeywordExtractor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KeywordExtractorTest {

    @Test
    void testExtractKeywordsBasic() {
        String text = "Россия и мир, новости сегодня, Россия победила в бою, бой за Москву.";
        List<String> keywords = KeywordExtractor.extractKeywords(text, 3);

        assertNotNull(keywords);
        assertTrue(keywords.contains("россия"));
        assertTrue(keywords.contains("бой") || keywords.contains("бою"));
    }

    @Test
    void testExtractKeywordsEmptyText() {
        List<String> keywords = KeywordExtractor.extractKeywords("", 5);
        assertNotNull(keywords);
        assertTrue(keywords.isEmpty());
    }

    @Test
    void testExtractKeywordsAllStopWords() {
        String text = "и в на с по что как а но за из к для это то же не о от до";
        List<String> keywords = KeywordExtractor.extractKeywords(text, 5);
        assertNotNull(keywords);
        assertTrue(keywords.isEmpty());
    }

    @Test
    void testExtractKeywordsTopNLimit() {
        String text = "один два три четыре пять один два три один два";
        List<String> keywords = KeywordExtractor.extractKeywords(text, 2);
        assertEquals(2, keywords.size());
        assertEquals("один", keywords.get(0));
        assertEquals("два", keywords.get(1));
    }
}
