package com.napier.proj.dao;

import com.napier.proj.model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO {
    private Connection con;

    public CountryDAO(Connection con) {
        this.con = con;
    }

    // 1. All countries in the world
    public List<Country> getAllCountriesByPopulation() {
        List<Country> countries = new ArrayList<>();

        System.out.println("\nAll the countries in the world organised by largest population to smallest.\n");

        try{
            Statement stmt = con.createStatement();

            String sql =
                    "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                            "FROM country c " +
                            "LEFT JOIN city ci ON c.Capital = ci.ID " +
                            "ORDER BY c.Population DESC;";

            ResultSet rs = stmt.executeQuery(sql);
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
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries");
        }

        return  countries;
    }

    // 2. All countries in a continent
        public List<Country> getAllCountriesInContinentByPopulation(String continent) {
        List<Country> countries = new ArrayList<>();

        System.out.println("\nAll the countries in a continent organised by largest population to smallest.\n");

        String sql =
                    "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                            "FROM country c " +
                            "LEFT JOIN city ci ON c.Capital = ci.ID " +
                            "WHERE c.Continent = ?"+
                            "ORDER BY c.Population DESC;";

        try(PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1,continent);

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


    // 3. All countries in a region
    public List<Country> getAllCountriesInRegionByPopulation(String region) {
        List<Country> countries = new ArrayList<>();

        System.out.println("\nAll the countries in a region organised by largest population to smallest.\n");

        String sql =
                "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                        "FROM country c " +
                        "LEFT JOIN city ci ON c.Capital = ci.ID " +
                        "WHERE c.Region = ? " +
                        "ORDER BY c.Population DESC;";

        try(PreparedStatement pstmt = con.prepareStatement(sql)){
            pstmt.setString(1,region);

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


    // 4. Top N countries in the world
    public List<Country> getTopNPopulatedCountriesIntheworld(int n)  {
        List<Country> countries = new ArrayList<>();

        System.out.println("\nThe top N populated countries in the world where N is provided by the user.\n");

            String sql =
                    "SELECT c.Code, c.Name, c.Continent, c.Region, c.Population, ci.Name AS Capital " +
                            "FROM country c " +
                            "LEFT JOIN city ci ON c.Capital = ci.ID " +
                            "ORDER BY c.Population DESC " +
                            "LIMIT ?;";

            try(PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setInt(1, n);

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


    // 5. Top N countries in the continent
//    public List<Country> getTopNCountriesInContinent(String continent, int n) { ... }

    // 6. Top N countries in a region
//    public List<Country> getTopNCountriesInRegion(String region, int n) { ... }

}
