package com.napier.proj.dao;

import com.napier.proj.App;
import com.napier.proj.config.DatabaseConfig;
import com.napier.proj.model.CapitalCity;
import com.napier.proj.model.City;
import com.napier.proj.model.Country;
import com.napier.proj.model.Language;
import com.napier.proj.model.Population;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * AppIntegrationTest performs integration testing on the DAO classes and their
 * corresponding report methods. It establishes a real database connection
 * and verifies that each DAO correctly retrieves data from the database
 * and maps it to the appropriate model objects (Country, City, CapitalCity,
 * Population, Language).
 *
 * @author Lin Myat Thu
 * @author Phone Myat Thu
 * @author Min Wanna Hlan
 * @author So Pyay Tun
 * @author Ingyin Thwe
 */
class AppIntegrationTest {

    static Connection conn;
    static CountryDAO countryDAO;
    static CityDAO cityDAO;

    static PopulationDAO populationDAO;
    static LanguageDAO languageDAO;

    static CapitalCityDAO capitalCityDAO;



    @BeforeAll
    static void setup()
    {
        // Open real DB connection
        DatabaseConfig.openConnection("localhost:33060", 30000);
        conn = DatabaseConfig.getConnection();

        countryDAO = new CountryDAO(conn);
        populationDAO = new PopulationDAO(conn);
        languageDAO = new LanguageDAO(conn);
        capitalCityDAO = new CapitalCityDAO(conn);
        cityDAO = new CityDAO(conn);
    }

    @AfterAll
    static void tearDown() {
        DatabaseConfig.closeConnection();
    }

    /**
     * Tests that a valid database connection is successfully established
     * and that the connection is verified to be valid by the driver.
     */
    @Test
    void testConnection() {
        assertNotNull(conn, "Connection should not be null");
        try {
            assertTrue(conn.isValid(5), "Connection should be valid");
        } catch (Exception e) {
            fail("Connection is not valid: " + e.getMessage());
        }
    }

    /**
     * Verifies that getAllCountriesByPopulation() returns
     * a non-null, non-empty list of correctly mapped Country objects
     * sorted by population in descending order.
     */
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

    /**
     * Ensures getAllCountriesByPopulation() handles SQL exceptions properly
     * by returning an empty, non-null list when the query fails.
     *
     * @throws Exception simulated SQL error
     */
    @Test
    void getAllCountriesByPopulation_Exception() throws Exception {
        // Mock the DB connection
        Connection mockCon = mock(Connection.class);

        // Inject the mocked connection into the DAO
        CountryDAO countryDAO = new CountryDAO(mockCon);

        // Force the prepareStatement() call to fail
        when(mockCon.prepareStatement(anyString())).thenThrow(new SQLException("Simulated DB error"));

        // Call the method
        List<Country> countries = countryDAO.getAllCountriesByPopulation();

        // Assertions
        assertNotNull(countries);        // Method must still return a list
        assertTrue(countries.isEmpty()); // It should be empty due to the exception
    }

    /**
     * Tests that countries in a given continent are retrieved correctly,
     * sorted by population, and mapped to valid Country objects.
     */
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

    /**
     * Ensures continent filtering query fails gracefully by returning
     * an empty list when an SQL exception occurs.
     *
     * @throws Exception simulated SQL error
     */
    @Test
    void getAllCountriesInContinentByPopulation_Exception() throws Exception {
        // Mock the DB connection
        Connection mockCon = mock(Connection.class);

        // Inject the mocked connection into the DAO
        CountryDAO countryDAO = new CountryDAO(mockCon);

        // Force the prepareStatement() call to fail
        when(mockCon.prepareStatement(anyString())).thenThrow(new SQLException("Simulated DB error"));

        // Call the method
        List<Country> countries = countryDAO.getAllCountriesInContinentByPopulation("Europe");

        // Assertions
        assertNotNull(countries);        // Method must still return a list
        assertTrue(countries.isEmpty()); // It should be empty due to the exception
    }

    /**
     * Tests retrieving all countries in a specific region ordered by population.
     * Verifies that the returned list is not null, not empty, and the first
     * country in the list has valid data.
     */
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

    /**
     * Ensures the region-based population filtering query fails gracefully by
     * returning an empty list when an SQL exception occurs.
     *
     * @throws Exception simulated SQL error during statement preparation
     */
    @Test
    void getAllCountriesInRegionByPopulation_Exception() throws Exception {
        // Mock the DB connection
        Connection mockCon = mock(Connection.class);

        // Inject the mocked connection into the DAO
        CountryDAO countryDAO = new CountryDAO(mockCon);

        // Force the prepareStatement() call to fail
        when(mockCon.prepareStatement(anyString())).thenThrow(new SQLException("Simulated DB error"));

        // Call the method
        List<Country> countries = countryDAO.getAllCountriesInRegionByPopulation("Southern Europe");

        // Assertions
        assertNotNull(countries);        // Method must still return a list
        assertTrue(countries.isEmpty()); // It should be empty due to the exception
    }

    /**
     * Tests retrieving the top N most populated countries in the world.
     * Verifies correct list size, sorting order, and expected top result.
     */
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

    /**
     * Ensures the world top-N population query returns an empty list when an
     * SQL exception occurs instead of crashing.
     *
     * @throws Exception simulated SQL error
     */
    @Test
    void getTopNPopulatedCountriesIntheworld_Exception() throws Exception {
        // Mock the DB connection
        Connection mockCon = mock(Connection.class);

        // Inject the mocked connection into the DAO
        CountryDAO countryDAO = new CountryDAO(mockCon);

        // Force the prepareStatement() call to fail
        when(mockCon.prepareStatement(anyString())).thenThrow(new SQLException("Simulated DB error"));

        // Call the method
        List<Country> countries = countryDAO.getTopNPopulatedCountriesIntheworld(2);

        // Assertions
        assertNotNull(countries);        // Method must still return a list
        assertTrue(countries.isEmpty()); // It should be empty due to the exception
    }

    /**
     * Tests retrieving the top N most populated countries in a given continent.
     * Validates list size constraints, correct continent filtering, and sorting order.
     */
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

    /**
     * Ensures the continent-based top-N population query returns an empty list
     * gracefully when an SQL exception occurs.
     *
     * @throws Exception simulated SQL error
     */
    @Test
    void getTopNPopulatedCountriesInContinent_Exception() throws Exception {
        // Mock the DB connection
        Connection mockCon = mock(Connection.class);

        // Inject the mocked connection into the DAO
        CountryDAO countryDAO = new CountryDAO(mockCon);

        // Force the prepareStatement() call to fail
        when(mockCon.prepareStatement(anyString())).thenThrow(new SQLException("Simulated DB error"));

        // Call the method
        List<Country> countries = countryDAO.getTopNPopulatedCountriesInContinent("Europe",2);

        // Assertions
        assertNotNull(countries);        // Method must still return a list
        assertTrue(countries.isEmpty()); // It should be empty due to the exception
    }

    /**
     * Tests retrieving the top N most populated countries in a specific region.
     * Confirms list size, data correctness, and population descending order.
     */
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

    /**
     * Ensures the region-based top-N population query returns an empty list
     * when a database error occurs.
     *
     * @throws Exception simulated SQL error
     */
    @Test
    void getTopNPopulatedCountriesInRegion_Exception() throws Exception {
        // Mock the DB connection
        Connection mockCon = mock(Connection.class);

        // Inject the mocked connection into the DAO
        CountryDAO countryDAO = new CountryDAO(mockCon);

        // Force the prepareStatement() call to fail
        when(mockCon.prepareStatement(anyString())).thenThrow(new SQLException("Simulated DB error"));

        // Call the method
        List<Country> countries = countryDAO.getTopNPopulatedCountriesInRegion("Europe",2);

        // Assertions
        assertNotNull(countries);        // Method must still return a list
        assertTrue(countries.isEmpty()); // It should be empty due to the exception
    }


    /**
     * Tests retrieving world population data.
     * Ensures the result is not null, contains data, and validates expected values
     * including correct name mapping and population count from dataset.
     */
    @Test
    void getWorldPopulation(){
        List<Population> populations = populationDAO.getWorldPopulation();

        assertNotNull(populations);
        assertFalse(populations.isEmpty());

        Population population = populations.getFirst();

        // Validate object mapping
        assertEquals("World", population.getName());
        assertTrue(population.getTotalPopulation() > 0);
        assertEquals(6078749450L, population.getTotalPopulation());
    }

    /**
     * Ensures the world population query handles SQL exceptions gracefully by
     * returning an empty list instead of throwing an error.
     *
     * @throws SQLException simulated SQL exception
     */
    @Test
    void getWorldPopulation_SQLExceptionHandled() throws SQLException {
        // Mock connection
        Connection mockConnection = Mockito.mock(Connection.class);

        // Make prepareStatement throw SQLException
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString()))
                .thenThrow(new SQLException("Mock SQL Error"));

        // Inject mock connection into DAO
        PopulationDAO faultyDAO = new PopulationDAO(mockConnection);

        // Run method
        List<Population> result = faultyDAO.getWorldPopulation();

        // Assertions
        assertNotNull(result);           // Method should still return a list
        assertTrue(result.isEmpty());    // No data added since exception occurred
    }

    /**
     * Tests retrieving continent-level population statistics.
     * Validates presence of data and correctness of mapped population values.
     */
    @Test
    void getContinentPopulation(){
        List<Population> populations = populationDAO.getContinentPopulation("Asia");

        assertNotNull(populations);
        assertFalse(populations.isEmpty());

        Population population = populations.getFirst();

        assertEquals("Asia", population.getName());
        assertTrue(population.getTotalPopulation() > 0);
        assertEquals(3705025700L, population.getTotalPopulation());
    }


    /**
     * Ensures the continent population query returns an empty list when a database
     * failure occurs, instead of causing the method to crash.
     *
     * @throws Exception simulated SQL error
     */
    @Test
    void getContinentPopulation_Exception() throws Exception {
        Connection mockCon = mock(Connection.class);
        PopulationDAO dao = new PopulationDAO(mockCon);

        when(mockCon.prepareStatement(anyString()))
                .thenThrow(new SQLException("Simulated DB error"));

        List<Population> result = dao.getContinentPopulation("Asia");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }


    /**
     * Tests retrieving population statistics for a specific region.
     * Confirms a valid response and verifies population totals match expected dataset values.
     */
    @Test
    void  getRegionPopulation(){
        List<Population> populations = populationDAO.getRegionPopulation("Central Africa");

        assertNotNull(populations);
        assertFalse(populations.isEmpty());

        Population population = populations.getFirst();

        assertEquals("Central Africa", population.getName());
        assertTrue(population.getTotalPopulation() > 0);
        assertEquals(95652000L, population.getTotalPopulation());
    }

    /**
     * Verifies proper handling of SQL failure when querying regional population.
     * Method must return a non-null but empty list when an exception occurs.
     *
     * @throws Exception simulated SQL exception
     */
    @Test
    void getRegionPopulation_Exception() throws Exception {
        Connection mockCon = mock(Connection.class);
        PopulationDAO dao = new PopulationDAO(mockCon);

        when(mockCon.prepareStatement(anyString()))
                .thenThrow(new SQLException("Simulated DB error"));

        List<Population> result = dao.getRegionPopulation("Western Europe");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }


    /**
     * Tests retrieving population details for a specific country.
     * Ensures result list is non-empty and mapped data matches expected database values.
     */
    @Test
    void getCountryPopulation(){
        List<Population> populations = populationDAO.getCountryPopulation("Argentina");

        assertNotNull(populations);
        assertFalse(populations.isEmpty());

        Population population = populations.getFirst();

        assertEquals("Argentina", population.getName());
        assertEquals(37032000, population.getTotalPopulation()); // From world.sql dataset

    }

    /**
     * Ensures getCountryPopulation gracefully handles SQL exceptions by returning
     * an empty list rather than throwing an exception.
     *
     * @throws Exception simulated SQL error
     */
    @Test
    void getCountryPopulation_Exception() throws Exception {
        Connection mockCon = mock(Connection.class);
        PopulationDAO dao = new PopulationDAO(mockCon);

        when(mockCon.prepareStatement(anyString()))
                .thenThrow(new SQLException("Simulated DB error"));

        List<Population> result = dao.getCountryPopulation("France");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }


    /**
     * Tests retrieving population data for a specific district.
     * Ensures returned list is valid and first entry contains correct mapping and values.
     */
    @Test
    void getDistrictPopulation(){
        List<Population> populations = populationDAO.getDistrictPopulation("Benguela");

        assertNotNull(populations);
        assertFalse(populations.isEmpty());

        Population population = populations.getFirst();

        assertEquals("Benguela", population.getName());
        assertTrue(population.getTotalPopulation() > 0);
        assertEquals(258300L, population.getTotalPopulation());
    }

    /**
     * Confirms that the district population query returns an empty list when SQL failures occur,
     * maintaining application stability.
     *
     * @throws Exception simulated SQL exception
     */
    @Test
    void getDistrictPopulation_Exception() throws Exception {
        Connection mockCon = mock(Connection.class);
        PopulationDAO dao = new PopulationDAO(mockCon);

        when(mockCon.prepareStatement(anyString()))
                .thenThrow(new SQLException("Simulated DB error"));

        List<Population> result = dao.getDistrictPopulation("California");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }


    /**
     * Tests retrieving population data for a given city.
     * Ensures correct mapping of name and realistic population values from the dataset.
     */
    @Test
    void getCityPopulation(){
        List<Population> populations = populationDAO.getCityPopulation("Tokyo");

        assertNotNull(populations);
        assertFalse(populations.isEmpty());

        Population population = populations.getFirst();

        assertEquals("Tokyo", population.getName());
        assertTrue(population.getTotalPopulation() > 0);
        assertEquals(7980230, population.getTotalPopulation()); // From world.sql dataset
    }

    /**
     * Verifies that the city population query does not crash on SQL errors,
     * instead returning a safe, empty result list.
     *
     * @throws Exception simulated SQL error
     */
    @Test
    void getCityPopulation_Exception() throws Exception {
        Connection mockCon = mock(Connection.class);
        PopulationDAO dao = new PopulationDAO(mockCon);

        when(mockCon.prepareStatement(anyString()))
                .thenThrow(new SQLException("Simulated DB error"));

        List<Population> result = dao.getCityPopulation("Yangon");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }


    /**
     * Tests retrieval of the major language usage report.
     * Ensures the list is populated, contains exactly five major languages,
     * and is sorted correctly by descending number of speakers.
     */
    @Test
    void getMajorLanguageReport(){
        List<Language> languages = languageDAO.getMajorLanguageReport();
        // List must not be null
        assertNotNull(languages);

        // List must not be empty
        assertFalse(languages.isEmpty());

        // Ensure exactly 5 major languages are returned
        assertEquals(5, languages.size());

        // Check that expected languages exist
        boolean hasChinese = languages.stream().anyMatch(l -> l.getLanguage().equals("Chinese"));
        boolean hasEnglish = languages.stream().anyMatch(l -> l.getLanguage().equals("English"));
        boolean hasHindi = languages.stream().anyMatch(l -> l.getLanguage().equals("Hindi"));
        boolean hasSpanish = languages.stream().anyMatch(l -> l.getLanguage().equals("Spanish"));
        boolean hasArabic = languages.stream().anyMatch(l -> l.getLanguage().equals("Arabic"));

        assertTrue(hasChinese && hasEnglish && hasHindi && hasSpanish && hasArabic);


        // Check order by speakers (descending)
        for (int i = 1; i < languages.size(); i++) {
            assertTrue(languages.get(i - 1).getSpeakers() >= languages.get(i).getSpeakers(),
                    "Languages should be ordered by descending number of speakers");
        }
    }

    /**
     * Verifies that SQL errors during major language report queries are handled safely
     * by returning an empty but non-null list.
     *
     * @throws Exception simulated SQL error
     */
    @Test
    void getMajorLanguageReport_Exception() throws Exception {
        // Mock connection
        Connection mockCon = mock(Connection.class);

        // Create DAO with mocked connection
        LanguageDAO dao = new LanguageDAO(mockCon);

        // Force prepareStatement to throw exception
        when(mockCon.prepareStatement(anyString()))
                .thenThrow(new SQLException("Simulated DB error"));

        // Call the method
        List<Language> result = dao.getMajorLanguageReport();

        // Assertions
        assertNotNull(result);          // Should not return null
        assertTrue(result.isEmpty());   // Must be empty due to exception
    }


    /**
     * Tests retrieval of all capital cities.
     * Ensures the result contains entries and checks correct mapping of name and country.
     */
    @Test
    void getAllCapitalCities(){
        List<CapitalCity> capitalCities = capitalCityDAO.getAllCapitalCities();

        assertNotNull(capitalCities);

        assertFalse(capitalCities.isEmpty());

        CapitalCity capitalCity = capitalCities.getFirst();
        assertEquals("Seoul", capitalCity.getName());
        assertEquals("South Korea", capitalCity.getCountry());

    }

    /**
     * Ensures the all-capital-cities query returns an empty list in case of SQL error,
     * without throwing an exception.
     *
     * @throws Exception simulated DB error
     */
    @Test
    void getAllCapitalCities_Exception() throws Exception {
        Connection mockCon = mock(Connection.class);
        CapitalCityDAO dao = new CapitalCityDAO(mockCon);

        when(mockCon.prepareStatement(anyString()))
                .thenThrow(new SQLException("Simulated DB error"));

        List<CapitalCity> result = dao.getAllCapitalCities();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /**
     * Tests retrieving all capital cities in a specific continent.
     * Confirms the list is not empty and validates the mapped fields.
     */
    @Test
    void getAllCapitalCitiesInContinent(){
        List<CapitalCity> capitalCities = capitalCityDAO.getAllCapitalCitiesInContinent("Asia");

        assertNotNull(capitalCities);

        assertFalse(capitalCities.isEmpty());

        CapitalCity capitalCity = capitalCities.getFirst();
        assertEquals("Seoul", capitalCity.getName());
        assertEquals("South Korea", capitalCity.getCountry());
    }

    /**
     * Ensures continent-based capital cities query handles SQL exceptions gracefully
     * by returning an empty result list.
     *
     * @throws Exception simulated DB error
     */
    @Test
    void getAllCapitalCitiesInContinent_Exception() throws Exception {
        Connection mockCon = mock(Connection.class);
        CapitalCityDAO dao = new CapitalCityDAO(mockCon);

        when(mockCon.prepareStatement(anyString()))
                .thenThrow(new SQLException("Simulated DB error"));

        List<CapitalCity> result = dao.getAllCapitalCitiesInContinent("Asia");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /**
     * Tests retrieving all capital cities in a specific region.
     * Validates result mapping and that data exists for expected region.
     */
    @Test
    void getAllCapitalCitiesInRegion(){
        List<CapitalCity> capitalCities = capitalCityDAO.getAllCapitalCitiesInRegion("Caribbean");

        assertNotNull(capitalCities);

        assertFalse(capitalCities.isEmpty());

        CapitalCity capitalCity = capitalCities.getFirst();
        assertEquals("La Habana", capitalCity.getName());
        assertEquals("Cuba", capitalCity.getCountry());
    }

    /**
     * Verifies that a SQL failure during a region-specific capital city query
     * results in a non-null but empty list.
     *
     * @throws Exception simulated database error
     */
    @Test
    void getAllCapitalCitiesInRegion_Exception() throws Exception {
        Connection mockCon = mock(Connection.class);
        CapitalCityDAO dao = new CapitalCityDAO(mockCon);

        when(mockCon.prepareStatement(anyString()))
                .thenThrow(new SQLException("Simulated DB error"));

        List<CapitalCity> result = dao.getAllCapitalCitiesInRegion("Western Europe");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /**
     * Tests retrieval of the top N most populated capital cities.
     * Ensures valid ordering and correct first object mapping from dataset.
     */
    @Test
    void getTopNPopulatedCapitalCities(){
        List<CapitalCity> capitalCities = capitalCityDAO.getTopNPopulatedCapitalCities(10);

        assertNotNull(capitalCities);

        assertFalse(capitalCities.isEmpty());

        CapitalCity capitalCity = capitalCities.getFirst();
        assertEquals("Seoul", capitalCity.getName());
        assertEquals("South Korea", capitalCity.getCountry());
    }

    /**
     * Ensures proper exception handling for top-N capital city queries,
     * returning an empty list on SQL failure.
     *
     * @throws Exception simulated SQL error
     */
    @Test
    void getTopNPopulatedCapitalCities_Exception() throws Exception {
        Connection mockCon = mock(Connection.class);
        CapitalCityDAO dao = new CapitalCityDAO(mockCon);

        when(mockCon.prepareStatement(anyString()))
                .thenThrow(new SQLException("Simulated DB error"));

        List<CapitalCity> result = dao.getTopNPopulatedCapitalCities(10);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }


    /**
     * Tests retrieving the top N most populated capital cities by continent.
     * Verifies results are populated and ordered correctly by population.
     */
    @Test
    void getTopNPopulatedCapitalCitiesByContinent(){
        List<CapitalCity> capitalCities = capitalCityDAO.getTopNPopulatedCapitalCitiesByContinent("Asia", 10);

        assertNotNull(capitalCities);

        assertFalse(capitalCities.isEmpty());

        CapitalCity capitalCity = capitalCities.getFirst();
        assertEquals("Seoul", capitalCity.getName());
        assertEquals("South Korea", capitalCity.getCountry());
    }

    /**
     * Ensures continent-specific top-N capital city population query returns
     * a safe empty list when a SQL exception occurs.
     *
     * @throws Exception simulated DB error
     */
    @Test
    void getTopNPopulatedCapitalCitiesByContinent_Exception() throws Exception {
        Connection mockCon = mock(Connection.class);
        CapitalCityDAO dao = new CapitalCityDAO(mockCon);

        when(mockCon.prepareStatement(anyString()))
                .thenThrow(new SQLException("Simulated DB error"));

        List<CapitalCity> result = dao.getTopNPopulatedCapitalCitiesByContinent("Europe", 5);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    /**
     * Tests retrieving the top N most populated capital cities by region.
     * Confirms valid list and correct data mapping against dataset.
     */
    @Test
    void getTopNPopulatedCapitalCitiesByRegion(){
        List<CapitalCity> capitalCities = capitalCityDAO.getTopNPopulatedCapitalCitiesByRegion("Central Africa", 10);

        assertNotNull(capitalCities);

        assertFalse(capitalCities.isEmpty());

        CapitalCity capitalCity = capitalCities.getFirst();
        assertEquals("Kinshasa", capitalCity.getName());
        assertEquals("Congo, The Democratic Republic of the", capitalCity.getCountry());
    }

    /**
     * Ensures that regional top-N capital city population queries do not crash
     * and return an empty list when SQL errors occur.
     *
     * @throws Exception simulated SQL error
     */
    @Test
    void getTopNPopulatedCapitalCitiesByRegion_Exception() throws Exception {
        Connection mockCon = mock(Connection.class);
        CapitalCityDAO dao = new CapitalCityDAO(mockCon);

        when(mockCon.prepareStatement(anyString()))
                .thenThrow(new SQLException("Simulated DB error"));

        List<CapitalCity> result = dao.getTopNPopulatedCapitalCitiesByRegion("Caribbean", 3);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }


    /**
     * Tests population statistics for each continent including urban and non-urban counts.
     * Ensures result list contains correctly mapped data.
     */
    @Test
    void getEachContinentPopulationWithUrbanAndNonUrban(){
        List<Population> populations = populationDAO.getEachContinentPopulationWithUrbanAndNonUrban();

        assertNotNull(populations);

        assertFalse(populations.isEmpty());

        Population population = populations.getFirst();
        assertEquals("Asia", population.getName());
        assertEquals(3705025700L, population.getTotalPopulation());
    }

    /**
     * Verifies graceful handling of SQL errors in continent population breakdown queries
     * by returning an empty list.
     *
     * @throws Exception simulated DB failure
     */
    @Test
    void getEachContinentPopulationWithUrbanAndNonUrban_Exception() throws Exception {
        Connection mockCon = mock(Connection.class);
        PopulationDAO dao = new PopulationDAO(mockCon);

        when(mockCon.prepareStatement(anyString()))
                .thenThrow(new SQLException("Simulated DB error"));

        List<Population> result = dao.getEachContinentPopulationWithUrbanAndNonUrban();

        assertNotNull(result);
        assertTrue(result.isEmpty());  // Expected due to exception
    }

    /**
     * Tests regional population statistics including breakdown of urban vs non-urban.
     * Ensures valid values are returned matching expected dataset.
     */
    @Test
    void getEachRegionPopulationWithUrbanAndNonUrban(){
        List<Population> populations = populationDAO.getEachRegionPopulationWithUrbanAndNonUrban();

        assertNotNull(populations);

        assertFalse(populations.isEmpty());

        Population population = populations.getFirst();
        assertEquals("Antarctica", population.getName());
        assertEquals(0, population.getTotalPopulation());
    }

    /**
     * Ensures region population breakdown queries return an empty result when a SQL
     * exception occurs, preserving application stability.
     *
     * @throws Exception simulated SQL error
     */
    @Test
    void getEachRegionPopulationWithUrbanAndNonUrban_Exception() throws Exception {
        Connection mockCon = mock(Connection.class);
        PopulationDAO dao = new PopulationDAO(mockCon);

        when(mockCon.prepareStatement(anyString()))
                .thenThrow(new SQLException("Simulated DB error"));

        List<Population> result = dao.getEachRegionPopulationWithUrbanAndNonUrban();

        assertNotNull(result);
        assertTrue(result.isEmpty());  // Expected due to exception
    }

    /**
     * Tests country-level population statistics with urban and non-urban population.
     * Confirms correct mapping and non-empty results.
     */
    @Test
    void getEachCountryPopulationWithUrbanAndNonUrban(){
        List<Population> populations = populationDAO.getEachCountryPopulationWithUrbanAndNonUrban();

        assertNotNull(populations);

        assertFalse(populations.isEmpty());

        Population population = populations.getFirst();
        assertEquals("Afghanistan", population.getName());
        assertEquals(22720000, population.getTotalPopulation());
    }

    /**
     * Confirms that a SQL failure during country population breakdown queries
     * results in an empty list, not an exception.
     *
     * @throws Exception simulated SQL error
     */
    @Test
    void getEachCountryPopulationWithUrbanAndNonUrban_Exception() throws Exception {
        Connection mockCon = mock(Connection.class);
        PopulationDAO dao = new PopulationDAO(mockCon);

        when(mockCon.prepareStatement(anyString()))
                .thenThrow(new SQLException("Simulated DB error"));

        List<Population> result = dao.getEachCountryPopulationWithUrbanAndNonUrban();

        assertNotNull(result);
        assertTrue(result.isEmpty());  // Expected due to exception
    }

    /**
     * Verifies that the method retrieves all cities in the world ordered by population.
     * Ensures result list is non-empty and the highest populated city data is correct.
     */
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

    /**
     * Ensures that SQL exceptions during world-city retrieval result in an empty list
     * rather than failing the method execution.
     *
     * @throws Exception simulated SQL failure
     */
    @Test
    void getAllinWorldCitiesByPopulation_Exception() throws Exception {
        // Mock the DB connection
        Connection mockCon = mock(Connection.class);

        // Inject the mocked connection into the DAO
        CityDAO cityDAO = new CityDAO(mockCon);

        // Force the prepareStatement() call to fail
        when(mockCon.prepareStatement(anyString())).thenThrow(new SQLException("Simulated DB error"));

        // Call the method
        List<City> cities = cityDAO.getAllinWorldCitiesByPopulation();

        // Assertions
        assertNotNull(cities);        // Method must still return a list
        assertTrue(cities.isEmpty()); // It should be empty due to the exception
    }

    /**
     * Validates retrieval and ordering of cities by population within a continent.
     * Confirms the highest populated city fields are accurate.
     */
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

    /**
     * Confirms safe handling of database errors when retrieving continent-based city lists.
     * Returns an empty list instead of throwing an exception.
     *
     * @throws Exception simulated SQL error
     */
    @Test
    void getAllCitiesInContinentByPopulation_Exception() throws Exception {
        // Mock the DB connection
        Connection mockCon = mock(Connection.class);

        // Inject the mocked connection into the DAO
        CityDAO cityDAO = new CityDAO(mockCon);

        // Force the prepareStatement() call to fail
        when(mockCon.prepareStatement(anyString())).thenThrow(new SQLException("Simulated DB error"));

        // Call the method
        List<City> cities = cityDAO.getAllCitiesInContinentByPopulation("Asia");

        // Assertions
        assertNotNull(cities);        // Method must still return a list
        assertTrue(cities.isEmpty()); // It should be empty due to the exception
    }

    /**
     * Tests retrieval of city populations in a specific region ordered by population.
     * Ensures the result is valid and data integrity is maintained for a key element.
     */
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

    /**
     * Ensures resilience during SQL failure when querying cities by region, returning
     * an empty list while keeping system stability.
     *
     * @throws Exception simulated SQL error
     */
    @Test
    void getAllCitiesInRegionByPopulation_Exception() throws Exception {
        // Mock the DB connection
        Connection mockCon = mock(Connection.class);

        // Inject the mocked connection into the DAO
        CityDAO cityDAO = new CityDAO(mockCon);

        // Force the prepareStatement() call to fail
        when(mockCon.prepareStatement(anyString())).thenThrow(new SQLException("Simulated DB error"));

        // Call the method
        List<City> cities = cityDAO.getAllCitiesInRegionByPopulation("Central");

        // Assertions
        assertNotNull(cities);        // Method must still return a list
        assertTrue(cities.isEmpty()); // It should be empty due to the exception
    }

    /**
     * Validates fetching all cities within a specific country ordered by population.
     * Confirms proper data mapping of the most populated city.
     */
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

    /**
     * Ensures the country-level city population query remains fail-safe by returning
     * an empty list during SQL exception scenarios.
     *
     * @throws Exception simulated SQL failure
     */
    @Test
    void getAllCitiesInCountryByPopulation_Exception() throws Exception {
        // Mock the DB connection
        Connection mockCon = mock(Connection.class);

        // Inject the mocked connection into the DAO
        CityDAO cityDAO = new CityDAO(mockCon);

        // Force the prepareStatement() call to fail
        when(mockCon.prepareStatement(anyString())).thenThrow(new SQLException("Simulated DB error"));

        // Call the method
        List<City> cities = cityDAO.getAllCitiesInCountryByPopulation("Argentina");

        // Assertions
        assertNotNull(cities);        // Method must still return a list
        assertTrue(cities.isEmpty()); // It should be empty due to the exception
    }

    /**
     * Tests retrieval of cities within a district ordered by population.
     * Verifies correct population ranking and city attribute mapping.
     */
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

    /**
     * Confirms SQL errors in district-based city queries do not propagate but yield
     * an empty list as expected.
     *
     * @throws Exception simulated SQL error
     */
    @Test
    void getAllCitiesInDistrictByPopulation_Exception() throws Exception {
        // Mock the DB connection
        Connection mockCon = mock(Connection.class);

        // Inject the mocked connection into the DAO
        CityDAO cityDAO = new CityDAO(mockCon);

        // Force the prepareStatement() call to fail
        when(mockCon.prepareStatement(anyString())).thenThrow(new SQLException("Simulated DB error"));

        // Call the method
        List<City> cities = cityDAO.getAllCitiesInDistrictByPopulation("Benguela");

        // Assertions
        assertNotNull(cities);        // Method must still return a list
        assertTrue(cities.isEmpty()); // It should be empty due to the exception
    }

    /**
     * Tests retrieving the top N populated cities globally.
     * Ensures correct size constraints and proper descending-ordered city data.
     */
    @Test
    void getTopNPopulatedCitiesInWorld() {
        int n = 5;
        List<City> cities = cityDAO.getTopNPopulatedCitiesInWorld(n);

        assertNotNull(cities);
        assertFalse(cities.isEmpty());
        assertTrue(cities.size() <= n);

        City city = cities.get(0);
        assertEquals("Mumbai (Bombay)", city.getName());
        assertEquals("India", city.getCountry());
        assertEquals("Maharashtra", city.getDistrict());
        assertEquals(10500000L, city.getPopulation());
    }

    /**
     * Ensures robustness of top-N world city query under SQL failure by returning
     * an empty list instead of crashing execution.
     *
     * @throws Exception simulated SQL error
     */
    @Test
    void getTopNPopulatedCitiesInWorld_Exception() throws Exception {
        // Mock the DB connection
        Connection mockCon = mock(Connection.class);

        // Inject the mocked connection into the DAO
        CityDAO cityDAO = new CityDAO(mockCon);

        // Force the prepareStatement() call to fail
        when(mockCon.prepareStatement(anyString())).thenThrow(new SQLException("Simulated DB error"));

        // Call the method
        List<City> cities = cityDAO.getTopNPopulatedCitiesInWorld(5);

        // Assertions
        assertNotNull(cities);        // Method must still return a list
        assertTrue(cities.isEmpty()); // It should be empty due to the exception
    }

    /**
     * Verifies retrieval of the top N populated cities within a continent.
     * Validates ranking logic and correct city attribute values for the top result.
     */
    @Test
    void getTopNPopulatedCitiesInContinent() {
        int n = 5;
        String continent = "Asia";
        List<City> cities = cityDAO.getTopNPopulatedCitiesInContinent(continent, n);

        assertNotNull(cities);
        assertFalse(cities.isEmpty());
        assertTrue(cities.size() <= n);

        City city = cities.get(0);
        assertEquals("Mumbai (Bombay)", city.getName());
        assertEquals("India", city.getCountry());
        assertEquals("Maharashtra", city.getDistrict());
        assertEquals(10500000L, city.getPopulation());
    }

    /**
     * Ensures continent-based top-N population queries fail gracefully by returning
     * an empty list when the database throws an exception.
     *
     * @throws Exception simulated SQL failure
     */
    @Test
    void getTopNPopulatedCitiesInContinent_Exception() throws Exception {
        // Mock the DB connection
        Connection mockCon = mock(Connection.class);

        // Inject the mocked connection into the DAO
        CityDAO cityDAO = new CityDAO(mockCon);

        // Force the prepareStatement() call to fail
        when(mockCon.prepareStatement(anyString())).thenThrow(new SQLException("Simulated DB error"));

        // Call the method
        List<City> cities = cityDAO.getTopNPopulatedCitiesInContinent("Asia", 5);

        // Assertions
        assertNotNull(cities);        // Method must still return a list
        assertTrue(cities.isEmpty()); // It should be empty due to the exception
    }

    /**
     * Tests retrieval of the top N populated cities inside a specific region.
     * Confirms valid ordering and top-city details integrity.
     */
    @Test
    void getTopNPopulatedCitiesInRegion() {
        int n = 5;
        String region = "Eastern Europe";
        List<City> cities = cityDAO.getTopNPopulatedCitiesInRegion(region, n);

        assertNotNull(cities);
        assertFalse(cities.isEmpty());
        assertTrue(cities.size() <= n);

        City city = cities.get(0);
        assertEquals("Moscow", city.getName());
        assertEquals("Russian Federation", city.getCountry());
        assertEquals("Moscow (City)", city.getDistrict());
        assertEquals(8389200L, city.getPopulation());
    }

    /**
     * Validates graceful error handling of region-based top-N city queries by
     * returning an empty list during SQL exceptions.
     *
     * @throws Exception simulated SQL error
     */
    @Test
    void getTopNPopulatedCitiesInRegion_Exception() throws Exception {
        // Mock the DB connection
        Connection mockCon = mock(Connection.class);

        // Inject the mocked connection into the DAO
        CityDAO cityDAO = new CityDAO(mockCon);

        // Force the prepareStatement() call to fail
        when(mockCon.prepareStatement(anyString())).thenThrow(new SQLException("Simulated DB error"));

        // Call the method
        List<City> cities = cityDAO.getTopNPopulatedCitiesInRegion("Asia", 5);

        // Assertions
        assertNotNull(cities);        // Method must still return a list
        assertTrue(cities.isEmpty()); // It should be empty due to the exception
    }

    /**
     * Tests retrieving the top N populated cities of a specific country.
     * Ensures accuracy in returned top-ranked city data.
     */
    @Test
    void getTopNPopulatedCitiesInCountry() {
        int n = 5;
        String country = "Brazil";
        List<City> cities = cityDAO.getTopNPopulatedCitiesInCountry(country, n);

        assertNotNull(cities);
        assertFalse(cities.isEmpty());
        assertTrue(cities.size() <= n);

        City city = cities.get(0);
        assertEquals("São Paulo", city.getName());
        assertEquals("Brazil", city.getCountry());
        assertEquals("São Paulo", city.getDistrict());
        assertEquals(9968485L, city.getPopulation());
    }

    /**
     * Confirms that country-based top-N city queries remain fail-safe with
     * empty list returns on database errors.
     *
     * @throws Exception simulated SQL error
     */
    @Test
    void getTopNPopulatedCitiesInCountry_Exception() throws Exception {
        // Mock the DB connection
        Connection mockCon = mock(Connection.class);

        // Inject the mocked connection into the DAO
        CityDAO cityDAO = new CityDAO(mockCon);

        // Force the prepareStatement() call to fail
        when(mockCon.prepareStatement(anyString())).thenThrow(new SQLException("Simulated DB error"));

        // Call the method
        List<City> cities = cityDAO.getTopNPopulatedCitiesInCountry("Brazil", 5);

        // Assertions
        assertNotNull(cities);        // Method must still return a list
        assertTrue(cities.isEmpty()); // It should be empty due to the exception
    }

    /**
     * Tests retrieval of the top N populated cities in a single district.
     * Ensures correct city-ordering and top result content validity.
     */
    @Test
    void getTopNPopulatedCitiesInDistrict() {
        int n = 5;
        String district = "California";
        List<City> cities = cityDAO.getTopNPopulatedCitiesInDistrict(district, n);

        assertNotNull(cities);
        assertFalse(cities.isEmpty());
        assertTrue(cities.size() <= n);

        City city = cities.get(0);
        assertEquals("Los Angeles", city.getName());
        assertEquals("United States", city.getCountry());
        assertEquals("California", city.getDistrict());
        assertEquals(3694820L, city.getPopulation());
    }

    /**
     * Ensures SQL failures in district top-N retrieval scenarios result in empty output
     * while maintaining method contract compliance.
     *
     * @throws Exception simulated SQL failure
     */
    @Test
    void getTopNPopulatedCitiesInDistrict_Exception() throws Exception {
        // Mock the DB connection
        Connection mockCon = mock(Connection.class);

        // Inject the mocked connection into the DAO
        CityDAO cityDAO = new CityDAO(mockCon);

        // Force the prepareStatement() call to fail
        when(mockCon.prepareStatement(anyString())).thenThrow(new SQLException("Simulated DB error"));

        // Call the method
        List<City> cities = cityDAO.getTopNPopulatedCitiesInDistrict("California", 5);

        // Assertions
        assertNotNull(cities);        // Method must still return a list
        assertTrue(cities.isEmpty()); // It should be empty due to the exception
    }

    }