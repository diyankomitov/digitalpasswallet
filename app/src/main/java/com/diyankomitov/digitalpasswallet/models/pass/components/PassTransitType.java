package com.diyankomitov.digitalpasswallet.models.pass.components;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 * This enum represents the transit type for Boarding Pass style passes and provides a method to
 * convert between the pkpass string representation of this enum and this enum.
 */
public enum PassTransitType {
    /**
     * Air pass transit type.
     */
    AIR("PKTransitTypeAir"),
    /**
     * Boat pass transit type.
     */
    BOAT("PKTransitTypeBoat"),
    /**
     * Bus pass transit type.
     */
    BUS("PKTransitTypeBus"),
    /**
     * Generic pass transit type.
     */
    GENERIC("PKTransitTypeGeneric"),
    /**
     * Train pass transit type.
     */
    TRAIN("PKTransitTypeTrain"),
    ;
    
    private static final String TAG = "PassTransitType";
    
    private static final Map<String, PassTransitType> LOOKUP_MAP;
    
    static {
        LOOKUP_MAP = new HashMap<>();
        for (PassTransitType passTransitType : PassTransitType.values()) {
            LOOKUP_MAP.put(passTransitType.pkTransitType, passTransitType);
        }
    }
    
    private String pkTransitType;
    
    PassTransitType(String pkTransitType) {
        
        this.pkTransitType = pkTransitType;
    }
    
    /**
     * Gets the transit type represented by the given pkpass format string.
     *
     * @param pkTransitType the pkpass format string.
     *
     * @return the transit type or GENERIC if the given string was invalid
     */
    @NonNull
    public static PassTransitType getFromPkString(String pkTransitType) {
        
        PassTransitType passTransitType = LOOKUP_MAP.get(pkTransitType);
        
        return passTransitType == null ? GENERIC : passTransitType;
    }
    
}
