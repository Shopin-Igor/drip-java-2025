package com.example.news.tests;

import com.example.news.util.CategoryClassifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryClassifierTest {

    @Test
    void testClassifyPolitics() {
        String text = "Путин и выборы в парламенте — главные новости.";
        assertEquals("Политика", CategoryClassifier.classify(text));
    }

    @Test
    void testClassifyEconomy() {
        String text = "Курс доллара, рубля, евро — финансовые рынки колеблются.";
        assertEquals("Экономика", CategoryClassifier.classify(text));
    }

    @Test
    void testClassifyTechnology() {
        String text = "Нейросети и искусственный интеллект развиваются.";
        assertEquals("Технологии", CategoryClassifier.classify(text));
    }

    @Test
    void testClassifySport() {
        String text = "Футболисты играют в Лиге чемпионов. Сборная России победила.";
        assertEquals("Спорт", CategoryClassifier.classify(text));
    }

    @Test
    void testClassifyOther() {
        String text = "Сегодня хороший день для прогулки в парке.";
        assertEquals("Другое", CategoryClassifier.classify(text));
    }
}
