package com.example.news.tests;

import com.example.news.db.DatabaseManager;
import com.example.news.model.Source;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseManagerTest {

    @Test
    void testSettersAndClose() {
        DatabaseManager db = new DatabaseManager();
        db.SetСountRows(5);
        db.SetField_for_sort("title ASC");
        db.close();

        List<Source> sources = db.getSources();
        assertNotNull(sources);
    }
}
