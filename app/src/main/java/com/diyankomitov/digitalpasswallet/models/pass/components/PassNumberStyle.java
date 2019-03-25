package com.diyankomitov.digitalpasswallet.models.pass.components;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 * This enum represents the number style of a pass field and provides a method to convert between
 * the pkpass string representation of this enum and this enum
 */
public enum PassNumberStyle {
    
    /**
     * None pass number style.
     */
    NONE(""),
    /**
     * Decimal pass number style.
     */
    DECIMAL("PKNumberStyleDecimal"),
    /**
     * Percent pass number style.
     */
    PERCENT("PKNumberStylePercent"),
    /**
     * Scientific pass number style.
     */
    SCIENTIFIC("PKNumberStyleScientific"),
    /**
     * Spell Out pass number style.
     */
    SPELL_OUT("PKNumberStyleSpellOut"),
    ;
    
    private static final Map<String, PassNumberStyle> LOOKUP_MAP;
    
    static {
        LOOKUP_MAP = new HashMap<>();
        for (PassNumberStyle passNumberStyle : PassNumberStyle.values()) {
            LOOKUP_MAP.put(passNumberStyle.pkNumberStyle, passNumberStyle);
        }
    }
    
    private String pkNumberStyle;
    
    PassNumberStyle(String pkNumberStyle) {
        
        this.pkNumberStyle = pkNumberStyle;
    }
    
    /**
     * Gets the style represented by the given pkpass format string.
     *
     * @param pkNumberStyle the pkpass format string
     *
     * @return the style or NONE if the given string was invalid
     */
    public static PassNumberStyle getFromPkString(String pkNumberStyle) {
        
        PassNumberStyle passNumberStyle = LOOKUP_MAP.get(pkNumberStyle);
        return passNumberStyle == null ? NONE : passNumberStyle;
    }
}
