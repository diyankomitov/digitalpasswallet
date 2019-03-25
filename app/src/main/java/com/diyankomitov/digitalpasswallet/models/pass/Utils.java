package com.diyankomitov.digitalpasswallet.models.pass;

import android.os.Parcel;

import androidx.annotation.NonNull;

/**
 * This class provides some static utility methods needed by the models.
 */
public class Utils {
    
    /**
     * This is a convenience method that writes an enum to an Android Parcel.
     *
     * @param parcel the parcel
     * @param e      the enum
     */
    public static void writeEnumToParcel(Parcel parcel, @NonNull Enum e) {
            parcel.writeInt(e.ordinal());
    }
}
