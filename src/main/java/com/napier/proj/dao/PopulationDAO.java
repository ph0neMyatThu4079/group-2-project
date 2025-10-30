package com.napier.proj.dao;

import java.sql.Connection;
import com.napier.proj.model.Population;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This is Data Access Object (DAO) class for managing all database operations related to the Population entity.
 * <p>
 * This class provides methods to retrieve and display population data
 * from the database showing people living and not living in cities in each continent, region, and country
 * </p>
 *
 * @author Phone Myat Thu
 * @author Lin Myat Thu
 */
public class PopulationDAO {
    /** Database connection object used for executing SQL queries */
    private Connection con;

    /**
     * Constructs a PopulationDAO instance with the given database connection.
     *
     * @param con The active database connection.
     */
    public PopulationDAO(Connection con) {
        this.con = con;
    }

    /**
     * Retrieves the total population, city population, and non-city population for each continent.
     *
     * @return A list of Population objects containing data for each continent.
     */
    public List<Population> getEachContinentPopulationWithUrbanAndNonUrban() {
        List<Population> populations = new ArrayList<>();

        String sql = "SELECT " +
                "    co.Continent AS Continent, " +
                "    SUM(co.Population) AS total_population, " +
                "    COALESCE(SUM(city_pop.total_city_pop), 0) AS city_population, " +
                "    SUM(co.Population) - COALESCE(SUM(city_pop.total_city_pop), 0) AS non_city_population " +
                "FROM country co " +
                "LEFT JOIN ( " +
                "    SELECT CountryCode, SUM(Population) AS total_city_pop " +
                "    FROM city " +
                "    GROUP BY CountryCode " +
                ") city_pop ON co.Code = city_pop.CountryCode " +
                "GROUP BY co.Continent " +
                "ORDER BY co.Continent ";

        try(PreparedStatement pstmt = con.prepareStatement(sql)){


            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Population population = new Population();
                population.setName(rs.getString("Continent"));
                population.setTotalPopulation(rs.getLong("total_population"));
                population.setCityPopulation(rs.getLong("city_population"));
                population.setNonCityPopulation(rs.getLong("non_city_population"));
                population.setCityPercentage();
                population.setNonCityPercentage();
                populations.add(population);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details in each continent ");
        }

        return populations;
    }

    /**
     * Retrieves the total population, city population, and non-city population for each region.
     *
     * @return A list of Population objects containing data for each region.
     */
    public List<Population> getEachRegionPopulationWithUrbanAndNonUrban() {
        List<Population> populations = new ArrayList<>();

        String sql = "SELECT " +
                "    co.Region AS Region, " +
                "    SUM(co.Population) AS total_population, " +
                "    COALESCE(SUM(city_pop.total_city_pop), 0) AS city_population, " +
                "    SUM(co.Population) - COALESCE(SUM(city_pop.total_city_pop), 0) AS non_city_population " +
                "FROM country co " +
                "LEFT JOIN ( " +
                "    SELECT CountryCode, SUM(Population) AS total_city_pop " +
                "    FROM city " +
                "    GROUP BY CountryCode " +
                ") city_pop ON co.Code = city_pop.CountryCode " +
                "GROUP BY co.Region " +
                "ORDER BY co.Region ";

        try(PreparedStatement pstmt = con.prepareStatement(sql)){


            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Population population = new Population();
                population.setName(rs.getString("Region"));
                population.setTotalPopulation(rs.getLong("total_population"));
                population.setCityPopulation(rs.getLong("city_population"));
                population.setNonCityPopulation(rs.getLong("non_city_population"));
                population.setCityPercentage();
                population.setNonCityPercentage();
                populations.add(population);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details in each region ");
        }

        return populations;
    }

    /**
     * Retrieves the total population, city population, and non-city population for each country.
     *
     * @return A list of Population objects containing data for each country.
     */
    public List<Population> getEachCountryPopulationWithUrbanAndNonUrban() {
        List<Population> populations = new ArrayList<>();

        String sql = "SELECT " +
                "    co.Name AS Name, " +
                "    co.Population AS total_population, " +
                "    COALESCE(SUM(ci.Population), 0) AS city_population, " +
                "    co.Population - COALESCE(SUM(ci.Population), 0) AS non_city_population " +
                "FROM country co " +
                "LEFT JOIN city ci ON co.Code = ci.CountryCode " +
                "GROUP BY co.Code " +
                "ORDER BY co.Name ";

        try(PreparedStatement pstmt = con.prepareStatement(sql)){

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Population population = new Population();
                population.setName(rs.getString("Name"));
                population.setTotalPopulation(rs.getLong("total_population"));
                population.setCityPopulation(rs.getLong("city_population"));
                population.setNonCityPopulation(rs.getLong("non_city_population"));
                population.setCityPercentage();
                population.setNonCityPercentage();
                populations.add(population);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details in each country ");
        }

        return populations;
    }
    /**
     * Retrieves the total population of the world.
     * @return A {@link Population} object representing world population.
     */
    public List<Population> getWorldPopulation() {
        List<Population> populations = new ArrayList<>();
        String sql = "SELECT SUM(Population) AS TotalPopulation FROM country;";

        try (PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Population population = new Population();
                population.setName("World");
                population.setTotalPopulation(rs.getLong("TotalPopulation"));
                populations.add(population);
            }

        } catch (SQLException e) {
            System.out.println("Failed to get world population: " + e.getMessage());
        }

        return populations;
    }
    /**
     * Retrieves the total population of a specific continent.
     * @param continentName The name of the continent.
     * @return A list containing one {@link Population} object representing continent population.
     */
    public List<Population> getContinentPopulation(String continentName) {
        List<Population> populations = new ArrayList<>();
        String sql = "SELECT SUM(Population) AS TotalPopulation FROM country WHERE Continent = ?;";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, continentName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Population population = new Population();
                population.setName(continentName);
                population.setTotalPopulation(rs.getLong("TotalPopulation"));
                populations.add(population);
            }

        } catch (SQLException e) {
            System.out.println("Failed to get continent population: " + e.getMessage());
        }

        return populations;
    }
    /**
     * Retrieves the total population of a specific region.
     * @param regionName The name of the region.
     * @return A list containing one {@link Population} object representing region population.
     */
    public List<Population> getRegionPopulation(String regionName) {
        List<Population> populations = new ArrayList<>();
        String sql = "SELECT SUM(Population) AS TotalPopulation FROM country WHERE Region = ?;";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, regionName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Population population = new Population();
                population.setName(regionName);
                population.setTotalPopulation(rs.getLong("TotalPopulation"));
                populations.add(population);
            }

        } catch (SQLException e) {
            System.out.println("Failed to get region population: " + e.getMessage());
        }

        return populations;
    }
    /**
     * Retrieves the total population of a specific country.
     * @param countryName The name of the country.
     * @return A list containing one {@link Population} object representing country population.
     */
    public List<Population> getCountryPopulation(String countryName) {
        List<Population> populations = new ArrayList<>();
        String sql = "SELECT Population AS TotalPopulation FROM country WHERE Name = ?;";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, countryName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Population population = new Population();
                population.setName(countryName);
                population.setTotalPopulation(rs.getLong("TotalPopulation"));
                populations.add(population);
            }

        } catch (SQLException e) {
            System.out.println("Failed to get country population: " + e.getMessage());
        }

        return populations;
    }
    /**
     * Retrieves the total population of a specific district.
     * @param districtName The name of the district.
     * @return A list containing one {@link Population} object representing district population.
     */
    public List<Population> getDistrictPopulation(String districtName) {
        List<Population> populations = new ArrayList<>();
        String sql = "SELECT SUM(Population) AS TotalPopulation FROM city WHERE District = ?;";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, districtName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Population population = new Population();
                population.setName(districtName);
                population.setTotalPopulation(rs.getLong("TotalPopulation"));
                populations.add(population);
            }

        } catch (SQLException e) {
            System.out.println("Failed to get district population: " + e.getMessage());
        }

        return populations;
    }
    /**
     * Retrieves the total population of a specific city.
     * @param cityName The name of the city.
     * @return A list containing one {@link Population} object representing city population.
     */
    public List<Population> getCityPopulation(String cityName) {
        List<Population> populations = new ArrayList<>();
        String sql = "SELECT Population AS TotalPopulation FROM city WHERE Name = ?;";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, cityName);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Population population = new Population();
                population.setName(cityName);
                population.setTotalPopulation(rs.getLong("TotalPopulation"));
                populations.add(population);
            }

        } catch (SQLException e) {
            System.out.println("Failed to get city population: " + e.getMessage());
        }

        return populations;
    }



}
