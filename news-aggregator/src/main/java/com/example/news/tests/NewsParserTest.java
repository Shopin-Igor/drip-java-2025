package com.example.news.tests;

import com.example.news.model.NewsArticle;
import com.example.news.parser.NewsParser;
import com.example.news.NewsAggregator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewsParserTest {

    @Test
    void testParseReturnsList() {
        NewsParser parser = new NewsParser();
        NewsAggregator newsAggregator1 = new NewsAggregator();
        List<NewsArticle> articles = parser.parse("2025-06-08", newsAggregator1.getAllSources());

        assertNotNull(articles);
        assertTrue(articles instanceof List);
    }
}
