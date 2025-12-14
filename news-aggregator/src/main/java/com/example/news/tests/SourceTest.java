package com.example.news.tests;

import com.example.news.model.Source;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SourceTest {

    @Test
    void testSourceGettersAndSetters() {
        Source source = new Source(
                1,
                "title", "date", "summary", "url",
                "media", "rbc", "source_id", "http://json.url", "root"
        );

        assertEquals(1, source.getId());
        assertEquals("title", source.getTitle_field_name());
        assertEquals("date", source.getDate_field_name());
        assertEquals("summary", source.getSummary_field_name());
        assertEquals("url", source.getUrl_field_name());
        assertEquals("media", source.getVideoOrFotoUrl_field_name());
        assertEquals("rbc", source.getSource());
        assertEquals("source_id", source.getSourceId_field_name());
        assertEquals("http://json.url", source.getJson_url());
        assertEquals("root", source.getRoot_tag());

        source.setTitle_field_name("t");
        source.setDate_field_name("d");
        source.setSummary_field_name("s");
        source.setUrl_field_name("u");
        source.setVideoOrFotoUrl_field_name("m");
        source.setSource("mail");
        source.setSourceId_field_name("sid");
        source.setJson_url("http://updated.url");
        source.setRoot_tag("updated");

        assertEquals("t", source.getTitle_field_name());
        assertEquals("d", source.getDate_field_name());
        assertEquals("s", source.getSummary_field_name());
        assertEquals("u", source.getUrl_field_name());
        assertEquals("m", source.getVideoOrFotoUrl_field_name());
        assertEquals("mail", source.getSource());
        assertEquals("sid", source.getSourceId_field_name());
        assertEquals("http://updated.url", source.getJson_url());
        assertEquals("updated", source.getRoot_tag());
    }
}
