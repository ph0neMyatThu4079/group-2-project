package com.napier.proj.report;


import com.napier.proj.dao.LanguageDAO;
import com.napier.proj.model.Language;

import java.util.List;

/**
 * Report class responsible for generating and displaying language statistics.
 *
 * @author Lin Myat Thu
 */
public class LanguageReport {
    /** Data Access Object for retrieving language data. */
    private LanguageDAO languageDAO;

    /**
     * Constructs a {@code LanguageReport} object with a given {@link LanguageDAO}.
     *
     * @param languageDAO the data access object used to fetch language data.
     */
    public LanguageReport(LanguageDAO languageDAO) {
        this.languageDAO = languageDAO;
    }
    /**
     * Prints the number of speakers and world population percentage
     * for major languages (Chinese, English, Hindi, Spanish, Arabic)
     * from greatest number of speakers to smallest.
     */
    public void printMajorLanguageReport() {
        // Retrieve list of major languages with speaker count and world percentage.
        List<Language> languages = this.languageDAO.getMajorLanguageReport();

        System.out.println("\nThe population of people who speak the following languages (from greatest to smallest),");
        System.out.println("including the percentage of the world population: Chinese, English, Hindi, Spanish, Arabic.\n");

        // Print table header
        System.out.printf("%-15s %-20s %-20s%n",
                "Language", "Total Speakers", "% of World Population");
        System.out.println("-----------------------------------------------------------");

        // Print each language's details
        for (Language language : languages) {
            System.out.printf("%-15s %-20d %.2f%%%n",
                    language.getLanguage(),
                    language.getSpeakers(),
                    language.getWorldPercentage());
        }
    }

}
