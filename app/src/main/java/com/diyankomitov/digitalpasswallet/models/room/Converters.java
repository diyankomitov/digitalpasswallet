package com.diyankomitov.digitalpasswallet.models.room;

import com.diyankomitov.digitalpasswallet.models.pass.components.PassBarcode;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassBeacon;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassField;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassLocation;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassTransitType;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import androidx.room.TypeConverter;

/**
 * This class provides Type Converters used by Android Room to store components of a Pass. It uses
 * Gson to convert the components to JSON strings before storing them as well as to convert them
 * back.
 *
 * TypeToken GSON code provided by Aman Gupta at https://stackoverflow.com/a/48260739/
 */
class Converters {
    
    private static final Gson gson = new Gson();
    
    /**
     * Converts a json string to a list of pass fields
     *
     * @param json the json string
     *
     * @return the pass fields
     */
    @TypeConverter
    public static List<PassField> jsonToPassFieldList(String json) {
        
        if (json == null) {
            return new ArrayList<>();
        }
        
        Type listType = new TypeToken<List<PassField>>() {
        }.getType();
        return gson.fromJson(json, listType);
    }
    
    /**
     * Converts a list of pass fields to a json string
     *
     * @param passFields the pass fields
     *
     * @return the json string
     */
    @TypeConverter
    public static String passFieldListToJson(List<PassField> passFields) {
        
        return gson.toJson(passFields);
    }
    
    /**
     * Converts a json string to a list of pass barcodes
     *
     * @param json the json string
     *
     * @return the pass barcodes
     */
    @TypeConverter
    public static List<PassBarcode> jsonToPassBarcodeList(String json) {
        
        if (json == null) {
            return new ArrayList<>();
        }
        
        Type listType = new TypeToken<List<PassBarcode>>() {
        }.getType();
        return gson.fromJson(json, listType);
    }
    
    /**
     * Converts a list of pass barcodes to a json string
     *
     * @param passBarcodes the pass barcodes
     *
     * @return the json string
     */
    @TypeConverter
    public static String passBarcodeListToJson(List<PassBarcode> passBarcodes) {
        
        return gson.toJson(passBarcodes);
    }
    
    /**
     * Converts a json string to a list of pass beacons
     *
     * @param json the json string
     *
     * @return the pass beacons
     */
    @TypeConverter
    public static List<PassBeacon> jsonToPassBeaconList(String json) {
        
        if (json == null) {
            return new ArrayList<>();
        }
        
        Type listType = new TypeToken<List<PassBeacon>>() {
        }.getType();
        return gson.fromJson(json, listType);
    }
    
    /**
     * Converts a list of pass beacons to a json string
     *
     * @param passBeacons the pass beacons
     *
     * @return the json string
     */
    @TypeConverter
    public static String passBeaconListToJson(List<PassBeacon> passBeacons) {
        
        return gson.toJson(passBeacons);
    }
    
    /**
     * Converts a json string to a list of pass locations
     *
     * @param json the json string
     *
     * @return the pass locations
     */
    @TypeConverter
    public static List<PassLocation> jsonToPassLocationList(String json) {
        
        if (json == null) {
            return new ArrayList<>();
        }
        
        Type listType = new TypeToken<List<PassLocation>>() {
        }.getType();
        return gson.fromJson(json, listType);
    }
    
    /**
     * Converts a list of pass locations to a json string
     *
     * @param passLocations the pass locations
     *
     * @return the json string
     */
    @TypeConverter
    public static String passLocationListToJson(List<PassLocation> passLocations) {
        
        return gson.toJson(passLocations);
    }
    
    /**
     * Converts a json string to a pass type
     *
     * @param json the json string
     *
     * @return the pass type
     */
    @TypeConverter
    public static PassType jsonToPassType(String json) {
        
        if (json == null) {
            return PassType.GENERIC;
        }
        
        Type listType = new TypeToken<PassType>() {
        }.getType();
        return gson.fromJson(json, listType);
    }
    
    /**
     * Converts a pass type to a json string
     *
     * @param passType the pass type
     *
     * @return the json string
     */
    @TypeConverter
    public static String passTypeToJson(PassType passType) {
        
        return gson.toJson(passType);
    }
    
    /**
     * Converts a json string to a pass transit type
     *
     * @param json the json string
     *
     * @return the pass transit type
     */
    @TypeConverter
    public static PassTransitType jsonToPassTransitType(String json) {
        
        if (json == null) {
            return PassTransitType.GENERIC;
        }
        
        Type listType = new TypeToken<PassTransitType>() {
        }.getType();
        return gson.fromJson(json, listType);
    }
    
    /**
     * Converts a pass transit type to a json string
     *
     * @param passTransitType the pass transit type
     *
     * @return the json string
     */
    @TypeConverter
    public static String passTransitTypeToJson(PassTransitType passTransitType) {
        
        return gson.toJson(passTransitType);
    }
}
