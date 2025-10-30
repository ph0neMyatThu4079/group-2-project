package com.napier.proj.report;

import com.napier.proj.dao.CapitalCityDAO;
import com.napier.proj.dao.PopulationDAO;
import com.napier.proj.model.CapitalCity;
import com.napier.proj.model.Population;

import java.util.List;

/**
 * Handles the display of population reports for continents, regions, and countries.
 * <p>
 * This class retrieves population data from {@link PopulationDAO} and
 * prints it in a formatted table showing total, city, and non-city populations,
 * along with their respective percentages.
 * </p>
 *
 * @author Phone Myat Thu
 */
public class PopulationReport {
    /** Data Access Object for retrieving population data. */
    private PopulationDAO populationDAO;

    /**
     * Constructs a {@code PopulationReport} object with a given {@link PopulationDAO}.
     *
     * @param populationDAO the data access object used to fetch country data.
     */
    public PopulationReport(PopulationDAO populationDAO) {
        this.populationDAO = populationDAO;
    }

    /**
     * Prints the total, city, and non-city population for each continent,
     * along with the percentage of people living and not living in cities.
     */
    public void printEachContinentPopulationWithUrbanAndNonUrban() {
        // Retrieve all population of people, people living in cities, and people not living in cities in each continent.
        List<Population> populations = this.populationDAO.getEachContinentPopulationWithUrbanAndNonUrban();

        System.out.println("\nThe population of people, people living in cities, and people not living in cities in each continent.\n");

        // Print table header
        System.out.printf("%-25s %-20s %-20s %-20s %-15s %-15s\n",
                "Continent", "TotalPopulation", "CityPopulation", "NonCityPopulation", "CityPercentage", "NonCityPercentage");
        System.out.println("----------------------------------------------------------");

        // Print each population's details
        for (Population population : populations) {
            System.out.printf("%-25s %-20s %-20s %-20s %-15s %-15s\n",
                    population.getName(),
                    population.getTotalPopulation(),
                    population.getCityPopulation(),
                    population.getNonCityPopulation(),
                    population.getCityPercentage() + "%",
                    population.getNonCityPercentage() + "%"
            );
        }
    }

    /**
     * Prints the total, city, and non-city population for each region,
     * along with the percentage of people living and not living in cities.
     */
    public void printEachRegionPopulationWithUrbanAndNonUrban() {
        // Retrieve all population of people, people living in cities, and people not living in cities in each region.
        List<Population> populations = this.populationDAO.getEachRegionPopulationWithUrbanAndNonUrban();

        System.out.println("\nThe population of people, people living in cities, and people not living in cities in each region.\n");

        // Print table header
        System.out.printf("%-25s %-20s %-20s %-20s %-15s %-15s\n",
                "Region", "TotalPopulation", "CityPopulation", "NonCityPopulation", "CityPercentage", "NonCityPercentage");
        System.out.println("----------------------------------------------------------");

        // Print each population's details
        for (Population population : populations) {
            System.out.printf("%-25s %-20s %-20s %-20s %-15s %-15s\n",
                    population.getName(),
                    population.getTotalPopulation(),
                    population.getCityPopulation(),
                    population.getNonCityPopulation(),
                    population.getCityPercentage() + "%",
                    population.getNonCityPercentage() + "%"
            );
        }
    }

    /**
     * Prints the total, city, and non-city population for each country,
     * along with the percentage of people living and not living in cities.
     */
    public void printEachCountryPopulationWithUrbanAndNonUrban() {
        // Retrieve all population of people, people living in cities, and people not living in cities in each country.
        List<Population> populations = this.populationDAO.getEachCountryPopulationWithUrbanAndNonUrban();

        System.out.println("\nThe population of people, people living in cities, and people not living in cities in each country.\n");

        // Print table header
        System.out.printf("%-25s %-20s %-20s %-20s %-15s %-15s\n",
                "Country", "TotalPopulation", "CityPopulation", "NonCityPopulation", "CityPercentage", "NonCityPercentage");
        System.out.println("----------------------------------------------------------");

        // Print each population's details
        for (Population population : populations) {
            System.out.printf("%-25s %-20s %-20s %-20s %-15s %-15s\n",
                    population.getName(),
                    population.getTotalPopulation(),
                    population.getCityPopulation(),
                    population.getNonCityPopulation(),
                    population.getCityPercentage() + "%",
                    population.getNonCityPercentage() + "%"
            );
        }
    }

    /**
     * Print the total population of the world
     */
    public void printWorldPopulation() {
        List<Population> populations = populationDAO.getWorldPopulation();

        System.out.println("\nThe total population of the world.\n");
        System.out.printf("%-20s %-20s\n", "Name", "TotalPopulation");
        System.out.println("----------------------------------");

        for (Population population : populations) {
            System.out.printf("%-20s %-20d\n",
                    population.getName(),
                    population.getTotalPopulation());
        }
    }
    /**
     * Print the total population of a continent.
     */
    public void printContinentPopulation(String continent) {
        List<Population> populations = populationDAO.getContinentPopulation(continent);

        System.out.println("\nThe total population of the continent: " + continent + "\n");
        System.out.printf("%-20s %-20s\n", "Name", "TotalPopulation");
        System.out.println("----------------------------------");

        for (Population population : populations) {
            System.out.printf("%-20s %-20d\n",
                    population.getName(),
                    population.getTotalPopulation());
        }
    }

    /**
     * Print the total population of a region.
     */
    public void printRegionPopulation(String region) {
        List<Population> populations = populationDAO.getRegionPopulation(region);

        System.out.println("\nThe total population of the region: " + region + "\n");
        System.out.printf("%-20s %-20s\n", "Name", "TotalPopulation");
        System.out.println("----------------------------------");

        for (Population population : populations) {
            System.out.printf("%-20s %-20d\n",
                    population.getName(),
                    population.getTotalPopulation());
        }
    }
    /**
     * Print the total population of a country.
     */
    public void printCountryPopulation(String country) {
        List<Population> populations = populationDAO.getCountryPopulation(country);

        System.out.println("\nThe total population of the country: " + country + "\n");
        System.out.printf("%-20s %-20s\n", "Name", "TotalPopulation");
        System.out.println("----------------------------------");

        for (Population population : populations) {
            System.out.printf("%-20s %-20d\n",
                    population.getName(),
                    population.getTotalPopulation());
        }
    }
    /**
     * Print the total population of a district.
     */
    public void printDistrictPopulation(String district) {
        List<Population> populations = populationDAO.getDistrictPopulation(district);

        System.out.println("\nThe total population of the district: " + district + "\n");
        System.out.printf("%-20s %-20s\n", "Name", "TotalPopulation");
        System.out.println("----------------------------------");

        for (Population population : populations) {
            System.out.printf("%-20s %-20d\n",
                    population.getName(),
                    population.getTotalPopulation());
        }
    }
    /**
     * Print the total population of a city.
     */
    public void printCityPopulation(String city) {
        List<Population> populations = populationDAO.getCityPopulation(city);

        System.out.println("\nThe total population of the city: " + city + "\n");
        System.out.printf("%-20s %-20s\n", "Name", "TotalPopulation");
        System.out.println("----------------------------------");

        for (Population population : populations) {
            System.out.printf("%-20s %-20d\n",
                    population.getName(),
                    population.getTotalPopulation());
        }
    }




}
