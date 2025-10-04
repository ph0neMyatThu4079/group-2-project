package com.napier.proj.report;

import com.napier.proj.dao.CityDAO;
import com.napier.proj.model.City;

import java.util.List;

public class CityReport {

    private CityDAO cityDAO;

    public CityReport(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    public void printAllCitiesByPopulation() {
        List<City> cities = this.cityDAO.getAllCitiesByPopulation();

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





