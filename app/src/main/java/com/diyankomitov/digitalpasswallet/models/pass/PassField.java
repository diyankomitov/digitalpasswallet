package com.diyankomitov.digitalpasswallet.models.pass;

import java.util.List;

public class PassField {
    private String key;
    private String value;
    private String label;
    private String attributedValue;
    private String changeMessage;
    private String textAlignment;
    private List<String> dataDetectorTypes;

    public PassField() {

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAttributedValue() {
        return attributedValue;
    }

    public void setAttributedValue(String attributedValue) {
        this.attributedValue = attributedValue;
    }

    public String getChangeMessage() {
        return changeMessage;
    }

    public void setChangeMessage(String changeMessage) {
        this.changeMessage = changeMessage;
    }

    public String getTextAlignment() {
        return textAlignment;
    }

    public void setTextAlignment(String textAlignment) {
        this.textAlignment = textAlignment;
    }

    public List<String> getDataDetectorTypes() {
        return dataDetectorTypes;
    }

    public void setDataDetectorTypes(List<String> dataDetectorTypes) {
        this.dataDetectorTypes = dataDetectorTypes;
    }
}
