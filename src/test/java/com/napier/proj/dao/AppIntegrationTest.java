package com.napier.proj.dao;

import com.napier.proj.App;
import com.napier.proj.config.DatabaseConfig;
import com.napier.proj.model.City;
import com.napier.proj.model.Country;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static com.napier.proj.report.AppUnitTest.cityDAO;
import static org.junit.jupiter.api.Assertions.*;

class AppIntegrationTest {

    static Connection conn;
    static CountryDAO countryDAO;
    static CityDAO cityDAO;

    @BeforeAll
    static void setup()
    {
        // Open real DB connection
        DatabaseConfig.openConnection("localhost:33060", 30000);
        conn = DatabaseConfig.getConnection();

        assertNotNull(conn);

        countryDAO = new CountryDAO(conn);
        cityDAO = new CityDAO(conn);
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

    // integration testing for city dao
    @Test
    void getAllinWorldCitiesByPopulation() {
        List<City> cities = cityDAO.getAllinWorldCitiesByPopulation();

        // Check if a country list is not null
        assertNotNull(cities);

        // Check if a list is not empty
        assertFalse(cities.isEmpty());

        // Verifies data integrity
        City city = cities.get(0); // use get(0) instead of getFirst() for List
        assertEquals("Mumbai (Bombay)", city.getName());
        assertEquals("India", city.getCountry());
        assertEquals("Maharashtra", city.getDistrict());
        assertEquals(10500000L, city.getPopulation()); // Compare as long
    }

    @Test
    void getAllCitiesInContinentByPopulation() {
        List<City> cities = cityDAO.getAllCitiesInContinentByPopulation("Asia");

        // Check if a country list is not null
        assertNotNull(cities);

        // Check if a list is not empty
        assertFalse(cities.isEmpty());

        // Verifies data integrity
        City city = cities.get(0); // use get(0) instead of getFirst() for List
        assertEquals("Mumbai (Bombay)", city.getName());
        assertEquals("India", city.getCountry());
        assertEquals("Maharashtra", city.getDistrict());
        assertEquals(10500000L, city.getPopulation()); // Compare as long
    }

    @Test
    void getAllCitiesInRegionByPopulation() {
        List<City> cities = cityDAO.getAllCitiesInRegionByPopulation("Central Africa");

        // Check if a country list is not null
        assertNotNull(cities);

        // Check if a list is not empty
        assertFalse(cities.isEmpty());

        // Verifies data integrity
        City city = cities.get(1); // use get(0) instead of getFirst() for List
        assertEquals("Luanda", city.getName());
        assertEquals("Angola", city.getCountry());
        assertEquals("Luanda", city.getDistrict());
        assertEquals(2022000L, city.getPopulation()); // Compare as long
    }

    @Test
    void getAllCitiesInCountryByPopulation() {
        List<City> cities = cityDAO.getAllCitiesInCountryByPopulation("Argentina");

        // Check if a country list is not null
        assertNotNull(cities);

        // Check if a list is not empty
        assertFalse(cities.isEmpty());

        // Verifies data integrity
        City city = cities.get(0); // use get(0) instead of getFirst() for List
        assertEquals("Buenos Aires", city.getName());
        assertEquals("Argentina", city.getCountry());
        assertEquals("Distrito Federal", city.getDistrict());
        assertEquals(2982146L, city.getPopulation()); // Compare as long
    }

    @Test
    void getAllCitiesInDistrictByPopulation() {
        List<City> cities = cityDAO.getAllCitiesInDistrictByPopulation("Benguela");

        // Check if a country list is not null
        assertNotNull(cities);

        // Check if a list is not empty
        assertFalse(cities.isEmpty());

        // Verifies data integrity
        City city = cities.get(0); // use get(0) instead of getFirst() for List
        assertEquals("Lobito", city.getName());
        assertEquals("Angola", city.getCountry());
        assertEquals("Benguela", city.getDistrict());
        assertEquals(130000L, city.getPopulation()); // Compare as long
    }
}