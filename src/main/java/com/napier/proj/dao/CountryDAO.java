package com.napier.proj.dao;

import com.napier.proj.model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This is Data Access Object (DAO) class for managing all database operations related to the Country entity.
 * <p>
 * This class provides methods to retrieve and display country data
 * from the database, sorted by population in various contexts (world, continent, region).
 * </p>
 *
 * @author So Pyay Tun
 * @author Phone Myat Thu
 */
public class CountryDAO {
    /** Database connection object used for executing SQL queries */
    private Connection con;

    /**
     * Constructs a CountryDAO instance with the given database connection.
     *
     * @param con The active database connection.
     */
    public CountryDAO(Connection con) {
        this.con = con;
    }

    /**
     * Retrieves all countries in the world, sorted by population (descending order).
     *
     * @return A list of Country objects representing all countries in the world.
     */
    public List<Country> getAllCountriesByPopulation() {
        List<Country> countries = new ArrayList<>();

        System.out.println("\nAll the countries in the world organised by largest population to smallest.\n");

        // SQL query to retrieve all countries in the world by descending order
        String sql =
                "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                        "FROM country c " +
                        "LEFT JOIN city ci ON c.Capital = ci.ID " +
                        "ORDER BY c.Population DESC;";

        try(PreparedStatement pstmt = con.prepareStatement(sql)){
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    Country country = new Country();
                    country.setCode(rs.getString("Code"));
                    country.setName(rs.getString("Name"));
                    country.setContinent(rs.getString("Continent"));
                    country.setRegion(rs.getString("Region"));
                    country.setPopulation(rs.getLong("Population"));
                    country.setCapital(rs.getString("Capital"));
                    countries.add(country);
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries");
        }

        return  countries;
    }

    /**
     * Retrieves all countries in a specific continent, sorted by population (descending order).
     *
     * @param continent The name of the continent to filter by.
     * @return A list of Country objects for the specified continent.
     */
    public List<Country> getAllCountriesInContinentByPopulation(String continent) {
        List<Country> countries = new ArrayList<>();

        System.out.println("\nAll the countries in a continent organised by largest population to smallest.\n");

        // SQL query with parameter for continent
        String sql =
                    "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                            "FROM country c " +
                            "LEFT JOIN city ci ON c.Capital = ci.ID " +
                            "WHERE c.Continent = ?"+
                            "ORDER BY c.Population DESC;";

        try(PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1,continent); // Set the continent paramater

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    Country country = new Country();
                    country.setCode(rs.getString("Code"));
                    country.setName(rs.getString("Name"));
                    country.setContinent(rs.getString("Continent"));
                    country.setRegion(rs.getString("Region"));
                    country.setPopulation(rs.getLong("Population"));
                    country.setCapital(rs.getString("Capital"));
                    countries.add(country);
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries by population for continent: " + continent);
        }
        return countries;
    }


    /**
     * Retrieves all countries in a specific region, sorted by population (descending order).
     *
     * @param region The name of the region to filter by.
     * @return A list of Country objects for the specified region.
     */
    public List<Country> getAllCountriesInRegionByPopulation(String region) {
        List<Country> countries = new ArrayList<>();

        System.out.println("\nAll the countries in a region organised by largest population to smallest.\n");

        // SQL query to get countries in a region
        String sql =
                "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                        "FROM country c " +
                        "LEFT JOIN city ci ON c.Capital = ci.ID " +
                        "WHERE c.Region = ? " +
                        "ORDER BY c.Population DESC;";

        try(PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1,region); // Set the region paramater

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    Country country = new Country();
                    country.setCode(rs.getString("Code"));
                    country.setName(rs.getString("Name"));
                    country.setContinent(rs.getString("Continent"));
                    country.setRegion(rs.getString("Region"));
                    country.setPopulation(rs.getLong("Population"));
                    country.setCapital(rs.getString("Capital"));
                    countries.add(country);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries by population for region: " + region);
        }

        return countries;
    }


    /**
     * Retrieves the top N populated countries in the world.
     *
     * @param n The number of top countries to retrieve.
     * @return A list of the top N Country objects.
     */
    public List<Country> getTopNPopulatedCountriesIntheworld(int n)  {
        List<Country> countries = new ArrayList<>();

        System.out.println("\nThe top N populated countries in the world where N is provided by the user.\n");

        // SQL query with LIMIT for top N countries
        String sql =
                "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                        "FROM country c " +
                        "LEFT JOIN city ci ON c.Capital = ci.ID " +
                        "ORDER BY c.Population DESC " +
                        "LIMIT ?;";

            try(PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, n); // Set limit value

                try(ResultSet rs = pstmt.executeQuery()){
                    while (rs.next()) {
                        Country country = new Country();
                        country.setCode(rs.getString("Code"));
                        country.setName(rs.getString("Name"));
                        country.setContinent(rs.getString("Continent"));
                        country.setRegion(rs.getString("Region"));
                        country.setPopulation(rs.getLong("Population"));
                        country.setCapital(rs.getString("Capital"));
                        countries.add(country);
                    }
                }
            }

         catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top " + n + " populated countries");
        }

        return countries;
    }

    /**
     * Retrieves the top N populated countries in a specific continent.
     *
     * @param continent The name of the continent.
     * @param n         The number of countries to retrieve.
     * @return A list of the top N Country objects in the specified continent.
     */
    public List<Country> getTopNPopulatedCountriesInContinent(String continent, int n) {
        List<Country> countries = new ArrayList<>();

        System.out.println("\nThe top N populated countries in a continent where N is provided by the user.\n");

        // SQL query with continent filter and limit
        String sql =
                "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                        "FROM country c " +
                        "LEFT JOIN city ci ON c.Capital = ci.ID " +
                        "WHERE c.Continent = ? " +
                        "ORDER BY c.Population DESC " +
                        "LIMIT ?;";

        try(PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, continent); // Set continent paramater
            pstmt.setInt(2, n); // Set limit

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Country country = new Country();
                    country.setCode(rs.getString("Code"));
                    country.setName(rs.getString("Name"));
                    country.setContinent(rs.getString("Continent"));
                    country.setRegion(rs.getString("Region"));
                    country.setPopulation(rs.getLong("Population"));
                    country.setCapital(rs.getString("Capital"));
                    countries.add(country);
                }
            }
        }
         catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top " + n + " populated countries in continent: " + continent);
        }

        return countries;
    }


    /**
     * Retrieves the top N populated countries in a specific region.
     *
     * @param region The name of the region.
     * @param n      The number of countries to retrieve.
     * @return A list of the top N Country objects in the specified region.
     */
    public List<Country> getTopNPopulatedCountriesInRegion(String region, int n) {
        List<Country> countries = new ArrayList<>();


        System.out.println("\nThe top N populated countries in a region where N is provided by the user.\n");

        // SQL query with region filter and limit
        String sql =
                "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                        "FROM country c " +
                        "LEFT JOIN city ci ON c.Capital = ci.ID " +
                        "WHERE c.Region = ? " +
                        "ORDER BY c.Population DESC " +
                        "LIMIT ?;";

        try(PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, region); // Set region paramater
            pstmt.setInt(2, n); // Set limit

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Country country = new Country();
                    country.setCode(rs.getString("Code"));
                    country.setName(rs.getString("Name"));
                    country.setContinent(rs.getString("Continent"));
                    country.setRegion(rs.getString("Region"));
                    country.setPopulation(rs.getLong("Population"));
                    country.setCapital(rs.getString("Capital"));
                    countries.add(country);
                }
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top " + n + " populated countries in region: " + region);
        }

        return countries;
    }

}
