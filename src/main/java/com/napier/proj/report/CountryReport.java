package com.napier.proj.report;

import com.napier.proj.dao.CountryDAO;
import com.napier.proj.model.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code CountryReport} class is responsible for generating and printing
 * formatted reports of countries based on their population.
 *
 * <p>It uses the {@link CountryDAO} to retrieve country data from the database
 * and outputs the results in a table format to the console.</p>
 *
 * @author So Pyay Tun
 */
public class CountryReport {

    /** The Data Access Object (DAO) used to query country data. */
    private CountryDAO countryDAO;

    /**
     * Constructs a {@code CountryReport} object with a given {@link CountryDAO}.
     *
     * @param countryDAO the data access object used to fetch country data.
     */
    public CountryReport(CountryDAO countryDAO) {
        this.countryDAO = countryDAO;
    }

    /**
     * Prints all countries in the world sorted by population (from largest to smallest).
     */
    public void printAllCountriesByPopulation() {
        // Retrieve list of all countries sorted by population
        List<Country> countries = this.countryDAO.getAllCountriesByPopulation();

        System.out.println("\nAll the countries in the world organized by largest population to smallest.\n");

        // Print table header
        System.out.printf("%-7s %-25s %-15s %-25s %-20s %-20s%n", "Code", "Name", "Continent", "Region", "Population",  "Capital");

        System.out.println("--------------------------------------------------------------------------------------------");

        // Print each country's details
        for (Country country : countries) {
            System.out.printf("%-7s %-25s %-15s %-25s %-20s %-20s%n",
                    country.getCode(),
                    country.getName(),
                    country.getContinent(),
                    country.getRegion(),
                    country.getPopulation(),
                    country.getCapital());
        }
    }

    /**
     * Prints all countries within a given continent, sorted by population (descending).
     *
     * @param continent the name of the continent.
     */
    public void printAllCountriesInContinentByPopulation(String continent) {
        // Retrieve all countries in the specified continent
        List<Country> countries = this.countryDAO.getAllCountriesInContinentByPopulation(continent);

        System.out.println("\nAll the countries in a continent organized by largest population to smallest.\n");

        // Print table header
        System.out.printf("%-7s %-25s %-15s %-25s %-20s %-20s%n", "Code", "Name", "Continent", "Region", "Population",  "Capital");

        System.out.println("--------------------------------------------------------------------------------------------");

        // Print each country's details
        for (Country country : countries) {
            System.out.printf("%-7s %-25s %-15s %-25s %-20s %-20s%n",
                    country.getCode(),
                    country.getName(),
                    country.getContinent(),
                    country.getRegion(),
                    country.getPopulation(),
                    country.getCapital());
        }
    }

    /**
     * Prints all countries within a given region, sorted by population (descending).
     *
     * @param region the name of the region.
     */
    public void printAllCountriesInRegionByPopulation(String region) {
        // Retrieve all countries in the specified region
        List<Country> countries = this.countryDAO.getAllCountriesInRegionByPopulation(region);

        System.out.println("\nAll the countries in a region organized by largest population to smallest.\n");

        // Print table header
        System.out.printf("%-7s %-25s %-15s %-25s %-20s %-20s%n", "Code", "Name", "Continent", "Region", "Population",  "Capital");

        System.out.println("--------------------------------------------------------------------------------------------");

        // Print each country's details
        for (Country country : countries) {
            System.out.printf("%-7s %-25s %-15s %-25s %-20s %-20s%n",
                    country.getCode(),
                    country.getName(),
                    country.getContinent(),
                    country.getRegion(),
                    country.getPopulation(),
                    country.getCapital());
        }
    }

    /**
     * Prints the top N most populated countries in the world.
     *
     * @param n the number of top countries to display.
     */
    public void getTopNPopulatedCountriesIntheworld(int n) {
        // Retrieve top N countries globally
        List<Country> countries = this.countryDAO.getTopNPopulatedCountriesIntheworld(n);

        System.out.println("\nThe top N populated countries in the world where N is provided by the user.");

        // Print table header
        System.out.printf("%-7s %-25s %-15s %-25s %-20s %-20s%n", "Code", "Name", "Continent", "Region", "Population",  "Capital");

        System.out.println("--------------------------------------------------------------------------------------------");

        // Print each country's details
        for (Country country : countries) {
            System.out.printf("%-7s %-25s %-15s %-25s %-20s %-20s%n",
                    country.getCode(),
                    country.getName(),
                    country.getContinent(),
                    country.getRegion(),
                    country.getPopulation(),
                    country.getCapital());
        }
    }

    /**
     * Prints the top N most populated countries within a given continent.
     *
     * @param continent the name of the continent.
     * @param n the number of top countries to display.
     */
    public void getTopNPopulatedCountriesInContinent(String continent, int n) {
        // Retrieve top N countries in the specified continent
        List<Country> countries = this.countryDAO.getTopNPopulatedCountriesInContinent(continent ,n);

        System.out.println("\nThe top N populated countries in a continent where N is provided by the user.");

        // Print table header
        System.out.printf("%-7s %-25s %-15s %-25s %-20s %-20s%n", "Code", "Name", "Continent", "Region", "Population",  "Capital");

        System.out.println("--------------------------------------------------------------------------------------------");

        // Print each country's details
        for (Country country : countries) {
            System.out.printf("%-7s %-25s %-15s %-25s %-20s %-20s%n",
                    country.getCode(),
                    country.getName(),
                    country.getContinent(),
                    country.getRegion(),
                    country.getPopulation(),
                    country.getCapital());
        }
    }

    /**
     * Prints the top N most populated countries within a given region.
     *
     * @param region the name of the region.
     * @param n the number of top countries to display.
     */
    public void getTopNPopulatedCountriesInRegion(String region, int n) {
        // Retrieve top N countries in the specified region
        List<Country> countries = this.countryDAO.getTopNPopulatedCountriesInRegion(region ,n);

        System.out.println("\nThe top N populated countries in a region where N is provided by the user.");

        // Print table header
        System.out.printf("%-7s %-25s %-15s %-25s %-20s %-20s%n", "Code", "Name", "Continent", "Region", "Population",  "Capital");

        System.out.println("--------------------------------------------------------------------------------------------");

        // Print each country's details
        for (Country country : countries) {
            System.out.printf("%-7s %-25s %-15s %-25s %-20s %-20s%n",
                    country.getCode(),
                    country.getName(),
                    country.getContinent(),
                    country.getRegion(),
                    country.getPopulation(),
                    country.getCapital());
        }
    }

}
