package com.diyankomitov.digitalpasswallet.models.pass;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.CharacterSetECI;

public class PassBarcode {

    private String altText;
    private BarcodeFormat format;
    private String message;
    private CharacterSetECI messageEncoding;

    public PassBarcode(String message, String altText, BarcodeFormat format, CharacterSetECI messageEncoding) {

        this.message = message;
        this.altText = altText;
        this.format = format;
        this.messageEncoding = messageEncoding;
    }

    public PassBarcode() {

    }

    public String getAltText() {
        return altText;
    }

    public void setAltText(String altText) {
        this.altText = altText;
    }

    public BarcodeFormat getFormat() {
        return format;
    }

    public void setFormat(BarcodeFormat format) {
        this.format = format;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CharacterSetECI getMessageEncoding() {
        return messageEncoding;
    }

    public void setMessageEncoding(CharacterSetECI messageEncoding) {
        this.messageEncoding = messageEncoding;
    }

    public void setMessageEncoding(String messageEncoding) {
        this.messageEncoding = CharacterSetECI.getCharacterSetECIByName(messageEncoding.toUpperCase());
        //TODO: Potential issues with touppercase
    }

    public void setFormat(String format) {

        switch (format) {
            case "PKBarcodeFormatQR":
                this.format = BarcodeFormat.QR_CODE;
            case "PKBarcodeFormatPDF417":
                this.format = BarcodeFormat.PDF_417;
            case "PKBarcodeFormatAztec":
                this.format = BarcodeFormat.AZTEC;
            case "PKBarcodeFormatCode128":
                this.format = BarcodeFormat.CODE_128;
            default:
                this.format = BarcodeFormat.QR_CODE;
        }
        //TODO: Ensure it works correctly
    }
}
