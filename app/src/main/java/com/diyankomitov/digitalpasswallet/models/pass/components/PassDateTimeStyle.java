package com.diyankomitov.digitalpasswallet.models.pass.components;

import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 * This enum represents the date or time style of a pass field and provides methods to convert
 * between the pkpass string representation and java.time FormatStyle representation
 */
public enum PassDateTimeStyle {
    
    /**
     * No style.
     */
    NONE("PKDateStyleNone", null),
    /**
     * Short style.
     */
    SHORT("PKDateStyleShort", FormatStyle.SHORT),
    /**
     * Medium style.
     */
    MEDIUM("PKDateStyleMedium", FormatStyle.MEDIUM),
    /**
     * Long style.
     */
    LONG("PKDateStyleLong", FormatStyle.LONG),
    /**
     * Full style.
     */
    FULL("PKDateStyleFull", FormatStyle.FULL),
    ;
    
    private static final Map<String, PassDateTimeStyle> LOOKUP_MAP;
    
    static {
        LOOKUP_MAP = new HashMap<>();
        for (PassDateTimeStyle passDateTimeStyle : PassDateTimeStyle.values()) {
            LOOKUP_MAP.put(passDateTimeStyle.pkDateStyle, passDateTimeStyle);
        }
    }
    
    private final String pkDateStyle;
    private final FormatStyle formatStyle;
    
    PassDateTimeStyle(String pkDateStyle, FormatStyle formatStyle) {
        
        this.pkDateStyle = pkDateStyle;
        this.formatStyle = formatStyle;
    }
    
    /**
     * Gets the java.time FormatStyle representation of this style.
     *
     * @return the java.time FormatStyle representation of this style
     */
    public FormatStyle getFormatStyle() {
        
        return formatStyle;
    }
    
    /**
     * Gets the style represented by the given pkpass format string
     *
     * @param pkDateStyle the pkpass format string
     *
     * @return the style or NONE if the given string was invalid
     */
    @NonNull
    public static PassDateTimeStyle getFromPkString(String pkDateStyle) {
        
        PassDateTimeStyle passDateTimeStyle = LOOKUP_MAP.get(pkDateStyle);
        
        return passDateTimeStyle == null ? NONE : passDateTimeStyle;
    }
}
