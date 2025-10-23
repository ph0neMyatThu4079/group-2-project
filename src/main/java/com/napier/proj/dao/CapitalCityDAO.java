package com.napier.proj.dao;

import com.napier.proj.model.CapitalCity;
import com.napier.proj.model.Population;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CapitalCityDAO {

    private Connection con;

    public CapitalCityDAO(Connection con) {
        this.con = con;
    }

    //    All the capital cities in the world organised by largest population to smallest.
    public List<CapitalCity> getAllCapitalCities() {
        List<CapitalCity> capitalCities = new ArrayList<>();

        String sql = "SELECT city.Name AS CapitalCity, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC ";

        try(PreparedStatement pstmt = con.prepareStatement(sql)){

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CapitalCity capitalCity = new CapitalCity();
                capitalCity.setName(rs.getString("CapitalCity"));
                capitalCity.setCountry(rs.getString("Country"));
                capitalCity.setPopulation(rs.getLong("Population"));
                capitalCities.add(capitalCity);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities");
        }

        return capitalCities;
    }


    //    All the capital cities in a continent organized by the largest population to smallest.
    public List<CapitalCity> getAllCapitalCitiesInContinent(String continent) {
        List<CapitalCity> capitalCities = new ArrayList<>();

        String sql = "SELECT city.Name AS CapitalCity, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC ";

        try(PreparedStatement pstmt = con.prepareStatement(sql)){

            pstmt.setString(1, continent);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CapitalCity capitalCity = new CapitalCity();
                capitalCity.setName(rs.getString("CapitalCity"));
                capitalCity.setCountry(rs.getString("Country"));
                capitalCity.setPopulation(rs.getLong("Population"));
                capitalCities.add(capitalCity);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities by a continent");
        }

        return capitalCities;
    }


    //    All the capital cities in a region organized by the largest population to smallest.
    public List<CapitalCity> getAllCapitalCitiesInRegion(String region) {
        List<CapitalCity> capitalCities = new ArrayList<>();

        String sql = "SELECT city.Name AS CapitalCity, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC ";

        try(PreparedStatement pstmt = con.prepareStatement(sql)){

            pstmt.setString(1, region);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CapitalCity capitalCity = new CapitalCity();
                capitalCity.setName(rs.getString("CapitalCity"));
                capitalCity.setCountry(rs.getString("Country"));
                capitalCity.setPopulation(rs.getLong("Population"));
                capitalCities.add(capitalCity);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital cities by a region");
        }

        return capitalCities;
    }

    //    The top N populated capital cities in the world where N is provided by the user.
    public List<CapitalCity> getTopNPopulatedCapitalCities(int n) {
        List<CapitalCity> capitalCities = new ArrayList<>();

        String sql = "SELECT city.Name AS CapitalCity, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "ORDER BY city.Population DESC " +
                "LIMIT ?";

        try(PreparedStatement pstmt = con.prepareStatement(sql)){

            pstmt.setInt(1, n);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CapitalCity capitalCity = new CapitalCity();
                capitalCity.setName(rs.getString("CapitalCity"));
                capitalCity.setCountry(rs.getString("Country"));
                capitalCity.setPopulation(rs.getLong("Population"));
                capitalCities.add(capitalCity);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top " + n + " populated capital cities");
        }

        return capitalCities;
    }

    //    The top N populated capital cities in a continent where N is provided by the user.
    public List<CapitalCity> getTopNPopulatedCapitalCitiesByContinent(String continent, int n) {
        List<CapitalCity> capitalCities = new ArrayList<>();

        String sql = "SELECT city.Name AS CapitalCity, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Continent = ? " +
                "ORDER BY city.Population DESC " +
                "LIMIT ?";

        try(PreparedStatement pstmt = con.prepareStatement(sql)){

            pstmt.setString(1, continent);
            pstmt.setInt(2, n);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CapitalCity capitalCity = new CapitalCity();
                capitalCity.setName(rs.getString("CapitalCity"));
                capitalCity.setCountry(rs.getString("Country"));
                capitalCity.setPopulation(rs.getLong("Population"));
                capitalCities.add(capitalCity);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top " + n + " populated capital cities in continent " + continent);
        }

        return capitalCities;
    }

    //    The top N populated capital cities in a region where N is provided by the user.
    public List<CapitalCity> getTopNPopulatedCapitalCitiesByRegion(String region, int n) {
        List<CapitalCity> capitalCities = new ArrayList<>();

        String sql = "SELECT city.Name AS CapitalCity, country.Name AS Country, city.Population " +
                "FROM city " +
                "JOIN country ON city.ID = country.Capital " +
                "WHERE country.Region = ? " +
                "ORDER BY city.Population DESC " +
                "LIMIT ?";

        try(PreparedStatement pstmt = con.prepareStatement(sql)){

            pstmt.setString(1, region);
            pstmt.setInt(2, n);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CapitalCity capitalCity = new CapitalCity();
                capitalCity.setName(rs.getString("CapitalCity"));
                capitalCity.setCountry(rs.getString("Country"));
                capitalCity.setPopulation(rs.getLong("Population"));
                capitalCities.add(capitalCity);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top " + n + " populated capital cities in region " + region);
        }

        return capitalCities;
    }



//    The top N populated capital cities in a region where N is provided by the user.
//    public ArrayList<CapitalCity> getTopNPopulatedCitiesInRegion(String region, int n) {
//        ArrayList<CapitalCity> capitalCityArrayList = new ArrayList<>();
//
//        String sql = "SELECT city.Name As Capacity"
//    }

}
