package com.diyankomitov.digitalpasswallet.views.form.util;

import android.graphics.Color;

import androidx.annotation.ColorInt;

public class ColorUtils {
    
    public static String getHexStringFromColorInt(@ColorInt int color) {
        return String.format("#%06X", (0xFFFFFF & color));
    }
    
    public static @ColorInt int getColorBasedOnBackgroundColor(@ColorInt int backgroundColor) {
    
        int red = Color.red(backgroundColor);
        int green = Color.green(backgroundColor);
        int blue = Color.blue(backgroundColor);
    
        return (red * 0.299 + green * 0.587 + blue * 0.114) > 186 ? Color.BLACK : Color.WHITE;
    }
    
}
