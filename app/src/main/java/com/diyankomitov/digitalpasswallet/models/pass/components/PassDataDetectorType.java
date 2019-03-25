package com.diyankomitov.digitalpasswallet.models.pass.components;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 * This enum represents a field data detector type of a pass and provides a method to convert
 * between the pkpass string representation of this enum and this enum.
 * <p>
 * If a field has any of these detector types associated with it, the UI should identify such types
 * in the text and highlight them.
 */
public enum PassDataDetectorType {
    
    /**
     * Phone Number detector type.
     */
    PHONE_NUMBER("PKDataDetectorTypePhoneNumber"),
    /**
     * Link detector type.
     */
    LINK("PKDataDetectorTypeLink"),
    /**
     * Address detector type.
     */
    ADDRESS("PKDataDetectorTypeAddress"),
    /**
     * Calendar Event detector type.
     */
    CALENDAR_EVENT("PKDataDetectorTypeCalendarEvent"),
    ;
    
    private static final Map<String, PassDataDetectorType> LOOKUP_MAP;
    
    static {
        LOOKUP_MAP = new HashMap<>();
        for (PassDataDetectorType passDataDetectorType : PassDataDetectorType.values()) {
            LOOKUP_MAP.put(passDataDetectorType.pkDataDetectorType, passDataDetectorType);
        }
    }
    
    private String pkDataDetectorType;
    
    PassDataDetectorType(String pkDataDetectorType) {
        
        this.pkDataDetectorType = pkDataDetectorType;
    }
    
    /**
     * Gets the detector type represented by the given pkpass format string.
     *
     * @param pkDataDetectorType the pkpass format string
     *
     * @return the detector type
     */
    public static PassDataDetectorType getFromPkString(String pkDataDetectorType) {
        
        return LOOKUP_MAP.get(pkDataDetectorType);
    }
}
