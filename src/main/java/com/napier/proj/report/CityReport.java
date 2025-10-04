package com.napier.proj.report;

import com.napier.proj.dao.CityDAO;
import com.napier.proj.model.City;

import java.util.List;

public class CityReport {

    private CityDAO cityDAO;

    public CityReport(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    public void printAllCitiesInWorldByPopulation() {
        List<City> cities = this.cityDAO.getAllinWorldCitiesByPopulation();

        System.out.println("\nAll the cities in the world organised by largest population to smallest.\n");

        System.out.printf("%-25s %-25s %-25s %-15s%n",
                "City", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------");

        for (City city : cities) {
            System.out.printf("%-25s %-25s %-25s %-15d%n",
                    city.getName(),
                    city.getCountry(),
                    city.getDistrict(),
                    city.getPopulation());
        }
    }
    public void printAllCitiesInContinentByPopulation(String continent) {
        List<City> cities = this.cityDAO.getAllCitiesInContinentByPopulation(continent);

        System.out.println("\nAll the cities in a continent organised by largest population to smallest.\n");

        System.out.printf("%-25s %-25s %-25s %-15s%n",
                "City", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------");

        for (City city : cities) {
            System.out.printf("%-25s %-25s %-25s %-15d%n",
                    city.getName(),
                    city.getCountry(),
                    city.getDistrict(),
                    city.getPopulation());
        }
    }
    public void printAllCitiesInRegionByPopulation(String region) {
        List<City> cities = this.cityDAO.getAllCitiesInRegionByPopulation(region);

        System.out.println("\nAll the cities in a region organised by largest population to smallest.\n");

        System.out.printf("%-25s %-25s %-25s %-15s%n",
                "City", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------");

        for (City city : cities) {
            System.out.printf("%-25s %-25s %-25s %-15d%n",
                    city.getName(),
                    city.getCountry(),
                    city.getDistrict(),
                    city.getPopulation());
        }
    }


}





