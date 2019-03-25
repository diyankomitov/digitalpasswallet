package com.diyankomitov.digitalpasswallet.models.pass.components;

import android.os.Parcel;
import android.os.Parcelable;

import com.diyankomitov.digitalpasswallet.models.pass.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import androidx.annotation.NonNull;

import static com.diyankomitov.digitalpasswallet.models.pass.components.PassDataDetectorType.ADDRESS;
import static com.diyankomitov.digitalpasswallet.models.pass.components.PassDataDetectorType.CALENDAR_EVENT;
import static com.diyankomitov.digitalpasswallet.models.pass.components.PassDataDetectorType.LINK;
import static com.diyankomitov.digitalpasswallet.models.pass.components.PassDataDetectorType.PHONE_NUMBER;
import static com.diyankomitov.digitalpasswallet.models.pass.components.PassTextAlignment.NATURAL;

/**
 * This represents a field shown at the front or back of the pass.
 * <p>
 * This can include anything from start times, points, booking numbers, cardholder names,
 * addresses and more.
 * <p>
 * Generally speaking these fields can be divided into header fields, primary fields, secondary
 * fields, auxiliary fields and back fields.
 * <p>
 * All besides the back fields would appear on the front of the pass, given that there is enough
 * space, and the back fields would appear on the back of the pass.
 * <p>
 * Every field consists of a label and a value, which are visible to the user, as well as a key
 * which is not.
 * <p>
 * Values only support plain text and any html code is displayed as plain text.
 * If {@code <a href=""></a>} tags are required the attributed value should instead be used, which
 * replaces the value on screen.
 * <p>
 * If the value is to be treated as a date, the dateStyle and timeStyle properties should both be
 * set to a PassDateTimeStyle that isn't {@link PassDateTimeStyle#NONE NONE}
 */
public class PassField implements Parcelable {
    
    //Standard keys
    @NonNull
    private String key;
    @NonNull
    private String label;
    @NonNull
    private String value;
    @NonNull
    private String attributedValue;
    @NonNull
    private String changeMessage;
    @NonNull
    private PassTextAlignment textAlignment;
    @NonNull
    private Set<PassDataDetectorType> dataDetectorTypes;
    
    //Date Style keys
    @NonNull
    private PassDateTimeStyle dateStyle;
    @NonNull
    private PassDateTimeStyle timeStyle;
    private boolean ignoresTimeZone;
    private boolean isRelative;
    
    //Number Style keys;
    @NonNull
    private String currencyCode;
    @NonNull
    private PassNumberStyle numberStyle;
    
    public PassField(String label, String value) {
        
        this();
        this.label = label == null ? "" : label;
        this.value = value == null ? "" : value;
    }
    
    /**
     * Instantiates a new Pass Field.
     */
    public PassField() {
        
        this.key = "";
        this.label = "";
        this.value = "";
        this.attributedValue = "";
        this.changeMessage = "";
        this.textAlignment = NATURAL;
        this.dataDetectorTypes = new HashSet<>();
        Collections.addAll(dataDetectorTypes, PHONE_NUMBER, LINK, ADDRESS, CALENDAR_EVENT);
        
        this.dateStyle = PassDateTimeStyle.NONE;
        this.timeStyle = PassDateTimeStyle.NONE;
        this.ignoresTimeZone = false;
        this.isRelative = false;    //TODO: fix
        
        this.currencyCode = "";
        this.numberStyle = PassNumberStyle.NONE;
    }
    
    // Parcelable
    
    protected PassField(Parcel in) {
        
        key = Objects.requireNonNull(in.readString());
        label = Objects.requireNonNull(in.readString());
        value = Objects.requireNonNull(in.readString());
        attributedValue = Objects.requireNonNull(in.readString());
        changeMessage = Objects.requireNonNull(in.readString());
        textAlignment = PassTextAlignment.values()[in.readInt()];
        
        dataDetectorTypes = new HashSet<>();
        List<Integer> dataDetectorTypeIntList = new ArrayList<>();
        in.readList(dataDetectorTypeIntList, Integer.class.getClassLoader());
        for (Integer i : dataDetectorTypeIntList) {
            dataDetectorTypes.add(PassDataDetectorType.values()[i]);
        }
        
        dateStyle = PassDateTimeStyle.values()[in.readInt()];
        timeStyle = PassDateTimeStyle.values()[in.readInt()];
        
        ignoresTimeZone = in.readByte() != 0;
        isRelative = in.readByte() != 0;
        currencyCode = Objects.requireNonNull(in.readString());
        numberStyle = PassNumberStyle.values()[in.readInt()];
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        
        dest.writeString(key);
        dest.writeString(label);
        dest.writeString(value);
        dest.writeString(attributedValue);
        dest.writeString(changeMessage);
        Utils.writeEnumToParcel(dest, textAlignment);
        
        List<Integer> dataDetectorTypesIntList = new ArrayList<>();
        for (PassDataDetectorType dataDetectorType : dataDetectorTypes) {
            dataDetectorTypesIntList.add(dataDetectorType.ordinal());
        }
        dest.writeList(dataDetectorTypesIntList);
        
        Utils.writeEnumToParcel(dest, dateStyle);
        Utils.writeEnumToParcel(dest, timeStyle);
        dest.writeByte((byte) (ignoresTimeZone ? 1 : 0));
        dest.writeByte((byte) (isRelative ? 1 : 0));
        dest.writeString(currencyCode);
        Utils.writeEnumToParcel(dest, numberStyle);
    }
    
    @Override
    public int describeContents() {
        
        return 0;
    }
    
    public static final Creator<PassField> CREATOR = new Creator<PassField>() {
        @Override
        public PassField createFromParcel(Parcel in) {
            
            return new PassField(in);
        }
        
        @Override
        public PassField[] newArray(int size) {
            
            return new PassField[size];
        }
    };
    
    // Getters and Setters
    
    /**
     * Gets the field key.
     *
     * @return the key
     */
    @NonNull
    public String getKey() {
        
        return key;
    }
    
    /**
     * Sets the field key.
     *
     * @param key the key
     */
    public void setKey(String key) {
        
        this.key = key == null ? "" : key;
    }
    
    /**
     * Gets the field value.
     *
     * @return the value
     */
    @NonNull
    public String getValue() {
        
        return value;
    }
    
    /**
     * Sets the field value.
     *
     * @param value the value
     */
    public void setValue(String value) {
        
        this.value = value == null ? "" : value;
    }
    
    /**
     * Gets the field label.
     * <p>
     * Any html tags are displayed as plain text
     *
     * @return the label
     */
    @NonNull
    public String getLabel() {
        
        return label;
    }
    
    /**
     * Sets the field label.
     * <p>
     * Any html tags are displayed as plain text
     *
     * @param label the label
     */
    public void setLabel(String label) {
        
        this.label = label == null ? "" : label;
    }
    
    /**
     * Gets the attributed value.
     * <p>
     * This can contain {@code <a href=""></a>} tags
     *
     * @return the attributed value
     */
    @NonNull
    public String getAttributedValue() {
        
        return attributedValue;
    }
    
    /**
     * Sets the attributed value.
     * <p>
     * This can contain {@code <a href=""></a>} tags
     *
     * @param attributedValue the attributed value
     */
    public void setAttributedValue(String attributedValue) {
        
        this.attributedValue = attributedValue == null ? "" : attributedValue;
    }
    
    /**
     * Gets the change message.
     * <p>
     * This displays as part of the value when the field is updated with a
     * new value. it must contain %@ which is replaced with the new value.
     *
     * @return the change message
     */
    @NonNull
    public String getChangeMessage() {
        
        return changeMessage;
    }
    
    /**
     * Sets the change message.
     * <p>
     * This displays as part of the value when the field is updated with a
     * new value. it must contain %@ which is replaced with the new value.
     *
     * @param changeMessage the change message
     */
    public void setChangeMessage(String changeMessage) {
        
        this.changeMessage = changeMessage == null ? "" : changeMessage;
    }
    
    /**
     * Gets the text alignment.
     * <p>
     * This controls how the field's contents will appear. They will
     * either be aligned to the left, to the right, centrally, or naturally which is based upon the
     * script's direction
     *
     * @return the text alignment
     */
    @NonNull
    public PassTextAlignment getTextAlignment() {
        
        return textAlignment;
    }
    
    /**
     * Sets the text alignment.
     * <p>
     * This controls how the field's contents will appear. They will
     * either be aligned to the left, to the right, centrally, or naturally which is based upon the
     * script's direction
     *
     * @param textAlignment the text alignment
     */
    public void setTextAlignment(PassTextAlignment textAlignment) {
        
        this.textAlignment = textAlignment == null ? NATURAL : textAlignment;
    }
    
    /**
     * Gets the data detector types.
     * <p>
     * These detect if a field's value is of a certain type and will
     * display it accordingly. Valid values are Phone Number, Link, Address and Calendar Event. By
     * default all detectors are enabled.
     *
     * @return the data detector types
     */
    @NonNull
    public Set<PassDataDetectorType> getDataDetectorTypes() {
        
        return dataDetectorTypes;
    }
    
    /**
     * Sets the data detector types.
     * <p>
     * These detect if a field's value is of a certain type and will
     * display it accordingly. Valid values are Phone Number, Link, Address and Calendar Event. By
     * default all detectors are enabled.
     *
     * @param dataDetectorTypes the data detector types
     */
    public void setDataDetectorTypes(Set<PassDataDetectorType> dataDetectorTypes) {
        
        if (dataDetectorTypes == null) {
            this.dataDetectorTypes.clear();
            Collections.addAll(this.dataDetectorTypes, PHONE_NUMBER, LINK, ADDRESS, CALENDAR_EVENT);
        } else {
            this.dataDetectorTypes = dataDetectorTypes;
        }
    }
    
    /**
     * Gets the date style.
     * <p>
     * If this and timeStyle are both set, to a PassDateTimeStyle that isn't {@link
     * PassDateTimeStyle#NONE NONE}, the field will be treated as a date.
     *
     * @return the date style
     */
    @NonNull
    public PassDateTimeStyle getDateStyle() {
        
        return dateStyle;
    }
    
    /**
     * Sets the date style.
     * <p>
     * If this and timeStyle are both set, to a PassDateTimeStyle that isn't {@link
     * PassDateTimeStyle#NONE NONE}, the field will be treated as a date.
     *
     * @param dateStyle the date style
     */
    public void setDateStyle(PassDateTimeStyle dateStyle) {
        
        this.dateStyle = dateStyle == null ? PassDateTimeStyle.NONE : dateStyle;
    }
    
    /**
     * Gets the time style.
     * <p>
     * If this and dateStyle are both set, to a PassDateTimeStyle that isn't {@link
     * PassDateTimeStyle#NONE NONE}, the field will be treated as a date.
     *
     * @return the time style
     */
    @NonNull
    public PassDateTimeStyle getTimeStyle() {
        
        return timeStyle;
    }
    
    /**
     * Sets the time style.
     * <p>
     * If this and dateStyle are both set, to a PassDateTimeStyle that isn't {@link
     * PassDateTimeStyle#NONE NONE}, the field will be treated as a date.
     *
     * @param timeStyle the time style
     */
    public void setTimeStyle(PassDateTimeStyle timeStyle) {
        
        this.timeStyle = timeStyle == null ? PassDateTimeStyle.NONE : timeStyle;
    }
    
    /**
     * Checks if the field should ignore the user's current time zone. If true the date will be
     * displayed in the given timezone. The default value is false.
     *
     * @return true if the date ignores the current time zone, false otherwise
     */
    public boolean isIgnoresTimeZone() {
        
        return ignoresTimeZone;
    }
    
    /**
     * Sets if the field should ignore the user's time zone. If true the date will be displayed in
     * the given timezone. The default value is false.
     *
     * @param ignoresTimeZone true if the current time zone should be ignored, false otherwise.
     */
    public void setIgnoresTimeZone(boolean ignoresTimeZone) {
        
        this.ignoresTimeZone = ignoresTimeZone;
    }
    
    /**
     * Checks if the date should be displayed relatively. The default value is false.
     * For example: Tomorrow, Yesterday, In two weeks, etc.
     *
     * @return true if the date should be displayed relatively, false otherwise.
     */
    public boolean isRelative() {
        
        return isRelative;
    }
    
    /**
     * Sets if the date should be displayed relatively. The default value is false.
     * For example: Tomorrow, Yesterday, In two weeks, etc.
     *
     * @param relative true if the date should be displayed relatively, false otherwise.
     */
    public void setRelative(boolean relative) {
        
        isRelative = relative;
    }
    
    /**
     * Gets the currency code.
     *
     * @return the currency code
     */
    @NonNull
    public String getCurrencyCode() {
        
        return currencyCode;
    }
    
    /**
     * Sets the currency code.
     *
     * @param currencyCode the currency code
     */
    public void setCurrencyCode(String currencyCode) {
        
        this.currencyCode = currencyCode  == null ? "" : currencyCode;
    }
    
    /**
     * Gets the number style.
     *
     * @return the number style
     */
    @NonNull
    public PassNumberStyle getNumberStyle() {
        
        return numberStyle;
    }
    
    /**
     * Sets the number style.
     *
     * @param numberStyle the number style
     */
    public void setNumberStyle(PassNumberStyle numberStyle) {
        
        this.numberStyle = numberStyle == null ? PassNumberStyle.NONE : numberStyle;
    }
}
