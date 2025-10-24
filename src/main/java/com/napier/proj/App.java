package com.napier.proj;

import com.napier.proj.config.DatabaseConfig;
import com.napier.proj.dao.*;
import com.napier.proj.report.*;

import java.sql.Connection;

/**
 * The main entry point for the Population and Geography Reporting System.
 * <p>
 * This class initializes the database connection, creates Data Access Object (DAO)
 * instances, and generates various reports such as Country, City, Capital City,
 * and Population reports.
 * </p>
 *
 * <p>
 * It demonstrates how the DAO and Report classes work together to fetch and display
 * data from the world database in different categories and population contexts.
 * </p>
 *
 * @author Phone Myat Thu
 * @author Lin Myat Thu
 * @author So Pyay Tun
 * @author Min Wanna Hlan
 * @author Ingyin Thwe
 */
public class App {

    /**
     * The main method of the application.
     * <p>
     * It establishes the database connection, initializes DAO and report classes,
     * and prints different reports such as country, city, capital city, and population
     * distributions.
     * </p>
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        // Open database connection
        DatabaseConfig.openConnection();
        Connection conn = DatabaseConfig.getConnection();


        // Initialize DAO objects
        CountryDAO countryDAO = new CountryDAO(conn);
        CityDAO cityDAO = new CityDAO(conn);
        CapitalCityDAO capitalCityDAO = new CapitalCityDAO(conn);
        PopulationDAO populationDAO = new PopulationDAO(conn);
//        LanguageDAO languageDAO = new LanguageDAO(conn);

        // Initialize Report objects
        CountryReport countryReport = new CountryReport(countryDAO);
        CityReport cityReport = new CityReport(cityDAO);
        CapitalCityReport  capitalCityReport = new CapitalCityReport(capitalCityDAO);
        PopulationReport populationReport = new PopulationReport(populationDAO);
//        LanguageReport languageReport = new LanguageReport(languageDAO);

        // *** Population Reports ***
        populationReport.printEachContinentPopulationWithUrbanAndNonUrban();
        populationReport.printEachRegionPopulationWithUrbanAndNonUrban();
        populationReport.printEachCountryPopulationWithUrbanAndNonUrban();


        // *** Country Reports ***
        countryReport.printAllCountriesByPopulation();
        countryReport.printAllCountriesInContinentByPopulation("Europe");
        countryReport.printAllCountriesInRegionByPopulation("Southern and Central Asia");
        countryReport.getTopNPopulatedCountriesIntheworld(10);
        countryReport.getTopNPopulatedCountriesInContinent("North America",10);
        countryReport.getTopNPopulatedCountriesInRegion("Middle East",10);

        // *** City Reports ***
        cityReport.printAllCitiesInWorldByPopulation();
        cityReport.printAllCitiesInContinentByPopulation("Asia");
        cityReport.printAllCitiesInRegionByPopulation("Central Africa");
        cityReport.printAllCitiesInCountryByPopulation("Argentina");
        cityReport.printAllCitiesInDistrictByPopulation("Benguela");

        // *** Capital Cities Reports **
        capitalCityReport.printAllCapitalCities();
        capitalCityReport.printAllCapitalCitiesInContinent("Asia");
        capitalCityReport.printAllCapitalCitiesInRegion("Caribbean");
        capitalCityReport.printTopNPopulatedCapitalCities(10);
        capitalCityReport.printTopNPopulatedCapitalCitiesInContinent("Asia", 10);
        capitalCityReport.printTopNPopulatedCapitalCitiesInRegion("Central Africa", 10);

        // Close database connection
        DatabaseConfig.closeConnection();

    }
}
