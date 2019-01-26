package com.diyankomitov.digitalpasswallet.models.parser;

import android.util.Log;

import com.diyankomitov.digitalpasswallet.models.pass.Pass;
import com.diyankomitov.digitalpasswallet.models.pass.util.PassBarcode;
import com.diyankomitov.digitalpasswallet.models.pass.util.PassBeacon;
import com.diyankomitov.digitalpasswallet.models.pass.util.PassField;
import com.diyankomitov.digitalpasswallet.models.pass.util.PassLocation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PassJSONParser {
    public static final String PARSER_TAG = "Json Parser";

    public static void parse(JSONObject passJson, Pass pass) {

        try {
            /* Standard Keys */
            pass.setDescription(passJson.getString("description"));
            pass.setFormatVersion(passJson.getInt("formatVersion"));
            pass.setOrganizationName(passJson.getString("organizationName"));
            pass.setPassTypeIdentifier(passJson.getString("passTypeIdentifier"));
            pass.setSerialNumber(passJson.getString("serialNumber"));
            pass.setTeamIdentifier(passJson.getString("teamIdentifier"));

            //TODO: Skipping Associated App Keys
            //TODO: Skipping Companion App Keys

            /* Expiration Keys */
            pass.setExpirationDate(passJson.optString("expirationDate", ""));
            pass.setVoided(passJson.optBoolean("voided", false));

            /* Relevance Keys */
            parseBeacons(passJson, pass);   //TODO: fix beacons and locations
            parseLocations(passJson, pass);
            pass.setMaxDistance(passJson.optInt("maxDistance", Integer.MAX_VALUE));
            pass.setRelevantDate(passJson.optString("relevantDate", ""));

            /*Pass Structure Keys*/
            parsePassStructure(passJson.getJSONObject(pass.getType().getPkType()), pass);

            /* Barcodes Keys */
            parseBarcodes(passJson, pass);

            /* Visual Appearance Keys */
            pass.setBackgroundColor(passJson.optString("backgroundColor", ""));
            pass.setForegroundColor(passJson.optString("foregroundColor", ""));
            pass.setGroupingIdentifier(passJson.optString("groupingIdentifier", ""));
            pass.setLabelColor(passJson.optString("labelColor", ""));
            pass.setLogoText(passJson.optString("logoText"));
            pass.setSuppressStripShine(passJson.optBoolean("suppressStripShine", false));

            /* Web Service Keys */
            pass.setAuthenticationToken(passJson.optString("authenticationToken", ""));
            pass.setWebServiceURL(passJson.optString("webServiceURL"));
        }
        catch (JSONException e) {
            Log.wtf(PARSER_TAG, "Parsing failed inside parser", e);
            e.printStackTrace();    //TODO: Fix exception
        }
    }

    private static void parseBeacons(JSONObject passJson, Pass pass) throws JSONException {
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

        pass.setBeacons(beacons);
    }

    private static void parseLocations(JSONObject passJson, Pass pass) throws JSONException {
        JSONArray locationsJson = passJson.optJSONArray("locations");
        List<PassLocation> locations = new ArrayList<>();

        if (locationsJson != null) {
            for (int i = 0; i < locationsJson.length(); i++) {
                JSONObject locationJson = locationsJson.getJSONObject(i);
                PassLocation passLocation = new PassLocation();

                passLocation.setLatitude(locationJson.getDouble("latitude"));
                passLocation.setLongitude(locationJson.getDouble("longitude"));
                passLocation.setAltitude(locationJson.optDouble("altitude"));
                passLocation.setRelevantText(locationJson.optString("relevantText"));
                locations.add(passLocation);
            }
        }

        pass.setLocations(locations);
    }

    private static void parsePassStructure(JSONObject passStructureJson, Pass pass) throws JSONException {
        JSONArray headerFieldsJson = passStructureJson.optJSONArray("headerFields");
        if (headerFieldsJson != null) {
            pass.setHeaderFields(parsePassFields(headerFieldsJson));
        }

        JSONArray primaryFieldsJson = passStructureJson.optJSONArray("primaryFields");
        if (primaryFieldsJson != null) {
            pass.setPrimaryFields(parsePassFields(primaryFieldsJson));
        }

        JSONArray secondaryFieldsJson = passStructureJson.optJSONArray("secondaryFields");
        if (secondaryFieldsJson != null) {
            pass.setSecondaryFields(parsePassFields(secondaryFieldsJson));
        }

        JSONArray auxiliaryFieldsJson = passStructureJson.optJSONArray("auxiliaryFields");
        if (auxiliaryFieldsJson != null) {
            pass.setAuxiliaryFields(parsePassFields(auxiliaryFieldsJson));
        }

        JSONArray backFieldsJson = passStructureJson.optJSONArray("backFields");
        if (backFieldsJson != null) {
            pass.setBackFields(parsePassFields(backFieldsJson));
        }
    }

    private static List<PassField> parsePassFields(JSONArray passFieldsJson) throws JSONException {
        List<PassField> passFields = new ArrayList<>();
        for (int i = 0; i < passFieldsJson.length(); i++) {
            JSONObject headerFieldJSON = passFieldsJson.getJSONObject(i);
            PassField passField = new PassField();
            passField.setKey(headerFieldJSON.getString("key"));
            passField.setValue(headerFieldJSON.getString("value"));
            passField.setLabel(headerFieldJSON.optString("label"));
            passField.setAttributedValue(headerFieldJSON.optString("attributedValue"));
            passField.setChangeMessage(headerFieldJSON.optString("changeMessage"));
            passField.setTextAlignment(headerFieldJSON.optString("textAlignment"));

            JSONArray dataDetectorTypesJson = headerFieldJSON.optJSONArray("dataDetectorTypes");
            List<String> dataDetectorTypes = new ArrayList<>();
            if (dataDetectorTypesJson != null) {
                for (int j = 0; j < dataDetectorTypesJson.length(); j++) {
                    dataDetectorTypes.add(dataDetectorTypesJson.getString(j));
                }
            }
            passField.setDataDetectorTypes(dataDetectorTypes);

            passFields.add(passField);
        }
        return passFields;
    }

    private static void parseBarcodes(JSONObject passJson, Pass pass) throws JSONException {

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

        pass.setBarcodes(barcodes);
    }

    private static PassBarcode parseBarcode(JSONObject barcodeJson) throws JSONException {
        PassBarcode barcode = new PassBarcode();
        barcode.setMessage(barcodeJson.getString("message"));
        barcode.setFormat(barcodeJson.getString("format"));
        barcode.setMessageEncoding(barcodeJson.getString("messageEncoding"));
        barcode.setAltText(barcodeJson.optString("altText"));
        return barcode;
    }
}
