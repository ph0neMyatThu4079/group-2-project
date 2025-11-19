package com.napier.proj.dao;

import com.napier.proj.App;
import com.napier.proj.config.DatabaseConfig;
import com.napier.proj.model.Country;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppIntegrationTest {

    static Connection conn;
    static CountryDAO countryDAO;

    @BeforeAll
    static void setup()
    {
        // Open real DB connection
        DatabaseConfig.openConnection("localhost:33060", 30000);
        conn = DatabaseConfig.getConnection();

        assertNotNull(conn);

        countryDAO = new CountryDAO(conn);
    }

    @AfterAll
    static void tearDown() {
        DatabaseConfig.closeConnection();
    }

    @Test
    void getAllCountriesByPopulation() {

        List<Country> countries = countryDAO.getAllCountriesByPopulation();

        // Check if a country list is not null
        assertNotNull(countries);

        // Check if a list is not empty
        assertFalse(countries.isEmpty());

        // Verifies data integrity
        Country country = countries.getFirst();
        assertEquals("China", country.getName());
        assertEquals("Asia", country.getContinent());
    }

    @Test
    void getAllCountriesInContinentByPopulation() {
        List<Country> countries = countryDAO.getAllCountriesInContinentByPopulation("Europe");

        // Check if a country list is not null
        assertNotNull(countries);

        // Check if a list is not empty
        assertFalse(countries.isEmpty());

        // Verifies data integrity
        Country country = countries.getFirst();
        assertEquals("Russian Federation",  country.getName());
        assertEquals("Europe",  country.getContinent());
    }

    @Test
    void getAllCountriesInRegionByPopulation() {
        List<Country> countries = countryDAO.getAllCountriesInRegionByPopulation("Southern Europe");

        // Check if a country list is not null
        assertNotNull(countries);

        //Check if a list is not empty
        assertFalse(countries.isEmpty());

        //Verifies data integrity
        Country country = countries.getFirst();
        assertEquals("Italy",  country.getName());
        assertEquals("Southern Europe",  country.getRegion());
    }

    @Test
    void getTopNPopulatedCountriesIntheworld() {
        List<Country> countries = countryDAO.getTopNPopulatedCountriesIntheworld(2);

        // Check if a country list is not null
        assertNotNull(countries);

        //Check if a list is not empty
        assertFalse(countries.isEmpty());

        //Check the list contains exactly N items
        assertEquals(2, countries.size(),
                "The DAO must return exactly " + 2 + " countries.");

        // Verify the first country is the most populated in the world
        Country first = countries.getFirst();
        assertEquals("China", first.getName());
        assertTrue(first.getPopulation() > 1_000_000_000);

        // Ensure countries are sorted in descending order of population
        long previousPopulation = Long.MAX_VALUE;
        for (Country c : countries) {
            assertTrue(
                    c.getPopulation() <= previousPopulation,
                    "Countries must be sorted from largest to smallest population."
            );
            previousPopulation = c.getPopulation();
        }

    }

    @Test
    void getTopNPopulatedCountriesInContinent() {
        List<Country> countries = countryDAO.getTopNPopulatedCountriesInContinent("Europe",2);

        // Check if a country list is not null
        assertNotNull(countries);

        //Check if a list is not empty
        assertFalse(countries.isEmpty());

        // Assert - list contains exactly N countries (or fewer if continent has less than N countries)
        assertTrue(countries.size() <= 2, "List size should be at most " + 2);

        // Verify first country is the most populated in the continent
        Country first = countries.getFirst();
        assertEquals("Russian Federation", first.getName());
        assertEquals("Europe", first.getContinent());
        assertTrue(first.getPopulation() > 40_000_000, "Population should be realistic");

        // Ensure countries are sorted by descending population
        long previousPop = Long.MAX_VALUE;
        for (Country c : countries) {
            assertTrue(c.getPopulation() <= previousPop, "Countries must be sorted by descending population");
            previousPop = c.getPopulation();
        }

    }

    @Test
    void getTopNPopulatedCountriesInRegion() {
        List<Country> countries = countryDAO.getTopNPopulatedCountriesInRegion("Southern Europe",2);

        // Check if a country list is not null
        assertNotNull(countries);

        //Check if a list is not empty
        assertFalse(countries.isEmpty());

        // Assert - list contains at most N countries
        assertTrue(countries.size() <= 2, "List size should be at most " + 2);

            // Verify first country is the most populated in the region
            Country first = countries.getFirst();
            assertEquals("Italy", first.getName());
            assertEquals("Southern Europe", first.getRegion());
            assertTrue(first.getPopulation() > 1_000_000, "Population should be realistic");

            // Ensure countries are sorted by descending population
            long previousPop = Long.MAX_VALUE;
            for (Country c : countries) {
                assertTrue(c.getPopulation() <= previousPop, "Countries must be sorted by descending population");
                previousPop = c.getPopulation();
            }
        }

    }
