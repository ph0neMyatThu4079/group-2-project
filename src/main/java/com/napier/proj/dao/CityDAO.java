package com.napier.proj.dao;

import com.napier.proj.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {
    private Connection con;

    public CityDAO(Connection con) {
        this.con = con;
    }

    // 7. Get all cities in the world sorted by population (desc).
    public List<City> getAllCitiesByPopulation() {
        List<City> cities = new ArrayList<>();

        String sql = "SELECT ci.Name AS CityName, c.Name AS Country, ci.District, ci.Population " +
                "FROM city ci " +
                "JOIN country c ON ci.CountryCode = c.Code " +
                "ORDER BY ci.Population DESC;";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                City city = new City();
                city.setName(rs.getString("CityName"));
                city.setCountry(rs.getString("Country"));
                city.setDistrict(rs.getString("District"));
                city.setPopulation(rs.getLong("Population"));
                cities.add(city);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities");
        }

        return cities;
    }




    // 8. All the cities in a continent organised by largest population to smallest.
//    public List<Country> getTopNCountriesInRegion(String region, int n) { ... }

    // 9. All the cities in a region organised by largest population to smallest.
//    public List<Country> getTopNCountriesInRegion(String region, int n) { ... }

    // 10. All the cities in a country organised by largest population to smallest.
//    public List<Country> getTopNCountriesInRegion(String region, int n) { ... }

    // 11. All the cities in a district organised by largest population to smallest.
//    public List<Country> getTopNCountriesInRegion(String region, int n) { ... }
}
