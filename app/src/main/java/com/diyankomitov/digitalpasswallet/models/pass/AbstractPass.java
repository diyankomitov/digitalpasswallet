package com.diyankomitov.digitalpasswallet.models.pass;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.diyankomitov.digitalpasswallet.models.pass.util.PassBarcode;
import com.diyankomitov.digitalpasswallet.models.pass.util.PassBeacon;
import com.diyankomitov.digitalpasswallet.models.pass.util.PassField;
import com.diyankomitov.digitalpasswallet.models.pass.util.PassLocation;
import com.diyankomitov.digitalpasswallet.models.pass.util.PassType;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.List;

public abstract class AbstractPass implements Pass {
    //Standard Keys
    private String description;
    private int formatVersion;
    private String organizationName;
    private String passTypeIdentifier;
    private String serialNumber;
    private String teamIdentifier;

    //Associated App Keys TODO: probably remove
    private String appLaunchURL;
    private int[] associatedStoreIdentifiers;

    //Companion App Keys TODO: maybe remove
    private Object userInfo;

    //Expiration Keys
    private String expirationDate;
    private boolean voided;

    //Relevance Keys
    private List<PassBeacon> beacons;
    private List<PassLocation> locations;
    private int maxDistance;
    private String relevantDate;

    //Style Keys TODO: Decide how to do it

    //Visual Appearance Keys
    private List<PassBarcode> barcodes;
    private int backgroundColor;  //TODO: Maybe change from color to string
    private int foregroundColor;
    private String groupingIdentifier;
    private int labelColor;
    private String logoText;
    private boolean suppressStripShine;

    //Web Service Keys;
    private String authenticationToken;
    private String webServiceURL;

    //NFC-Enabled Pass Keys TODO: probably not needed;

    //Pass Structure Dictionary Keys
    private List<PassField> auxiliaryFields;
    private List<PassField> backFields;
    private List<PassField> headerFields;
    private List<PassField> primaryFields;
    private List<PassField> secondaryFields;
    //transitType, probs make it just in boarding card;

    //Images
    private Bitmap backgroundImage;
    private Bitmap footer;
    private Bitmap icon;
    private Bitmap logo;
    private Bitmap strip;
    private Bitmap thumbnail;

    //Type
    private final PassType type;


    public AbstractPass(PassType type) {
        this.type = type;
    }

//    public AbstractPass(Bitmap logo, String message, String logoText, List<PassField> headerFields) {
//
//        PassBarcode barcode = new PassBarcode(message, message, BarcodeFormat.QR_CODE, CharacterSetECI.ISO8859_1);
//
//        barcodes = new ArrayList<>();
//        barcodes.add(barcode);
//
//        this.logo = logo;
//        this.logoText = logoText;
//        this.headerFields = headerFields;
//    }

    @Override
    public Bitmap getBarcode() {
        Bitmap barcodeBitmap = null;
        PassBarcode barcode = barcodes.get(0);
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();

        try {
            barcodeBitmap = barcodeEncoder.encodeBitmap(barcode.getMessage(), barcode.getFormat(), 400, 400);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return barcodeBitmap;
    }


    /*Getters and Setters*/

    @Override
    public String getDescription() {
        return description;
    }


    @Override
    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public int getFormatVersion() {
        return formatVersion;
    }


    @Override
    public void setFormatVersion(int formatVersion) {
        this.formatVersion = formatVersion;
    }


    @Override
    public String getOrganizationName() {
        return organizationName;
    }


    @Override
    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }


    @Override
    public String getPassTypeIdentifier() {
        return passTypeIdentifier;
    }


    @Override
    public void setPassTypeIdentifier(String passTypeIdentifier) {
        this.passTypeIdentifier = passTypeIdentifier;
    }


    @Override
    public String getSerialNumber() {
        return serialNumber;
    }


    @Override
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }


    @Override
    public String getTeamIdentifier() {
        return teamIdentifier;
    }


    @Override
    public void setTeamIdentifier(String teamIdentifier) {
        this.teamIdentifier = teamIdentifier;
    }


    @Override
    public String getAppLaunchURL() {
        return appLaunchURL;
    }


    @Override
    public void setAppLaunchURL(String appLaunchURL) {
        this.appLaunchURL = appLaunchURL;
    }


    @Override
    public int[] getAssociatedStoreIdentifiers() {
        return associatedStoreIdentifiers;
    }


    @Override
    public void setAssociatedStoreIdentifiers(int[] associatedStoreIdentifiers) {
        this.associatedStoreIdentifiers = associatedStoreIdentifiers;
    }


    @Override
    public Object getUserInfo() {
        return userInfo;
    }


    @Override
    public void setUserInfo(Object userInfo) {
        this.userInfo = userInfo;
    }


    @Override
    public String getExpirationDate() {
        return expirationDate;
    }


    @Override
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }


    @Override
    public boolean isVoided() {
        return voided;
    }


    @Override
    public void setVoided(boolean voided) {
        this.voided = voided;
    }


    @Override
    public List<PassBeacon> getBeacons() {
        return beacons;
    }


    @Override
    public void setBeacons(List<PassBeacon> beacons) {
        this.beacons = beacons;
    }


    @Override
    public List<PassLocation> getLocations() {
        return locations;
    }


    @Override
    public void setLocations(List<PassLocation> locations) {
        this.locations = locations;
    }


    @Override
    public int getMaxDistance() {
        return maxDistance;
    }


    @Override
    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }


    @Override
    public String getRelevantDate() {
        return relevantDate;
    }


    @Override
    public void setRelevantDate(String relevantDate) {
        this.relevantDate = relevantDate;
    }


    @Override
    public List<PassBarcode> getBarcodes() {
        return barcodes;
    }


    @Override
    public void setBarcodes(List<PassBarcode> barcodes) {
        this.barcodes = barcodes;
    }


    @Override
    public int getBackgroundColor() {
        return backgroundColor;
    }


    @Override
    public void setBackgroundColor(String backgroundColor) {
        if (!backgroundColor.equals("")) {
            String[] rgb = backgroundColor.substring(4, backgroundColor.length()-1).split(",");
            int color = Color.rgb(Integer.parseInt(rgb[0].trim()), Integer.parseInt(rgb[1].trim()), Integer.parseInt(rgb[2].trim()));

            this.backgroundColor = color;
        }
    }


    @Override
    public int getForegroundColor() {
        return foregroundColor;
    }


    @Override
    public void setForegroundColor(String foregroundColor) {
        if (!foregroundColor.equals("")) {
            String[] rgb = foregroundColor.substring(4, foregroundColor.length()-1).split(",");
            int color = Color.rgb(Integer.parseInt(rgb[0].trim()), Integer.parseInt(rgb[1].trim()), Integer.parseInt(rgb[2].trim()));

            this.foregroundColor = color;
        }
    }


    @Override
    public String getGroupingIdentifier() {
        return groupingIdentifier;
    }


    @Override
    public void setGroupingIdentifier(String groupingIdentifier) {
        this.groupingIdentifier = groupingIdentifier;
    }


    @Override
    public int getLabelColor() {
        return labelColor;
    }


    @Override
    public void setLabelColor(String labelColor) {
        if (!labelColor.equals("")) {
            String[] rgb = labelColor.substring(4, labelColor.length()-1).split(",");
            int color = Color.rgb(Integer.parseInt(rgb[0].trim()), Integer.parseInt(rgb[1].trim()), Integer.parseInt(rgb[2].trim()));

            this.labelColor = color;
        }
    }


    @Override
    public String getLogoText() {
        return logoText;
    }


    @Override
    public void setLogoText(String logoText) {
        this.logoText = logoText;
    }


    @Override
    public boolean isSuppressStripShine() {
        return suppressStripShine;
    }


    @Override
    public void setSuppressStripShine(boolean suppressStripShine) {
        this.suppressStripShine = suppressStripShine;
    }


    @Override
    public String getAuthenticationToken() {
        return authenticationToken;
    }


    @Override
    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }


    @Override
    public String getWebServiceURL() {
        return webServiceURL;
    }


    @Override
    public void setWebServiceURL(String webServiceURL) {
        this.webServiceURL = webServiceURL;
    }


    @Override
    public List<PassField> getAuxiliaryFields() {
        return auxiliaryFields;
    }


    @Override
    public void setAuxiliaryFields(List<PassField> auxiliaryFields) {
        this.auxiliaryFields = auxiliaryFields;
    }


    @Override
    public List<PassField> getBackFields() {
        return backFields;
    }


    @Override
    public void setBackFields(List<PassField> backFields) {
        this.backFields = backFields;
    }


    @Override
    public List<PassField> getHeaderFields() {
        return headerFields;
    }


    @Override
    public void setHeaderFields(List<PassField> headerFields) {
        this.headerFields = headerFields;
    }


    @Override
    public List<PassField> getPrimaryFields() {
        return primaryFields;
    }


    @Override
    public void setPrimaryFields(List<PassField> primaryFields) {
        this.primaryFields = primaryFields;
    }


    @Override
    public List<PassField> getSecondaryFields() {
        return secondaryFields;
    }


    @Override
    public void setSecondaryFields(List<PassField> secondaryFields) {
        this.secondaryFields = secondaryFields;
    }

    @Override
    public Bitmap getBackgroundImage() {
        return backgroundImage;
    }

    @Override
    public void setBackgroundImage(Bitmap backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    @Override
    public Bitmap getFooter() {
        return footer;
    }

    @Override
    public void setFooter(Bitmap footer) {
        this.footer = footer;
    }

    @Override
    public Bitmap getIcon() {
        return icon;
    }

    @Override
    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    @Override
    public Bitmap getLogo() {
        return logo;
    }

    @Override
    public void setLogo(Bitmap logo) {
        this.logo = logo;
    }

    @Override
    public Bitmap getStrip() {
        return strip;
    }

    @Override
    public void setStrip(Bitmap strip) {
        this.strip = strip;
    }

    @Override
    public Bitmap getThumbnail() {
        return thumbnail;
    }

    @Override
    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public PassType getType() {
        return type;
    }
}
