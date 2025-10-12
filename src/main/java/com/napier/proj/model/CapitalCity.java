package com.napier.proj.model;


/**
 * Represents a capital city with its basic demographic information.
 * <p>
 * This class stores the capital city's name, the country it belongs to,
 * and its population. It is mainly used for generating reports that
 * display population data of capital cities across different countries.
 * </p>
 *
 * @author Phone Myat Thu
 */
public class CapitalCity {
    private String name;
    private String country;
    private long population;

    /**
     * Returns the name of the capital city.
     *
     * @return the name of the capital city
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the capital city.
     *
     * @param name the name of the capital city to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the country to which this capital city belongs.
     *
     * @return the country name
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the name of the country to which this capital city belongs.
     *
     * @param country the country name to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the population of the capital city.
     *
     * @return the population
     */
    public long getPopulation() {
        return population;
    }

    /**
     * Sets the population of the capital city.
     *
     * @param population the population to set
     */
    public void setPopulation(long population) {
        this.population = population;
    }
}
