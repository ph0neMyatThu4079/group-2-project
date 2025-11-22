package com.napier.proj.dao;

import com.napier.proj.config.DatabaseConfig;
import com.napier.proj.report.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReportIntegrationTest {
    static Connection conn;
    static CountryReport countryReport;
    static CityReport cityReport;
    static CapitalCityReport capitalCityReport;
    static PopulationReport populationReport;
    static LanguageReport languageReport;

    @BeforeAll
    static void setup() {
        DatabaseConfig.openConnection("localhost:33060", 30000);
        conn = DatabaseConfig.getConnection();
        assertNotNull(conn);

        // DAOs
        CountryDAO countryDAO = new CountryDAO(conn);
        CityDAO cityDAO = new CityDAO(conn);
        CapitalCityDAO capitalCityDAO = new CapitalCityDAO(conn);
        PopulationDAO populationDAO = new PopulationDAO(conn);
        LanguageDAO languageDAO = new LanguageDAO(conn);

        // Reports
        countryReport = new CountryReport(countryDAO);
        cityReport = new CityReport(cityDAO);
        capitalCityReport = new CapitalCityReport(capitalCityDAO);
        populationReport = new PopulationReport(populationDAO);
        languageReport = new LanguageReport(languageDAO);
    }

    @Test
    void testCountryReports() {
        countryReport.printAllCountriesByPopulation();
        countryReport.printAllCountriesInContinentByPopulation("Europe");
        countryReport.printAllCountriesInRegionByPopulation("Southern and Central Asia");
        countryReport.getTopNPopulatedCountriesIntheworld(10);
        countryReport.getTopNPopulatedCountriesInContinent("North America",10);
        countryReport.getTopNPopulatedCountriesInRegion("Middle East",10);
    }

    @Test
    void testCityReports() {
        cityReport.printAllCitiesInWorldByPopulation();
        cityReport.printAllCitiesInContinentByPopulation("Asia");
        cityReport.printAllCitiesInRegionByPopulation("Central Africa");
        cityReport.printAllCitiesInCountryByPopulation("Argentina");
        cityReport.printAllCitiesInDistrictByPopulation("Benguela");

        cityReport.printTopNPopulatedCitiesInWorld(10);
        cityReport.printTopNPopulatedCitiesInContinent("Asia", 10);
        cityReport.printTopNPopulatedCitiesInRegion("Eastern Europe", 10);
        cityReport.printTopNPopulatedCitiesInCountry("Brazil", 10);
        cityReport.printTopNPopulatedCitiesInDistrict("California", 10);
    }

    @Test
    void testCapitalCityReports() {
        capitalCityReport.printAllCapitalCities();
        capitalCityReport.printAllCapitalCitiesInContinent("Asia");
        capitalCityReport.printAllCapitalCitiesInRegion("Caribbean");
        capitalCityReport.printTopNPopulatedCapitalCities(10);
        capitalCityReport.printTopNPopulatedCapitalCitiesInContinent("Asia", 10);
        capitalCityReport.printTopNPopulatedCapitalCitiesInRegion("Central Africa", 10);
    }

    @Test
    void testPopulationReports() {
        populationReport.printEachContinentPopulationWithUrbanAndNonUrban();
        populationReport.printEachRegionPopulationWithUrbanAndNonUrban();
        populationReport.printEachCountryPopulationWithUrbanAndNonUrban();
        populationReport.printWorldPopulation();
        populationReport.printContinentPopulation("Asia");
        populationReport.printRegionPopulation("Central Africa");
        populationReport.printCountryPopulation("Argentina");
        populationReport.printDistrictPopulation("Benguela");
        populationReport.printCityPopulation("Tokyo");
    }

    @Test
    void testLanguageReports() {
        languageReport.printMajorLanguageReport();
    }
}
