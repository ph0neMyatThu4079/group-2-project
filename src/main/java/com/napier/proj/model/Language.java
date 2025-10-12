package com.napier.proj.model;

/**
 * Represents a language spoken in the world.
 * <p>
 * This class stores the language name, the number of speakers, and the percentage of the world's population that speaks it.
 * Useful for linguistic analysis in world population reports.
 * </p>
 *
 * @author Phone Myat Thu
 */
public class Language {
    private String language;
    private long speakers;
    private double worldPercentage;

    /**
     * Returns the language name.
     *
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the language name.
     *
     * @param language the language name to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Returns the number of people speaking this language.
     *
     * @return the number of speakers
     */
    public long getSpeakers() {
        return speakers;
    }

    /**
     * Sets the number of speakers of this language.
     *
     * @param speakers the number of speakers to set
     */
    public void setSpeakers(long speakers) {
        this.speakers = speakers;
    }

    /**
     * Returns the percentage of the world population speaking this language.
     *
     * @return the world population percentage
     */
    public double getWorldPercentage() {
        return worldPercentage;
    }

    /**
     * Sets the percentage of the world population speaking this language.
     *
     * @param worldPercentage the world population percentage to set
     */
    public void setWorldPercentage(double worldPercentage) {
        this.worldPercentage = worldPercentage;
    }
}
