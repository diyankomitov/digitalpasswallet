package com.diyankomitov.digitalpasswallet.models.pass;

import android.graphics.Bitmap;

import com.diyankomitov.digitalpasswallet.models.pass.util.PassBarcode;
import com.diyankomitov.digitalpasswallet.models.pass.util.PassBeacon;
import com.diyankomitov.digitalpasswallet.models.pass.util.PassField;
import com.diyankomitov.digitalpasswallet.models.pass.util.PassLocation;
import com.diyankomitov.digitalpasswallet.models.pass.util.PassType;

import java.util.List;

public interface Pass {


    Bitmap getBarcode();

    String getDescription();

    void setDescription(String description);

    int getFormatVersion();

    void setFormatVersion(int formatVersion);

    String getOrganizationName();

    void setOrganizationName(String organizationName);

    String getPassTypeIdentifier();

    void setPassTypeIdentifier(String passTypeIdentifier);

    String getSerialNumber();

    void setSerialNumber(String serialNumber);

    String getTeamIdentifier();

    void setTeamIdentifier(String teamIdentifier);

    String getAppLaunchURL();

    void setAppLaunchURL(String appLaunchURL);

    int[] getAssociatedStoreIdentifiers();

    void setAssociatedStoreIdentifiers(int[] associatedStoreIdentifiers);

    Object getUserInfo();

    void setUserInfo(Object userInfo);

    String getExpirationDate();

    void setExpirationDate(String expirationDate);

    boolean isVoided();

    void setVoided(boolean voided);

    List<PassBeacon> getBeacons();

    void setBeacons(List<PassBeacon> beacons);

    List<PassLocation> getLocations();

    void setLocations(List<PassLocation> locations);

    int getMaxDistance();

    void setMaxDistance(int maxDistance);

    String getRelevantDate();

    void setRelevantDate(String relevantDate);

    List<PassBarcode> getBarcodes();

    void setBarcodes(List<PassBarcode> barcodes);

    int getBackgroundColor();

    void setBackgroundColor(String backgroundColor);

    int getForegroundColor();

    void setForegroundColor(String foregroundColor);

    String getGroupingIdentifier();

    void setGroupingIdentifier(String groupingIdentifier);

    int getLabelColor();

    void setLabelColor(String labelColor);

    String getLogoText();

    void setLogoText(String logoText);

    boolean isSuppressStripShine();

    void setSuppressStripShine(boolean suppressStripShine);

    String getAuthenticationToken();

    void setAuthenticationToken(String authenticationToken);

    String getWebServiceURL();

    void setWebServiceURL(String webServiceURL);

    List<PassField> getAuxiliaryFields();

    void setAuxiliaryFields(List<PassField> auxiliaryFields);

    List<PassField> getBackFields();

    void setBackFields(List<PassField> backFields);

    List<PassField> getHeaderFields();

    void setHeaderFields(List<PassField> headerFields);

    List<PassField> getPrimaryFields();

    void setPrimaryFields(List<PassField> primaryFields);

    List<PassField> getSecondaryFields();

    void setSecondaryFields(List<PassField> secondaryFields);

    Bitmap getBackgroundImage();

    void setBackgroundImage(Bitmap backgroundImage);

    Bitmap getFooter();

    void setFooter(Bitmap footer);

    Bitmap getIcon();

    void setIcon(Bitmap icon);

    Bitmap getLogo();

    void setLogo(Bitmap logo);

    Bitmap getStrip();

    void setStrip(Bitmap strip);

    Bitmap getThumbnail();

    void setThumbnail(Bitmap thumbnail);

    PassType getType();


}
