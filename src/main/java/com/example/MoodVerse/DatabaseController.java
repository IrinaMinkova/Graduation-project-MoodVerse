package com.example.MoodVerse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
    public class DatabaseController {

        @Autowired
        DataSource dataSource;

        @GetMapping("/datatest") // better to have it in the repo and the test() function can be checked internally
        public String test() throws SQLException {
            try (Connection conn = dataSource.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT 1+1")) {
                rs.next();
                int result = rs.getInt(1);
                return "Database connection is "+ (result == 2 ? "OK!" : "Not Ok!");
            }
        }
    }

