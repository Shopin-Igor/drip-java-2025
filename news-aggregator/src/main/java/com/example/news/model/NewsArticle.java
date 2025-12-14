package com.example.news.model;

import java.time.LocalDateTime;

public class NewsArticle {
    private String title;
    private LocalDateTime date;
    private String summary;
    private String url;
    private String video_or_foto_url;
    private String category;
    private String source;
    private String source_id;

    public NewsArticle(String title, LocalDateTime date, String summary, String url, String video_or_foto_url, String category,String source, String source_id) {
        this.title = title;
        this.date = date;
        this.summary = summary;
        this.url = url;
        this.video_or_foto_url = video_or_foto_url;
        this.category = category;
        this.source = source;
        this.source_id = source_id;
    }

    public String getTitle() { return title; }
    public LocalDateTime getDate() { return date; }
    public String getSummary() { return summary; }
    public String getUrl() { return url; }
    public String getVideo_or_foto_url() { return video_or_foto_url; };
    public String getCategory() { return category; }
    public String getSource() { return source; };
    public String getSource_id() { return source_id; };

    public void setTitle(String newTitle) { title = newTitle;}
    public void setSummary(String newSummary) { summary = newSummary;}
    public void setUrl(String newUrl) {url =  newUrl;}

    public void setVideo_or_foto_url(String url) {video_or_foto_url = url;}

    public void setCategory(String newCategory) { category = newCategory;}

    public void setSource(String newSource) {source = newSource;}

    public void setSource_id(String newSource_id) {source_id =  newSource_id;}
}
