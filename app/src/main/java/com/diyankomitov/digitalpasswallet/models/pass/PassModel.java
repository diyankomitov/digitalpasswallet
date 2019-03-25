package com.diyankomitov.digitalpasswallet.models.pass;

import android.os.Parcel;

import com.diyankomitov.digitalpasswallet.models.pass.components.PassBarcode;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassBeacon;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassField;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassLocation;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassTransitType;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * This is the implementation of the Pass interface. It also provides proper functionality for usage
 * with Android Room databases as well as proper functionality for the Android Parcelable interface
 */
@Entity
public class PassModel implements Pass {
    
    //Room primary key
    @PrimaryKey(autoGenerate = true)
    private int id;
    
    // Type / Style Keys
    @NonNull
    private final PassType type;
    
    @NonNull
    private PassTransitType transitType;
    
    //Standard Keys
    @NonNull
    private String description;
    private int formatVersion;
    @NonNull
    private String organizationName;
    @NonNull
    private String passTypeIdentifier;
    @NonNull
    private String serialNumber;
    @NonNull
    private String teamIdentifier;
    
    //Expiration Keys
    @NonNull
    private String expirationDate;
    private boolean voided;
    
    //Relevance Keys
    @NonNull
    private List<PassBeacon> beacons;
    @NonNull
    private List<PassLocation> locations;
    private int maxDistance;
    @NonNull
    private String relevantDate;
    
    //Visual Appearance Keys
    @NonNull
    private List<PassBarcode> barcodes;
    @ColorInt
    private int backgroundColor;
    @ColorInt
    private int foregroundColor;
    @ColorInt
    private int labelColor;
    @NonNull
    private String groupingIdentifier;
    @NonNull
    private String logoText;
    private boolean suppressStripShine;
    
    //Web Service Keys;
    @NonNull
    private String authenticationToken;
    @NonNull
    private String webServiceURL;
    
    //Pass Structure Dictionary Keys
    @NonNull
    private List<PassField> headerFields;
    @NonNull
    private List<PassField> primaryFields;
    @NonNull
    private List<PassField> secondaryFields;
    @NonNull
    private List<PassField> auxiliaryFields;
    @NonNull
    private List<PassField> backFields;
    
    //Image Paths
    @NonNull
    private String backgroundImagePath;
    @NonNull
    private String footerPath;
    @NonNull
    private String iconPath;
    @NonNull
    private String logoPath;
    @NonNull
    private String stripPath;
    @NonNull
    private String thumbnailPath;
    
    /**
     * Instantiates a new Pass.
     *
     * @param type the pass type
     */
    public PassModel(PassType type) {
        
        this.type = type == null ? PassType.GENERIC : type;
        this.transitType = PassTransitType.GENERIC;
        
        this.description = "";
        this.formatVersion = 1;
        this.organizationName = "";
        this.passTypeIdentifier = "";
        this.serialNumber = "";
        this.teamIdentifier = "";
        
        this.expirationDate = "";
        this.voided = false;
        
        this.beacons = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.maxDistance = 0;
        this.relevantDate = "";
        
        this.backgroundColor = 0;
        this.foregroundColor = 0;
        this.labelColor = 0;
        this.groupingIdentifier = "";
        this.logoText = "";
        this.suppressStripShine = false;
        
        this.authenticationToken = "";
        this.webServiceURL = "";
        
        this.backgroundImagePath = "";
        this.footerPath = "";
        this.iconPath = "";
        this.logoPath = "";
        this.stripPath = "";
        this.thumbnailPath = "";
        
        this.barcodes = new ArrayList<>();
        this.headerFields = new ArrayList<>();
        this.primaryFields = new ArrayList<>();
        this.secondaryFields = new ArrayList<>();
        this.auxiliaryFields = new ArrayList<>();
        this.backFields = new ArrayList<>();
    }
    
    // Parcelable
    
    /**
     * Instantiates a new Pass from a parcel.
     *
     * @param in the parcel
     */
    protected PassModel(Parcel in) {
        
        this.type = PassType.values()[in.readInt()];
        this.transitType = PassTransitType.values()[in.readInt()];
        this.description = Objects.requireNonNull(in.readString());
        this.formatVersion = in.readInt();
        this.organizationName = Objects.requireNonNull(in.readString());
        this.passTypeIdentifier = Objects.requireNonNull(in.readString());
        this.serialNumber = Objects.requireNonNull(in.readString());
        this.teamIdentifier = Objects.requireNonNull(in.readString());
        this.expirationDate = Objects.requireNonNull(in.readString());
        this.voided = in.readByte() != 0;
        this.maxDistance = in.readInt();
        this.relevantDate = Objects.requireNonNull(in.readString());
        this.barcodes = Objects.requireNonNull(in.createTypedArrayList(PassBarcode.CREATOR));
        this.backgroundColor = in.readInt();
        this.foregroundColor = in.readInt();
        this.labelColor = in.readInt();
        this.groupingIdentifier = Objects.requireNonNull(in.readString());
        this.logoText = Objects.requireNonNull(in.readString());
        this.suppressStripShine = in.readByte() != 0;
        this.authenticationToken = Objects.requireNonNull(in.readString());
        this.webServiceURL = Objects.requireNonNull(in.readString());
        this.headerFields = Objects.requireNonNull(in.createTypedArrayList(PassField.CREATOR));
        this.primaryFields = Objects.requireNonNull(in.createTypedArrayList(PassField.CREATOR));
        this.secondaryFields = Objects.requireNonNull(in.createTypedArrayList(PassField.CREATOR));
        this.auxiliaryFields = Objects.requireNonNull(in.createTypedArrayList(PassField.CREATOR));
        this.backFields = Objects.requireNonNull(in.createTypedArrayList(PassField.CREATOR));
        this.backgroundImagePath = Objects.requireNonNull(in.readString());
        this.footerPath = Objects.requireNonNull(in.readString());
        this.iconPath = Objects.requireNonNull(in.readString());
        this.logoPath = Objects.requireNonNull(in.readString());
        this.stripPath = Objects.requireNonNull(in.readString());
        this.thumbnailPath = Objects.requireNonNull(in.readString());
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        
        Utils.writeEnumToParcel(dest, type);
        Utils.writeEnumToParcel(dest, transitType);
        dest.writeString(description);
        dest.writeInt(formatVersion);
        dest.writeString(organizationName);
        dest.writeString(passTypeIdentifier);
        dest.writeString(serialNumber);
        dest.writeString(teamIdentifier);
        dest.writeString(expirationDate);
        dest.writeByte((byte) (voided ? 1 : 0));
        dest.writeInt(maxDistance);
        dest.writeString(relevantDate);
        dest.writeTypedList(barcodes);
        dest.writeInt(backgroundColor);
        dest.writeInt(foregroundColor);
        dest.writeInt(labelColor);
        dest.writeString(groupingIdentifier);
        dest.writeString(logoText);
        dest.writeByte((byte) (suppressStripShine ? 1 : 0));
        dest.writeString(authenticationToken);
        dest.writeString(webServiceURL);
        dest.writeTypedList(headerFields);
        dest.writeTypedList(primaryFields);
        dest.writeTypedList(secondaryFields);
        dest.writeTypedList(auxiliaryFields);
        dest.writeTypedList(backFields);
        dest.writeString(backgroundImagePath);
        dest.writeString(footerPath);
        dest.writeString(iconPath);
        dest.writeString(logoPath);
        dest.writeString(stripPath);
        dest.writeString(thumbnailPath);
    }
    
    @Override
    public int describeContents() {
        
        return 0;
    }
    
    /**
     * The constant CREATOR used byt Parcelable.
     */
    public static final Creator<PassModel> CREATOR = new Creator<PassModel>() {
        @Override
        public PassModel createFromParcel(Parcel in) {
            
            return new PassModel(in);
        }
        
        @Override
        public PassModel[] newArray(int size) {
            
            return new PassModel[size];
        }
    };
    
    // Getters and Setters
    
    @NonNull
    @Override
    public String getDescription() {
        
        return description;
    }
    
    @Override
    public void setDescription(String description) {
        
        this.description = description == null ? "" : description;
    }
    
    @Override
    public int getFormatVersion() {
        
        return formatVersion;
    }
    
    @Override
    public void setFormatVersion(int formatVersion) {
        
        this.formatVersion = formatVersion;
    }
    
    @NonNull
    @Override
    public String getOrganizationName() {
        
        return organizationName;
    }
    
    @Override
    public void setOrganizationName(String organizationName) {
        
        this.organizationName = organizationName == null ? "" : organizationName;
    }
    
    @NonNull
    @Override
    public String getPassTypeIdentifier() {
        
        return passTypeIdentifier;
    }
    
    @Override
    public void setPassTypeIdentifier(String passTypeIdentifier) {
        
        this.passTypeIdentifier = passTypeIdentifier == null ? "" : passTypeIdentifier;
    }
    
    @NonNull
    @Override
    public String getSerialNumber() {
        
        return serialNumber;
    }
    
    @Override
    public void setSerialNumber(String serialNumber) {
        
        this.serialNumber = serialNumber == null ? "" : serialNumber;
    }
    
    @NonNull
    @Override
    public String getTeamIdentifier() {
        
        return teamIdentifier;
    }
    
    @Override
    public void setTeamIdentifier(String teamIdentifier) {
        
        this.teamIdentifier = teamIdentifier == null ? "" : teamIdentifier;
    }
    
    @NonNull
    @Override
    public String getExpirationDate() {
        
        return expirationDate;
    }
    
    @Override
    public void setExpirationDate(String expirationDate) {
        
        this.expirationDate = expirationDate == null ? "" : expirationDate;
    }
    
    @Override
    public boolean isVoided() {
        
        return voided;
    }
    
    @Override
    public void setVoided(boolean voided) {
        
        this.voided = voided;
    }
    
    @NonNull
    @Override
    public List<PassBeacon> getBeacons() {
        
        return beacons;
    }
    
    @Override
    public void setBeacons(List<PassBeacon> beacons) {
        
        this.beacons = beacons == null ? new ArrayList<>() : beacons;
    }
    
    @NonNull
    @Override
    public List<PassLocation> getLocations() {
        
        return locations;
    }
    
    @Override
    public void setLocations(List<PassLocation> locations) {
        
        this.locations = locations == null ? new ArrayList<>() : locations;
    }
    
    @Override
    public int getMaxDistance() {
        
        return maxDistance;
    }
    
    @Override
    public void setMaxDistance(int maxDistance) {
        
        this.maxDistance = maxDistance;
    }
    
    @NonNull
    @Override
    public String getRelevantDate() {
        
        return relevantDate;
    }
    
    @Override
    public void setRelevantDate(String relevantDate) {
        
        this.relevantDate = relevantDate == null ? "" : relevantDate;
    }
    
    @NonNull
    @Override
    public List<PassBarcode> getBarcodes() {
        
        return barcodes;
    }
    
    @Override
    public void setBarcodes(List<PassBarcode> barcodes) {
        
        this.barcodes = barcodes == null ? new ArrayList<>() : barcodes;
    }
    
    @ColorInt
    @Override
    public int getBackgroundColor() {
        
        return backgroundColor;
    }
    
    @Override
    public void setBackgroundColor(@ColorInt int backgroundColor) {
        
        this.backgroundColor = backgroundColor;
    }
    
    @ColorInt
    @Override
    public int getForegroundColor() {
        
        return foregroundColor;
    }
    
    public void setForegroundColor(@ColorInt int foregroundColor) {
        
        this.foregroundColor = foregroundColor;
    }
    
    @ColorInt
    @Override
    public int getLabelColor() {
        
        return labelColor;
    }
    
    public void setLabelColor(@ColorInt int labelColor) {
        
        this.labelColor = labelColor;
    }
    
    @NonNull
    @Override
    public String getGroupingIdentifier() {
        
        return groupingIdentifier;
    }
    
    @Override
    public void setGroupingIdentifier(String groupingIdentifier) {
        
        this.groupingIdentifier = groupingIdentifier == null ? "" : groupingIdentifier;
    }
    
    @NonNull
    @Override
    public String getLogoText() {
        
        return logoText;
    }
    
    @Override
    public void setLogoText(String logoText) {
        
        this.logoText = logoText == null ? "" : logoText;
    }
    
    @Override
    public boolean isSuppressStripShine() {
        
        return suppressStripShine;
    }
    
    @Override
    public void setSuppressStripShine(boolean suppressStripShine) {
        
        this.suppressStripShine = suppressStripShine;
    }
    
    @NonNull
    @Override
    public String getAuthenticationToken() {
        
        return authenticationToken;
    }
    
    @Override
    public void setAuthenticationToken(String authenticationToken) {
        
        this.authenticationToken = authenticationToken == null ? "" : authenticationToken;
    }
    
    @NonNull
    @Override
    public String getWebServiceURL() {
        
        return webServiceURL;
    }
    
    @Override
    public void setWebServiceURL(String webServiceURL) {
        
        this.webServiceURL = webServiceURL == null ? "" : webServiceURL;
    }
    
    @NonNull
    @Override
    public List<PassField> getAuxiliaryFields() {
        
        return auxiliaryFields;
    }
    
    @Override
    public void setAuxiliaryFields(List<PassField> auxiliaryFields) {
        
        this.auxiliaryFields = auxiliaryFields == null ? new ArrayList<>() : auxiliaryFields;
    }
    
    @NonNull
    @Override
    public List<PassField> getBackFields() {
        
        return backFields;
    }
    
    @Override
    public void setBackFields(List<PassField> backFields) {
        
        this.backFields = backFields == null ? new ArrayList<>() : backFields;
    }
    
    @NonNull
    @Override
    public List<PassField> getHeaderFields() {
        
        return headerFields;
    }
    
    @Override
    public void setHeaderFields(List<PassField> headerFields) {
        
        this.headerFields = headerFields == null ? new ArrayList<>() : headerFields;
    }
    
    @NonNull
    @Override
    public List<PassField> getPrimaryFields() {
        
        return primaryFields;
    }
    
    @Override
    public void setPrimaryFields(List<PassField> primaryFields) {
        
        this.primaryFields = primaryFields == null ? new ArrayList<>() : primaryFields;
    }
    
    @NonNull
    @Override
    public List<PassField> getSecondaryFields() {
        
        return secondaryFields;
    }
    
    @Override
    public void setSecondaryFields(List<PassField> secondaryFields) {
        
        this.secondaryFields = secondaryFields == null ? new ArrayList<>() : secondaryFields;
    }
    
    @NonNull
    @Override
    public String getBackgroundImagePath() {
        
        return backgroundImagePath;
    }
    
    @Override
    public void setBackgroundImagePath(String backgroundImagePath) {
        
        this.backgroundImagePath = backgroundImagePath == null ? "" : backgroundImagePath;
    }
    
    @NonNull
    @Override
    public String getFooterPath() {
        
        return footerPath;
    }
    
    @Override
    public void setFooterPath(String footerPath) {
        
        this.footerPath = footerPath == null ? "" : footerPath;
    }
    
    @NonNull
    @Override
    public String getIconPath() {
        
        return iconPath;
    }
    
    @Override
    public void setIconPath(String iconPath) {
        
        this.iconPath = iconPath == null ? "" : iconPath;
    }
    
    @NonNull
    @Override
    public String getLogoPath() {
        
        return logoPath;
    }
    
    @Override
    public void setLogoPath(String logoPath) {
        
        this.logoPath = logoPath == null ? "" : logoPath;
    }
    
    @NonNull
    @Override
    public String getStripPath() {
        
        return stripPath;
    }
    
    @Override
    public void setStripPath(String stripPath) {
        
        this.stripPath = stripPath == null ? "" : stripPath;
    }
    
    @NonNull
    @Override
    public String getThumbnailPath() {
        
        return thumbnailPath;
    }
    
    @Override
    public void setThumbnailPath(String thumbnailPath) {
        
        this.thumbnailPath = thumbnailPath == null ? "" : thumbnailPath;
    }
    
    @NonNull
    @Override
    public PassType getType() {
        
        return type;
    }
    
    @NonNull
    @Override
    public PassTransitType getTransitType() {
        
        return transitType;
    }
    
    @Override
    public void setTransitType(PassTransitType transitType) {
        
        this.transitType = transitType == null ? PassTransitType.GENERIC : transitType;
    }
    
    @Override
    public int getId() {
        
        return id;
    }
    
    @Override
    public void setId(int id) {
        
        this.id = id;
    }
}
