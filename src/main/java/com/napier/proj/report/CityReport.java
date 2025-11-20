package com.napier.proj.report;

import com.napier.proj.dao.CityDAO;
import com.napier.proj.model.City;

import java.util.List;

/**
 * The {@code CityReport} class is responsible for generating and printing
 * formatted reports of cities based on population. It retrieves data through
 * {@link CityDAO} and outputs readable tables in the console.
 *
 * @author Min Wanna Hlan
 */
public class CityReport {

    /** The DAO object used to access city-related database operations. */
    private CityDAO cityDAO;

    /**
     * Constructs a {@code CityReport} with a given {@link CityDAO}.
     *
     * @param cityDAO The data access object for city queries.
     */
    public CityReport(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    /**
     * Prints all cities in the world, sorted by population in descending order.
     * The data is formatted into a readable table format.
     */
    public void printAllCitiesInWorldByPopulation() {
        // Retrieve all cities sorted by population from the DAO
        List<City> cities = this.cityDAO.getAllinWorldCitiesByPopulation();

        System.out.println("\nAll the cities in the world organized by largest population to smallest.\n");

        // Print table header
        System.out.printf("%-25s %-25s %-25s %-15s%n",
                "City", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------------------");

        // ADD THIS CHECK — REQUIRED
        if (cities == null || cities.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        // Print each city's details
        for (City city : cities) {

            if (city == null) continue; // avoid null element crash

            System.out.printf("%-25s %-25s %-25s %-15d%n",
                    city.getName(),
                    city.getCountry(),
                    city.getDistrict(),
                    city.getPopulation());
        }
    }

    /**
     * Prints all cities within a given continent, sorted by population in descending order.
     *
     * @param continent The name of the continent.
     */
    public void printAllCitiesInContinentByPopulation(String continent) {
        // Retrieve cities for the specified continent
        List<City> cities = this.cityDAO.getAllCitiesInContinentByPopulation(continent);

        System.out.println("\nAll the cities in a continent organized by largest population to smallest.\n");

        // Print table header
        System.out.printf("%-25s %-25s %-25s %-15s%n",
                "City", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------------------");

        // ADD THIS CHECK — REQUIRED
        if (cities == null || cities.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        // Print each city's details
        for (City city : cities) {

            if (city == null) continue; // avoid null element crash

            System.out.printf("%-25s %-25s %-25s %-15d%n",
                    city.getName(),
                    city.getCountry(),
                    city.getDistrict(),
                    city.getPopulation());
        }
    }

    /**
     * Prints all cities within a given region, sorted by population in descending order.
     *
     * @param region The name of the region.
     */
    public void printAllCitiesInRegionByPopulation(String region) {
        // Retrieve cities for the specified region
        List<City> cities = this.cityDAO.getAllCitiesInRegionByPopulation(region);

        System.out.println("\nAll the cities in a region organized by largest population to smallest.\n");

        // Print table header
        System.out.printf("%-25s %-25s %-25s %-15s%n",
                "City", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------------------");

        // ADD THIS CHECK — REQUIRED
        if (cities == null || cities.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        // Print each city's details
        for (City city : cities) {

            if (city == null) continue; // avoid null element crash

            System.out.printf("%-25s %-25s %-25s %-15d%n",
                    city.getName(),
                    city.getCountry(),
                    city.getDistrict(),
                    city.getPopulation());
        }
    }

    /**
     * Prints all cities within a given country, sorted by population in descending order.
     *
     * @param country The name of the country.
     */
    public void printAllCitiesInCountryByPopulation(String country) {
        // Retrieve cities for the specified country
        List<City> cities = this.cityDAO.getAllCitiesInCountryByPopulation(country);

        System.out.println("\nAll the cities in a country organized by largest population to smallest.\n");

        // Print table header
        System.out.printf("%-25s %-25s %-25s %-15s%n",
                "City", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------------------");

        // ADD THIS CHECK — REQUIRED
        if (cities == null || cities.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        // Print each city's details
        for (City city : cities) {

            if (city == null) continue; // avoid null element crash

            System.out.printf("%-25s %-25s %-25s %-15d%n",
                    city.getName(),
                    city.getCountry(),
                    city.getDistrict(),
                    city.getPopulation());
        }
    }

    /**
     * Prints all cities within a given district, sorted by population in descending order.
     *
     * @param district The name of the district.
     */
    public void printAllCitiesInDistrictByPopulation(String district) {
        // Retrieve cities for the specified district
        List<City> cities = this.cityDAO.getAllCitiesInDistrictByPopulation(district);

        System.out.println("\nAll the cities in a district organized by largest population to smallest.\n");

        // Print table header
        System.out.printf("%-25s %-25s %-25s %-15s%n",
                "City", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------------------");

        // ADD THIS CHECK — REQUIRED
        if (cities == null || cities.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        // Print each city's details
        for (City city : cities) {

            if (city == null) continue; // avoid null element crash

            System.out.printf("%-25s %-25s %-25s %-15d%n",
                    city.getName(),
                    city.getCountry(),
                    city.getDistrict(),
                    city.getPopulation());
        }
    }

    /**
     * Prints the top N most populated cities in the world.
     *
     * @param n The number of cities to display, based on highest population.
     */
    public void printTopNPopulatedCitiesInWorld(int n) {
        List<City> cities = this.cityDAO.getTopNPopulatedCitiesInWorld(n);

        System.out.println("\nThe top " + n + " populated cities in the world where N is provided by the user.\n");

        System.out.printf("%-25s %-25s %-25s %-15s%n",
                "City", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------------------");

        if (cities == null || cities.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        for (City city : cities) {
            if (city == null) continue;
            System.out.printf("%-25s %-25s %-25s %-15d%n",
                    city.getName(),
                    city.getCountry(),
                    city.getDistrict(),
                    city.getPopulation());
        }
    }

    /**
     * Prints the top N most populated cities within a specific continent.
     *
     * @param continent The name of the continent to filter by.
     * @param n The number of cities to display, based on highest population.
     */
    public void printTopNPopulatedCitiesInContinent(String continent, int n) {
        List<City> cities = this.cityDAO.getTopNPopulatedCitiesInContinent(continent, n);

        System.out.println("\nThe top " + n + " populated cities in continent: " + continent + ".\n");

        System.out.printf("%-25s %-25s %-25s %-15s%n",
                "City", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------------------");

        if (cities == null || cities.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        for (City city : cities) {
            if (city == null) continue;
            System.out.printf("%-25s %-25s %-25s %-15d%n",
                    city.getName(),
                    city.getCountry(),
                    city.getDistrict(),
                    city.getPopulation());
        }
    }

    /**
     * Prints the top N most populated cities within a specific region.
     *
     * @param region The name of the region to filter by.
     * @param n The number of cities to display, based on highest population.
     */
    public void printTopNPopulatedCitiesInRegion(String region, int n) {
        List<City> cities = this.cityDAO.getTopNPopulatedCitiesInRegion(region, n);

        System.out.println("\nThe top " + n + " populated cities in region: " + region + ".\n");

        System.out.printf("%-25s %-25s %-25s %-15s%n",
                "City", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------------------");

        if (cities == null || cities.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        for (City city : cities) {
            if (city == null) continue;
            System.out.printf("%-25s %-25s %-25s %-15d%n",
                    city.getName(),
                    city.getCountry(),
                    city.getDistrict(),
                    city.getPopulation());
        }
    }

    /**
     * Prints the top N most populated cities within a specific country.
     *
     * @param country The name of the country to filter by.
     * @param n The number of cities to display, based on highest population.
     */
    public void printTopNPopulatedCitiesInCountry(String country, int n) {
        List<City> cities = this.cityDAO.getTopNPopulatedCitiesInCountry(country, n);

        System.out.println("\nThe top " + n + " populated cities in country: " + country + ".\n");

        System.out.printf("%-25s %-25s %-25s %-15s%n",
                "City", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------------------");

        if (cities == null || cities.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        for (City city : cities) {
            if (city == null) continue;
            System.out.printf("%-25s %-25s %-25s %-15d%n",
                    city.getName(),
                    city.getCountry(),
                    city.getDistrict(),
                    city.getPopulation());
        }
    }

    /**
     * Prints the top N most populated cities within a specific district.
     *
     * @param district The district name to filter by.
     * @param n The number of cities to display, based on highest population.
     */
    public void printTopNPopulatedCitiesInDistrict(String district, int n) {
        List<City> cities = this.cityDAO.getTopNPopulatedCitiesInDistrict(district, n);

        System.out.println("\nThe top " + n + " populated cities in district: " + district + ".\n");

        System.out.printf("%-25s %-25s %-25s %-15s%n",
                "City", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------------------");

        if (cities == null || cities.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        for (City city : cities) {
            if (city == null) continue;
            System.out.printf("%-25s %-25s %-25s %-15d%n",
                    city.getName(),
                    city.getCountry(),
                    city.getDistrict(),
                    city.getPopulation());
        }
    }
}






