package com.example.news;


import com.example.news.db.DatabaseManager;
import com.example.news.model.NewsArticle;
import com.example.news.model.Source;
import com.example.news.parser.NewsParser;

import java.time.LocalDate;
import java.util.List;


public class NewsAggregator {

    private final NewsParser rbcParser = new NewsParser();
    private final DatabaseManager db = new DatabaseManager();


    public void CloseDB() {
        db.close();
    }

    public void SetСountRows(int new_count_rows) {
        db.SetСountRows(new_count_rows);
    }

    public void SetSortByDate() {
        db.SetSortByDate();
        System.out.print("Включена сортировка по дате");
    }
    public void SetSortByPopularity() {
        db.SetSortByPopularity();
    }
    public void SetSortBySource() {
        db.SetSortBySource();
        System.out.print("Включена сортировка по источнику");
    }

    public void fetchAndStore() {
        List<NewsArticle> rbcNews = rbcParser.parse( (LocalDate.now().minusDays(4)).toString(), db.getAllSources());
        db.saveArticles(rbcNews);
    }

     public void ShowNews() {
         db.printRecentNews();
     }

    public void ShowNewsFull() {
        db.printRecentNewsFull();
    }

    public void FindNews_Title_And_Text(String keyword) {
        db.FindNews_Title_And_Text(keyword);
    }

    public void FindNews_Title(String keyword) {
        db.FindNews_Title(keyword);
    }

    public void FindNews_Text(String keyword) {
        db.FindNews_Text(keyword);
    }

    public void FindNews_Date(String keyword) {
        db.FindNews_Date(keyword);
    }

    public void FindNews_Category(String keyword) {
        db.FindNews_Category(keyword);
    }

    public void FindNews_Source(String keyword) {
        db.FindNews_Source(keyword);
    }

    public void exportToCSV() {
        db.exportToCSV();
    }

    public void listSources() {
        db.listSources();
    }

    public void insertSource(String title_field_name, String date_field_name, String summary_field_name, String url_field_name,
                             String video_or_foto_url_field_name, String source, String sourceId_field_name, String json_url, String root_tag) {
        db.insertSource(title_field_name, date_field_name, summary_field_name, url_field_name, video_or_foto_url_field_name, source, sourceId_field_name, json_url, root_tag);
    }

    public void deleteSourceById(int id) {
        db.deleteSourceById(id);
    }

    public void Analitics() {
        db.Analitics();
    }

    public List<Source>  getAllSources() {
        return db.getAllSources();
    }


}

