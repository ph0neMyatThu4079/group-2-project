package com.napier.proj.dao;

import com.napier.proj.model.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for retrieving language report data
 * from the World database.
 *
 * @author Lin Myat Thu
 */
public class LanguageDAO {
    /** Database connection object used for executing SQL queries */
    private Connection con;

    /**
     * Constructs a LanguageDAO instance with the given database connection.
     *
     * @param con The active database connection.
     */
    public LanguageDAO(Connection con) {
        this.con = con;
    }
    public List<Language> getMajorLanguageReport() {
        List<Language> languages = new ArrayList<>();
        try {
            // SQL query to find speakers of 5 major languages and calculate % of world population
            String sql =
                    "SELECT cl.Language AS language, " +
                            "       SUM(c.Population * cl.Percentage / 100) AS speakers, " +
                            "       (SUM(c.Population * cl.Percentage / 100) / " +
                            "        (SELECT SUM(Population) FROM country) * 100) AS world_percentage " +
                            "FROM countrylanguage cl " +
                            "JOIN country c ON cl.CountryCode = c.Code " +
                            "WHERE cl.Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                            "GROUP BY cl.Language " +
                            "ORDER BY speakers DESC;";

            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Language lang = new Language();
                lang.setLanguage(rs.getString("language"));
                lang.setSpeakers(rs.getLong("speakers"));
                lang.setWorldPercentage(rs.getDouble("world_percentage"));

                languages.add(lang);
            }

        } catch (Exception e) {
            System.out.println("Error fetching language data: " + e.getMessage());
        }

        return languages;
    }


}
