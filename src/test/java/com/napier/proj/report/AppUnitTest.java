package com.napier.proj.report;

import com.napier.proj.App;
import com.napier.proj.dao.*;
import com.napier.proj.model.Country;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AppUnitTest {

    static CountryDAO countryDAO;
    static CountryReport countryReport;

    static CityDAO cityDAO;
    static CityReport cityReport;

    static CapitalCityDAO capitalCityDAO;
    static CapitalCityReport capitalCityReport;

    static PopulationDAO populationDAO;
    static PopulationReport populationReport;

    static LanguageDAO languageDAO;
    static LanguageReport languageReport;


    @BeforeEach
    void setUp() {
        countryDAO = Mockito.mock(CountryDAO.class);
        countryReport = new CountryReport(countryDAO);

        cityDAO = Mockito.mock(CityDAO.class);
        cityReport = new CityReport(cityDAO);

        capitalCityDAO = Mockito.mock(CapitalCityDAO.class);
        capitalCityReport = new CapitalCityReport(capitalCityDAO);

        populationDAO = Mockito.mock(PopulationDAO.class);
        populationReport = new PopulationReport(populationDAO);

        languageDAO = Mockito.mock(LanguageDAO.class);
        languageReport = new LanguageReport(languageDAO);
    }

    @AfterEach
    void tearDown() {
    }

    // Unit Testings for Country Reports
    @Test
    void printAllCountriesByPopulation() {
        Country c1 = new Country();
        c1.setCode("ABW");
        c1.setName("Aruba");
        c1.setContinent("North America");
        c1.setRegion("Caribbean");
        c1.setPopulation(103000);
        c1.setCapital("America");

        ArrayList<Country> mockCountries = new ArrayList<>();
        mockCountries.add(c1);

        Mockito.when(countryDAO.getAllCountriesByPopulation()).thenReturn(mockCountries);

        assertDoesNotThrow(() -> countryReport.printAllCountriesByPopulation());
    }

    @Test
    void printAllCountriesInContinentByPopulation() {

    }

    @Test
    void printAllCountriesInRegionByPopulation() {
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


    // Unit Testings for City Reports

    // Unit Testings for Capital City Reports

    // Unit Testings for Population Reports

    // Unit Testings for Language Reports

}