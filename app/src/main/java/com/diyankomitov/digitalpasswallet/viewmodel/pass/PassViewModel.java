package com.diyankomitov.digitalpasswallet.viewmodel.pass;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.diyankomitov.digitalpasswallet.models.pass.Pass;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassBarcode;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassBarcodeFormat;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassField;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassTransitType;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassType;
import com.diyankomitov.digitalpasswallet.viewmodel.WalletViewModel;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class PassViewModel {
    
    private static final String TAG = "PassViewModel";
    
    private WalletViewModel walletViewModel;
    
    private int passId;
    private PassType passType;
    @NonNull
    private PassTransitType transitType;
    
    //Text fields
    private String logoText;
    
    private String headerLabel1;
    private String headerValue1;
    private String headerLabel2;
    private String headerValue2;
    private String headerLabel3;
    private String headerValue3;
    
    private String primaryLabel1;
    private String primaryValue1;
    private String primaryLabel2;
    private String primaryValue2;
    
    private String secondaryLabel1;
    private String secondaryValue1;
    private String secondaryLabel2;
    private String secondaryValue2;
    private String secondaryLabel3;
    private String secondaryValue3;
    private String secondaryLabel4;
    private String secondaryValue4;
    
    private String auxiliaryLabel1;
    private String auxiliaryValue1;
    private String auxiliaryLabel2;
    private String auxiliaryValue2;
    private String auxiliaryLabel3;
    private String auxiliaryValue3;
    private String auxiliaryLabel4;
    private String auxiliaryValue4;
    private String auxiliaryLabel5;
    private String auxiliaryValue5;
    
    private List<PassFieldViewModel> backFields;
    
    //Colors
    @ColorInt
    private int backgroundColor;
    @ColorInt
    private int foregroundColor;
    @ColorInt
    private int labelColor;
    
    //Images
    private Bitmap barcode;
    private String backgroundImagePath;
    private String footerPath;
    private String iconPath;
    private String logoPath;
    private String stripPath;
    private String thumbnailPath;
    
    public PassViewModel(@NonNull Pass pass, WalletViewModel walletViewModel) {
        
        this.walletViewModel = walletViewModel;
        
        init(pass);
    }
    
    public void onClickDelete() {
    
        Log.d(TAG, "onClickDelete: pressed");
        walletViewModel.deletePass(passId);
    }
    
    private void init(Pass pass) {
        
        this.barcode = generateBarcode(pass.getBarcodes().get(0));
        
        this.backgroundImagePath = pass.getBackgroundImagePath();
        this.footerPath = pass.getFooterPath();
        this.iconPath = pass.getIconPath();
        this.logoPath = pass.getLogoPath();
        this.stripPath = pass.getStripPath();
        this.thumbnailPath = pass.getThumbnailPath();
        this.logoText = pass.getLogoText();
        
        this.backgroundColor = pass.getBackgroundColor();
        this.foregroundColor = pass.getForegroundColor();
        this.labelColor = pass.getLabelColor();
        
        List<PassFieldViewModel> headerFields = new ArrayList<>();
        for (PassField headerField : pass.getHeaderFields()) {
            headerFields.add(new PassFieldViewModel(headerField));
        }
        this.headerLabel1 = getPassFieldLabel(headerFields, 0);
        this.headerValue1 = getPassFieldValue(headerFields, 0);
        this.headerLabel2 = getPassFieldLabel(headerFields, 1);
        this.headerValue2 = getPassFieldValue(headerFields, 1);
        this.headerLabel3 = getPassFieldLabel(headerFields, 2);
        this.headerValue3 = getPassFieldValue(headerFields, 2);
        
        List<PassFieldViewModel> primaryFields = new ArrayList<>();
        for (PassField primaryField : pass.getPrimaryFields()) {
            primaryFields.add(new PassFieldViewModel(primaryField));
        }
        this.primaryLabel1 = getPassFieldLabel(primaryFields, 0);
        this.primaryValue1 = getPassFieldValue(primaryFields, 0);
        this.primaryLabel2 = getPassFieldLabel(primaryFields, 1);
        this.primaryValue2 = getPassFieldValue(primaryFields, 1);
        
        List<PassFieldViewModel> secondaryFields = new ArrayList<>();
        for (PassField secondaryField : pass.getSecondaryFields()) {
            secondaryFields.add(new PassFieldViewModel(secondaryField));
        }
        this.secondaryLabel1 = getPassFieldLabel(secondaryFields, 0);
        this.secondaryValue1 = getPassFieldValue(secondaryFields, 0);
        this.secondaryLabel2 = getPassFieldLabel(secondaryFields, 1);
        this.secondaryValue2 = getPassFieldValue(secondaryFields, 1);
        this.secondaryLabel3 = getPassFieldLabel(secondaryFields, 2);
        this.secondaryValue3 = getPassFieldValue(secondaryFields, 2);
        this.secondaryLabel4 = getPassFieldLabel(secondaryFields, 3);
        this.secondaryValue4 = getPassFieldValue(secondaryFields, 3);
        
        List<PassFieldViewModel> auxiliaryFields = new ArrayList<>();
        for (PassField auxiliaryField : pass.getAuxiliaryFields()) {
            auxiliaryFields.add(new PassFieldViewModel(auxiliaryField));
        }
        this.auxiliaryLabel1 = getPassFieldLabel(auxiliaryFields, 0);
        this.auxiliaryValue1 = getPassFieldValue(auxiliaryFields, 0);
        this.auxiliaryLabel2 = getPassFieldLabel(auxiliaryFields, 1);
        this.auxiliaryValue2 = getPassFieldValue(auxiliaryFields, 1);
        this.auxiliaryLabel3 = getPassFieldLabel(auxiliaryFields, 2);
        this.auxiliaryValue3 = getPassFieldValue(auxiliaryFields, 2);
        this.auxiliaryLabel4 = getPassFieldLabel(auxiliaryFields, 3);
        this.auxiliaryValue4 = getPassFieldValue(auxiliaryFields, 3);
        this.auxiliaryLabel5 = getPassFieldLabel(auxiliaryFields, 4);
        this.auxiliaryValue5 = getPassFieldValue(auxiliaryFields, 4);
        
        this.backFields = new ArrayList<>();
        for (PassField backField : pass.getBackFields()) {
            backFields.add(new PassFieldViewModel(backField));
        }
        
        this.passType = pass.getType();
        
        this.transitType = pass.getTransitType();
        
        this.passId = pass.getId();
    }
    
    private Bitmap generateBarcode(PassBarcode barcode) {
        
        PassBarcodeFormat passBarcodeFormat = barcode.getFormat();
        
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        
        int width;
        int height;
        
        if (passBarcodeFormat == PassBarcodeFormat.QR ||
            passBarcodeFormat == PassBarcodeFormat.AZTEC) {
            width = (int) (displayMetrics.widthPixels / 2.5);
            height = width;
        } else {
            width = displayMetrics.widthPixels / 2;
            height = width / 3;
        }
        
        Bitmap barcodeBitmap = null;
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        try {
            barcodeBitmap = barcodeEncoder
                    .encodeBitmap(barcode.getMessage(), passBarcodeFormat.getZXingBarcodeFormat(),
                                  width, height);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        
        return barcodeBitmap;
    }
    
    private String getPassFieldLabel(List<PassFieldViewModel> passFields, int index) {
        
        if (passFields != null && index < passFields.size()) {
            return passFields.get(index).getLabel().toUpperCase();
        }
        
        return null;
    }
    
    private String getPassFieldValue(List<PassFieldViewModel> passFields, int index) {
        
        if (passFields != null && index < passFields.size()) {
            return passFields.get(index).getValue();
        }
        
        return null;
    }
    
    //Getters
    
    public Bitmap getBarcode() {
        
        return barcode;
    }
    
    public String getBackgroundImagePath() {
        
        return backgroundImagePath;
    }
    
    public String getFooterPath() {
        
        return footerPath;
    }
    
    public String getIconPath() {
        
        return iconPath;
    }
    
    public String getLogoPath() {
        
        return logoPath;
    }
    
    public String getStripPath() {
        
        return stripPath;
    }
    
    public String getThumbnailPath() {
        
        return thumbnailPath;
    }
    
    @ColorInt
    public int getBackgroundColor() {
        
        return backgroundColor;
    }
    
    @ColorInt
    public int getForegroundColor() {
        
        return foregroundColor;
    }
    
    @ColorInt
    public int getLabelColor() {
        
        return labelColor;
    }
    
    public String getLogoText() {
        
        return logoText;
    }
    
    public String getHeaderLabel1() {
        
        return headerLabel1;
    }
    
    public String getHeaderValue1() {
        
        return headerValue1;
    }
    
    public String getHeaderLabel2() {
        
        return headerLabel2;
    }
    
    public String getHeaderValue2() {
        
        return headerValue2;
    }
    
    public String getHeaderLabel3() {
        
        return headerLabel3;
    }
    
    public String getHeaderValue3() {
        
        return headerValue3;
    }
    
    public String getPrimaryLabel1() {
        
        return primaryLabel1;
    }
    
    public String getPrimaryValue1() {
        
        return primaryValue1;
    }
    
    public String getPrimaryLabel2() {
        
        return primaryLabel2;
    }
    
    public String getPrimaryValue2() {
        
        return primaryValue2;
    }
    
    public String getSecondaryLabel1() {
        
        return secondaryLabel1;
    }
    
    public String getSecondaryValue1() {
        
        return secondaryValue1;
    }
    
    public String getSecondaryLabel2() {
        
        return secondaryLabel2;
    }
    
    public String getSecondaryValue2() {
        
        return secondaryValue2;
    }
    
    public String getSecondaryLabel3() {
        
        return secondaryLabel3;
    }
    
    public String getSecondaryValue3() {
        
        return secondaryValue3;
    }
    
    public String getSecondaryLabel4() {
        
        return secondaryLabel4;
    }
    
    public String getSecondaryValue4() {
        
        return secondaryValue4;
    }
    
    public String getAuxiliaryLabel1() {
        
        return auxiliaryLabel1;
    }
    
    public String getAuxiliaryValue1() {
        
        return auxiliaryValue1;
    }
    
    public String getAuxiliaryLabel2() {
        
        return auxiliaryLabel2;
    }
    
    public String getAuxiliaryValue2() {
        
        return auxiliaryValue2;
    }
    
    public String getAuxiliaryLabel3() {
        
        return auxiliaryLabel3;
    }
    
    public String getAuxiliaryValue3() {
        
        return auxiliaryValue3;
    }
    
    public String getAuxiliaryLabel4() {
        
        return auxiliaryLabel4;
    }
    
    public String getAuxiliaryValue4() {
        
        return auxiliaryValue4;
    }
    
    public String getAuxiliaryLabel5() {
        
        return auxiliaryLabel5;
    }
    
    public String getAuxiliaryValue5() {
        
        return auxiliaryValue5;
    }
    
    public List<PassFieldViewModel> getBackFields() {
        
        return backFields;
    }
    
    public PassType getPassType() {
        
        return passType;
    }
    
    @NonNull
    public PassTransitType getTransitType() {
        
        return transitType;
    }
    
    public int getPassId() {
        
        return passId;
    }
    
    @Override
    public boolean equals(Object o) {
        
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PassViewModel that = (PassViewModel) o;
        return getBackgroundColor() == that.getBackgroundColor() &&
               getForegroundColor() == that.getForegroundColor() &&
               getLabelColor() == that.getLabelColor() && getPassId() == that.getPassId() &&
               getBarcode().sameAs(that.getBarcode()) &&
               Objects.equals(getBackgroundImagePath(), that.getBackgroundImagePath()) &&
               Objects.equals(getFooterPath(), that.getFooterPath()) &&
               Objects.equals(getIconPath(), that.getIconPath()) &&
               Objects.equals(getLogoPath(), that.getLogoPath()) &&
               Objects.equals(getStripPath(), that.getStripPath()) &&
               Objects.equals(getThumbnailPath(), that.getThumbnailPath()) &&
               Objects.equals(getLogoText(), that.getLogoText()) &&
               Objects.equals(getHeaderLabel1(), that.getHeaderLabel1()) &&
               Objects.equals(getHeaderValue1(), that.getHeaderValue1()) &&
               Objects.equals(getHeaderLabel2(), that.getHeaderLabel2()) &&
               Objects.equals(getHeaderValue2(), that.getHeaderValue2()) &&
               Objects.equals(getHeaderLabel3(), that.getHeaderLabel3()) &&
               Objects.equals(getHeaderValue3(), that.getHeaderValue3()) &&
               Objects.equals(getPrimaryLabel1(), that.getPrimaryLabel1()) &&
               Objects.equals(getPrimaryValue1(), that.getPrimaryValue1()) &&
               Objects.equals(getPrimaryLabel2(), that.getPrimaryLabel2()) &&
               Objects.equals(getPrimaryValue2(), that.getPrimaryValue2()) &&
               Objects.equals(getSecondaryLabel1(), that.getSecondaryLabel1()) &&
               Objects.equals(getSecondaryValue1(), that.getSecondaryValue1()) &&
               Objects.equals(getSecondaryLabel2(), that.getSecondaryLabel2()) &&
               Objects.equals(getSecondaryValue2(), that.getSecondaryValue2()) &&
               Objects.equals(getSecondaryLabel3(), that.getSecondaryLabel3()) &&
               Objects.equals(getSecondaryValue3(), that.getSecondaryValue3()) &&
               Objects.equals(getSecondaryLabel4(), that.getSecondaryLabel4()) &&
               Objects.equals(getSecondaryValue4(), that.getSecondaryValue4()) &&
               Objects.equals(getAuxiliaryLabel1(), that.getAuxiliaryLabel1()) &&
               Objects.equals(getAuxiliaryValue1(), that.getAuxiliaryValue1()) &&
               Objects.equals(getAuxiliaryLabel2(), that.getAuxiliaryLabel2()) &&
               Objects.equals(getAuxiliaryValue2(), that.getAuxiliaryValue2()) &&
               Objects.equals(getAuxiliaryLabel3(), that.getAuxiliaryLabel3()) &&
               Objects.equals(getAuxiliaryValue3(), that.getAuxiliaryValue3()) &&
               Objects.equals(getAuxiliaryLabel4(), that.getAuxiliaryLabel4()) &&
               Objects.equals(getAuxiliaryValue4(), that.getAuxiliaryValue4()) &&
               Objects.equals(getAuxiliaryLabel5(), that.getAuxiliaryLabel5()) &&
               Objects.equals(getAuxiliaryValue5(), that.getAuxiliaryValue5()) &&
               Objects.equals(getBackFields(), that.getBackFields()) &&
               getPassType() == that.getPassType() && getTransitType() == that.getTransitType();
    }
    
    @Override
    public int hashCode() {
        
        return Objects.hash(getBarcode(), getBackgroundImagePath(), getFooterPath(), getIconPath(),
                            getLogoPath(), getStripPath(), getThumbnailPath(), getBackgroundColor(),
                            getForegroundColor(), getLabelColor(), getLogoText(), getHeaderLabel1(),
                            getHeaderValue1(), getHeaderLabel2(), getHeaderValue2(),
                            getHeaderLabel3(), getHeaderValue3(), getPrimaryLabel1(),
                            getPrimaryValue1(), getPrimaryLabel2(), getPrimaryValue2(),
                            getSecondaryLabel1(), getSecondaryValue1(), getSecondaryLabel2(),
                            getSecondaryValue2(), getSecondaryLabel3(), getSecondaryValue3(),
                            getSecondaryLabel4(), getSecondaryValue4(), getAuxiliaryLabel1(),
                            getAuxiliaryValue1(), getAuxiliaryLabel2(), getAuxiliaryValue2(),
                            getAuxiliaryLabel3(), getAuxiliaryValue3(), getAuxiliaryLabel4(),
                            getAuxiliaryValue4(), getAuxiliaryLabel5(), getAuxiliaryValue5(),
                            getBackFields(), getPassType(), getTransitType(), getPassId());
    }
}
