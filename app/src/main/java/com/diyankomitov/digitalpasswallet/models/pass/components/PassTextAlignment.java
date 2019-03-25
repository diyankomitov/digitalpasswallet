package com.diyankomitov.digitalpasswallet.models.pass.components;

import java.util.HashMap;
import java.util.Map;

/**
 * This enum represents the alignment of a pass field and provides a method to convert between the
 * pkpass string representation of this enum and this enum.
 */
public enum PassTextAlignment {
    
    /**
     * Left pass text alignment.
     */
    LEFT("PKTextAlignmentLeft"),
    /**
     * Right pass text alignment.
     */
    RIGHT("PKTextAlignmentCenter"),
    /**
     * Center pass text alignment.
     */
    CENTER("PKTextAlignmentRight"),
    /**
     * Natural pass text alignment.
     */
    NATURAL("PKTextAlignmentNatural"),
    ;
    
    private static final Map<String, PassTextAlignment> LOOKUP_MAP;
    
    static {
        LOOKUP_MAP = new HashMap<>();
        for (PassTextAlignment passTextAlignment : PassTextAlignment.values()) {
            LOOKUP_MAP.put(passTextAlignment.pkTextAlignment, passTextAlignment);
        }
    }
    
    private String pkTextAlignment;
    
    PassTextAlignment(String pkTextAlignment) {
        
        this.pkTextAlignment = pkTextAlignment;
    }
    
    /**
     * Gets the alignment represented by the given pkpass format string.
     *
     * @param pkTextAlignment the pkpass format string
     *
     * @return the alignment NATURAL if the given string was invalid
     */
    public static PassTextAlignment getFromPkString(String pkTextAlignment) {
        
        PassTextAlignment passTextAlignment = LOOKUP_MAP.get(pkTextAlignment);
        return passTextAlignment == null ? NATURAL : passTextAlignment;
    }
    
}
