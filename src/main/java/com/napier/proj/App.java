package com.napier.proj;

import com.napier.proj.config.DatabaseConfig;
import com.napier.proj.dao.*;
import com.napier.proj.report.*;

import java.sql.Connection;

/**
 *  The App class establishes a database connection, initializes all DAO
 *  (Data Access Object) and report classes, and triggers the execution of
 *  every required report for countries, cities, capital cities, populations,
 *  and languages.
 * @author Lin Myat Thu
 * @author Phone Myat Thu
 * @author Min Wanna Hlan
 * @author So Pyay Tun
 * @author Ingyin Thwe
 */
public class App {
    public static void main(String[] args) {


        if(args.length < 1){
            DatabaseConfig.openConnection("localhost:33060", 30000);
        }
        else{
            DatabaseConfig.openConnection(args[0], Integer.parseInt(args[1]));
        }
        Connection conn = DatabaseConfig.getConnection();


        // method call for dao object
        CountryDAO countryDAO = new CountryDAO(conn);
        CityDAO cityDAO = new CityDAO(conn);
        CapitalCityDAO capitalCityDAO = new CapitalCityDAO(conn);
        PopulationDAO populationDAO = new PopulationDAO(conn);
        LanguageDAO languageDAO = new LanguageDAO(conn);

        CountryReport countryReport = new CountryReport(countryDAO);
        CityReport cityReport = new CityReport(cityDAO);
        CapitalCityReport  capitalCityReport = new CapitalCityReport(capitalCityDAO);
        PopulationReport populationReport = new PopulationReport(populationDAO);
        LanguageReport languageReport = new LanguageReport(languageDAO);


        // *** Country Reports ***
        // All countries in the world
        countryReport.printAllCountriesByPopulation();
        // All countries in a continent
        countryReport.printAllCountriesInContinentByPopulation("Europe");
        // All countries in a region
        countryReport.printAllCountriesInRegionByPopulation("Southern and Central Asia");
        // Top N countries in the world
        countryReport.getTopNPopulatedCountriesIntheworld(10);
        // Top N countries in the continent
        countryReport.getTopNPopulatedCountriesInContinent("North America",10);
        // Top N countries in the region
        countryReport.getTopNPopulatedCountriesInRegion("Middle East",10);



        // *** City Reports ***
        cityReport.printAllCitiesInWorldByPopulation();
        cityReport.printAllCitiesInContinentByPopulation("Asia");
        cityReport.printAllCitiesInRegionByPopulation("Central Africa");
        cityReport.printAllCitiesInCountryByPopulation("Argentina");
        cityReport.printAllCitiesInDistrictByPopulation("Benguela");
        // *** Top N Populated City Reports ***
        cityReport.printTopNPopulatedCitiesInWorld(10);
        cityReport.printTopNPopulatedCitiesInContinent("Asia", 10);
        cityReport.printTopNPopulatedCitiesInRegion("Eastern Europe", 10);
        cityReport.printTopNPopulatedCitiesInCountry("Brazil", 10);
        cityReport.printTopNPopulatedCitiesInDistrict("California", 10);



        // *** Capital Cities Reports **
        capitalCityReport.printAllCapitalCities();
        capitalCityReport.printAllCapitalCitiesInContinent("Asia");
        capitalCityReport.printAllCapitalCitiesInRegion("Caribbean");
        capitalCityReport.printTopNPopulatedCapitalCities(10);
        capitalCityReport.printTopNPopulatedCapitalCitiesInContinent("Asia", 10);
        capitalCityReport.printTopNPopulatedCapitalCitiesInRegion("Central Africa", 10);

        // *** Population Reports ***
        populationReport.printEachContinentPopulationWithUrbanAndNonUrban();
        populationReport.printEachRegionPopulationWithUrbanAndNonUrban();
        populationReport.printEachCountryPopulationWithUrbanAndNonUrban();
        populationReport.printWorldPopulation();
        populationReport.printContinentPopulation("Asia");
        populationReport.printRegionPopulation("Central Africa");
        populationReport.printCountryPopulation("Argentina");
        populationReport.printDistrictPopulation("Benguela");
        populationReport.printCityPopulation("Tokyo");

        // *** Language Reports ***
        languageReport.printMajorLanguageReport();




        DatabaseConfig.closeConnection();

    }
}
