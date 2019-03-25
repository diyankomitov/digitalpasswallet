package com.diyankomitov.digitalpasswallet.models.pass.components;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

public class PassFieldTest {
    
    private static PassField passField;
    
    @Before
    public void setUp() {
        
        passField = new PassField();
    }
    
    // Test defaults
    
    @Test
    public void testKeyDefault() {
        
        assertThat(passField.getKey()).isEmpty();
    }
    
    @Test
    public void testValueDefault() {
        
        assertThat(passField.getValue()).isEmpty();
    }
    
    @Test
    public void testLabelDefault() {
        
        assertThat(passField.getLabel()).isEmpty();
    }
    
    @Test
    public void testAttributedValueDefault() {
        
        assertThat(passField.getAttributedValue()).isEmpty();
    }
    
    @Test
    public void testChangeMessageDefault() {
        
        assertThat(passField.getChangeMessage()).isEmpty();
    }
    
    @Test
    public void testTextAlignmentDefault() {
        
        assertThat(passField.getTextAlignment()).isEqualTo(PassTextAlignment.NATURAL);
    }
    
    @Test
    public void testDataDetectorTypesDefault() {
        
        assertThat(passField.getDataDetectorTypes()).containsExactly(PassDataDetectorType.ADDRESS,
                                                                     PassDataDetectorType.CALENDAR_EVENT,
                                                                     PassDataDetectorType.LINK,
                                                                     PassDataDetectorType.PHONE_NUMBER);
    }
    
    @Test
    public void testDateStyleDefault() {
        
        assertThat(passField.getDateStyle()).isEqualTo(PassDateTimeStyle.NONE);
    }
    
    @Test
    public void testTimeStyleDefault() {
        
        assertThat(passField.getTimeStyle()).isEqualTo(PassDateTimeStyle.NONE);
    }
    
    @Test
    public void testIsIgnoresTimeZoneDefault() {
        
        assertThat(passField.isIgnoresTimeZone()).isFalse();
    }
    
    @Test
    public void testIsRelativeDefault() {
        
        assertThat(passField.isRelative()).isFalse();
    }
    
    @Test
    public void testCurrencyCodeDefault() {
        
        assertThat(passField.getCurrencyCode()).isEmpty();
    }
    
    @Test
    public void testNumberStyleDefault() {
        
        assertThat(passField.getNumberStyle()).isEqualTo(PassNumberStyle.NONE);
    }
    
    //Test setting to null should give default
    
    @Test
    public void testKeySetToNullReturnsDefault() {
        
        passField.setKey(null);
        assertThat(passField.getKey()).isEmpty();
    }
    
    @Test
    public void testValueSetToNullReturnsDefault() {
        
        passField.setValue(null);
        assertThat(passField.getValue()).isEmpty();
    }
    
    @Test
    public void testLabelSetToNullReturnsDefault() {
        
        passField.setLabel(null);
        assertThat(passField.getLabel()).isEmpty();
    }
    
    @Test
    public void testAttributedValueSetToNullReturnsDefault() {
        
        passField.setAttributedValue(null);
        assertThat(passField.getAttributedValue()).isEmpty();
    }
    
    @Test
    public void testChangeMessageSetToNullReturnsDefault() {
        
        passField.setChangeMessage(null);
        assertThat(passField.getChangeMessage()).isEmpty();
    }
    
    @Test
    public void testTextAlignmentSetToNullReturnsDefault() {
        
        passField.setTextAlignment(null);
        assertThat(passField.getTextAlignment()).isEqualTo(PassTextAlignment.NATURAL);
    }
    
    @Test
    public void testDataDetectorTypesSetToNullReturnsDefault() {
        
        passField.setDataDetectorTypes(null);
        assertThat(passField.getDataDetectorTypes()).containsExactly(PassDataDetectorType.ADDRESS,
                                                                     PassDataDetectorType.CALENDAR_EVENT,
                                                                     PassDataDetectorType.LINK,
                                                                     PassDataDetectorType.PHONE_NUMBER);
    }
    
    @Test
    public void testDateStyleSetToNullReturnsDefault() {
        
        passField.setDateStyle(null);
        assertThat(passField.getDateStyle()).isEqualTo(PassDateTimeStyle.NONE);
    }
    
    @Test
    public void testTimeStyleSetToNullReturnsDefault() {
        
        passField.setTimeStyle(null);
        assertThat(passField.getTimeStyle()).isEqualTo(PassDateTimeStyle.NONE);
    }
    
    @Test
    public void testCurrencyCodeSetToNullReturnsDefault() {
        
        passField.setCurrencyCode(null);
        assertThat(passField.getCurrencyCode()).isEmpty();
    }
    
    @Test
    public void testNumberStyleSetToNullReturnsDefault() {
        
        passField.setNumberStyle(null);
        assertThat(passField.getNumberStyle()).isEqualTo(PassNumberStyle.NONE);
    }
    
    //Test setting sets correctly
    
    @Test
    public void testKeySetReturnsCorrectly() {
        
        passField.setKey("key");
        assertThat(passField.getKey()).matches("key");
    }
    
    @Test
    public void testValueSetReturnsCorrectly() {
        
        passField.setValue("value");
        assertThat(passField.getValue()).matches("value");
    }
    
    @Test
    public void testLabelSetReturnsCorrectly() {
        
        passField.setLabel("label");
        assertThat(passField.getLabel()).matches("label");
    }
    
    @Test
    public void testAttributedValueSetReturnsCorrectly() {
        
        passField.setAttributedValue("attributedValue");
        assertThat(passField.getAttributedValue()).matches("attributedValue");
    }
    
    @Test
    public void testChangeMessageSetReturnsCorrectly() {
        
        passField.setChangeMessage("changeMessage");
        assertThat(passField.getChangeMessage()).matches("changeMessage");
    }
    
    @Test
    public void testTextAlignmentSetReturnsCorrectly() {
        
        passField.setTextAlignment(PassTextAlignment.CENTER);
        assertThat(passField.getTextAlignment()).isEqualTo(PassTextAlignment.CENTER);
    }
    
    @Test
    public void testDataDetectorTypesSetReturnsCorrectly() {
        
        Set<PassDataDetectorType> dataDetectorTypes = new HashSet<>();
        passField.setDataDetectorTypes(dataDetectorTypes);
        assertThat(passField.getDataDetectorTypes()).isEqualTo(dataDetectorTypes);
    }
    
    @Test
    public void testDateStyleSetReturnsCorrectly() {
        
        passField.setDateStyle(PassDateTimeStyle.FULL);
        assertThat(passField.getDateStyle()).isEqualTo(PassDateTimeStyle.FULL);
    }
    
    @Test
    public void testTimeStyleSetReturnsCorrectly() {
        
        passField.setTimeStyle(PassDateTimeStyle.FULL);
        assertThat(passField.getTimeStyle()).isEqualTo(PassDateTimeStyle.FULL);
    }
    
    @Test
    public void testCurrencyCodeSetReturnsCorrectly() {
        
        passField.setCurrencyCode("currencyCode");
        assertThat(passField.getCurrencyCode()).matches("currencyCode");
    }
    
    @Test
    public void testNumberStyleSetReturnsCorrectly() {
        
        passField.setNumberStyle(PassNumberStyle.SCIENTIFIC);
        assertThat(passField.getNumberStyle()).isEqualTo(PassNumberStyle.SCIENTIFIC);
    }
}