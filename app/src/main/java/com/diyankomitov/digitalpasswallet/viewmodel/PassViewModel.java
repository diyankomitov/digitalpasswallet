package com.diyankomitov.digitalpasswallet.viewmodel;

import android.graphics.Bitmap;

import com.diyankomitov.digitalpasswallet.models.pass.Pass;
import com.diyankomitov.digitalpasswallet.models.pass.util.PassField;
import com.diyankomitov.digitalpasswallet.models.pass.util.PassType;

import java.util.List;
import java.util.Objects;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PassViewModel extends ViewModel {

    //Images
    private MutableLiveData<Bitmap> backgroundImage;
    private MutableLiveData<Bitmap> barcode;
    private MutableLiveData<Bitmap> footer;
    private MutableLiveData<Bitmap> icon;
    private MutableLiveData<Bitmap> logo;
    private MutableLiveData<Bitmap> strip;
    private MutableLiveData<Bitmap> thumbnail;

    //Colors
    private MutableLiveData<Integer> backgroundColor;
    private MutableLiveData<Integer> foregroundColor;
    private MutableLiveData<Integer> labelColor;

    //Text
    private MutableLiveData<String> logoText;

    private MutableLiveData<String> headerLabel1;
    private MutableLiveData<String> headerValue1;
    private MutableLiveData<String> headerLabel2;
    private MutableLiveData<String> headerValue2;
    private MutableLiveData<String> headerLabel3;
    private MutableLiveData<String> headerValue3;

    private MutableLiveData<String> primaryLabel1;
    private MutableLiveData<String> primaryValue1;
    private MutableLiveData<String> primaryLabel2;
    private MutableLiveData<String> primaryValue2;

    private MutableLiveData<String> secondaryLabel1;
    private MutableLiveData<String> secondaryValue1;
    private MutableLiveData<String> secondaryLabel2;
    private MutableLiveData<String> secondaryValue2;
    private MutableLiveData<String> secondaryLabel3;
    private MutableLiveData<String> secondaryValue3;
    private MutableLiveData<String> secondaryLabel4;
    private MutableLiveData<String> secondaryValue4;

    private MutableLiveData<String> auxiliaryLabel1;
    private MutableLiveData<String> auxiliaryValue1;
    private MutableLiveData<String> auxiliaryLabel2;
    private MutableLiveData<String> auxiliaryValue2;
    private MutableLiveData<String> auxiliaryLabel3;
    private MutableLiveData<String> auxiliaryValue3;
    private MutableLiveData<String> auxiliaryLabel4;
    private MutableLiveData<String> auxiliaryValue4;
    private MutableLiveData<String> auxiliaryLabel5;
    private MutableLiveData<String> auxiliaryValue5;

    private MutableLiveData<PassType> passType;

    public PassViewModel(Pass pass) {
        this.backgroundImage = new MutableLiveData<>();
        this.barcode = new MutableLiveData<>();
        this.footer = new MutableLiveData<>();
        this.icon = new MutableLiveData<>();
        this.logo = new MutableLiveData<>();
        this.strip = new MutableLiveData<>();
        this.thumbnail = new MutableLiveData<>();
        this.logoText = new MutableLiveData<>();

        this.backgroundColor = new MutableLiveData<>();
        this.foregroundColor = new MutableLiveData<>();
        this.labelColor = new MutableLiveData<>();

        this.headerLabel1 = new MutableLiveData<>();
        this.headerValue1 = new MutableLiveData<>();
        this.headerLabel2 = new MutableLiveData<>();
        this.headerValue2 = new MutableLiveData<>();
        this.headerLabel3 = new MutableLiveData<>();
        this.headerValue3 = new MutableLiveData<>();

        this.primaryLabel1 = new MutableLiveData<>();
        this.primaryValue1 = new MutableLiveData<>();
        this.primaryLabel2 = new MutableLiveData<>();
        this.primaryValue2 = new MutableLiveData<>();

        this.secondaryLabel1 = new MutableLiveData<>();
        this.secondaryValue1 = new MutableLiveData<>();
        this.secondaryLabel2 = new MutableLiveData<>();
        this.secondaryValue2 = new MutableLiveData<>();
        this.secondaryLabel3 = new MutableLiveData<>();
        this.secondaryValue3 = new MutableLiveData<>();
        this.secondaryLabel4 = new MutableLiveData<>();
        this.secondaryValue4 = new MutableLiveData<>();

        this.auxiliaryLabel1 = new MutableLiveData<>();
        this.auxiliaryValue1 = new MutableLiveData<>();
        this.auxiliaryLabel2 = new MutableLiveData<>();
        this.auxiliaryValue2 = new MutableLiveData<>();
        this.auxiliaryLabel3 = new MutableLiveData<>();
        this.auxiliaryValue3 = new MutableLiveData<>();
        this.auxiliaryLabel4 = new MutableLiveData<>();
        this.auxiliaryValue4 = new MutableLiveData<>();
        this.auxiliaryLabel5 = new MutableLiveData<>();
        this.auxiliaryValue5 = new MutableLiveData<>();

        this.passType = new MutableLiveData<>();

        if (pass != null) {
            this.backgroundImage.setValue(pass.getBackgroundImage());
            this.barcode.setValue(pass.getBarcode());
            this.footer.setValue(pass.getFooter());
            this.icon.setValue(pass.getIcon());
            this.logo.setValue(pass.getLogo());
            this.strip.setValue(pass.getStrip());
            this.thumbnail.setValue(pass.getThumbnail());
            this.logoText.setValue(pass.getLogoText());

            this.backgroundColor.setValue(pass.getBackgroundColor());
            this.foregroundColor.setValue(pass.getForegroundColor());
            this.labelColor.setValue(pass.getLabelColor());

            List<PassField> headerFields = pass.getHeaderFields();
            this.headerLabel1.setValue(getPassFieldLabel(headerFields, 0));
            this.headerValue1.setValue(getPassFieldValue(headerFields, 0));
            this.headerLabel2.setValue(getPassFieldLabel(headerFields, 1));
            this.headerValue2.setValue(getPassFieldValue(headerFields, 1));
            this.headerLabel3.setValue(getPassFieldLabel(headerFields, 2));
            this.headerValue3.setValue(getPassFieldValue(headerFields, 2));

            List<PassField> primaryFields = pass.getHeaderFields();
            this.primaryLabel1.setValue(getPassFieldLabel(primaryFields, 0));
            this.primaryValue1.setValue(getPassFieldValue(primaryFields, 0));
            this.primaryLabel2.setValue(getPassFieldLabel(primaryFields, 1));
            this.primaryValue2.setValue(getPassFieldValue(primaryFields, 1));

            List<PassField> secondaryFields = pass.getHeaderFields();
            this.secondaryLabel1.setValue(getPassFieldLabel(secondaryFields, 0));
            this.secondaryValue1.setValue(getPassFieldValue(secondaryFields, 0));
            this.secondaryLabel2.setValue(getPassFieldLabel(secondaryFields, 1));
            this.secondaryValue2.setValue(getPassFieldValue(secondaryFields, 1));
            this.secondaryLabel3.setValue(getPassFieldLabel(secondaryFields, 2));
            this.secondaryValue3.setValue(getPassFieldValue(secondaryFields, 2));
            this.secondaryLabel4.setValue(getPassFieldLabel(secondaryFields, 3));
            this.secondaryValue4.setValue(getPassFieldValue(secondaryFields, 3));

            List<PassField> auxiliaryFields = pass.getHeaderFields();
            this.auxiliaryLabel1.setValue(getPassFieldLabel(auxiliaryFields, 0));
            this.auxiliaryValue1.setValue(getPassFieldValue(auxiliaryFields, 0));
            this.auxiliaryLabel2.setValue(getPassFieldLabel(auxiliaryFields, 1));
            this.auxiliaryValue2.setValue(getPassFieldValue(auxiliaryFields, 1));
            this.auxiliaryLabel3.setValue(getPassFieldLabel(auxiliaryFields, 2));
            this.auxiliaryValue3.setValue(getPassFieldValue(auxiliaryFields, 2));
            this.auxiliaryLabel4.setValue(getPassFieldLabel(auxiliaryFields, 3));
            this.auxiliaryValue4.setValue(getPassFieldValue(auxiliaryFields, 3));
            this.auxiliaryLabel5.setValue(getPassFieldLabel(auxiliaryFields, 4));
            this.auxiliaryValue5.setValue(getPassFieldValue(auxiliaryFields, 4));

            this.passType.setValue(pass.getType());
        }

    }

    private String getPassFieldLabel(List<PassField> passFields, int index) {

        if (passFields != null && index < passFields.size()) {
            return passFields.get(index).getLabel();
        }

        //TODO: change to empty string?
        return null;
    }

    private String getPassFieldValue(List<PassField> passFields, int index) {

        if (passFields != null && index < passFields.size()) {
            return passFields.get(index).getValue();
        }

        //TODO: change to empty string?
        return null;
    }

    public LiveData<Bitmap> getBackgroundImage() {
        return backgroundImage;
    }

    public LiveData<Bitmap> getBarcode() {
        return barcode;
    }

    public LiveData<Bitmap> getFooter() {
        return footer;
    }

    public LiveData<Bitmap> getIcon() {
        return icon;
    }

    public LiveData<Bitmap> getLogo() {
        return logo;
    }

    public LiveData<Bitmap> getStrip() {
        return strip;
    }

    public LiveData<Bitmap> getThumbnail() {
        return thumbnail;
    }

    public LiveData<Integer> getBackgroundColor() {
        return backgroundColor;
    }

    public LiveData<Integer> getForegroundColor() {
        return foregroundColor;
    }

    public LiveData<Integer> getLabelColor() {
        return labelColor;
    }

    public LiveData<String> getLogoText() {
        return logoText;
    }

    public LiveData<String> getHeaderLabel1() {
        return headerLabel1;
    }

    public LiveData<String> getHeaderValue1() {
        return headerValue1;
    }

    public LiveData<String> getHeaderLabel2() {
        return headerLabel2;
    }

    public LiveData<String> getHeaderValue2() {
        return headerValue2;
    }

    public LiveData<String> getHeaderLabel3() {
        return headerLabel3;
    }

    public LiveData<String> getHeaderValue3() {
        return headerValue3;
    }

    public LiveData<String> getPrimaryLabel1() {
        return primaryLabel1;
    }

    public LiveData<String> getPrimaryValue1() {
        return primaryValue1;
    }

    public LiveData<String> getPrimaryLabel2() {
        return primaryLabel2;
    }

    public LiveData<String> getPrimaryValue2() {
        return primaryValue2;
    }

    public LiveData<String> getSecondaryLabel1() {
        return secondaryLabel1;
    }

    public LiveData<String> getSecondaryValue1() {
        return secondaryValue1;
    }

    public LiveData<String> getSecondaryLabel2() {
        return secondaryLabel2;
    }

    public LiveData<String> getSecondaryValue2() {
        return secondaryValue2;
    }

    public LiveData<String> getSecondaryLabel3() {
        return secondaryLabel3;
    }

    public LiveData<String> getSecondaryValue3() {
        return secondaryValue3;
    }

    public LiveData<String> getSecondaryLabel4() {
        return secondaryLabel4;
    }

    public LiveData<String> getSecondaryValue4() {
        return secondaryValue4;
    }

    public LiveData<String> getAuxiliaryLabel1() {
        return auxiliaryLabel1;
    }

    public LiveData<String> getAuxiliaryValue1() {
        return auxiliaryValue1;
    }

    public LiveData<String> getAuxiliaryLabel2() {
        return auxiliaryLabel2;
    }

    public LiveData<String> getAuxiliaryValue2() {
        return auxiliaryValue2;
    }

    public LiveData<String> getAuxiliaryLabel3() {
        return auxiliaryLabel3;
    }

    public LiveData<String> getAuxiliaryValue3() {
        return auxiliaryValue3;
    }

    public LiveData<String> getAuxiliaryLabel4() {
        return auxiliaryLabel4;
    }

    public LiveData<String> getAuxiliaryValue4() {
        return auxiliaryValue4;
    }

    public LiveData<String> getAuxiliaryLabel5() {
        return auxiliaryLabel5;
    }

    public LiveData<String> getAuxiliaryValue5() {
        return auxiliaryValue5;
    }

    public LiveData<PassType> getPassType() {
        return passType;
    }
}
