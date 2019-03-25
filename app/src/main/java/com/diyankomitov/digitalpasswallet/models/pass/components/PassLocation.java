package com.diyankomitov.digitalpasswallet.models.pass.components;

public class PassLocation {
    
    public static final double NO_VALUE = -500.0;
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
