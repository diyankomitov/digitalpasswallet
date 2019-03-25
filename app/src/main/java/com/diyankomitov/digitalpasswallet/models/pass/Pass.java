package com.diyankomitov.digitalpasswallet.models.pass;

import android.os.Parcelable;

import com.diyankomitov.digitalpasswallet.models.pass.components.PassBarcode;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassBeacon;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassField;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassLocation;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassTransitType;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassType;

import java.util.List;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

/**
 * This represents a pass object.
 * <p>
 * It contains all information that needs to be displayed to the user such as fields, image paths,
 * barcode information etc.
 * <p>
 * It also contains other other information used internally by the program.
 */
public interface Pass extends Parcelable {
    
    /**
     * Gets id.
     *
     * @return the id
     */
    int getId();
    
    /**
     * Sets id.
     *
     * @param id the id
     */
    void setId(int id);
    
    /**
     * Gets type.
     *
     * @return the type
     */
    @NonNull
    PassType getType();
    
    /**
     * Gets description.
     *
     * @return the description
     */
    @NonNull
    String getDescription();
    
    /**
     * Sets description.
     *
     * @param description the description
     */
    void setDescription(String description);
    
    /**
     * Gets format version.
     *
     * @return the format version
     */
    @NonNull
    int getFormatVersion();
    
    /**
     * Sets format version.
     *
     * @param formatVersion the format version
     */
    void setFormatVersion(int formatVersion);
    
    /**
     * Gets organization name.
     *
     * @return the organization name
     */
    @NonNull
    String getOrganizationName();
    
    /**
     * Sets organization name.
     *
     * @param organizationName the organization name
     */
    void setOrganizationName(String organizationName);
    
    /**
     * Gets pass type identifier.
     *
     * @return the pass type identifier
     */
    @NonNull
    String getPassTypeIdentifier();
    
    /**
     * Sets pass type identifier.
     *
     * @param passTypeIdentifier the pass type identifier
     */
    void setPassTypeIdentifier(String passTypeIdentifier);
    
    /**
     * Gets serial number.
     *
     * @return the serial number
     */
    @NonNull
    String getSerialNumber();
    
    /**
     * Sets serial number.
     *
     * @param serialNumber the serial number
     */
    void setSerialNumber(String serialNumber);
    
    /**
     * Gets team identifier.
     *
     * @return the team identifier
     */
    @NonNull
    String getTeamIdentifier();
    
    /**
     * Sets team identifier.
     *
     * @param teamIdentifier the team identifier
     */
    void setTeamIdentifier(String teamIdentifier);
    
    /**
     * Gets expiration date.
     *
     * @return the expiration date
     */
    @NonNull
    String getExpirationDate();
    
    /**
     * Sets expiration date.
     *
     * @param expirationDate the expiration date
     */
    void setExpirationDate(String expirationDate);
    
    /**
     * Is voided boolean.
     *
     * @return the boolean
     */
    boolean isVoided();
    
    /**
     * Sets voided.
     *
     * @param voided the voided
     */
    void setVoided(boolean voided);
    
    /**
     * Gets beacons.
     *
     * @return the beacons
     */
    @NonNull
    List<PassBeacon> getBeacons();
    
    /**
     * Sets beacons.
     *
     * @param beacons the beacons
     */
    void setBeacons(List<PassBeacon> beacons);
    
    /**
     * Gets locations.
     *
     * @return the locations
     */
    @NonNull
    List<PassLocation> getLocations();
    
    /**
     * Sets locations.
     *
     * @param locations the locations
     */
    void setLocations(List<PassLocation> locations);
    
    /**
     * Gets max distance.
     *
     * @return the max distance
     */
    int getMaxDistance();
    
    /**
     * Sets max distance.
     *
     * @param maxDistance the max distance
     */
    void setMaxDistance(int maxDistance);
    
    /**
     * Gets relevant date.
     *
     * @return the relevant date
     */
    @NonNull
    String getRelevantDate();
    
    /**
     * Sets relevant date.
     *
     * @param relevantDate the relevant date
     */
    void setRelevantDate(String relevantDate);
    
    /**
     * Gets barcodes.
     *
     * @return the barcodes
     */
    @NonNull
    List<PassBarcode> getBarcodes();
    
    /**
     * Sets barcodes.
     *
     * @param barcodes the barcodes
     */
    void setBarcodes(List<PassBarcode> barcodes);
    
    /**
     * Gets background color.
     *
     * @return the background color
     */
    @ColorInt
    @NonNull
    int getBackgroundColor();
    
    /**
     * Sets background color.
     *
     * @param backgroundColor the background color
     */
    void setBackgroundColor(@ColorInt int backgroundColor);
    
    /**
     * Gets foreground color.
     *
     * @return the foreground color
     */
    @ColorInt
    @NonNull
    int getForegroundColor();
    
    /**
     * Sets foreground color.
     *
     * @param foregroundColor the foreground color
     */
    void setForegroundColor(@ColorInt int foregroundColor);
    
    /**
     * Gets label color.
     *
     * @return the label color
     */
    @ColorInt
    @NonNull
    int getLabelColor();
    
    /**
     * Sets label color.
     *
     * @param labelColor the label color
     */
    void setLabelColor(@ColorInt int labelColor);
    
    /**
     * Gets grouping identifier.
     *
     * @return the grouping identifier
     */
    @NonNull
    String getGroupingIdentifier();
    
    /**
     * Sets grouping identifier.
     *
     * @param groupingIdentifier the grouping identifier
     */
    void setGroupingIdentifier(String groupingIdentifier);
    
    /**
     * Gets logo text.
     *
     * @return the logo text
     */
    @NonNull
    String getLogoText();
    
    /**
     * Sets logo text.
     *
     * @param logoText the logo text
     */
    void setLogoText(String logoText);
    
    /**
     * Is suppress strip shine boolean.
     *
     * @return the boolean
     */
    boolean isSuppressStripShine();
    
    /**
     * Sets suppress strip shine.
     *
     * @param suppressStripShine the suppress strip shine
     */
    void setSuppressStripShine(boolean suppressStripShine);
    
    /**
     * Gets authentication token.
     *
     * @return the authentication token
     */
    @NonNull
    String getAuthenticationToken();
    
    /**
     * Sets authentication token.
     *
     * @param authenticationToken the authentication token
     */
    void setAuthenticationToken(String authenticationToken);
    
    /**
     * Gets web service url.
     *
     * @return the web service url
     */
    @NonNull
    String getWebServiceURL();
    
    /**
     * Sets web service url.
     *
     * @param webServiceURL the web service url
     */
    void setWebServiceURL(String webServiceURL);
    
    /**
     * Gets auxiliary fields.
     *
     * @return the auxiliary fields
     */
    @NonNull
    List<PassField> getAuxiliaryFields();
    
    /**
     * Sets auxiliary fields.
     *
     * @param auxiliaryFields the auxiliary fields
     */
    void setAuxiliaryFields(List<PassField> auxiliaryFields);
    
    /**
     * Gets back fields.
     *
     * @return the back fields
     */
    @NonNull
    List<PassField> getBackFields();
    
    /**
     * Sets back fields.
     *
     * @param backFields the back fields
     */
    void setBackFields(List<PassField> backFields);
    
    /**
     * Gets header fields.
     *
     * @return the header fields
     */
    @NonNull
    List<PassField> getHeaderFields();
    
    /**
     * Sets header fields.
     *
     * @param headerFields the header fields
     */
    void setHeaderFields(List<PassField> headerFields);
    
    /**
     * Gets primary fields.
     *
     * @return the primary fields
     */
    @NonNull
    List<PassField> getPrimaryFields();
    
    /**
     * Sets primary fields.
     *
     * @param primaryFields the primary fields
     */
    void setPrimaryFields(List<PassField> primaryFields);
    
    /**
     * Gets secondary fields.
     *
     * @return the secondary fields
     */
    @NonNull
    List<PassField> getSecondaryFields();
    
    /**
     * Sets secondary fields.
     *
     * @param secondaryFields the secondary fields
     */
    void setSecondaryFields(List<PassField> secondaryFields);
    
    /**
     * Gets transit type.
     *
     * @return the transit type
     */
    @NonNull
    PassTransitType getTransitType();
    
    /**
     * Sets transit type.
     *
     * @param transitType the transit type
     */
    void setTransitType(PassTransitType transitType);
    
    /**
     * Gets background image path.
     *
     * @return the background image path
     */
    @NonNull
    String getBackgroundImagePath();
    
    /**
     * Sets background image path.
     *
     * @param backgroundImagePath the background image path
     */
    void setBackgroundImagePath(String backgroundImagePath);
    
    /**
     * Gets footer path.
     *
     * @return the footer path
     */
    @NonNull
    String getFooterPath();
    
    /**
     * Sets footer path.
     *
     * @param footerPath the footer path
     */
    void setFooterPath(String footerPath);
    
    /**
     * Gets icon path.
     *
     * @return the icon path
     */
    @NonNull
    String getIconPath();
    
    /**
     * Sets icon path.
     *
     * @param iconPath the icon path
     */
    void setIconPath(String iconPath);
    
    /**
     * Gets logo path.
     *
     * @return the logo path
     */
    @NonNull
    String getLogoPath();
    
    /**
     * Sets logo path.
     *
     * @param logoPath the logo path
     */
    void setLogoPath(String logoPath);
    
    /**
     * Gets strip path.
     *
     * @return the strip path
     */
    @NonNull
    String getStripPath();
    
    /**
     * Sets strip path.
     *
     * @param stripPath the strip path
     */
    void setStripPath(String stripPath);
    
    /**
     * Gets thumbnail path.
     *
     * @return the thumbnail path
     */
    @NonNull
    String getThumbnailPath();
    
    /**
     * Sets thumbnail path.
     *
     * @param thumbnailPath the thumbnail path
     */
    void setThumbnailPath(String thumbnailPath);
}
