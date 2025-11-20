package com.napier.proj.dao;

import com.napier.proj.App;
import com.napier.proj.config.DatabaseConfig;
import com.napier.proj.model.CapitalCity;
import com.napier.proj.model.City;
import com.napier.proj.model.Country;
import com.napier.proj.model.Language;
import com.napier.proj.model.Population;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppIntegrationTest {

    static Connection conn;
    static CountryDAO countryDAO;
    static CityDAO cityDAO;

    static PopulationDAO populationDAO;
    static LanguageDAO languageDAO;

    static CapitalCityDAO capitalCityDAO;


    @Test
    void testConnection() {
        assertNotNull(conn, "Connection should not be null");

        try {
            assertTrue(conn.isValid(5), "Connection should be valid");
        } catch (Exception e) {
            fail("Connection is not valid: " + e.getMessage());
        }
    }

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


    //Testings for Population DAO
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

    @Test
    void getCountryPopulation(){
        List<Population> populations = populationDAO.getCountryPopulation("Argentina");

        assertNotNull(populations);
        assertFalse(populations.isEmpty());

        Population population = populations.getFirst();

        assertEquals("Argentina", population.getName());
        assertEquals(37032000, population.getTotalPopulation()); // From world.sql dataset

    }

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

    //Testing for LanguageDAO
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

    // Test Population
    @Test
    void getEachContinentPopulationWithUrbanAndNonUrban(){
        List<Population> populations = populationDAO.getEachContinentPopulationWithUrbanAndNonUrban();

        assertNotNull(populations);

        assertFalse(populations.isEmpty());

        Population population = populations.getFirst();
        assertEquals("Asia", population.getName());
        assertEquals(3705025700L, population.getTotalPopulation());
    }

    @Test
    void getEachRegionPopulationWithUrbanAndNonUrban(){
        List<Population> populations = populationDAO.getEachRegionPopulationWithUrbanAndNonUrban();

        assertNotNull(populations);

        assertFalse(populations.isEmpty());

        Population population = populations.getFirst();
        assertEquals("Antarctica", population.getName());
        assertEquals(0, population.getTotalPopulation());
    }

    @Test
    void getEachCountryPopulationWithUrbanAndNonUrban(){
        List<Population> populations = populationDAO.getEachCountryPopulationWithUrbanAndNonUrban();

        assertNotNull(populations);

        assertFalse(populations.isEmpty());

        Population population = populations.getFirst();
        assertEquals("Afghanistan", population.getName());
        assertEquals(22720000, population.getTotalPopulation());

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

    // Integration Testings for Top N City DAO
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
    }