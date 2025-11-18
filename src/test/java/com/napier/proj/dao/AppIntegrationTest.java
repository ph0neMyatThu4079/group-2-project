package com.napier.proj.dao;

import com.napier.proj.App;
import com.napier.proj.config.DatabaseConfig;
import com.napier.proj.model.CapitalCity;
import com.napier.proj.model.Country;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppIntegrationTest {

    static Connection conn;
    static CountryDAO countryDAO;
    static CapitalCityDAO capitalCityDAO;

    @BeforeAll
    static void setup()
    {
        // Open real DB connection
        DatabaseConfig.openConnection("localhost:33060", 30000);
        conn = DatabaseConfig.getConnection();

        assertNotNull(conn);

        countryDAO = new CountryDAO(conn);
        capitalCityDAO = new CapitalCityDAO(conn);
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
    }

    @Test
    void getTopNPopulatedCountriesIntheworld() {
    }

    @Test
    void getTopNPopulatedCountriesInContinent() {
    }

    @Test
    void getTopNPopulatedCountriesInRegion() {
    }

    // Test Capital City
    @Test
    void getAllCapitalCities(){
        List<CapitalCity> capitalCities = capitalCityDAO.getAllCapitalCities();

        assertNotNull(capitalCities);

        assertFalse(capitalCities.isEmpty());

        CapitalCity capitalCity = capitalCities.getFirst();
        assertEquals("Seoul", capitalCity.getName());
        assertEquals("South Korea", capitalCity.getCountry());

    }

    @Test
    void getAllCapitalCitiesInContinent(){
        List<CapitalCity> capitalCities = capitalCityDAO.getAllCapitalCitiesInContinent("Asia");

        assertNotNull(capitalCities);

        assertFalse(capitalCities.isEmpty());

        CapitalCity capitalCity = capitalCities.getFirst();
        assertEquals("Seoul", capitalCity.getName());
        assertEquals("South Korea", capitalCity.getCountry());

    }

    @Test
    void getAllCapitalCitiesInRegion(){
        List<CapitalCity> capitalCities = capitalCityDAO.getAllCapitalCitiesInRegion("Caribbean");

        assertNotNull(capitalCities);

        assertFalse(capitalCities.isEmpty());

        CapitalCity capitalCity = capitalCities.getFirst();
        assertEquals("La Habana", capitalCity.getName());
        assertEquals("Cuba", capitalCity.getCountry());

    }

    @Test
    void getTopNPopulatedCapitalCities(){
        List<CapitalCity> capitalCities = capitalCityDAO.getTopNPopulatedCapitalCities(10);

        assertNotNull(capitalCities);

        assertFalse(capitalCities.isEmpty());

        CapitalCity capitalCity = capitalCities.getFirst();
        assertEquals("Seoul", capitalCity.getName());
        assertEquals("South Korea", capitalCity.getCountry());
    }

    @Test
    void getTopNPopulatedCapitalCitiesByContinent(){
        List<CapitalCity> capitalCities = capitalCityDAO.getTopNPopulatedCapitalCitiesByContinent("Asia", 10);

        assertNotNull(capitalCities);

        assertFalse(capitalCities.isEmpty());

        CapitalCity capitalCity = capitalCities.getFirst();
        assertEquals("Seoul", capitalCity.getName());
        assertEquals("South Korea", capitalCity.getCountry());
    }

    @Test
    void getTopNPopulatedCapitalCitiesByRegion(){
        List<CapitalCity> capitalCities = capitalCityDAO.getTopNPopulatedCapitalCitiesByRegion("Central Africa", 10);

        assertNotNull(capitalCities);

        assertFalse(capitalCities.isEmpty());

        CapitalCity capitalCity = capitalCities.getFirst();
        assertEquals("Kinshasa", capitalCity.getName());
        assertEquals("Congo, The Democratic Republic of the", capitalCity.getCountry());
    }
}