package com.diyankomitov.digitalpasswallet.models.pass.components;

import android.os.Parcel;
import android.os.Parcelable;

import com.diyankomitov.digitalpasswallet.models.pass.Utils;
import com.google.zxing.common.CharacterSetECI;

import java.util.Objects;

import androidx.annotation.NonNull;

/**
 * This represents a barcode shown on the front of the pass.
 */
public class PassBarcode implements Parcelable {
    
    @NonNull
    private String message;
    @NonNull
    private String altText;
    @NonNull
    private PassBarcodeFormat format;
    @NonNull
    private CharacterSetECI messageEncoding;
    
    /**
     * Instantiates a new Pass barcode.
     *
     * @param message         the message
     * @param altText         the alt text
     * @param format          the format
     * @param messageEncoding the message encoding
     */
    public PassBarcode(String message, String altText, PassBarcodeFormat format,
                       CharacterSetECI messageEncoding) {
        
        this.message = message == null ? "" : message;
        this.altText = altText == null ? "" : altText;
        this.format = format == null ? PassBarcodeFormat.QR : format;
        this.messageEncoding =
                messageEncoding == null ? CharacterSetECI.ISO8859_1 : messageEncoding;
    }
    
    /**
     * Instantiates a new Pass barcode.
     */
    public PassBarcode() {
        
        this.message = "";
        this.altText = "";
        this.format = PassBarcodeFormat.QR;
        this.messageEncoding = CharacterSetECI.ISO8859_1;
    }
    
    // Parcelable
    
    /**
     * Instantiates a new Pass barcode.
     *
     * @param in the in
     */
    protected PassBarcode(Parcel in) throws NullPointerException {
        
        message = Objects.requireNonNull(in.readString());
        altText = Objects.requireNonNull(in.readString());
        format = PassBarcodeFormat.values()[in.readInt()];
        messageEncoding = CharacterSetECI.values()[in.readInt()];
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        
        dest.writeString(message);
        dest.writeString(altText);
        Utils.writeEnumToParcel(dest, format);
        Utils.writeEnumToParcel(dest, messageEncoding);
    }
    
    @Override
    public int describeContents() {
        
        return 0;
    }
    
    /**
     * The constant CREATOR needed by Parcelable.
     */
    public static final Creator<PassBarcode> CREATOR = new Creator<PassBarcode>() {
        @Override
        public PassBarcode createFromParcel(Parcel in) {
            
            return new PassBarcode(in);
        }
        
        @Override
        public PassBarcode[] newArray(int size) {
            
            return new PassBarcode[size];
        }
    };
    
    // Getters and Setters
    
    /**
     * Gets message.
     *
     * @return the message
     */
    @NonNull
    public String getMessage() {
        
        return message;
    }
    
    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        
        this.message = message == null ? "" : message;
    }
    
    /**
     * Gets alt text.
     *
     * @return the alt text
     */
    @NonNull
    public String getAltText() {
        
        return altText;
    }
    
    /**
     * Sets alt text.
     *
     * @param altText the alt text
     */
    public void setAltText(String altText) {
        
        this.altText = altText == null ? "" : altText;
    }
    
    /**
     * Gets format.
     *
     * @return the format
     */
    @NonNull
    public PassBarcodeFormat getFormat() {
        
        return format;
    }
    
    /**
     * Sets format.
     *
     * @param passBarcodeFormat the pass barcode format
     */
    public void setFormat(PassBarcodeFormat passBarcodeFormat) {
        
        this.format = passBarcodeFormat == null ? PassBarcodeFormat.QR : passBarcodeFormat;
    }
    
    /**
     * Gets message encoding.
     *
     * @return the message encoding
     */
    @NonNull
    public CharacterSetECI getMessageEncoding() {
        
        return messageEncoding;
    }
    
    /**
     * Sets message encoding.
     *
     * @param messageEncoding the message encoding
     */
    public void setMessageEncoding(String messageEncoding) {
        
        this.messageEncoding = messageEncoding == null
                               ? CharacterSetECI.ISO8859_1
                               : CharacterSetECI.getCharacterSetECIByName(
                                       messageEncoding.toUpperCase());
    }
    
    /**
     * Sets message encoding.
     *
     * @param messageEncoding the message encoding
     */
    public void setMessageEncoding(CharacterSetECI messageEncoding) {
        
        this.messageEncoding =
                messageEncoding == null ? CharacterSetECI.ISO8859_1 : messageEncoding;
    }
}
