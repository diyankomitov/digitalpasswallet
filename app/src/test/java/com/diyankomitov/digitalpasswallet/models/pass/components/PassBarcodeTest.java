package com.diyankomitov.digitalpasswallet.models.pass.components;

import com.google.zxing.common.CharacterSetECI;

import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class PassBarcodeTest {
    
    private static PassBarcode passBarcode;
    
    @Before
    public void setUp() {
    
        passBarcode = new PassBarcode();
    }
    
    // Test defaults
    
    @Test
    public void testMessageDefault() {
        
        assertThat(passBarcode.getMessage()).isEmpty();
    }
    
    @Test
    public void testAltTextDefault() {
        
        assertThat(passBarcode.getAltText()).isEmpty();
    }
    
    @Test
    public void testFormatDefault() {
        
        assertThat(passBarcode.getFormat()).isEqualTo(PassBarcodeFormat.QR);
    }
    
    @Test
    public void testMessageEncodingDefault() {
        
        assertThat(passBarcode.getMessageEncoding()).isEqualTo(CharacterSetECI.ISO8859_1);
    }
    
    //Test setting to null should give default
    
    @Test
    public void testMessageSetToNullReturnsDefault() {
        
        passBarcode.setMessage(null);
        assertThat(passBarcode.getMessage()).isEmpty();
    }
    
    @Test
    public void testAltTextSetToNullReturnsDefault() {
        
        passBarcode.setAltText(null);
        assertThat(passBarcode.getAltText()).isEmpty();
    }
    
    @Test
    public void testFormatSetToNullReturnsDefault() {
        
        passBarcode.setFormat(null);
        assertThat(passBarcode.getFormat()).isEqualTo(PassBarcodeFormat.QR);
    }
    
    @Test
    public void testMessageEncodingSetToNullReturnsDefault() {
        
        passBarcode.setMessageEncoding((CharacterSetECI) null);
        assertThat(passBarcode.getMessageEncoding()).isEqualTo(CharacterSetECI.ISO8859_1);
    }
    
    @Test
    public void testMessageEncodingStringSetToNullReturnsDefault() {
        
        passBarcode.setMessageEncoding((String) null);
        assertThat(passBarcode.getMessageEncoding()).isEqualTo(CharacterSetECI.ISO8859_1);
    }
    
    //Test setting sets correctly
    
    @Test
    public void testMessageSetReturnsCorrectly() {
        
        passBarcode.setMessage("message");
        assertThat(passBarcode.getMessage()).matches("message");
    }
    
    @Test
    public void testAltTextSetReturnsCorrectly() {
        
        passBarcode.setAltText("altText");
        assertThat(passBarcode.getAltText()).matches("altText");
    }
    
    @Test
    public void testFormatSetReturnsCorrectly() {
        
        passBarcode.setFormat(PassBarcodeFormat.AZTEC);
        assertThat(passBarcode.getFormat()).isEqualTo(PassBarcodeFormat.AZTEC);
    }
    
    @Test
    public void testMessageEncodingSetReturnsCorrectly() {
        
        passBarcode.setMessageEncoding(CharacterSetECI.ASCII);
        assertThat(passBarcode.getMessageEncoding()).isEqualTo(CharacterSetECI.ASCII);
    }
    
    @Test
    public void testMessageEncodingStringSetReturnsCorrectly() {
        
        passBarcode.setMessageEncoding("ISO-8859-1");
        assertThat(passBarcode.getMessageEncoding()).isEqualTo(CharacterSetECI.ISO8859_1);
    }
}