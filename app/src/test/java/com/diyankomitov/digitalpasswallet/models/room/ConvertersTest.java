package com.diyankomitov.digitalpasswallet.models.room;

import com.diyankomitov.digitalpasswallet.models.pass.components.PassBarcode;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassBarcodeFormat;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassDateTimeStyle;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassField;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassNumberStyle;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassTextAlignment;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassTransitType;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassType;
import com.google.zxing.common.CharacterSetECI;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class ConvertersTest {
    
    @Test
    public void testPassFieldListConvert() {
        
        List<PassField> passFields = new ArrayList<>();
        PassField passField = new PassField();
        passField.setKey("key");
        passField.setLabel("label");
        passField.setValue("value");
        passField.setAttributedValue("attributedValue");
        passField.setChangeMessage("changeMessage");
        passField.setTextAlignment(PassTextAlignment.CENTER);
        passField.setDataDetectorTypes(new HashSet<>());
        passField.setDateStyle(PassDateTimeStyle.FULL);
        passField.setTimeStyle(PassDateTimeStyle.FULL);
        passField.setIgnoresTimeZone(true);
        passField.setRelative(true);
        passField.setCurrencyCode("currencyCode");
        passField.setNumberStyle(PassNumberStyle.SCIENTIFIC);
        passFields.add(passField);
        
        String converted = Converters.passFieldListToJson(passFields);
        List<PassField> convertedFields = Converters.jsonToPassFieldList(converted);
        
        assertThat(convertedFields).hasSize(1);
        PassField convertedField = convertedFields.get(0);
        assertThat(convertedField.getKey()).matches(passField.getKey());
        assertThat(convertedField.getLabel()).matches(passField.getLabel());
        assertThat(convertedField.getValue()).matches(passField.getValue());
        assertThat(convertedField.getAttributedValue()).matches(passField.getAttributedValue());
        assertThat(convertedField.getChangeMessage()).matches(passField.getChangeMessage());
        assertThat(convertedField.getTextAlignment()).isEqualTo(passField.getTextAlignment());
        assertThat(convertedField.getDataDetectorTypes()).containsExactlyElementsIn(
                passField.getDataDetectorTypes());
        assertThat(convertedField.getDateStyle()).isEqualTo(passField.getDateStyle());
        assertThat(convertedField.isIgnoresTimeZone()).isEqualTo(passField.isIgnoresTimeZone());
        assertThat(convertedField.isRelative()).isEqualTo(passField.isRelative());
        assertThat(convertedField.getCurrencyCode()).matches(passField.getCurrencyCode());
        assertThat(convertedField.getNumberStyle()).isEqualTo(passField.getNumberStyle());
    }
    
    @Test
    public void testPassBarcodeListConvert() {
        
        List<PassBarcode> passBarcodes = new ArrayList<>();
        PassBarcode passBarcode = new PassBarcode();
        passBarcode.setMessage("message");
        passBarcode.setAltText("altText");
        passBarcode.setFormat(PassBarcodeFormat.AZTEC);
        passBarcode.setMessageEncoding(CharacterSetECI.ASCII);
        passBarcodes.add(passBarcode);
        
        String converted = Converters.passBarcodeListToJson(passBarcodes);
        List<PassBarcode> convertedFields = Converters.jsonToPassBarcodeList(converted);
        
        assertThat(convertedFields).hasSize(1);
        PassBarcode convertedField = convertedFields.get(0);
        assertThat(convertedField.getMessage()).matches("message");
        assertThat(convertedField.getAltText()).matches("altText");
        assertThat(convertedField.getFormat()).isEqualTo(PassBarcodeFormat.AZTEC);
        assertThat(convertedField.getMessageEncoding()).isEqualTo(CharacterSetECI.ASCII);
    }
    
    @Test
    public void testPassTypeConvert() {
        
        String convertedString = Converters.passTypeToJson(PassType.BOARDING_PASS);
        PassType converted = Converters.jsonToPassType(convertedString);
        assertThat(converted).isEqualTo(PassType.BOARDING_PASS);
        
        convertedString = Converters.passTypeToJson(PassType.EVENT_TICKET);
        converted = Converters.jsonToPassType(convertedString);
        assertThat(converted).isEqualTo(PassType.EVENT_TICKET);
        
        convertedString = Converters.passTypeToJson(PassType.COUPON);
        converted = Converters.jsonToPassType(convertedString);
        assertThat(converted).isEqualTo(PassType.COUPON);
        
        convertedString = Converters.passTypeToJson(PassType.STORE_CARD);
        converted = Converters.jsonToPassType(convertedString);
        assertThat(converted).isEqualTo(PassType.STORE_CARD);
        
        convertedString = Converters.passTypeToJson(PassType.GENERIC);
        converted = Converters.jsonToPassType(convertedString);
        assertThat(converted).isEqualTo(PassType.GENERIC);
        
    }
    @Test
    public void testPassTransitTypeConvert() {
        
        String convertedString = Converters.passTransitTypeToJson(PassTransitType.AIR);
        PassTransitType converted = Converters.jsonToPassTransitType(convertedString);
        assertThat(converted).isEqualTo(PassTransitType.AIR);
        
        convertedString = Converters.passTransitTypeToJson(PassTransitType.BOAT);
        converted = Converters.jsonToPassTransitType(convertedString);
        assertThat(converted).isEqualTo(PassTransitType.BOAT);
        
        convertedString = Converters.passTransitTypeToJson(PassTransitType.BUS);
        converted = Converters.jsonToPassTransitType(convertedString);
        assertThat(converted).isEqualTo(PassTransitType.BUS);
        
        convertedString = Converters.passTransitTypeToJson(PassTransitType.GENERIC);
        converted = Converters.jsonToPassTransitType(convertedString);
        assertThat(converted).isEqualTo(PassTransitType.GENERIC);
        
        convertedString = Converters.passTransitTypeToJson(PassTransitType.TRAIN);
        converted = Converters.jsonToPassTransitType(convertedString);
        assertThat(converted).isEqualTo(PassTransitType.TRAIN);
    }
}
