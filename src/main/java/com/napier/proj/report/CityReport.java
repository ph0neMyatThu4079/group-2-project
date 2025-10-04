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

        System.out.println("\nAll the cities in the world organized by largest population to smallest.\n");

        System.out.printf("%-25s %-25s %-25s %-15s%n",
                "City", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------------------");

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

        System.out.println("\nAll the cities in a continent organized by largest population to smallest.\n");

        System.out.printf("%-25s %-25s %-25s %-15s%n",
                "City", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------------------");

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

        System.out.println("\nAll the cities in a region organized by largest population to smallest.\n");

        System.out.printf("%-25s %-25s %-25s %-15s%n",
                "City", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------------------");

        for (City city : cities) {
            System.out.printf("%-25s %-25s %-25s %-15d%n",
                    city.getName(),
                    city.getCountry(),
                    city.getDistrict(),
                    city.getPopulation());
        }
    }
    public void printAllCitiesInCountryByPopulation(String country) {
        List<City> cities = this.cityDAO.getAllCitiesInCountryByPopulation(country);

        System.out.println("\nAll the cities in a country organized by largest population to smallest.\n");

        System.out.printf("%-25s %-25s %-25s %-15s%n",
                "City", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------------------");

        for (City city : cities) {
            System.out.printf("%-25s %-25s %-25s %-15d%n",
                    city.getName(),
                    city.getCountry(),
                    city.getDistrict(),
                    city.getPopulation());
        }
    }
    public void printAllCitiesInDistrictByPopulation(String district) {
        List<City> cities = this.cityDAO.getAllCitiesInDistrictByPopulation(district);

        System.out.println("\nAll the cities in a district organized by largest population to smallest.\n");

        System.out.printf("%-25s %-25s %-25s %-15s%n",
                "City", "Country", "District", "Population");
        System.out.println("---------------------------------------------------------------------------------------------");

        for (City city : cities) {
            System.out.printf("%-25s %-25s %-25s %-15d%n",
                    city.getName(),
                    city.getCountry(),
                    city.getDistrict(),
                    city.getPopulation());
        }
    }




}





