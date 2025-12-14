package com.example.news.model;


public class Source {
    private int id;
    private String title_field_name;
    private String date_field_name;
    private String summary_field_name;
    private String url_field_name;
    private String videoOrFotoUrl_field_name;
    private String source;
    private String sourceId_field_name;
    private String json_url;
    private String root_tag;

    public Source(int id, String title_field_name, String date_field_name, String summary_field_name, String url_field_name,
                  String videoOrFotoUrl_field_name, String source, String sourceId_field_name, String json_url, String root_tag) {
        this.id = id;
        this.title_field_name = title_field_name;
        this.date_field_name = date_field_name;
        this.summary_field_name = summary_field_name;
        this.url_field_name = url_field_name;
        this.videoOrFotoUrl_field_name = videoOrFotoUrl_field_name;
        this.source = source;
        this.sourceId_field_name = sourceId_field_name;
        this.json_url = json_url;
        this.root_tag = root_tag;

    }

    public int getId() { return id; }
    public String getTitle_field_name() { return title_field_name; }
    public String getDate_field_name() { return date_field_name; }
    public String getSummary_field_name() { return summary_field_name; }
    public String getUrl_field_name() { return url_field_name; }
    public String getVideoOrFotoUrl_field_name() { return videoOrFotoUrl_field_name; }
    public String getSource() { return source; }
    public String getSourceId_field_name() { return sourceId_field_name; }
    public String getJson_url() { return json_url; }
    public String getRoot_tag() { return root_tag; }


    @Override
    public String toString() {
        return String.format("""
                ID: %d
                Поле для Title: %s
                Поле для Date: %s
                Поле для Summary: %s
                Поле для URL: %s
                Поле для ссылки на видео/аудио: %s
                Имя Источника: %s
                Поле для Source ID: %s
                Название корня для JSON: %s
                URL сайта для JSON: %s
                """,
                id, title_field_name, date_field_name, summary_field_name, url_field_name, videoOrFotoUrl_field_name, source, sourceId_field_name, root_tag, json_url);
    }

    public void setTitle_field_name(String s) {title_field_name = s;}

    public void setDate_field_name(String s) {date_field_name = s;}

    public void setSummary_field_name(String s) {summary_field_name = s;}

    public void setUrl_field_name(String s) {url_field_name = s;}

    public void setVideoOrFotoUrl_field_name(String s) {videoOrFotoUrl_field_name = s;}

    public void setSource(String s) {source = s;}

    public void setSourceId_field_name(String s) {sourceId_field_name = s;}

    public void setJson_url(String s) {json_url = s;}

    public void setRoot_tag(String s) {root_tag = s;}
}