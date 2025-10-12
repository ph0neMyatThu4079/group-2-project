package com.napier.proj.model;

/**
 * Represents a country with basic geographic and demographic information.
 * <p>
 * This class stores the country's code, name, continent, region, population, and capital city.
 * It can be used for generating world population reports.
 * </p>
 *
 * @author Phone Myat Thu
 */
public class Country {
    private String code;
    private String name;
    private String continent;
    private String region;
    private long population;
    private String capital; // capital name (join with city table)

    /**
     * Returns the country's code (ISO 3-letter code).
     *
     * @return the code of the country
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the country's code (ISO 3-letter code).
     *
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns the country's name.
     *
     * @return the name of the country
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the country's name.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Returns the continent where the country is located.
     *
     * @return the continent
     */
    public String getContinent() {
        return continent;
    }

    /**
     * Sets the continent of the country.
     *
     * @param continent the continent to set
     */
    public void setContinent(String continent) {
        this.continent = continent;
    }

    /**
     * Returns the region of the country.
     *
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the region of the country.
     *
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Returns the total population of the country.
     *
     * @return the population
     */
    public long getPopulation() {
        return population;
    }

    /**
     * Sets the population of the country.
     *
     * @param population the population to set
     */
    public void setPopulation(long population) {
        this.population = population;
    }

    /**
     * Returns the name of the country's capital city.
     *
     * @return the capital city name
     */
    public String getCapital() {
        return capital;
    }

    /**
     * Sets the capital city name of the country.
     *
     * @param capital the capital city to set
     */
    public void setCapital(String capital) {
        this.capital = capital;
    }
}
