package com.napier.proj;

import com.napier.proj.config.DatabaseConfig;
import com.napier.proj.dao.CityDAO;
import com.napier.proj.dao.CountryDAO;
import com.napier.proj.dao.LanguageDAO;
import com.napier.proj.dao.PopulationDAO;
import com.napier.proj.report.CityReport;
import com.napier.proj.report.CountryReport;
import com.napier.proj.report.LanguageReport;
import com.napier.proj.report.PopulationReport;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {


        DatabaseConfig.openConnection();
        Connection conn = DatabaseConfig.getConnection();

//        System.out.println("Now, it's time to start project");


        // method call for dao object
        CountryDAO countryDAO = new CountryDAO(conn);
//        CityDAO cityDAO = new CityDAO(conn);
//        LanguageDAO languageDAO = new LanguageDAO(conn);
//        PopulationDAO populationDAO = new PopulationDAO(conn);

        CountryReport countryReport = new CountryReport(countryDAO);
//        CityReport cityReport = new CityReport(cityDAO);
//        LanguageReport languageReport = new LanguageReport(languageDAO);
//        PopulationReport populationReport = new PopulationReport(populationDAO);

        // *** Country Report ***
        // All countries in the world
        countryReport.printAllCountriesByPopulation();
        // All countries in a continent
        countryReport.printAllCountriesInContinentByPopulation("Europe");




        DatabaseConfig.closeConnection();

    }
}
