package com.napier.proj.model;

public class Population {
    private String name;              // continent, region, or country
    private long totalPopulation;
    private long cityPopulation;
    private long nonCityPopulation;
    private double cityPercentage;
    private double nonCityPercentage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(long totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    public long getCityPopulation() {
        return cityPopulation;
    }

    public void setCityPopulation(long cityPopulation) {
        this.cityPopulation = cityPopulation;
    }

    public long getNonCityPopulation() {
        return nonCityPopulation;
    }

    public void setNonCityPopulation(long nonCityPopulation) {
        this.nonCityPopulation = nonCityPopulation;
    }

    public double getCityPercentage() {
        return cityPercentage;
    }

    public void setCityPercentage(double cityPercentage) {
        this.cityPercentage = cityPercentage;
    }

    public double getNonCityPercentage() {
        return nonCityPercentage;
    }

    public void setNonCityPercentage(double nonCityPercentage) {
        this.nonCityPercentage = nonCityPercentage;
    }
}
