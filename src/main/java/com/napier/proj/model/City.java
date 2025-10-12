package com.napier.proj.model;

/**
 * Represents a city with demographic and geographic information.
 * <p>
 * This class stores the city's name, the country it belongs to, its district, and its population.
 * Useful for city-level population analysis in world population reports.
 * </p>
 *
 * @author Phone Myat Thu
 */
public class City {
    private String name;
    private String country;   // country name (join with Country)
    private String district;
    private long population;

    /**
     * Returns the name of the city.
     *
     * @return the city name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the city.
     *
     * @param name the city name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the country name that the city belongs to.
     *
     * @return the country name
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country name of the city.
     *
     * @param country the country name to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the district of the city.
     *
     * @return the district name
     */
    public String getDistrict() {
        return district;
    }

    /**
     * Sets the district of the city.
     *
     * @param district the district name to set
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * Returns the population of the city.
     *
     * @return the population
     */
    public long getPopulation() {
        return population;
    }

    /**
     * Sets the population of the city.
     *
     * @param population the population to set
     */
    public void setPopulation(long population) {
        this.population = population;
    }
}
