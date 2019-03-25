package com.diyankomitov.digitalpasswallet.models.pass;

import com.diyankomitov.digitalpasswallet.models.pass.components.PassBarcode;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassField;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassTransitType;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassType;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class PassModelTest {
    
    private static Pass pass;
    
    @Before
    public void setUp() {
        
        pass = new PassModel(PassType.GENERIC);
    }
    
    // Test defaults
    
    @Test
    public void testTransitTypeDefault() {
        
        assertThat(pass.getTransitType()).isEqualTo(PassTransitType.GENERIC);
    }
    
    @Test
    public void testDescriptionDefault() {
        
        assertThat(pass.getDescription()).isEmpty();
    }
    
    @Test
    public void testFormatVersionDefault() {
        
        assertThat(pass.getFormatVersion()).isEqualTo(1);
    }
    
    @Test
    public void testOrganizationNameFormatVersionDefault() {
        
        assertThat(pass.getOrganizationName()).isEmpty();
    }
    
    @Test
    public void testPassTypeIdentifierNameDefault() {
        
        assertThat(pass.getPassTypeIdentifier()).isEmpty();
    }
    
    @Test
    public void testSerialNumberDefault() {
        
        assertThat(pass.getSerialNumber()).isEmpty();
    }
    
    @Test
    public void testTeamIdentifierDefault() {
        
        assertThat(pass.getTeamIdentifier()).isEmpty();
    }
    
    @Test
    public void testExpirationDateDefault() {
        
        assertThat(pass.getExpirationDate()).isEmpty();
    }
    
    @Test
    public void testVoidedDefault() {
        
        assertThat(pass.isVoided()).isFalse();
    }
    
    @Test
    public void testBeaconsDefault() {
        
        assertThat(pass.getBeacons()).isEmpty();
    }
    
    @Test
    public void testLocationsDefault() {
        
        assertThat(pass.getLocations()).isEmpty();
    }
    
    @Test
    public void testMaxDistanceDefault() {
        
        assertThat(pass.getMaxDistance()).isEqualTo(0);
    }
    
    @Test
    public void testRelevantDateDefault() {
        
        assertThat(pass.getRelevantDate()).isEmpty();
    }
    
    @Test
    public void testBackgroundColorDefault() {
        
        assertThat(pass.getBackgroundColor()).isEqualTo(0);
    }
    
    @Test
    public void testForegroundColorDefault() {
        
        assertThat(pass.getForegroundColor()).isEqualTo(0);
    }
    
    @Test
    public void testLabelColorDefault() {
        
        assertThat(pass.getLabelColor()).isEqualTo(0);
    }
    
    @Test
    public void testGroupingIdentifierDefault() {
        
        assertThat(pass.getGroupingIdentifier()).isEmpty();
    }
    
    @Test
    public void testLogoTextDefault() {
        
        assertThat(pass.getLogoText()).isEmpty();
    }
    
    @Test
    public void testSuppressStripShineDefault() {
        
        assertThat(pass.isSuppressStripShine()).isFalse();
    }
    
    @Test
    public void testAuthenticationTokenDefault() {
        
        assertThat(pass.getAuthenticationToken()).isEmpty();
    }
    
    @Test
    public void testWebServiceURLDefault() {
        
        assertThat(pass.getWebServiceURL()).isEmpty();
    }
    
    @Test
    public void testBackgroundImagePathDefault() {
        
        assertThat(pass.getBackgroundImagePath()).isEmpty();
    }
    
    @Test
    public void testFooterPathDefault() {
        
        assertThat(pass.getFooterPath()).isEmpty();
    }
    
    @Test
    public void testIconPathDefault() {
        
        assertThat(pass.getIconPath()).isEmpty();
    }
    
    @Test
    public void testLogoPathDefault() {
        
        assertThat(pass.getLogoPath()).isEmpty();
    }
    
    @Test
    public void testStripPathDefault() {
        
        assertThat(pass.getStripPath()).isEmpty();
    }
    
    @Test
    public void testThumbnailDefault() {
        
        assertThat(pass.getThumbnailPath()).isEmpty();
    }
    
    @Test
    public void testBarcodesDefault() {
        
        assertThat(pass.getBarcodes()).isEmpty();
    }
    
    @Test
    public void testHeaderFieldsDefault() {
        
        assertThat(pass.getHeaderFields()).isEmpty();
    }
    
    @Test
    public void testPrimaryFieldsDefault() {
        
        assertThat(pass.getPrimaryFields()).isEmpty();
    }
    
    @Test
    public void testSecondaryFieldsDefault() {
        
        assertThat(pass.getSecondaryFields()).isEmpty();
    }
    
    @Test
    public void testAuxiliaryFieldsDefault() {
        
        assertThat(pass.getAuxiliaryFields()).isEmpty();
    }
    
    @Test
    public void testBackFieldsDefault() {
        
        assertThat(pass.getBackFields()).isEmpty();
    }
    
    //Test setting to null should give default
    
    @Test
    public void testPassTypeSetToNullReturnsDefault() {
        
        pass = new PassModel((PassType) null);
        assertThat(pass.getType()).isEqualTo(PassType.GENERIC);
    }
    
    @Test
    public void testTransitTypeSetToNullReturnsDefault() {
        
        pass.setTransitType(null);
        assertThat(pass.getTransitType()).isEqualTo(PassTransitType.GENERIC);
    }
    
    @Test
    public void testDescriptionSetToNullReturnsDefault() {
        
        pass.setDescription(null);
        assertThat(pass.getDescription()).isEmpty();
    }
    
    @Test
    public void testOrganizationNameSetToNullReturnsDefault() {
        
        pass.setOrganizationName(null);
        assertThat(pass.getOrganizationName()).isEmpty();
    }
    
    @Test
    public void testPassTypeIdentifierNameSetToNullReturnsDefault() {
        
        pass.setPassTypeIdentifier(null);
        assertThat(pass.getPassTypeIdentifier()).isEmpty();
    }
    
    @Test
    public void testSerialNumberSetToNullReturnsDefault() {
        
        pass.setSerialNumber(null);
        assertThat(pass.getSerialNumber()).isEmpty();
    }
    
    @Test
    public void testTeamIdentifierSetToNullReturnsDefault() {
        
        pass.setTeamIdentifier(null);
        assertThat(pass.getTeamIdentifier()).isEmpty();
    }
    
    @Test
    public void testExpirationDateSetToNullReturnsDefault() {
        
        pass.setExpirationDate(null);
        assertThat(pass.getExpirationDate()).isEmpty();
    }
    
    @Test
    public void testBeaconsSetToNullReturnsDefault() {
        
        pass.setBeacons(null);
        assertThat(pass.getBeacons()).isEmpty();
    }
    
    @Test
    public void testLocationsSetToNullReturnsDefault() {
        
        pass.setLocations(null);
        assertThat(pass.getLocations()).isEmpty();
    }
    
    @Test
    public void testRelevantDateSetToNullReturnsDefault() {
        
        pass.setRelevantDate(null);
        assertThat(pass.getRelevantDate()).isEmpty();
    }
    
    @Test
    public void testGroupingIdentifierSetToNullReturnsDefault() {
        
        pass.setGroupingIdentifier(null);
        assertThat(pass.getGroupingIdentifier()).isEmpty();
    }
    
    @Test
    public void testLogoTextSetToNullReturnsDefault() {
        
        pass.setLogoText(null);
        assertThat(pass.getLogoText()).isEmpty();
    }
    
    @Test
    public void testAuthenticationTokenSetToNullReturnsDefault() {
        
        pass.setAuthenticationToken(null);
        assertThat(pass.getAuthenticationToken()).isEmpty();
    }
    
    @Test
    public void testWebServiceURLSetToNullReturnsDefault() {
        
        pass.setWebServiceURL(null);
        assertThat(pass.getWebServiceURL()).isEmpty();
    }
    
    @Test
    public void testBackgroundImagePathSetToNullReturnsDefault() {
        
        pass.setBackgroundImagePath(null);
        assertThat(pass.getBackgroundImagePath()).isEmpty();
    }
    
    @Test
    public void testFooterPathSetToNullReturnsDefault() {
        
        pass.setFooterPath(null);
        assertThat(pass.getFooterPath()).isEmpty();
    }
    
    @Test
    public void testIconPathSetToNullReturnsDefault() {
        
        pass.setIconPath(null);
        assertThat(pass.getIconPath()).isEmpty();
    }
    
    @Test
    public void testLogoPathSetToNullReturnsDefault() {
        
        pass.setLogoPath(null);
        assertThat(pass.getLogoPath()).isEmpty();
    }
    
    @Test
    public void testStripPathSetToNullReturnsDefault() {
        
        pass.setStripPath(null);
        assertThat(pass.getStripPath()).isEmpty();
    }
    
    @Test
    public void testThumbnailSetToNullReturnsDefault() {
        
        pass.setThumbnailPath(null);
        assertThat(pass.getThumbnailPath()).isEmpty();
    }
    
    @Test
    public void testBarcodesSetToNullReturnsDefault() {
        
        pass.setBarcodes(null);
        assertThat(pass.getBarcodes()).isEmpty();
    }
    
    @Test
    public void testHeaderFieldsSetToNullReturnsDefault() {
        
        pass.setHeaderFields(null);
        assertThat(pass.getHeaderFields()).isEmpty();
    }
    
    @Test
    public void testPrimaryFieldsSetToNullReturnsDefault() {
        
        pass.setPrimaryFields(null);
        assertThat(pass.getPrimaryFields()).isEmpty();
    }
    
    @Test
    public void testSecondaryFieldsSetToNullReturnsDefault() {
        
        pass.setSecondaryFields(null);
        assertThat(pass.getSecondaryFields()).isEmpty();
    }
    
    @Test
    public void testAuxiliaryFieldsSetToNullReturnsDefault() {
        
        pass.setAuxiliaryFields(null);
        assertThat(pass.getAuxiliaryFields()).isEmpty();
    }
    
    @Test
    public void testBackFieldsSetToNullReturnsDefault() {
        
        pass.setBackFields(null);
        assertThat(pass.getBackFields()).isEmpty();
    }
    
    //Test setting sets correctly
    
    @Test
    public void testPassTypeSetReturnsCorrectly() {
        
        pass = new PassModel(PassType.EVENT_TICKET);
        assertThat(pass.getType()).isEqualTo(PassType.EVENT_TICKET);
    }
    
    @Test
    public void testTransitTypeSetReturnsCorrectly() {
        
        pass.setTransitType(PassTransitType.AIR);
        assertThat(pass.getTransitType()).isEqualTo(PassTransitType.AIR);
    }
    
    @Test
    public void testDescriptionSetReturnsCorrectly() {
        
        pass.setDescription("description");
        assertThat(pass.getDescription()).matches("description");
    }
    
    @Test
    public void testFormatVersionSetReturnsCorrectly() {
        
        pass.setFormatVersion(10);
        assertThat(pass.getFormatVersion()).isEqualTo(10);
    }
    
    @Test
    public void testOrganizationNameSetReturnsCorrectly() {
        
        pass.setOrganizationName("organizationName");
        assertThat(pass.getOrganizationName()).matches("organizationName");
    }
    
    @Test
    public void testPassTypeIdentifierSetReturnsCorrectly() {
        
        pass.setPassTypeIdentifier("passTypeIdentifier");
        assertThat(pass.getPassTypeIdentifier()).matches("passTypeIdentifier");
    }
    
    @Test
    public void testSerialNumberSetReturnsCorrectly() {
        
        pass.setSerialNumber("serialNumber");
        assertThat(pass.getSerialNumber()).matches("serialNumber");
    }
    
    @Test
    public void testTeamIdentifierSetReturnsCorrectly() {
        
        pass.setTeamIdentifier("teamIdentifier");
        assertThat(pass.getTeamIdentifier()).matches("teamIdentifier");
    }
    
    @Test
    public void testExpirationDateSetReturnsCorrectly() {
        
        pass.setExpirationDate("expirationDate");
        assertThat(pass.getExpirationDate()).matches("expirationDate");
    }
    
    @Test
    public void testVoidedSetReturnsCorrectly() {
        
        pass.setVoided(true);
        assertThat(pass.isVoided()).isTrue();
    }
    
    @Test
    public void testMaxDistanceSetReturnsCorrectly() {
        
        pass.setMaxDistance(11);
        assertThat(pass.getMaxDistance()).isEqualTo(11);
    }
    
    @Test
    public void testRelevantDateSetReturnsCorrectly() {
        
        pass.setRelevantDate("relevantDate");
        assertThat(pass.getRelevantDate()).matches("relevantDate");
    }
    
    @Test
    public void testBackgroundColorSetReturnsCorrectly() {
        
        pass.setBackgroundColor(12);
        assertThat(pass.getBackgroundColor()).isEqualTo(12);
    }
    
    @Test
    public void testForegroundColorSetReturnsCorrectly() {
        
        pass.setForegroundColor(13);
        assertThat(pass.getForegroundColor()).isEqualTo(13);
    }
    
    @Test
    public void testLabelColorSetReturnsCorrectly() {
        
        pass.setLabelColor(14);
        assertThat(pass.getLabelColor()).isEqualTo(14);
    }
    
    @Test
    public void testGroupingIdentifierSetReturnsCorrectly() {
        
        pass.setGroupingIdentifier("groupingIdentifier");
        assertThat(pass.getGroupingIdentifier()).matches("groupingIdentifier");
    }
    
    @Test
    public void testLogoTextSetReturnsCorrectly() {
        
        pass.setLogoText("logoText");
        assertThat(pass.getLogoText()).matches("logoText");
    }
    
    @Test
    public void testSuppressStripShineSetReturnsCorrectly() {
        
        pass.setSuppressStripShine(true);
        assertThat(pass.isSuppressStripShine()).isTrue();
    }
    
    @Test
    public void testAuthenticationTokenSetReturnsCorrectly() {
        
        pass.setAuthenticationToken("authenticationToken");
        assertThat(pass.getAuthenticationToken()).matches("authenticationToken");
    }
    
    @Test
    public void testWebServiceURLSetReturnsCorrectly() {
        
        pass.setWebServiceURL("webServiceURL");
        assertThat(pass.getWebServiceURL()).matches("webServiceURL");
    }
    
    @Test
    public void testBackgroundImagePathSetReturnsCorrectly() {
        
        pass.setBackgroundImagePath("backgroundImagePath");
        assertThat(pass.getBackgroundImagePath()).matches("backgroundImagePath");
    }
    
    @Test
    public void testFooterPathSetReturnsCorrectly() {
        
        pass.setFooterPath("footerPath");
        assertThat(pass.getFooterPath()).matches("footerPath");
    }
    
    @Test
    public void testIconPathSetReturnsCorrectly() {
        
        pass.setIconPath("iconPath");
        assertThat(pass.getIconPath()).matches("iconPath");
    }
    
    @Test
    public void testLogoPathSetReturnsCorrectly() {
        
        pass.setLogoPath("logoPath");
        assertThat(pass.getLogoPath()).matches("logoPath");
    }
    
    @Test
    public void testStripPathSetReturnsCorrectly() {
        
        pass.setStripPath("stripPath");
        assertThat(pass.getStripPath()).matches("stripPath");
    }
    
    @Test
    public void testThumbnailSetReturnsCorrectly() {
        
        pass.setThumbnailPath("thumbnailPath");
        assertThat(pass.getThumbnailPath()).matches("thumbnailPath");
    }
    
    @Test
    public void testBarcodesSetReturnsCorrectly() {
        
        List<PassBarcode> barcodes = new ArrayList<>();
        pass.setBarcodes(barcodes);
        assertThat(pass.getBarcodes()).isEqualTo(barcodes);
    }
    
    @Test
    public void testHeaderFieldsSetReturnsCorrectly() {
        
        List<PassField> headerFields = new ArrayList<>();
        pass.setHeaderFields(headerFields);
        assertThat(pass.getHeaderFields()).isEqualTo(headerFields);
    }
    
    @Test
    public void testPrimaryFieldsSetReturnsCorrectly() {
        
        List<PassField> primaryFields = new ArrayList<>();
        pass.setPrimaryFields(primaryFields);
        assertThat(pass.getPrimaryFields()).isEqualTo(primaryFields);
    }
    
    @Test
    public void testSecondaryFieldsSetReturnsCorrectly() {
        
        List<PassField> secondaryFields = new ArrayList<>();
        pass.setSecondaryFields(secondaryFields);
        assertThat(pass.getSecondaryFields()).isEqualTo(secondaryFields);
    }
    
    @Test
    public void testAuxiliaryFieldsSetReturnsCorrectly() {
        
        List<PassField> auxiliaryFields = new ArrayList<>();
        pass.setAuxiliaryFields(auxiliaryFields);
        assertThat(pass.getAuxiliaryFields()).isEqualTo(auxiliaryFields);
    }
    
    @Test
    public void testBackFieldsSetReturnsCorrectly() {
        
        List<PassField> backFields = new ArrayList<>();
        pass.setBackFields(backFields);
        assertThat(pass.getBackFields()).isEqualTo(backFields);
    }
}