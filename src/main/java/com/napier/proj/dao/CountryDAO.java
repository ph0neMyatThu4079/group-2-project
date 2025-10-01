package com.napier.proj.dao;

import com.napier.proj.model.Country;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
                country.code = rs.getString("Code");
                country.name = rs.getString("Name");
                country.continent = rs.getString("Continent");
                country.region = rs.getString("Region");
                country.population = rs.getLong("Population");
                country.capital = rs.getString("Capital");
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
//    public List<Country> getCountriesByContinent(String continent) { ... }

    // 3. All countries in a region
//    public List<Country> getCountriesByRegion(String region) { ... }

    // 4. Top N countries in the world
//    public List<Country> getTopNCountries(int n) { ... }

    // 5. Top N countries in the continent
//    public List<Country> getTopNCountriesInContinent(String continent, int n) { ... }

    // 6. Top N countries in a region
//    public List<Country> getTopNCountriesInRegion(String region, int n) { ... }

}
