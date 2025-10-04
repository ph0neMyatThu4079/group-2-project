package com.napier.proj.model;

public class Language {
    private String language;
    private long speakers;
    private double worldPercentage;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getSpeakers() {
        return speakers;
    }

    public void setSpeakers(long speakers) {
        this.speakers = speakers;
    }

    public double getWorldPercentage() {
        return worldPercentage;
    }

    public void setWorldPercentage(double worldPercentage) {
        this.worldPercentage = worldPercentage;
    }
}
