package com.napier.proj.report;

import com.napier.proj.App;
import com.napier.proj.dao.*;
import com.napier.proj.model.CapitalCity;
import com.napier.proj.model.City;
import com.napier.proj.model.Country;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppUnitTest {

    static CountryDAO countryDAO;
    static CountryReport countryReport;

    public static CityDAO cityDAO;
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
    @Test
    void printAllCitiesInWorldByPopulation() {
        City ci1 = new City();
        ci1.setName("Kabul");
        ci1.setCountry("Afghanistan");
        ci1.setDistrict("Kabol");
        ci1.setPopulation(1780000);

        ArrayList<City> cities = new ArrayList<>();
        cities.add(ci1);

        // Case 1 — valid list
        Mockito.when(cityDAO.getAllinWorldCitiesByPopulation()).thenReturn(cities);
        assertDoesNotThrow(() -> cityReport.printAllCitiesInWorldByPopulation());

        // Case 2 — null list
        Mockito.when(cityDAO.getAllinWorldCitiesByPopulation()).thenReturn(null);
        assertDoesNotThrow(() -> cityReport.printAllCitiesInWorldByPopulation());

        // Case 3 — empty list
        Mockito.when(cityDAO.getAllinWorldCitiesByPopulation()).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> cityReport.printAllCitiesInWorldByPopulation());

        // Case 4 — list with all null element
        ArrayList<City> nullList = new ArrayList<>();
        nullList.add(null);

        Mockito.when(cityDAO.getAllinWorldCitiesByPopulation()).thenReturn(nullList);
        assertDoesNotThrow(() -> cityReport.printAllCitiesInWorldByPopulation());
    }

    @Test
    void printAllCitiesInContinentByPopulation() {
        String continent = "Europe";
        City ci1 = new City();
        ci1.setName("Berlin");
        ci1.setCountry("Germany");
        ci1.setDistrict("Berlin");
        ci1.setPopulation(3500000);

        ArrayList<City> list1 = new ArrayList<>();
        list1.add(ci1);

        Mockito.when(cityDAO.getAllCitiesInContinentByPopulation(continent)).thenReturn(list1);
        assertDoesNotThrow(() -> cityReport.printAllCitiesInContinentByPopulation(continent));

        // CASE 2 — null list
        Mockito.when(cityDAO.getAllCitiesInContinentByPopulation(continent)).thenReturn(null);
        assertDoesNotThrow(() -> cityReport.printAllCitiesInContinentByPopulation(continent));

        // CASE 3 — empty list
        Mockito.when(cityDAO.getAllCitiesInContinentByPopulation(continent)).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> cityReport.printAllCitiesInContinentByPopulation(continent));

        // CASE 4 — list containing null
        ArrayList<City> listWithNull = new ArrayList<>();
        listWithNull.add(null);
        Mockito.when(cityDAO.getAllCitiesInContinentByPopulation(continent)).thenReturn(listWithNull);
        assertDoesNotThrow(() -> cityReport.printAllCitiesInContinentByPopulation(continent));

        // CASE 5 — invalid continent name
        assertDoesNotThrow(() -> cityReport.printAllCitiesInContinentByPopulation(""));
        assertDoesNotThrow(() -> cityReport.printAllCitiesInContinentByPopulation(null));
    }

    @Test
    void printAllCitiesInRegionByPopulation() {
        String region = "Southeast Asia";
        City ci1 = new City();
        ci1.setName("Bangkok");
        ci1.setCountry("Thailand");
        ci1.setDistrict("Bangkok");
        ci1.setPopulation(8300000);

        ArrayList<City> validList = new ArrayList<>();
        validList.add(ci1);

        Mockito.when(cityDAO.getAllCitiesInRegionByPopulation(region)).thenReturn(validList);
        assertDoesNotThrow(() -> cityReport.printAllCitiesInRegionByPopulation(region));

        // Case 2 — Null list
        Mockito.when(cityDAO.getAllCitiesInRegionByPopulation(region)).thenReturn(null);
        assertDoesNotThrow(() -> cityReport.printAllCitiesInRegionByPopulation(region));

        // Case 3 — Empty list
        Mockito.when(cityDAO.getAllCitiesInRegionByPopulation(region)).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> cityReport.printAllCitiesInRegionByPopulation(region));

        // Case 4 — List containing null element
        ArrayList<City> listWithNull = new ArrayList<>();
        listWithNull.add(null);
        Mockito.when(cityDAO.getAllCitiesInRegionByPopulation(region)).thenReturn(listWithNull);
        assertDoesNotThrow(() -> cityReport.printAllCitiesInRegionByPopulation(region));

        // Case 5 — Invalid input
        assertDoesNotThrow(() -> cityReport.printAllCitiesInRegionByPopulation(""));
        assertDoesNotThrow(() -> cityReport.printAllCitiesInRegionByPopulation(null));
    }


    @Test
    void printAllCitiesInCountryByPopulation() {
        String country = "Myanmar";
        City ci1 = new City();
        ci1.setName("Yangon");
        ci1.setCountry("Myanmar");
        ci1.setDistrict("Yangon");
        ci1.setPopulation(5000000);

        ArrayList<City> mockCities = new ArrayList<>();
        mockCities.add(ci1);

        Mockito.when(cityDAO.getAllCitiesInCountryByPopulation(country)).thenReturn(mockCities);
        assertDoesNotThrow(() -> cityReport.printAllCitiesInCountryByPopulation(country));

        // Case 2 — Null list
        Mockito.when(cityDAO.getAllCitiesInCountryByPopulation(country)).thenReturn(null);
        assertDoesNotThrow(() -> cityReport.printAllCitiesInCountryByPopulation(country));

        // Case 3 — Empty list
        Mockito.when(cityDAO.getAllCitiesInCountryByPopulation(country)).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> cityReport.printAllCitiesInCountryByPopulation(country));

        // Case 4 — List containing null element
        ArrayList<City> listWithNull = new ArrayList<>();
        listWithNull.add(null);
        Mockito.when(cityDAO.getAllCitiesInCountryByPopulation(country)).thenReturn(listWithNull);
        assertDoesNotThrow(() -> cityReport.printAllCitiesInCountryByPopulation(country));

        // Case 5 — Invalid input
        assertDoesNotThrow(() -> cityReport.printAllCitiesInCountryByPopulation(""));
        assertDoesNotThrow(() -> cityReport.printAllCitiesInCountryByPopulation(null));
    }


    @Test
    void printAllCitiesInDistrictByPopulation() {
        String district = "Yangon";
        City ci1 = new City();
        ci1.setName("Yangon");
        ci1.setCountry("Myanmar");
        ci1.setDistrict("Yangon");
        ci1.setPopulation(5000000);

        ArrayList<City> mockCities = new ArrayList<>();
        mockCities.add(ci1);

        Mockito.when(cityDAO.getAllCitiesInDistrictByPopulation(district)).thenReturn(mockCities);
        assertDoesNotThrow(() -> cityReport.printAllCitiesInDistrictByPopulation(district));

        // Case 2 — Null list
        Mockito.when(cityDAO.getAllCitiesInDistrictByPopulation(district)).thenReturn(null);
        assertDoesNotThrow(() -> cityReport.printAllCitiesInDistrictByPopulation(district));

        // Case 3 — Empty list
        Mockito.when(cityDAO.getAllCitiesInDistrictByPopulation(district)).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> cityReport.printAllCitiesInDistrictByPopulation(district));

        // Case 4 — List containing null element
        ArrayList<City> listWithNull = new ArrayList<>();
        listWithNull.add(null);
        Mockito.when(cityDAO.getAllCitiesInDistrictByPopulation(district)).thenReturn(listWithNull);
        assertDoesNotThrow(() -> cityReport.printAllCitiesInDistrictByPopulation(district));

        // Case 5 — Invalid input
        assertDoesNotThrow(() -> cityReport.printAllCitiesInDistrictByPopulation(""));
        assertDoesNotThrow(() -> cityReport.printAllCitiesInDistrictByPopulation(null));
    }


    // Unit Testings for Capital City Reports
    @Test
    void printAllCapitalCities() {
        CapitalCity cc = new CapitalCity();
        cc.setName("Tokyo");
        cc.setCountry("Japan");
        cc.setPopulation(7980230);

        // Case 1 — Valid list with 1 country
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

        // Case 1 — Valid list with 1 country
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

        // Case 1 — Valid list with 1 country
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

        // Case 1 — Valid list with 1 country
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

        // Case 1 — Valid list with 1 country
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

        // Case 1 — Valid list with 1 country
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

    // Unit Testings for Top N City Reports
    @Test
    void printTopNPopulatedCitiesInWorld() {
        City ci1 = new City();
        ci1.setName("Tokyo");
        ci1.setCountry("Japan");
        ci1.setDistrict("Tokyo");
        ci1.setPopulation(37000000);

        ArrayList<City> list = new ArrayList<>();
        list.add(ci1);

        // Case 1 — valid list
        Mockito.when(cityDAO.getTopNPopulatedCitiesInWorld(5)).thenReturn(list);
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInWorld(5));

        // Case 2 — null list
        Mockito.when(cityDAO.getTopNPopulatedCitiesInWorld(5)).thenReturn(null);
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInWorld(5));

        // Case 3 — empty list
        Mockito.when(cityDAO.getTopNPopulatedCitiesInWorld(5)).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInWorld(5));

        // Case 4 — list containing null
        ArrayList<City> nullList = new ArrayList<>();
        nullList.add(null);
        Mockito.when(cityDAO.getTopNPopulatedCitiesInWorld(5)).thenReturn(nullList);
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInWorld(5));
    }

    @Test
    void printTopNPopulatedCitiesInContinent() {
        String continent = "Asia";

        City ci1 = new City();
        ci1.setName("Shanghai");
        ci1.setCountry("China");
        ci1.setDistrict("Shanghai");
        ci1.setPopulation(26000000);

        ArrayList<City> list = new ArrayList<>();
        list.add(ci1);

        Mockito.when(cityDAO.getTopNPopulatedCitiesInContinent(continent, 5)).thenReturn(list);
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInContinent(continent, 5));

        Mockito.when(cityDAO.getTopNPopulatedCitiesInContinent(continent, 5)).thenReturn(null);
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInContinent(continent, 5));

        Mockito.when(cityDAO.getTopNPopulatedCitiesInContinent(continent, 5)).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInContinent(continent, 5));

        ArrayList<City> listWithNull = new ArrayList<>();
        listWithNull.add(null);
        Mockito.when(cityDAO.getTopNPopulatedCitiesInContinent(continent, 5)).thenReturn(listWithNull);
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInContinent(continent, 5));

        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInContinent("", 5));
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInContinent(null, 5));
    }

    @Test
    void printTopNPopulatedCitiesInRegion() {
        String region = "Western Europe";

        City ci1 = new City();
        ci1.setName("Paris");
        ci1.setCountry("France");
        ci1.setDistrict("Île-de-France");
        ci1.setPopulation(11000000);

        ArrayList<City> list = new ArrayList<>();
        list.add(ci1);

        Mockito.when(cityDAO.getTopNPopulatedCitiesInRegion(region, 5)).thenReturn(list);
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInRegion(region, 5));

        Mockito.when(cityDAO.getTopNPopulatedCitiesInRegion(region, 5)).thenReturn(null);
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInRegion(region, 5));

        Mockito.when(cityDAO.getTopNPopulatedCitiesInRegion(region, 5)).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInRegion(region, 5));

        ArrayList<City> listWithNull = new ArrayList<>();
        listWithNull.add(null);
        Mockito.when(cityDAO.getTopNPopulatedCitiesInRegion(region, 5)).thenReturn(listWithNull);
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInRegion(region, 5));

        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInRegion("", 5));
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInRegion(null, 5));
    }

    @Test
    void printTopNPopulatedCitiesInCountry() {
        String country = "USA";

        City ci1 = new City();
        ci1.setName("New York");
        ci1.setCountry("USA");
        ci1.setDistrict("New York");
        ci1.setPopulation(19000000);

        ArrayList<City> list = new ArrayList<>();
        list.add(ci1);

        Mockito.when(cityDAO.getTopNPopulatedCitiesInCountry(country, 5)).thenReturn(list);
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInCountry(country, 5));

        Mockito.when(cityDAO.getTopNPopulatedCitiesInCountry(country, 5)).thenReturn(null);
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInCountry(country, 5));

        Mockito.when(cityDAO.getTopNPopulatedCitiesInCountry(country, 5)).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInCountry(country, 5));

        ArrayList<City> listWithNull = new ArrayList<>();
        listWithNull.add(null);
        Mockito.when(cityDAO.getTopNPopulatedCitiesInCountry(country, 5)).thenReturn(listWithNull);
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInCountry(country, 5));

        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInCountry("", 5));
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInCountry(null, 5));
    }

    @Test
    void printTopNPopulatedCitiesInDistrict() {
        String district = "California";

        City ci1 = new City();
        ci1.setName("Los Angeles");
        ci1.setCountry("USA");
        ci1.setDistrict("California");
        ci1.setPopulation(13000000);

        ArrayList<City> list = new ArrayList<>();
        list.add(ci1);

        Mockito.when(cityDAO.getTopNPopulatedCitiesInDistrict(district, 5)).thenReturn(list);
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInDistrict(district, 5));

        Mockito.when(cityDAO.getTopNPopulatedCitiesInDistrict(district, 5)).thenReturn(null);
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInDistrict(district, 5));

        Mockito.when(cityDAO.getTopNPopulatedCitiesInDistrict(district, 5)).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInDistrict(district, 5));

        ArrayList<City> listWithNull = new ArrayList<>();
        listWithNull.add(null);
        Mockito.when(cityDAO.getTopNPopulatedCitiesInDistrict(district, 5)).thenReturn(listWithNull);
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInDistrict(district, 5));

        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInDistrict("", 5));
        assertDoesNotThrow(() -> cityReport.printTopNPopulatedCitiesInDistrict(null, 5));
    }


    // Unit Testings for Population Reports

    // Unit Testings for Language Reports

}