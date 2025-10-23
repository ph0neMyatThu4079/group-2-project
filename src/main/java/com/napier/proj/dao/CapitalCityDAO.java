package com.napier.proj.dao;

import com.napier.proj.model.CapitalCity;
import com.napier.proj.model.Population;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * This is Data Access Object (DAO) class for managing all database operations related to the CapitalCity entity.
 * <p>
 * This class provides methods to retrieve and display capital cities data
 * from the database, sorted by population in various contexts (world, continent, region).
 * </p>
 *
 * @author Phone Myat Thu
 */
public class CapitalCityDAO {

    /** Database connection object used for executing SQL queries */
    private Connection con;

    /**
     * Constructs a CapitalCityDAO instance with the given database connection.
     *
     * @param con The active database connection.
     */
    public CapitalCityDAO(Connection con) {
        this.con = con;
    }

    /**
     * Retrieves all capital cities in the world ordered by population from largest to smallest.
     *
     * @return A list of capital cities sorted by population.
     */
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


    /**
     * Retrieves all capital cities in a specific continent ordered by population from largest to smallest.
     *
     * @param continent The continent name (e.g., "Asia", "Europe").
     * @return A list of capital cities within the specified continent.
     */
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


    /**
     * Retrieves all capital cities in a specific region ordered by population from largest to smallest.
     *
     * @param region The region name (e.g., "Southeast Asia", "Western Europe").
     * @return A list of capital cities within the specified region.
     */
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

    /**
     * Retrieves the top N most populated capital cities in the world.
     *
     * @param n The number of capital cities to return.
     * @return A list of the top N populated capital cities.
     */
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

    /**
            * Retrieves the top N most populated capital cities in a specific continent.
            *
            * @param continent The continent name (e.g., "Africa", "Asia").
            * @param n The number of capital cities to return.
            * @return A list of the top N populated capital cities within the specified continent.
     */
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

    /**
     * Retrieves the top N most populated capital cities in a specific region.
     *
     * @param region The region name (e.g., "Eastern Europe", "Caribbean").
     * @param n The number of capital cities to return.
     * @return A list of the top N populated capital cities within the specified region.
     */
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


}
