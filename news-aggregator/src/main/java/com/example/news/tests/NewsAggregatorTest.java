package com.example.news.tests;

import com.example.news.NewsAggregator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewsAggregatorTest {

    @Test
    void testAggregatorMethods() {
        NewsAggregator aggregator = new NewsAggregator();
        aggregator.SetСountRows(5);
        aggregator.CloseDB();
    }
}
