package com.napier.proj.report;

import com.napier.proj.App;
import com.napier.proj.dao.*;
import com.napier.proj.model.CapitalCity;
import com.napier.proj.model.Country;


import com.napier.proj.model.Population;
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
    @Test
    void printAllCapitalCities() {
        CapitalCity cc = new CapitalCity();
        cc.setName("Tokyo");
        cc.setCountry("Japan");
        cc.setPopulation(7980230);

        // Case 1 — Valid list
        ArrayList<CapitalCity> mockCapitalCity = new ArrayList<>();
        mockCapitalCity.add(cc);

        Mockito.when(capitalCityDAO.getAllCapitalCities()).thenReturn(mockCapitalCity);
        assertDoesNotThrow(() -> capitalCityReport.printAllCapitalCities());

        // Case 2 — Null list
        Mockito.when(capitalCityDAO.getAllCapitalCities()).thenReturn(null);
        assertDoesNotThrow(() -> capitalCityReport.printAllCapitalCities());

        // Case 3 — Empty list
        Mockito.when(capitalCityDAO.getAllCapitalCities()).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> capitalCityReport.printAllCapitalCities());

        // Case 4 — List with all null elements
        ArrayList<CapitalCity> listWithNull = new ArrayList<>();
        listWithNull.add(null);

        Mockito.when(capitalCityDAO.getAllCapitalCities()).thenReturn(listWithNull);
        assertDoesNotThrow(() -> capitalCityReport.printAllCapitalCities());
    }

    @Test
    void printAllCapitalCitiesInContinent() {
        CapitalCity cc = new CapitalCity();
        cc.setName("Seoul");
        cc.setCountry("South Korea");
        cc.setPopulation(9981619);

        // Case 1 — Valid list
        ArrayList<CapitalCity> mockCapitalCity = new ArrayList<>();
        mockCapitalCity.add(cc);

        Mockito.when(capitalCityDAO.getAllCapitalCitiesInContinent("Asia")).thenReturn(mockCapitalCity);
        assertDoesNotThrow(() -> capitalCityReport.printAllCapitalCitiesInContinent("Asia"));

        // Case 2 — Null list
        Mockito.when(capitalCityDAO.getAllCapitalCitiesInContinent("Asia")).thenReturn(null);
        assertDoesNotThrow(() -> capitalCityReport.printAllCapitalCitiesInContinent("Asia"));

        // Case 3 — Empty list
        Mockito.when(capitalCityDAO.getAllCapitalCitiesInContinent("Asia")).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> capitalCityReport.printAllCapitalCitiesInContinent("Asia"));

        // Case 4 — List with all null elements
        ArrayList<CapitalCity> listWithNull = new ArrayList<>();
        listWithNull.add(null);

        Mockito.when(capitalCityDAO.getAllCapitalCitiesInContinent("Asia")).thenReturn(listWithNull);
        assertDoesNotThrow(() -> capitalCityReport.printAllCapitalCitiesInContinent("Asia"));
    }

    @Test
    void printAllCapitalCitiesInRegion() {
        CapitalCity cc1 = new CapitalCity();
        cc1.setName("Seoul");
        cc1.setCountry("South Korea");
        cc1.setPopulation(9981619);

        CapitalCity cc2 = new CapitalCity();
        cc2.setName("Tokyo");
        cc2.setCountry("Japan");
        cc2.setPopulation(7980230);

        // Case 1 — Valid list
        ArrayList<CapitalCity> mockCapitalCity = new ArrayList<>();
        mockCapitalCity.add(cc1);
        mockCapitalCity.add(cc2);

        Mockito.when(capitalCityDAO.getAllCapitalCitiesInRegion("Eastern Asia")).thenReturn(mockCapitalCity);
        assertDoesNotThrow(() -> capitalCityReport.printAllCapitalCitiesInRegion("Eastern Asia"));

        // Case 2 — Null list
        Mockito.when(capitalCityDAO.getAllCapitalCitiesInRegion("Eastern Asia")).thenReturn(null);
        assertDoesNotThrow(() -> capitalCityReport.printAllCapitalCitiesInRegion("Eastern Asia"));

        // Case 3 — Empty list
        Mockito.when(capitalCityDAO.getAllCapitalCitiesInRegion("Eastern Asia")).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> capitalCityReport.printAllCapitalCitiesInRegion("Eastern Asia"));

        // Case 4 — List with all null elements
        ArrayList<CapitalCity> listWithNull = new ArrayList<>();
        listWithNull.add(null);

        Mockito.when(capitalCityDAO.getAllCapitalCitiesInRegion("Eastern Asia")).thenReturn(listWithNull);
        assertDoesNotThrow(() -> capitalCityReport.printAllCapitalCitiesInRegion("Eastern Asia"));
    }

    @Test
    void printTopNPopulatedCapitalCities() {
        CapitalCity cc1 = new CapitalCity();
        cc1.setName("Seoul");
        cc1.setCountry("South Korea");
        cc1.setPopulation(9981619);

        CapitalCity cc2 = new CapitalCity();
        cc2.setName("Jakarta");
        cc2.setCountry("Indonesia ");
        cc2.setPopulation(9604900);

        // Case 1 — Valid list
        ArrayList<CapitalCity> mockCapitalCity = new ArrayList<>();
        mockCapitalCity.add(cc1);
        mockCapitalCity.add(cc2);

        Mockito.when(capitalCityDAO.getTopNPopulatedCapitalCities(2)).thenReturn(mockCapitalCity);
        assertDoesNotThrow(() -> capitalCityReport.printTopNPopulatedCapitalCities(2));

        // Case 2 — Null list
        Mockito.when(capitalCityDAO.getTopNPopulatedCapitalCities(2)).thenReturn(null);
        assertDoesNotThrow(() -> capitalCityReport.printTopNPopulatedCapitalCities(2));

        // Case 3 — Empty list
        Mockito.when(capitalCityDAO.getTopNPopulatedCapitalCities(2)).thenReturn(new  ArrayList<>());
        assertDoesNotThrow(() -> capitalCityReport.printTopNPopulatedCapitalCities(2));

        // Case 4 — List with all null elements
        ArrayList<CapitalCity> listWithNull = new ArrayList<>();
        listWithNull.add(null);

        Mockito.when(capitalCityDAO.getTopNPopulatedCapitalCities(2)).thenReturn(listWithNull);
        assertDoesNotThrow(() -> capitalCityReport.printTopNPopulatedCapitalCities(2));
    }

    @Test
    void printTopNPopulatedCapitalCitiesInContinent() {
        CapitalCity cc1 = new CapitalCity();
        cc1.setName("Seoul");
        cc1.setCountry("South Korea");
        cc1.setPopulation(9981619);

        CapitalCity cc2 = new CapitalCity();
        cc2.setName("Jakarta");
        cc2.setCountry("Indonesia ");
        cc2.setPopulation(9604900);

        // Case 1 — Valid list
        ArrayList<CapitalCity> mockCapitalCity = new ArrayList<>();
        mockCapitalCity.add(cc1);
        mockCapitalCity.add(cc2);

        Mockito.when(capitalCityDAO.getTopNPopulatedCapitalCitiesByContinent("Asia", 2)).thenReturn(mockCapitalCity);
        assertDoesNotThrow(() -> capitalCityReport.printTopNPopulatedCapitalCitiesInContinent("Asia", 2));

        // Case 2 — Null list
        Mockito.when(capitalCityDAO.getTopNPopulatedCapitalCitiesByContinent("Asia", 2)).thenReturn(null);
        assertDoesNotThrow(() -> capitalCityReport.printTopNPopulatedCapitalCitiesInContinent("Asia", 2));

        // Case 3 — Empty list
        Mockito.when(capitalCityDAO.getTopNPopulatedCapitalCitiesByContinent("Asia", 2)).thenReturn(new  ArrayList<>());
        assertDoesNotThrow(() -> capitalCityReport.printTopNPopulatedCapitalCitiesInContinent("Asia", 2));

        // Case 4 — List with all null elements
        ArrayList<CapitalCity> listWithNull = new ArrayList<>();
        listWithNull.add(null);

        Mockito.when(capitalCityDAO.getTopNPopulatedCapitalCitiesByContinent("Asia", 2)).thenReturn(listWithNull);
        assertDoesNotThrow(() -> capitalCityReport.printTopNPopulatedCapitalCitiesInContinent("Asia", 2));
    }

    @Test
    void printTopNPopulatedCapitalCitiesInRegion() {
        CapitalCity cc1 = new CapitalCity();
        cc1.setName("Kinshasa");
        cc1.setCountry("Congo, The Democratic Republic of the");
        cc1.setPopulation(5064000);

        CapitalCity cc2 = new CapitalCity();
        cc2.setName("Luanda");
        cc2.setCountry("Angola");
        cc2.setPopulation(2022000);

        // Case 1 — Valid list
        ArrayList<CapitalCity> mockCapitalCity = new ArrayList<>();
        mockCapitalCity.add(cc1);
        mockCapitalCity.add(cc2);

        Mockito.when(capitalCityDAO.getTopNPopulatedCapitalCitiesByRegion("Central Africa", 2)).thenReturn(mockCapitalCity);
        assertDoesNotThrow(() -> capitalCityReport.printTopNPopulatedCapitalCitiesInRegion("Central Africa", 2));

        // Case 2 — Null list
        Mockito.when(capitalCityDAO.getTopNPopulatedCapitalCitiesByRegion("Central Africa", 2)).thenReturn(null);
        assertDoesNotThrow(() -> capitalCityReport.printTopNPopulatedCapitalCitiesInRegion("Central Africa", 2));

        // Case 3 — Empty list
        Mockito.when(capitalCityDAO.getTopNPopulatedCapitalCitiesByRegion("Central Africa", 2)).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> capitalCityReport.printTopNPopulatedCapitalCitiesInRegion("Central Africa", 2));

        // Case 4 — List with all null elements
        ArrayList<CapitalCity> listWithNull = new ArrayList<>();
        listWithNull.add(null);

        Mockito.when(capitalCityDAO.getTopNPopulatedCapitalCitiesByRegion("Central Africa", 2)).thenReturn(listWithNull);
        assertDoesNotThrow(() -> capitalCityReport.printTopNPopulatedCapitalCitiesInRegion("Central Africa", 2));
    }

    // Unit Testings for Population Reports
    @Test
    void printEachContinentPopulationWithUrbanAndNonUrban() {
        Population p = new Population();
        p.setName("Asia");
        p.setTotalPopulation(3705025700L);

        // Case 1 — Valid list with 1 country
        ArrayList<Population> mockPopulation = new ArrayList<>();
        mockPopulation.add(p);

        Mockito.when(populationDAO.getEachContinentPopulationWithUrbanAndNonUrban()).thenReturn(mockPopulation);
        assertDoesNotThrow(() -> populationReport.printEachContinentPopulationWithUrbanAndNonUrban());

        // Case 2 — Null list
        Mockito.when(populationDAO.getEachContinentPopulationWithUrbanAndNonUrban()).thenReturn(null);
        assertDoesNotThrow(() -> populationReport.printEachContinentPopulationWithUrbanAndNonUrban());

        // Case 3 — Empty list
        Mockito.when(populationDAO.getEachContinentPopulationWithUrbanAndNonUrban()).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> populationReport.printEachContinentPopulationWithUrbanAndNonUrban());

        // Case 4 — List with all null elements
        ArrayList<Population> listWithNull = new ArrayList<>();
        listWithNull.add(null);

        Mockito.when(populationDAO.getEachContinentPopulationWithUrbanAndNonUrban()).thenReturn(listWithNull);
        assertDoesNotThrow(() -> populationReport.printEachContinentPopulationWithUrbanAndNonUrban());
    }

    @Test
    void printEachRegionPopulationWithUrbanAndNonUrban() {
        Population p = new Population();
        p.setName("Australia and New Zealand");
        p.setTotalPopulation(22753100L);

        // Case 1 — Valid list with 1 country
        ArrayList<Population> mockPopulation = new ArrayList<>();
        mockPopulation.add(p);

        Mockito.when(populationDAO.getEachRegionPopulationWithUrbanAndNonUrban()).thenReturn(mockPopulation);
        assertDoesNotThrow(() -> populationReport.printEachRegionPopulationWithUrbanAndNonUrban());

        // Case 2 — Null list
        Mockito.when(populationDAO.getEachRegionPopulationWithUrbanAndNonUrban()).thenReturn(null);
        assertDoesNotThrow(() -> populationReport.printEachRegionPopulationWithUrbanAndNonUrban());

        // Case 3 — Empty list
        Mockito.when(populationDAO.getEachRegionPopulationWithUrbanAndNonUrban()).thenReturn(new  ArrayList<>());
        assertDoesNotThrow(() -> populationReport.printEachRegionPopulationWithUrbanAndNonUrban());

        // Case 4 — List with all null elements
        ArrayList<Population> listWithNull = new ArrayList<>();
        listWithNull.add(null);

        Mockito.when(populationDAO.getEachRegionPopulationWithUrbanAndNonUrban()).thenReturn(listWithNull);
        assertDoesNotThrow(() -> populationReport.printEachRegionPopulationWithUrbanAndNonUrban());
    }

    @Test
    void printEachCountryPopulationWithUrbanAndNonUrban() {
        Population p = new Population();
        p.setName("Afghanistan");
        p.setTotalPopulation(22720000L);

        // Case 1 — Valid list with 1 country
        ArrayList<Population> mockPopulation = new ArrayList<>();
        mockPopulation.add(p);

        Mockito.when(populationDAO.getEachCountryPopulationWithUrbanAndNonUrban()).thenReturn(mockPopulation);
        assertDoesNotThrow(() -> populationReport.printEachCountryPopulationWithUrbanAndNonUrban());

        // Case 2 — Null list
        Mockito.when(populationDAO.getEachCountryPopulationWithUrbanAndNonUrban()).thenReturn(null);
        assertDoesNotThrow(() -> populationReport.printEachCountryPopulationWithUrbanAndNonUrban());

        // Case 3 — Empty list
        Mockito.when(populationDAO.getEachCountryPopulationWithUrbanAndNonUrban()).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> populationReport.printEachCountryPopulationWithUrbanAndNonUrban());

        // Case 4 — List with all null elements
        ArrayList<Population> listWithNull = new ArrayList<>();
        listWithNull.add(null);

        Mockito.when(populationDAO.getEachCountryPopulationWithUrbanAndNonUrban()).thenReturn(listWithNull);
        assertDoesNotThrow(() -> populationReport.printEachCountryPopulationWithUrbanAndNonUrban());
    }

    // Unit Testings for Language Reports

}