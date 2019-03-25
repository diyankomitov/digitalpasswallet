package com.diyankomitov.digitalpasswallet.models.pass.components;

import com.google.zxing.BarcodeFormat;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

/**
 * This enum represents the barcode format of a pass barcode and provides methods for converting
 * between the pkpass string representation of the format and the zxing BarcodeFormat
 * representation.
 */
public enum PassBarcodeFormat {
    
    /**
     * QR barcode format.
     */
    QR("PKBarcodeFormatQR", BarcodeFormat.QR_CODE),
    /**
     * PDF417 barcode format.
     */
    PDF417("PKBarcodeFormatPDF417", BarcodeFormat.PDF_417),
    /**
     * AZTEC barcode format.
     */
    AZTEC("PKBarcodeFormatAztec", BarcodeFormat.AZTEC),
    /**
     * CODE128 barcode format.
     */
    CODE128("PKBarcodeFormatCode128", BarcodeFormat.CODE_128),
    ;
    
    private static final Map<String, PassBarcodeFormat> PK_LOOKUP_MAP;
    
    static {
        PK_LOOKUP_MAP = new HashMap<>();
        for (PassBarcodeFormat passBarcodeFormat : PassBarcodeFormat.values()) {
            PK_LOOKUP_MAP.put(passBarcodeFormat.pkBarcodeFormat, passBarcodeFormat);
        }
    }
    
    private static final Map<BarcodeFormat, PassBarcodeFormat> ZXING_LOOKUP_MAP;
    
    static {
        ZXING_LOOKUP_MAP = new HashMap<>();
        for (PassBarcodeFormat passBarcodeFormat : PassBarcodeFormat.values()) {
            ZXING_LOOKUP_MAP.put(passBarcodeFormat.zXingBarcodeFormat, passBarcodeFormat);
        }
    }
    
    private final String pkBarcodeFormat;
    private final BarcodeFormat zXingBarcodeFormat;
    
    PassBarcodeFormat(String pkBarcodeFormat, BarcodeFormat zXingBarcodeFormat) {
        
        this.pkBarcodeFormat = pkBarcodeFormat;
        this.zXingBarcodeFormat = zXingBarcodeFormat;
    }
    
    /**
     * Gets the zxing BarcodeFormat representation of this barcode format.
     *
     * @return the zxing barcode format
     */
    public BarcodeFormat getZXingBarcodeFormat() {
        
        return zXingBarcodeFormat;
    }
    
    /**
     * Gets the format represented by the given zxing barcode format.
     *
     * @param zxingFormat the zxing barcode format
     *
     * @return the format or QR if the given zxing format was invalid
     */
    
    @NonNull
    public static PassBarcodeFormat getFromZxingFormat(BarcodeFormat zxingFormat) {
        
        PassBarcodeFormat passBarcodeFormat = ZXING_LOOKUP_MAP.get(zxingFormat);
        
        return passBarcodeFormat == null ? QR : passBarcodeFormat;
    }
    
    /**
     * Gets the format represented by the given pkpass format string.
     *
     * @param pkBarcodeFormat the pkpass format string
     *
     * @return the format or QR if the given string was invalid
     */
    @NonNull
    public static PassBarcodeFormat getFromPkString(String pkBarcodeFormat) {
        
        PassBarcodeFormat passBarcodeFormat = PK_LOOKUP_MAP.get(pkBarcodeFormat);
        
        return passBarcodeFormat == null ? QR : passBarcodeFormat;
    }
}
