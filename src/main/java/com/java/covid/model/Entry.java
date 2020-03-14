package com.java.covid.model;

public class Entry {

    private String state;
    private String country;
    private int latestCases;
    private int deltaPreviousDate;

    public int getDeltaPreviousDate() {
        return deltaPreviousDate;
    }

    public void setDeltaPreviousDate(int deltaPreviousDate) {
        this.deltaPreviousDate = deltaPreviousDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestCases() {
        return latestCases;
    }

    public void setLatestCases(int latestCases) {
        this.latestCases = latestCases;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latestTotalCases=" + latestCases +
                '}';
    }
}
