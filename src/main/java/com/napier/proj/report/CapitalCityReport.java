package com.napier.proj.report;

import com.napier.proj.dao.CapitalCityDAO;
import com.napier.proj.dao.CountryDAO;
import com.napier.proj.model.CapitalCity;

import java.util.List;

/**
 * The {@code CapitalCityReport} class is responsible for generating and printing
 * formatted reports of capital cities based on their population.
 *
 * <p>It uses the {@link CapitalCityDAO} to retrieve capital cities data from the database
 * and outputs the results in a table format to the console.</p>
 *
 * @author Phone Myat Thu
 */
public class CapitalCityReport {

    private CapitalCityDAO capitalCityDAO;

    /**
     * Constructs a {@code CapitalCityReport} object with a given {@link CapitalCityDAO}.
     *
     * @param capitalCityDAO the data access object used to fetch country data.
     */
    public CapitalCityReport(CapitalCityDAO capitalCityDAO) {
        this.capitalCityDAO = capitalCityDAO;
    }

    /**
     * Prints all capital cities in the world sorted by population (from largest to smallest).
     */
    public void printAllCapitalCities() {
        // Retrieve all capital cities sorted by population from the DAO
        List<CapitalCity> capitalCities = this.capitalCityDAO.getAllCapitalCities();

        System.out.println("\nAll the capital cities in the world organised by largest population to smallest.\n");

        // Print table header
        System.out.printf("%-25s %-25s %-20s\n",
                "CapitalCity", "Country", "Population");
        System.out.println("----------------------------------------------------------");

        // Print each capital cities's details
        for (CapitalCity capitalCity : capitalCities) {
            System.out.printf("%-25s %-25s %-20s\n",
                    capitalCity.getName(),
                    capitalCity.getCountry(),
                    capitalCity.getPopulation()
            );
        }
    }

    /**
     * Prints all capital cities within a given continent, sorted by population (descending).
     *
     * @param continent the name of the continent.
     */
    public void printAllCapitalCitiesInContinent(String continent) {
        // Retrieve all capital cities in a continent sorted by population from the DAO
        List<CapitalCity> capitalCities = this.capitalCityDAO.getAllCapitalCitiesInContinent(continent);

        System.out.println("\nAll the capital cities in a continent organised by largest population to smallest.\n");

        // Print table header
        System.out.printf("%-25s %-25s %-20s\n",
                "CapitalCity", "Country", "Population");
        System.out.println("----------------------------------------------------------");

        // Print each capital cities's details
        for (CapitalCity capitalCity : capitalCities) {
            System.out.printf("%-25s %-25s %-20s\n",
                    capitalCity.getName(),
                    capitalCity.getCountry(),
                    capitalCity.getPopulation()
            );
        }
    }

    /**
     * Prints all capital cities within a given region, sorted by population (descending).
     *
     * @param region the name of the region.
     */
    public void printAllCapitalCitiesInRegion(String region) {
        // Retrieve all capital cities in a region sorted by population from the DAO
        List<CapitalCity> capitalCities = this.capitalCityDAO.getAllCapitalCitiesInRegion(region);

        System.out.println("\nAll the capital cities in a region organised by largest population to smallest.\n");

        // Print table header
        System.out.printf("%-25s %-25s %-20s\n",
                "CapitalCity", "Country", "Population");
        System.out.println("----------------------------------------------------------");

        // Print each capital cities's details
        for (CapitalCity capitalCity : capitalCities) {
            System.out.printf("%-25s %-25s %-20s\n",
                    capitalCity.getName(),
                    capitalCity.getCountry(),
                    capitalCity.getPopulation()
            );
        }
    }

    /**
     * Prints the top N most populated capital cities in the world.
     *
     * @param n the number of top capital cities to display.
     */
    public void printTopNPopulatedCapitalCities(int n) {
        // Retrieve top N capital cities globally
        List<CapitalCity> capitalCities = this.capitalCityDAO.getTopNPopulatedCapitalCities(n);

        System.out.println("\nThe top N populated capital cities in the world where N is provided by the user.\n");

        // Print table header
        System.out.printf("%-25s %-25s %-20s\n",
                "CapitalCity", "Country", "Population");
        System.out.println("----------------------------------------------------------");

        // Print each capital cities's details
        for (CapitalCity capitalCity : capitalCities) {
            System.out.printf("%-25s %-25s %-20s\n",
                    capitalCity.getName(),
                    capitalCity.getCountry(),
                    capitalCity.getPopulation()
            );
        }
    }

    /**
     * Prints the top N most populated capital cities within a given continent.
     *
     * @param continent the name of the continent.
     * @param n the number of top capital cities to display.
     */
    public void printTopNPopulatedCapitalCitiesInContinent(String continent, int n) {
        // Retrieve top N capital cities in the specified continent
        List<CapitalCity> capitalCities = this.capitalCityDAO.getTopNPopulatedCapitalCitiesByContinent(continent, n);

        System.out.println("\nThe top N populated capital cities in a continent where N is provided by the user.\n");

        // Print table header
        System.out.printf("%-25s %-25s %-20s\n",
                "CapitalCity", "Country", "Population");
        System.out.println("----------------------------------------------------------");

        // Print each capital cities's details
        for (CapitalCity capitalCity : capitalCities) {
            System.out.printf("%-25s %-25s %-20s\n",
                    capitalCity.getName(),
                    capitalCity.getCountry(),
                    capitalCity.getPopulation()
            );
        }
    }

    /**
     * Prints the top N most populated capital cities within a given region.
     *
     * @param region the name of the region.
     * @param n the number of top capital cities to display.
     */
    public void printTopNPopulatedCapitalCitiesInRegion(String region, int n) {
        // Retrieve top N capital cities in the specified region
        List<CapitalCity> capitalCities = this.capitalCityDAO.getTopNPopulatedCapitalCitiesByRegion(region, n);

        System.out.println("\nThe top N populated capital cities in a region where N is provided by the user.\n");

        // Print table header
        System.out.printf("%-25s %-25s %-20s\n",
                "CapitalCity", "Country", "Population");
        System.out.println("----------------------------------------------------------");

        // Print each capital cities's details
        for (CapitalCity capitalCity : capitalCities) {
            System.out.printf("%-25s %-25s %-20s\n",
                    capitalCity.getName(),
                    capitalCity.getCountry(),
                    capitalCity.getPopulation()
            );
        }
    }
}
