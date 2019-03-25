package com.diyankomitov.digitalpasswallet.viewmodel.pass.util;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.Locale;

import androidx.annotation.Nullable;

public class DateTimeUtil {
    
    //TODO: implement isRelative
    public static String getFormattedString(String string, @Nullable FormatStyle dateStyle,
                                            @Nullable FormatStyle timeStyle, boolean ignoreTimeZone,
                                            boolean isRelative) {
        
        if (dateStyle == null && timeStyle == null) {
            // if both are null then it is not a date
            return string;
        }
        
        // get parse string into offsetdatetime using an ISO-8601 formatter
        // OffsetDateTime represents a datetime with a timezone offset like +01:00, -02:00 or Z(for UTC)
        OffsetDateTime dateTime = OffsetDateTime.parse(string, DateTimeFormatter.ISO_DATE_TIME);
        
        // get the ZoneId which will either display the time in local timezone or given timezone
        ZoneId zoneId = ignoreTimeZone ? ZoneId.from(dateTime) : ZoneId.systemDefault();
        
        // create the format pattern given by the styles;
        String dateTimePattern = DateTimeFormatterBuilder
                .getLocalizedDateTimePattern(dateStyle, timeStyle, IsoChronology.INSTANCE,
                                             Locale.getDefault());
        
        // create the formatter
        DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern(dateTimePattern).withZone(zoneId);
        
        return dateTime.format(dateTimeFormatter);
    }
}
