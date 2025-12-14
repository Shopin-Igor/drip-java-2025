package com.example.news.parser;

import com.example.news.model.NewsArticle;
import com.example.news.model.Source;
import com.example.news.util.CategoryClassifier;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Парсер новостей с сайта РБК (https://www.rbc.ru) через AJAX API.
 * Использует формат: https://www.rbc.ru/search/ajax/?dateFrom=YYYY-MM-DD
 */
public class NewsParser {

    public List<NewsArticle> parse(String dateFrom, List<Source> sources) {
        List<NewsArticle> articles = new ArrayList<>();

        for (Source source : sources) {
            try {

                String url = source.getJson_url() + dateFrom;
                String source_name = source.getSource();


                // Получаем JSON-ответ от сервера
                String json = Jsoup.connect(url)
                        .ignoreContentType(true)
                        .method(Connection.Method.GET)
                        .execute()
                        .body();

                // Парсим JSON
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(json);

                for (JsonNode item : root.path(source.getRoot_tag())) {
                    String title = item.path(source.getTitle_field_name()).asText(); //
                    LocalDateTime date = Instant.ofEpochSecond(item.path(source.getDate_field_name()).asLong())
                            .atZone(ZoneId.of("Europe/Moscow"))
                            .toLocalDateTime();
                    String summary = item.path(source.getSummary_field_name()).asText();
                    if ("null".equals(summary.toLowerCase())) {
                        summary = "";
                    }
                    String link = item.path(source.getUrl_field_name()).asText();
                    String video_or_foto_url = item.path(source.getVideoOrFotoUrl_field_name()).asText();
                    String category = CategoryClassifier.classify(title + summary);
                    String source_id = item.path(source.getSourceId_field_name()).asText();

                    articles.add(new NewsArticle(title, date, summary, link, video_or_foto_url, category, source_name, source_id));
                }

            } catch (Exception e) {
                System.out.println("Ошибка при получении данных : "+ source.getSource()+ ". " + e.getMessage());
            }
        }
        return articles;
    }
}
