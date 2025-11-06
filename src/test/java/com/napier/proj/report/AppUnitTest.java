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

        // Case 1 — Valid list with 1 country
        ArrayList<Country> mockCountries = new ArrayList<>();
        mockCountries.add(c1);

        Mockito.when(countryDAO.getAllCountriesByPopulation()).thenReturn(mockCountries);
        assertDoesNotThrow(() -> countryReport.printAllCountriesByPopulation());

        // Case 2 — Null list
        Mockito.when(countryDAO.getAllCountriesByPopulation()).thenReturn(null);
        assertDoesNotThrow(() -> countryReport.printAllCountriesByPopulation());

        // Case 3 — Empty list
        Mockito.when(countryDAO.getAllCountriesByPopulation()).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> countryReport.printAllCountriesByPopulation());

        // Case 4 — List with all null elements
        ArrayList<Country> listWithNull = new ArrayList<>();
        listWithNull.add(null);

        Mockito.when(countryDAO.getAllCountriesByPopulation()).thenReturn(listWithNull);
        assertDoesNotThrow(() -> countryReport.printAllCountriesByPopulation());
    }

    @Test
    void printAllCountriesInContinentByPopulation() {
        Country c1 = new Country();
        c1.setCode("ABW");
        c1.setName("Aruba");
        c1.setContinent("North America");
        c1.setRegion("Caribbean");
        c1.setPopulation(103000);
        c1.setCapital("America");

        // Case 1 — Valid list with 1 country
        ArrayList<Country> mockCountries = new ArrayList<>();
        mockCountries.add(c1);

        Mockito.when(countryDAO.getAllCountriesInContinentByPopulation("North America")).thenReturn(mockCountries);
        assertDoesNotThrow(() -> countryReport.printAllCountriesInContinentByPopulation("North America"));

        // Case 2 — Null list
        Mockito.when(countryDAO.getAllCountriesInContinentByPopulation("Asia")).thenReturn(null);
        assertDoesNotThrow(() -> countryReport.printAllCountriesInContinentByPopulation("Asia"));

        // Case 3 — Empty list
        Mockito.when(countryDAO.getAllCountriesInContinentByPopulation("Europe")).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> countryReport.printAllCountriesInContinentByPopulation("Europe"));

        // Case 4 — List with all null elements
        ArrayList<Country> listWithNull = new ArrayList<>();
        listWithNull.add(null);

        Mockito.when(countryDAO.getAllCountriesInContinentByPopulation("Africa")).thenReturn(listWithNull);
        assertDoesNotThrow(() -> countryReport.printAllCountriesInContinentByPopulation("Africa"));
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