package com.example.news.db;

import com.example.news.model.Source;
import com.example.news.model.NewsArticle;
import com.example.news.util.KeywordExtractor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.PrintWriter;


public class DatabaseManager {
    private int count_rows = 10;
    private String field_for_sort = "date DESC";
    private Connection conn;

    public DatabaseManager() {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("src/main/resources/config.properties"));
            conn = DriverManager.getConnection(props.getProperty("db.url"), props);
            createTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createTable() throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS news (" +
                " id SERIAL PRIMARY KEY," +
                " title TEXT," +
                " date TIMESTAMP," +
                " summary TEXT," +
                " url TEXT," +
                " keywords TEXT," +
                " video_or_foto_url TEXT," +
                " category TEXT," +
                " source TEXT," +
                " source_id TEXT," +
                " CONSTRAINT unique_source_pair UNIQUE (source, source_id))");
        stmt.execute("CREATE TABLE IF NOT EXISTS sources (" +
                " id SERIAL PRIMARY KEY," +
                " title_field_name TEXT," +
                " date_field_name TEXT," +
                " summary_field_name TEXT," +
                " url_field_name TEXT," +
                " video_or_foto_url_field_name TEXT," +
                " source TEXT," +
                " source_id_field_name TEXT," +
                " json_url TEXT, " +
                " root_tag TEXT," +
                " CONSTRAINT unique_source_pair2 UNIQUE (source, source_id_field_name))");
    }


    public void insertSource(String title_field_name, String date_field_name, String summary_field_name, String url_field_name,
                                    String video_or_foto_url_field_name, String source, String sourceId_field_name, String json_url, String root_tag) {

        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO sources (title_field_name, date_field_name, summary_field_name, url_field_name, video_or_foto_url_field_name, source, source_id_field_name, json_url, root_tag)\n" +
                "                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)" +
                "ON CONFLICT (source, source_id_field_name) DO NOTHING")) {
            stmt.setString(1, title_field_name);
            stmt.setString(2, date_field_name);
            stmt.setString(3, summary_field_name);
            stmt.setString(4, url_field_name);
            stmt.setString(5, video_or_foto_url_field_name);
            stmt.setString(6, source);
            stmt.setString(7, sourceId_field_name);
            stmt.setString(8, json_url);
            stmt.setString(9, root_tag);
            stmt.executeUpdate();
            System.out.println("Источник добавлен.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSourceById(int id) {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM sources WHERE id = ?")) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Источник удалён.");
            } else {
                System.out.println("Источник не найден.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void Analitics() {
        try {
            System.out.println("Общее количество запросов по категориям:");
            System.out.println("Категория\tКоличество");
            ResultSet rs = conn.createStatement().executeQuery("select category, count(*) as cnt from news group by (category)order by 2 desc");
            while (rs.next()) {
                System.out.println(String.format("%-8s", rs.getString("category")) + "\t" + rs.getString("cnt"));
            }
            System.out.println();
            System.out.println("Динамика запросов");
            System.out.println("Категория\tДата\t\tКоличество");
            rs = conn.createStatement().executeQuery("select category, date::date as dt, count(*) as cnt from news group by (category, date::date) order by 1,2 desc");
            while (rs.next()) {
                System.out.println(String.format("%-8s", rs.getString("category")) + "\t" + rs.getString("dt")+ "\t" + rs.getString("cnt") );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listSources() {
        List<Source> sources = getAllSources();
        for (Source source : sources) {
            System.out.print(source.toString());
        }
    }

    public List<Source> getAllSources() {
        List<Source> sources = new ArrayList<>();
        try (ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM sources ORDER BY id")) {
            while (rs.next()) {
                Source source = new Source(
                        rs.getInt("id"),
                        rs.getString("title_field_name"),
                        rs.getString("date_field_name"),
                        rs.getString("summary_field_name"),
                        rs.getString("url_field_name"),
                        rs.getString("video_or_foto_url_field_name"),
                        rs.getString("source"),
                        rs.getString("source_id_field_name"),
                        rs.getString("json_url"),
                        rs.getString("root_tag")
                );
                sources.add(source);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sources;
    }


public void SetСountRows(int new_count_rows) {
        count_rows = new_count_rows;
    }

    public void SetSortByDate() {
        field_for_sort = "date DESC";
    }
    public void SetSortByPopularity() {
        field_for_sort = "date DESC";
    }
    public void SetSortBySource() {
        field_for_sort = "source";
    }

    public void saveArticles(List<NewsArticle> articles) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO news(title, date, summary, url, keywords, video_or_foto_url, category, source, source_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)" +
                        "ON CONFLICT (source, source_id) DO NOTHING");
            for (NewsArticle a : articles) {

                ps.setString(1, a.getTitle());
                ps.setTimestamp(2, Timestamp.valueOf(a.getDate()));
                ps.setString(3, a.getSummary());
                ps.setString(4, a.getUrl());    // TO DO категории
                String keywords = String.join(", ", KeywordExtractor.extractKeywords(a.getSummary() + " " + a.getTitle(), 5));
                ps.setString(5, keywords);
                ps.setString(6, a.getVideo_or_foto_url());
                ps.setString(7, a.getCategory());  // to do category
                ps.setString(8, a.getSource());
                ps.setString(9, a.getSource_id());


                ps.addBatch();
            }

            int[] results = ps.executeBatch();
            int inserted = 0;
            for (int result : results) {
                if (result == 1) {
                    inserted++;
                }
            }

            System.out.println("Сохранено " + inserted + " свежих новостей.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printRecentNews() {
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT title, url, keywords, video_or_foto_url, summary, category, source, source_id FROM news ORDER BY " + field_for_sort + ", date DESC LIMIT " + count_rows);
            while (rs.next()) {
                System.out.println(rs.getString("title") + " — " + rs.getString("url"));
                System.out.println("Краткое содержание:" +  rs.getString("summary") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printRecentNewsFull() {
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT title, url, keywords, video_or_foto_url, summary, category, source, date FROM news ORDER BY " + field_for_sort + ", date DESC LIMIT " + count_rows);
            while (rs.next()) {

                System.out.println(rs.getString("title") + " — " + rs.getString("url"));
                System.out.println("Краткое содержание: " +  rs.getString("summary"));
                System.out.println("Дата: " +  rs.getString("date") + " Ключевые слова: "+ rs.getString("keywords"));
                System.out.println("Кагория: " +  rs.getString("category") + " Источник: " +  rs.getString("source") + " Фото/видео: " +  rs.getString("video_or_foto_url") + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void FindNews_Title_And_Text(String keyword) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT title, url FROM news WHERE LOWER(title||summary) LIKE ? ORDER BY " + field_for_sort + ", date DESC LIMIT " + count_rows );
            ps.setString(1, "%" + keyword.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("title") + " — " + rs.getString("url"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void FindNews_Title(String keyword) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                "SELECT title, url FROM news WHERE LOWER(title) LIKE ? ORDER BY " + field_for_sort + ", date DESC LIMIT " + count_rows );
            ps.setString(1, "%" + keyword.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("title") + " — " + rs.getString("url"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void FindNews_Text(String keyword) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT title, url FROM news WHERE LOWER(summary) LIKE ? ORDER BY " + field_for_sort + ", date DESC LIMIT " + count_rows );
            ps.setString(1, "%" + keyword.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("title") + " — " + rs.getString("url"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void FindNews_Date(String keyword) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT title, url FROM news WHERE date::date = ? ORDER BY " + field_for_sort + ", date DESC LIMIT " + count_rows );
            ps.setDate(1, Date.valueOf(keyword));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("title") + " — " + rs.getString("url"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void FindNews_Category(String keyword) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT title, url FROM news WHERE LOWER(category) LIKE ? ORDER BY " + field_for_sort + ", date DESC LIMIT " + count_rows );
            ps.setString(1, "%" + keyword.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("title") + " — " + rs.getString("url"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void FindNews_Source(String keyword) {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT title, url FROM news WHERE LOWER(source) LIKE ? ORDER BY " + field_for_sort + ", date DESC LIMIT " + count_rows );
            ps.setString(1, "%" + keyword.toLowerCase() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("title") + " — " + rs.getString("url"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void exportToCSV() {
        try (PrintWriter writer = new PrintWriter("export.csv")) {
            writer.println("title, date,summary, url, keywords, video_or_foto_url, category, source, source_id");
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM news ORDER BY " + field_for_sort + ", date DESC");
            while (rs.next()) {
                String line = String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"",
                        rs.getString("title").replace("\"" , "'"),
                        rs.getTimestamp("date").toString(),
                        rs.getString("summary").replace("\"", "'"),
                        rs.getString("url"),
                        rs.getString("keywords"),
                        rs.getString("video_or_foto_url"),
                        rs.getString("category"),
                        rs.getString("source"),
                        rs.getString("source_id"));
                writer.println(line);
            }
            System.out.println("Файл export.csv сохранён.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
    }


    public void SetField_for_sort(String t) {field_for_sort = t;}


    public List<Source> getSources() {return getAllSources();}
}
