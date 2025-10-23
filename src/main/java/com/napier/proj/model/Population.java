package com.napier.proj.model;

/**
 * Represents population statistics for a continent, region, or country.
 * <p>
 * This class stores total population, population living in cities, non-city population,
 * and their respective percentages. Useful for creating population distribution reports.
 * </p>
 *
 * @author Phone Myat Thu
 */
public class Population {
    private String name;              // continent, region, or country
    private long totalPopulation;
    private long cityPopulation;
    private long nonCityPopulation;
    private double cityPercentage;
    private double nonCityPercentage;

    /**
     * Returns the name of the entity (continent, region, or country).
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the entity (continent, region, or country).
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the total population of the entity.
     *
     * @return the total population
     */
    public long getTotalPopulation() {
        return totalPopulation;
    }

    /**
     * Sets the total population of the entity.
     *
     * @param totalPopulation the total population to set
     */
    public void setTotalPopulation(long totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    /**
     * Returns the population living in cities.
     *
     * @return the city population
     */
    public long getCityPopulation() {
        return cityPopulation;
    }

    /**
     * Sets the population living in cities.
     *
     * @param cityPopulation the city population to set
     */
    public void setCityPopulation(long cityPopulation) {
        this.cityPopulation = cityPopulation;
    }

    /**
     * Returns the population not living in cities.
     *
     * @return the non-city population
     */
    public long getNonCityPopulation() {
        return nonCityPopulation;
    }

    /**
     * Sets the population not living in cities.
     *
     * @param nonCityPopulation the non-city population to set
     */
    public void setNonCityPopulation(long nonCityPopulation) {
        this.nonCityPopulation = nonCityPopulation;
    }

    /**
     * Returns the percentage of population living in cities.
     *
     * @return the city population percentage
     */
    public double getCityPercentage() {
        return cityPercentage;
    }

    /**
     * Sets the percentage of population living in cities.
     *
     */
    public void setCityPercentage() {
        if(this.totalPopulation > 0){
            double percentage = ((double) this.cityPopulation /  this.totalPopulation) * 100;
            this.cityPercentage = Math.round(percentage);
        }
        else {
            this.cityPercentage = 0;
        }
    }

    /**
     * Returns the percentage of population not living in cities.
     *
     * @return the non-city population percentage
     */
    public double getNonCityPercentage() {
        return nonCityPercentage;
    }

    /**
     * Sets the percentage of population not living in cities.
     *
     */
    public void setNonCityPercentage() {
        if(this.totalPopulation > 0){
            double percentage = ((double) this.nonCityPopulation /  this.totalPopulation) * 100;
            this.nonCityPercentage = Math.round(percentage);
        }
        else {
            this.nonCityPercentage = 0;
        }
    }
}
