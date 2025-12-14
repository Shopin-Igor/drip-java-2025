package com.example.news.tests;

import com.example.news.model.NewsArticle;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class NewsArticleTest {

    @Test
    void testNewsArticleGettersAndSetters() {
        LocalDateTime now = LocalDateTime.now();
        NewsArticle article = new NewsArticle(
                "Title", now, "Summary", "http://example.com",
                "http://example.com/image.jpg", "Politics", "RBC", "123"
        );

        assertEquals("Title", article.getTitle());
        assertEquals(now, article.getDate());
        assertEquals("Summary", article.getSummary());
        assertEquals("http://example.com", article.getUrl());
        assertEquals("http://example.com/image.jpg", article.getVideo_or_foto_url());
        assertEquals("Politics", article.getCategory());
        assertEquals("RBC", article.getSource());
        assertEquals("123", article.getSource_id());

        article.setTitle("New Title");
        article.setSummary("New Summary");
        article.setUrl("http://newurl.com");
        article.setVideo_or_foto_url("http://newimage.jpg");
        article.setCategory("Business");
        article.setSource("Mail");
        article.setSource_id("456");

        assertEquals("New Title", article.getTitle());
        assertEquals("New Summary", article.getSummary());
        assertEquals("http://newurl.com", article.getUrl());
        assertEquals("http://newimage.jpg", article.getVideo_or_foto_url());
        assertEquals("Business", article.getCategory());
        assertEquals("Mail", article.getSource());
        assertEquals("456", article.getSource_id());
    }
}
