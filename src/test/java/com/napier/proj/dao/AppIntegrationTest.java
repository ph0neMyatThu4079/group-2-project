package com.napier.proj.dao;

import com.napier.proj.App;
import com.napier.proj.config.DatabaseConfig;
import com.napier.proj.model.CapitalCity;
import com.napier.proj.model.Country;
<<<<<<< HEAD
import com.napier.proj.model.Language;
=======
>>>>>>> feature/capitalcity-reports
import com.napier.proj.model.Population;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AppIntegrationTest {

    static Connection conn;
    static CountryDAO countryDAO;
<<<<<<< HEAD
    static PopulationDAO populationDAO;
    static LanguageDAO languageDAO;
=======
    static CapitalCityDAO capitalCityDAO;
    static PopulationDAO populationDAO;
>>>>>>> feature/capitalcity-reports

    @BeforeAll
    static void setup()
    {
        // Open real DB connection
        DatabaseConfig.openConnection("localhost:33060", 30000);
        conn = DatabaseConfig.getConnection();

        assertNotNull(conn);

        countryDAO = new CountryDAO(conn);
<<<<<<< HEAD
        populationDAO = new PopulationDAO(conn);
        languageDAO = new LanguageDAO(conn);
=======
        capitalCityDAO = new CapitalCityDAO(conn);
        populationDAO = new PopulationDAO(conn);
>>>>>>> feature/capitalcity-reports
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

<<<<<<< HEAD

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

=======
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
>>>>>>> feature/capitalcity-reports
    }
}