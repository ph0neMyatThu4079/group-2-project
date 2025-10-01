package com.napier.proj.report;

import com.napier.proj.dao.CountryDAO;
import com.napier.proj.model.Country;

import java.util.ArrayList;
import java.util.List;

public class CountryReport {

    private CountryDAO countryDAO;

    public CountryReport(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    public void printAllCountriesByPopulation() {
        List<Country> countries = this.countryDAO.getAllCountriesByPopulation();

        System.out.printf("%-7s %-25s %-15s %-25s %-20s %-20s%n", "Code", "Name", "Continent", "Region", "Population",  "Capital");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Country country : countries) {
            System.out.printf("%-7s %-25s %-15s %-25s %-20s %-20s%n",
                    country.code,
                    country.name,
                    country.continent,
                    country.region,
                    country.population,
                    country.capital);
        }
    }
}
