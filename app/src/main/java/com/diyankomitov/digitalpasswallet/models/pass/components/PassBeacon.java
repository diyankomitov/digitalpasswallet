package com.diyankomitov.digitalpasswallet.models.pass.components;

public class PassBeacon {
    
    private int major;
    private int minor;
    private String proximityUUID;
    private String relevantText;
    
    public PassBeacon() {
    
    }
    
    public int getMajor() {
        
        return major;
    }
    
    public void setMajor(int major) {
        
        this.major = major;
    }
    
    public int getMinor() {
        
        return minor;
    }
    
    public void setMinor(int minor) {
        
        this.minor = minor;
    }
    
    public String getProximityUUID() {
        
        return proximityUUID;
    }
    
    public void setProximityUUID(String proximityUUID) {
        
        this.proximityUUID = proximityUUID;
    }
    
    public String getRelevantText() {
        
        return relevantText;
    }
    
    public void setRelevantText(String relevantText) {
        
        this.relevantText = relevantText;
    }
}
