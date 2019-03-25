package com.diyankomitov.digitalpasswallet.repository.passloader;

import android.graphics.Color;
import android.util.Log;

import com.diyankomitov.digitalpasswallet.models.pass.Pass;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassBarcode;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassBarcodeFormat;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassBeacon;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassDataDetectorType;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassDateTimeStyle;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassField;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassLocation;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassNumberStyle;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassTextAlignment;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassTransitType;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

public class PassJSONParser {
    
    private static final String TAG = "PassJSONParser";
    
    public static PassType parsePassType(JSONObject passJson) {
        
        Log.d(TAG, "parsePassType() called with: passJson = [" + passJson + "]");
        
        if (passJson.optJSONObject("boardingPass") != null) {
            return PassType.BOARDING_PASS;
        } else if (passJson.optJSONObject("coupon") != null) {
            return PassType.COUPON;
        } else if (passJson.optJSONObject("eventTicket") != null) {
            return PassType.EVENT_TICKET;
        } else if (passJson.optJSONObject("generic") != null) {
            return PassType.GENERIC;
        } else if (passJson.optJSONObject("storeCard") != null) {
            return PassType.STORE_CARD;
        } else {
            return null;
        }
    }
    
    private static String returnLocalised(@Nullable JSONObject stringsJson, String nonLocalised) {
        
        if (stringsJson != null) {
            return stringsJson.optString(nonLocalised, nonLocalised);
        }
        
        return nonLocalised;
    }
    
    public static void parse(JSONObject passJson, Pass pass, @Nullable JSONObject stringsJson) {
        
        Log.d(TAG, "parse() called with: passJson = [" + passJson + "], pass = [" + pass + "]");
        
        try {
            /* Standard Keys */
            pass.setDescription(returnLocalised(stringsJson, passJson.getString("description")));
            pass.setFormatVersion(passJson.getInt("formatVersion"));
            pass.setOrganizationName(
                    returnLocalised(stringsJson, passJson.getString("organizationName")));
            pass.setPassTypeIdentifier(passJson.getString("passTypeIdentifier"));
            pass.setSerialNumber(passJson.getString("serialNumber"));
            pass.setTeamIdentifier(passJson.getString("teamIdentifier"));
            
            //TODO: Skipping Associated App Keys
            //TODO: Skipping Companion App Keys
            
            /* Expiration Keys */
            pass.setExpirationDate(passJson.optString("expirationDate", ""));
            pass.setVoided(passJson.optBoolean("voided", false));
            
            /* Relevance Keys */
            pass.setBeacons(parseBeacons(passJson));
            pass.setLocations(parseLocations(passJson));
            pass.setMaxDistance(passJson.optInt("maxDistance", Integer.MAX_VALUE));
            pass.setRelevantDate(passJson.optString("relevantDate", ""));
            
            /*Pass Structure Keys*/
            parsePassStructure(passJson.getJSONObject(pass.getType().getPkType()), pass,
                               stringsJson);
            
            /* Barcodes Keys */
            pass.setBarcodes(parseBarcodes(passJson));
            
            /* Visual Appearance Keys */
            pass.setBackgroundColor(parseColor(passJson.optString("backgroundColor", "")));
            pass.setForegroundColor(parseColor(passJson.optString("foregroundColor", "")));
            pass.setLabelColor(parseColor(passJson.optString("labelColor", "")));
            pass.setGroupingIdentifier(passJson.optString("groupingIdentifier", ""));
            pass.setLogoText(returnLocalised(stringsJson, passJson.optString("logoText")));
            pass.setSuppressStripShine(passJson.optBoolean("suppressStripShine", false));
            
            /* Web Service Keys */
            pass.setAuthenticationToken(passJson.optString("authenticationToken", ""));
            pass.setWebServiceURL(passJson.optString("webServiceURL"));
        } catch (JSONException e) {
            Log.e(TAG, "parse: parsing json file failed somewhere", e);
        }
    }
    
    @ColorInt
    private static int parseColor(String colorString) {
        
        if (colorString.equals("")) {
            return 0;
        }
        
        try {
            return Color.parseColor(colorString);
        } catch (IllegalArgumentException e) {
            Log.d(TAG, "parseColor: not a hex color string, continuing");
        }
        
        try {
            
            String[] rgb = colorString.substring(4, colorString.length() - 1).split(",");
            @ColorInt int color =
                    Color.rgb(Integer.parseInt(rgb[0].trim()), Integer.parseInt(rgb[1].trim()),
                              Integer.parseInt(rgb[2].trim()));
            
            return color;
        } catch (Exception e) {
            return 0;
        }
    }
    
    private static List<PassBeacon> parseBeacons(JSONObject passJson) throws JSONException {
        
        Log.d(TAG, "parseBeacons() called with: passJson = [" + passJson + "]");
        
        JSONArray beaconsJson = passJson.optJSONArray("beacons");
        List<PassBeacon> beacons = new ArrayList<>();
        
        if (beaconsJson != null) {
            for (int i = 0; i < beaconsJson.length(); i++) {
                JSONObject beaconJson = beaconsJson.getJSONObject(i);
                PassBeacon passBeacon = new PassBeacon();
                
                passBeacon.setProximityUUID(beaconJson.getString("proximityUUID"));
                passBeacon.setMajor(beaconJson.getInt("major"));
                passBeacon.setMinor(beaconJson.getInt("minor"));
                passBeacon.setRelevantText(beaconJson.getString("relevantText"));
                beacons.add(passBeacon);
            }
        }
        
        return beacons;
    }
    
    private static List<PassLocation> parseLocations(JSONObject passJson) throws JSONException {
        
        Log.d(TAG, "parseLocations() called with: passJson = [" + passJson + "]");
        
        JSONArray locationsJson = passJson.optJSONArray("locations");
        List<PassLocation> locations = new ArrayList<>();
        
        if (locationsJson != null) {
            for (int i = 0; i < locationsJson.length(); i++) {
                JSONObject locationJson = locationsJson.getJSONObject(i);
                PassLocation passLocation = new PassLocation();
                
                passLocation.setLatitude(locationJson.getDouble("latitude"));
                passLocation.setLongitude(locationJson.getDouble("longitude"));
                passLocation.setAltitude(locationJson.optDouble("altitude", PassLocation.NO_VALUE));
                passLocation.setRelevantText(locationJson.optString("relevantText"));
                locations.add(passLocation);
            }
        }
        
        return locations;
    }
    
    private static void parsePassStructure(JSONObject passStructureJson, Pass pass,
                                           JSONObject stringsJson) throws JSONException {
        
        Log.d(TAG, "parsePassStructure() called with: passStructureJson = [" + passStructureJson +
                   "], pass = [" + pass + "]");
        
        JSONArray headerFieldsJson = passStructureJson.optJSONArray("headerFields");
        if (headerFieldsJson != null) {
            pass.setHeaderFields(parsePassFields(headerFieldsJson, stringsJson));
        }
        
        JSONArray primaryFieldsJson = passStructureJson.optJSONArray("primaryFields");
        if (primaryFieldsJson != null) {
            pass.setPrimaryFields(parsePassFields(primaryFieldsJson, stringsJson));
        }
        
        JSONArray secondaryFieldsJson = passStructureJson.optJSONArray("secondaryFields");
        if (secondaryFieldsJson != null) {
            pass.setSecondaryFields(parsePassFields(secondaryFieldsJson, stringsJson));
        }
        
        JSONArray auxiliaryFieldsJson = passStructureJson.optJSONArray("auxiliaryFields");
        if (auxiliaryFieldsJson != null) {
            pass.setAuxiliaryFields(parsePassFields(auxiliaryFieldsJson, stringsJson));
        }
        
        JSONArray backFieldsJson = passStructureJson.optJSONArray("backFields");
        if (backFieldsJson != null) {
            pass.setBackFields(parsePassFields(backFieldsJson, stringsJson));
        }
        
        pass.setTransitType(
                PassTransitType.getFromPkString(passStructureJson.optString("transitType")));
    }
    
    private static List<PassField> parsePassFields(JSONArray passFieldsJson,
                                                   JSONObject stringsJson) throws JSONException {
        
        Log.d(TAG, "parsePassFields() called with: passFieldsJson = [" + passFieldsJson + "]");
        
        List<PassField> passFields = new ArrayList<>();
        for (int i = 0; i < passFieldsJson.length(); i++) {
            if (passFieldsJson.isNull(i)) {
                continue;
            }
            JSONObject fieldJson = passFieldsJson.getJSONObject(i);
            PassField passField = new PassField();
            
            //Standard keys
            passField.setKey(fieldJson.getString("key"));
            passField.setValue(returnLocalised(stringsJson, fieldJson.getString("value")));
            passField.setLabel(returnLocalised(stringsJson, fieldJson.optString("label")));
            passField.setAttributedValue(
                    returnLocalised(stringsJson, fieldJson.optString("attributedValue")));
            passField.setChangeMessage(
                    returnLocalised(stringsJson, fieldJson.optString("changeMessage")));
            passField.setTextAlignment(
                    PassTextAlignment.getFromPkString(fieldJson.optString("textAlignment")));
            
            JSONArray dataDetectorTypesJson = fieldJson.optJSONArray("dataDetectorTypes");
            Set<PassDataDetectorType> dataDetectorTypes = new HashSet<>();
            if (dataDetectorTypesJson != null) {
                for (int j = 0; j < dataDetectorTypesJson.length(); j++) {
                    dataDetectorTypes.add(PassDataDetectorType.getFromPkString(
                            dataDetectorTypesJson.getString(j)));
                }
            }
            passField.setDataDetectorTypes(dataDetectorTypes);
            
            //Date Style keys
            passField.setDateStyle(
                    PassDateTimeStyle.getFromPkString(fieldJson.optString("dateStyle")));
            passField.setIgnoresTimeZone(fieldJson.optBoolean("ignoresTimeZone"));
            passField.setRelative(fieldJson.optBoolean("isRelative"));
            passField.setTimeStyle(
                    PassDateTimeStyle.getFromPkString(fieldJson.optString("timeStyle")));
            
            //Number Style keys
            passField.setCurrencyCode(fieldJson.optString("currencyCode"));
            passField.setNumberStyle(
                    PassNumberStyle.getFromPkString(fieldJson.optString("numberStyle")));
            
            passFields.add(passField);
        }
        return passFields;
    }
    
    private static List<PassBarcode> parseBarcodes(JSONObject passJson) throws JSONException {
        
        Log.d(TAG, "parseBarcodes() called with: passJson = [" + passJson + "]");
        
        JSONArray barcodesJson = passJson.optJSONArray("barcodes");
        JSONObject barcodeJson = passJson.optJSONObject("barcode");
        
        List<PassBarcode> barcodes = new ArrayList<>();
        
        if (barcodesJson != null) {
            for (int i = 0; i < barcodesJson.length(); i++) {
                PassBarcode passBarcode = parseBarcode(barcodesJson.getJSONObject(i));
                barcodes.add(passBarcode);
            }
        } else if (barcodeJson != null) {
            barcodes.add(parseBarcode(barcodeJson));
        }
        
        return barcodes;
    }
    
    private static PassBarcode parseBarcode(JSONObject barcodeJson) throws JSONException {
        
        Log.d(TAG, "parseBarcode() called with: barcodeJson = [" + barcodeJson + "]");
        
        PassBarcode barcode = new PassBarcode();
        barcode.setMessage(barcodeJson.getString("message"));
        barcode.setFormat(PassBarcodeFormat.getFromPkString(barcodeJson.getString("format")));
        barcode.setMessageEncoding(barcodeJson.getString("messageEncoding"));
        barcode.setAltText(barcodeJson.optString("altText"));
        return barcode;
    }
}
