package com.napier.proj.dao;

import com.napier.proj.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This is Data Access Object (DAO) class for managing database operations related to the City entity.
 * <p>
 * This class provides various methods to retrieve city data from the database,
 * filtered and sorted by population at different levels — world, continent, region, country, and district.
 * </p>
 *
 * @author Min Wanna Hlan
 */
public class CityDAO {
    /** Database connection object used for executing SQL queries */
    private Connection con;

    /**
     * Constructs a CityDAO with the given database connection.
     *
     * @param con The database connection to be used for queries.
     */
    public CityDAO(Connection con) {
        this.con = con;
    }

    /**
     * Retrieves all cities in the world, sorted by population in descending order.
     *
     * @return A list of City objects representing all cities in the world.
     */
    public List<City> getAllinWorldCitiesByPopulation() {
        List<City> cities = new ArrayList<>();

        // SQL query to fetch city, country, district, and population details, ordered by population (desc)
        String sql = "SELECT ci.Name AS CityName, c.Name AS Country, ci.District, ci.Population " +
                "FROM city ci " +
                "JOIN country c ON ci.CountryCode = c.Code " +
                "ORDER BY ci.Population DESC;";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            // Loop through the result set and populate the city list
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

    /**
     * Retrieves all cities in a specific continent, sorted by population in descending order.
     *
     * @param continent The name of the continent to filter cities by.
     * @return A list of City objects representing cities in the given continent.
     */
    public List<City> getAllCitiesInContinentByPopulation(String continent) {
        List<City> cities = new ArrayList<>();

        // SQL query to fetch all cities within a given continent
        String sql = "SELECT ci.Name AS CityName, c.Name AS Country, ci.District, ci.Population " +
                "FROM city ci " +
                "JOIN country c ON ci.CountryCode = c.Code " +
                "WHERE c.Continent = ? " +
                "ORDER BY ci.Population DESC;";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, continent);  // set continent parameter
            ResultSet rs = pstmt.executeQuery();

            // Populate list with query results
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
            System.out.println("Failed to get cities in continent");
        }

        return cities;
    }


    /**
     * Retrieves all cities in a specific region, sorted by population in descending order.
     *
     * @param region The name of the region to filter cities by.
     * @return A list of City objects representing cities in the given region.
     */
    public List<City> getAllCitiesInRegionByPopulation(String region) {
        List<City> cities = new ArrayList<>();

        // SQL query to fetch all cities within a given region
        String sql = "SELECT ci.Name AS CityName, c.Name AS Country, ci.District, ci.Population " +
                "FROM city ci " +
                "JOIN country c ON ci.CountryCode = c.Code " +
                "WHERE c.Region = ? " +
                "ORDER BY ci.Population DESC;";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, region);  // set region parameter
            ResultSet rs = pstmt.executeQuery();

            // Populate list with query results
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
            System.out.println("Failed to get cities in region");
        }

        return cities;
    }


    /**
     * Retrieves all cities in a specific country, sorted by population in descending order.
     *
     * @param country The name of the country to filter cities by.
     * @return A list of City objects representing cities in the given country.
     */
    public List<City> getAllCitiesInCountryByPopulation(String country) {
        List<City> cities = new ArrayList<>();

        // SQL query to fetch all cities within a given country
        String sql = "SELECT ci.Name AS CityName, c.Name AS Country, ci.District, ci.Population " +
                "FROM city ci " +
                "JOIN country c ON ci.CountryCode = c.Code " +
                "WHERE c.Name = ? " +
                "ORDER BY ci.Population DESC;";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, country);  // set country parameter
            ResultSet rs = pstmt.executeQuery();

            // Populate list with query results
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
            System.out.println("Failed to get cities in country");
        }

        return cities;
    }


    /**
     * Retrieves all cities in a specific district, sorted by population in descending order.
     *
     * @param district The name of the district to filter cities by.
     * @return A list of City objects representing cities in the given district.
     */
    public List<City> getAllCitiesInDistrictByPopulation(String district) {
        List<City> cities = new ArrayList<>();

        // SQL query to fetch all cities within a given district
        String sql = "SELECT ci.Name AS CityName, c.Name AS Country, ci.District, ci.Population " +
                "FROM city ci " +
                "JOIN country c ON ci.CountryCode = c.Code " +
                "WHERE ci.District = ? " +
                "ORDER BY ci.Population DESC;";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, district);  // set district parameter
            ResultSet rs = pstmt.executeQuery();

            // Populate list with query results
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
            System.out.println("Failed to get cities in district");
        }

        return cities;
    }


}
