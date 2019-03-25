package com.diyankomitov.digitalpasswallet.models.pass.components;

import android.os.Parcel;

import com.diyankomitov.digitalpasswallet.models.pass.Pass;
import com.diyankomitov.digitalpasswallet.models.pass.PassModel;
import com.google.zxing.common.CharacterSetECI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ParcelableTest {
    
    @Test
    public void testPassIsParcelable() {
        
        Pass pass = new PassModel(PassType.EVENT_TICKET);
        pass.setTransitType(PassTransitType.AIR);
        pass.setDescription("description");
        pass.setFormatVersion(10);
        pass.setOrganizationName("organizationName");
        pass.setPassTypeIdentifier ("passTypeIdentifier");
        pass.setSerialNumber("serialNumber");
        pass.setTeamIdentifier("teamIdentifier");
        pass.setExpirationDate("expirationDate");
        pass.setVoided(true);
        pass.setMaxDistance(11);
        pass.setRelevantDate("relevantDate");
        List<PassBarcode> barcodes = new ArrayList<>();
        pass.setBarcodes(barcodes);
        pass.setBackgroundColor(12);
        pass.setForegroundColor(13);
        pass.setLabelColor(14);
        pass.setGroupingIdentifier ("groupingIdentifier");
        pass.setLogoText("logoText");
        pass.setSuppressStripShine(true);
        pass.setAuthenticationToken("authenticationToken");
        pass.setWebServiceURL("webServiceURL");
        List<PassField> headerFields = new ArrayList<>();
        pass.setHeaderFields(headerFields);
        List<PassField> primaryFields = new ArrayList<>();
        pass.setPrimaryFields(primaryFields);
        List<PassField> secondaryFields = new ArrayList<>();
        pass.setSecondaryFields(secondaryFields);
        List<PassField> auxiliaryFields = new ArrayList<>();
        pass.setAuxiliaryFields(auxiliaryFields);
        List<PassField> backFields = new ArrayList<>();
        pass.setBackFields(backFields);
        pass.setBackgroundImagePath("backgroundImagePath");
        pass.setFooterPath ("footerPath");
        pass.setIconPath("iconPath");
        pass.setLogoPath("logoPath");
        pass.setStripPath("stripPath");
        pass.setThumbnailPath("thumbnailPath");
        
        Parcel parcel = Parcel.obtain();
        
        pass.writeToParcel(parcel, pass.describeContents());
        parcel.setDataPosition(0);
        
        Pass createdFromParcel = PassModel.CREATOR.createFromParcel(parcel);
        
        assertThat(createdFromParcel.getType()).isEqualTo(PassType.EVENT_TICKET);
        assertThat(createdFromParcel.getTransitType()).isEqualTo(PassTransitType.AIR);
        assertThat(createdFromParcel.getDescription()).matches("description");
        assertThat(createdFromParcel.getFormatVersion()).isEqualTo(10);
        assertThat(createdFromParcel.getOrganizationName()).matches("organizationName");
        assertThat(createdFromParcel.getPassTypeIdentifier()).matches("passTypeIdentifier");
        assertThat(createdFromParcel.getSerialNumber()).matches("serialNumber");
        assertThat(createdFromParcel.getTeamIdentifier()).matches("teamIdentifier");
        assertThat(createdFromParcel.getExpirationDate()).matches("expirationDate");
        assertThat(createdFromParcel.isVoided()).isTrue();
        assertThat(createdFromParcel.getMaxDistance()).isEqualTo(11);
        assertThat(createdFromParcel.getRelevantDate()).matches("relevantDate");
        assertThat(createdFromParcel.getBarcodes()).isEqualTo(barcodes);
        assertThat(createdFromParcel.getBackgroundColor()).isEqualTo(12);
        assertThat(createdFromParcel.getForegroundColor()).isEqualTo(13);
        assertThat(createdFromParcel.getLabelColor()).isEqualTo(14);
        assertThat(createdFromParcel.getGroupingIdentifier ()).matches("groupingIdentifier");
        assertThat(createdFromParcel.getLogoText()).matches("logoText");
        assertThat(createdFromParcel.isSuppressStripShine()).isTrue();
        assertThat(createdFromParcel.getAuthenticationToken()).matches("authenticationToken");
        assertThat(createdFromParcel.getWebServiceURL()).matches("webServiceURL");
        assertThat(createdFromParcel.getHeaderFields()).isEqualTo(headerFields);
        assertThat(createdFromParcel.getPrimaryFields()).isEqualTo(primaryFields);
        assertThat(createdFromParcel.getSecondaryFields()).isEqualTo(secondaryFields);
        assertThat(createdFromParcel.getAuxiliaryFields()).isEqualTo(auxiliaryFields);
        assertThat(createdFromParcel.getBackFields()).isEqualTo(backFields);
        assertThat(createdFromParcel.getBackgroundImagePath()).matches("backgroundImagePath");
        assertThat(createdFromParcel.getFooterPath ()).matches("footerPath");
        assertThat(createdFromParcel.getIconPath()).matches("iconPath");
        assertThat(createdFromParcel.getLogoPath()).matches("logoPath");
        assertThat(createdFromParcel.getStripPath()).matches("stripPath");
        assertThat(createdFromParcel.getThumbnailPath()).matches("thumbnailPath");
    }
    
    @Test
    public void testPassFieldIsParcelable() {
    
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
    
        Parcel parcel = Parcel.obtain();
    
        passField.writeToParcel(parcel, passField.describeContents());
        parcel.setDataPosition(0);
        
        PassField createdFromParcel = PassField.CREATOR.createFromParcel(parcel);
        
        assertThat(createdFromParcel.getKey()).matches("key");
        assertThat(createdFromParcel.getLabel()).matches("label");
        assertThat(createdFromParcel.getValue()).matches("value");
        assertThat(createdFromParcel.getAttributedValue()).matches("attributedValue");
        assertThat(createdFromParcel.getChangeMessage()).matches("changeMessage");
        assertThat(createdFromParcel.getTextAlignment()).isEqualTo(PassTextAlignment.CENTER);
        assertThat(createdFromParcel.getDataDetectorTypes()).isEmpty();
        assertThat(createdFromParcel.getDateStyle()).isEqualTo(PassDateTimeStyle.FULL);
        assertThat(createdFromParcel.isIgnoresTimeZone()).isTrue();
        assertThat(createdFromParcel.isRelative()).isTrue();
        assertThat(createdFromParcel.getCurrencyCode()).matches("currencyCode");
        assertThat(createdFromParcel.getNumberStyle()).isEqualTo(PassNumberStyle.SCIENTIFIC);
    }
    
    @Test
    public void testPassBarcodeIsParcelable() {
        
        PassBarcode passBarcode = new PassBarcode();
        passBarcode.setMessage("message");
        passBarcode.setAltText("altText");
        passBarcode.setFormat(PassBarcodeFormat.AZTEC);
        passBarcode.setMessageEncoding(CharacterSetECI.ASCII);
        
        Parcel parcel = Parcel.obtain();
        
        passBarcode.writeToParcel(parcel, passBarcode.describeContents());
        parcel.setDataPosition(0);
        
        PassBarcode createdFromParcel = PassBarcode.CREATOR.createFromParcel(parcel);
        
        assertThat(createdFromParcel.getMessage()).matches("message");
        assertThat(createdFromParcel.getAltText()).matches("altText");
        assertThat(createdFromParcel.getFormat()).isEqualTo(PassBarcodeFormat.AZTEC);
        assertThat(createdFromParcel.getMessageEncoding()).isEqualTo(CharacterSetECI.ASCII);
    }
}
