package com.diyankomitov.digitalpasswallet.models.pass;

public class PassLocation {

    private double altitude;
    private double latitude;
    private double longitude;
    private String relevantText;

    public PassLocation() {
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getRelevantText() {
        return relevantText;
    }

    public void setRelevantText(String relevantText) {
        this.relevantText = relevantText;
    }
}
