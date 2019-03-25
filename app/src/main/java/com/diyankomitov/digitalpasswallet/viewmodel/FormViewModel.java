package com.diyankomitov.digitalpasswallet.viewmodel;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.diyankomitov.digitalpasswallet.R;
import com.diyankomitov.digitalpasswallet.models.pass.Pass;
import com.diyankomitov.digitalpasswallet.models.pass.PassModel;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassBarcode;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassBarcodeFormat;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassField;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassTransitType;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassType;
import com.diyankomitov.digitalpasswallet.repository.PassFileUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.CharacterSetECI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class FormViewModel extends AndroidViewModel {
    
    private static final String TAG = "FormViewModel";
    
    private MutableLiveData<PassType> passType = new MutableLiveData<>();
    
    private MutableLiveData<PassTransitType> passTransitType = new MutableLiveData<>();
    
    private MutableLiveData<BarcodeFormat> barcodeFormat = new MutableLiveData<>();
    private MutableLiveData<String> barcodeMessage = new MutableLiveData<>();
    
    private MutableLiveData<String> description = new MutableLiveData<>();
    private MutableLiveData<String> logoText = new MutableLiveData<>();
    
    private MutableLiveData<String> headerLabel1 = new MutableLiveData<>();
    private MutableLiveData<String> headerValue1 = new MutableLiveData<>();
    private MutableLiveData<String> headerLabel2 = new MutableLiveData<>();
    private MutableLiveData<String> headerValue2 = new MutableLiveData<>();
    private MutableLiveData<String> headerLabel3 = new MutableLiveData<>();
    private MutableLiveData<String> headerValue3 = new MutableLiveData<>();
    
    private MutableLiveData<String> primaryLabel1 = new MutableLiveData<>();
    private MutableLiveData<String> primaryValue1 = new MutableLiveData<>();
    private MutableLiveData<String> primaryLabel2 = new MutableLiveData<>();
    private MutableLiveData<String> primaryValue2 = new MutableLiveData<>();
    
    private MutableLiveData<String> secondaryLabel1 = new MutableLiveData<>();
    private MutableLiveData<String> secondaryValue1 = new MutableLiveData<>();
    private MutableLiveData<String> secondaryLabel2 = new MutableLiveData<>();
    private MutableLiveData<String> secondaryValue2 = new MutableLiveData<>();
    private MutableLiveData<String> secondaryLabel3 = new MutableLiveData<>();
    private MutableLiveData<String> secondaryValue3 = new MutableLiveData<>();
    private MutableLiveData<String> secondaryLabel4 = new MutableLiveData<>();
    private MutableLiveData<String> secondaryValue4 = new MutableLiveData<>();
    
    private MutableLiveData<String> auxiliaryLabel1 = new MutableLiveData<>();
    private MutableLiveData<String> auxiliaryValue1 = new MutableLiveData<>();
    private MutableLiveData<String> auxiliaryLabel2 = new MutableLiveData<>();
    private MutableLiveData<String> auxiliaryValue2 = new MutableLiveData<>();
    private MutableLiveData<String> auxiliaryLabel3 = new MutableLiveData<>();
    private MutableLiveData<String> auxiliaryValue3 = new MutableLiveData<>();
    private MutableLiveData<String> auxiliaryLabel4 = new MutableLiveData<>();
    private MutableLiveData<String> auxiliaryValue4 = new MutableLiveData<>();
    private MutableLiveData<String> auxiliaryLabel5 = new MutableLiveData<>();
    private MutableLiveData<String> auxiliaryValue5 = new MutableLiveData<>();
    
    private MutableLiveData<String> backLabel1 = new MutableLiveData<>();
    private MutableLiveData<String> backValue1 = new MutableLiveData<>();
    private MutableLiveData<String> backLabel2 = new MutableLiveData<>();
    private MutableLiveData<String> backValue2 = new MutableLiveData<>();
    private MutableLiveData<String> backLabel3 = new MutableLiveData<>();
    private MutableLiveData<String> backValue3 = new MutableLiveData<>();
    private MutableLiveData<String> backLabel4 = new MutableLiveData<>();
    private MutableLiveData<String> backValue4 = new MutableLiveData<>();
    private MutableLiveData<String> backLabel5 = new MutableLiveData<>();
    private MutableLiveData<String> backValue5 = new MutableLiveData<>();
    private MutableLiveData<String> backLabel6 = new MutableLiveData<>();
    private MutableLiveData<String> backValue6 = new MutableLiveData<>();
    private MutableLiveData<String> backLabel7 = new MutableLiveData<>();
    private MutableLiveData<String> backValue7 = new MutableLiveData<>();
    private MutableLiveData<String> backLabel8 = new MutableLiveData<>();
    private MutableLiveData<String> backValue8 = new MutableLiveData<>();
    private MutableLiveData<String> backLabel9 = new MutableLiveData<>();
    private MutableLiveData<String> backValue9 = new MutableLiveData<>();
    private MutableLiveData<String> backLabel10 = new MutableLiveData<>();
    private MutableLiveData<String> backValue10 = new MutableLiveData<>();
    private MutableLiveData<String> backLabel11 = new MutableLiveData<>();
    private MutableLiveData<String> backValue11 = new MutableLiveData<>();
    private MutableLiveData<String> backLabel12 = new MutableLiveData<>();
    private MutableLiveData<String> backValue12 = new MutableLiveData<>();
    private MutableLiveData<String> backLabel13 = new MutableLiveData<>();
    private MutableLiveData<String> backValue13 = new MutableLiveData<>();
    private MutableLiveData<String> backLabel14 = new MutableLiveData<>();
    private MutableLiveData<String> backValue14 = new MutableLiveData<>();
    private MutableLiveData<String> backLabel15 = new MutableLiveData<>();
    private MutableLiveData<String> backValue15 = new MutableLiveData<>();
    private MutableLiveData<String> backLabel16 = new MutableLiveData<>();
    private MutableLiveData<String> backValue16 = new MutableLiveData<>();
    private MutableLiveData<String> backLabel17 = new MutableLiveData<>();
    private MutableLiveData<String> backValue17 = new MutableLiveData<>();
    private MutableLiveData<String> backLabel18 = new MutableLiveData<>();
    private MutableLiveData<String> backValue18 = new MutableLiveData<>();
    private MutableLiveData<String> backLabel19 = new MutableLiveData<>();
    private MutableLiveData<String> backValue19 = new MutableLiveData<>();
    private MutableLiveData<String> backLabel20 = new MutableLiveData<>();
    private MutableLiveData<String> backValue20 = new MutableLiveData<>();
    
    private MutableLiveData<Integer> backgroundColor = new MutableLiveData<>();
    private MutableLiveData<Integer> labelColor = new MutableLiveData<>();
    private MutableLiveData<Integer> valueColor = new MutableLiveData<>();
    
    private MutableLiveData<Bitmap> logoImage = new MutableLiveData<>();
    private MutableLiveData<Bitmap> stripImage = new MutableLiveData<>();
    private MutableLiveData<Bitmap> thumbnailImage = new MutableLiveData<>();
    private MutableLiveData<Bitmap> iconImage = new MutableLiveData<>();
    private MutableLiveData<Bitmap> footerImage = new MutableLiveData<>();
    private MutableLiveData<Bitmap> backgroundImage = new MutableLiveData<>();
    
    public FormViewModel(@NonNull Application application) {
        
        super(application);
        
        Context context = application.getApplicationContext();
        
        backgroundColor.postValue(context.getColor(R.color.colorPrimary));
        labelColor.postValue(context.getColor(R.color.colorLabel));
        valueColor.postValue(context.getColor(R.color.colorAccent));
    }
    
    public Pass getPass() {
        
        Pass pass =
                new PassModel(passType.getValue() == null ? PassType.GENERIC : passType.getValue());
        
        pass.setTransitType(passTransitType.getValue() == null
                            ? PassTransitType.GENERIC
                            : passTransitType.getValue());
        
        PassBarcode barcode = new PassBarcode(barcodeMessage.getValue(), barcodeMessage.getValue(),
                                              PassBarcodeFormat.getFromZxingFormat(
                                                      barcodeFormat.getValue() == null
                                                      ? BarcodeFormat.QR_CODE
                                                      : barcodeFormat.getValue()),
                                              CharacterSetECI.ISO8859_1);
        List<PassBarcode> barcodes = new ArrayList<>();
        barcodes.add(barcode);
        pass.setBarcodes(barcodes);
        
        pass.setDescription(description.getValue());
        pass.setLogoText(logoText.getValue());
        
        List<PassField> headers = new ArrayList<>();
        headers.add(
                new PassField(getLiveDataString(headerLabel1), getLiveDataString(headerValue1)));
        headers.add(
                new PassField(getLiveDataString(headerLabel2), getLiveDataString(headerValue2)));
        headers.add(
                new PassField(getLiveDataString(headerLabel3), getLiveDataString(headerValue3)));
        pass.setHeaderFields(headers);
        
        List<PassField> primaries = new ArrayList<>();
        primaries.add(
                new PassField(getLiveDataString(primaryLabel1), getLiveDataString(primaryValue1)));
        primaries.add(
                new PassField(getLiveDataString(primaryLabel2), getLiveDataString(primaryValue2)));
        pass.setPrimaryFields(primaries);
        
        List<PassField> secondaries = new ArrayList<>();
        secondaries.add(new PassField(getLiveDataString(secondaryLabel1),
                                      getLiveDataString(secondaryValue1)));
        secondaries.add(new PassField(getLiveDataString(secondaryLabel2),
                                      getLiveDataString(secondaryValue2)));
        secondaries.add(new PassField(getLiveDataString(secondaryLabel3),
                                      getLiveDataString(secondaryValue3)));
        secondaries.add(new PassField(getLiveDataString(secondaryLabel4),
                                      getLiveDataString(secondaryValue4)));
        pass.setSecondaryFields(secondaries);
        
        List<PassField> auxiliaries = new ArrayList<>();
        auxiliaries.add(new PassField(getLiveDataString(auxiliaryLabel1),
                                      getLiveDataString(auxiliaryValue1)));
        auxiliaries.add(new PassField(getLiveDataString(auxiliaryLabel2),
                                      getLiveDataString(auxiliaryValue2)));
        auxiliaries.add(new PassField(getLiveDataString(auxiliaryLabel3),
                                      getLiveDataString(auxiliaryValue3)));
        auxiliaries.add(new PassField(getLiveDataString(auxiliaryLabel4),
                                      getLiveDataString(auxiliaryValue4)));
        auxiliaries.add(new PassField(getLiveDataString(auxiliaryLabel5),
                                      getLiveDataString(auxiliaryValue5)));
        pass.setAuxiliaryFields(auxiliaries);
        
        List<PassField> backs = new ArrayList<>();
        if (backLabel1.getValue() != null || backValue1.getValue() != null) {
            backs.add(new PassField(getLiveDataString(backLabel1), getLiveDataString(backValue1)));
        }
        if (backLabel2.getValue() != null || backValue2.getValue() != null) {
            backs.add(new PassField(getLiveDataString(backLabel2), getLiveDataString(backValue2)));
        }
        if (backLabel3.getValue() != null || backValue3.getValue() != null) {
            backs.add(new PassField(getLiveDataString(backLabel3), getLiveDataString(backValue3)));
        }
        if (backLabel4.getValue() != null || backValue4.getValue() != null) {
            backs.add(new PassField(getLiveDataString(backLabel4), getLiveDataString(backValue4)));
        }
        if (backLabel5.getValue() != null || backValue5.getValue() != null) {
            backs.add(new PassField(getLiveDataString(backLabel5), getLiveDataString(backValue5)));
        }
        if (backLabel6.getValue() != null || backValue6.getValue() != null) {
            backs.add(new PassField(getLiveDataString(backLabel6), getLiveDataString(backValue6)));
        }
        if (backLabel7.getValue() != null || backValue7.getValue() != null) {
            backs.add(new PassField(getLiveDataString(backLabel7), getLiveDataString(backValue7)));
        }
        if (backLabel8.getValue() != null || backValue8.getValue() != null) {
            backs.add(new PassField(getLiveDataString(backLabel8), getLiveDataString(backValue8)));
        }
        if (backLabel9.getValue() != null || backValue9.getValue() != null) {
            backs.add(new PassField(getLiveDataString(backLabel9), getLiveDataString(backValue9)));
        }
        if (backLabel10.getValue() != null || backValue10.getValue() != null) {
            backs.add(
                    new PassField(getLiveDataString(backLabel10), getLiveDataString(backValue10)));
        }
        if (backLabel11.getValue() != null || backValue11.getValue() != null) {
            backs.add(
                    new PassField(getLiveDataString(backLabel11), getLiveDataString(backValue11)));
        }
        if (backLabel12.getValue() != null || backValue12.getValue() != null) {
            
            backs.add(
                    new PassField(getLiveDataString(backLabel12), getLiveDataString(backValue12)));
        }
        if (backLabel13.getValue() != null || backValue13.getValue() != null) {
            
            backs.add(
                    new PassField(getLiveDataString(backLabel13), getLiveDataString(backValue13)));
        }
        if (backLabel14.getValue() != null || backValue14.getValue() != null) {
            
            backs.add(
                    new PassField(getLiveDataString(backLabel14), getLiveDataString(backValue14)));
        }
        if (backLabel15.getValue() != null || backValue15.getValue() != null) {
            backs.add(
                    new PassField(getLiveDataString(backLabel15), getLiveDataString(backValue15)));
        }
        if (backLabel16.getValue() != null || backValue16.getValue() != null) {
            backs.add(
                    new PassField(getLiveDataString(backLabel16), getLiveDataString(backValue16)));
        }
        if (backLabel17.getValue() != null || backValue17.getValue() != null) {
            backs.add(
                    new PassField(getLiveDataString(backLabel17), getLiveDataString(backValue17)));
        }
        if (backLabel18.getValue() != null || backValue18.getValue() != null) {
            backs.add(
                    new PassField(getLiveDataString(backLabel18), getLiveDataString(backValue18)));
        }
        if (backLabel19.getValue() != null || backValue19.getValue() != null) {
            backs.add(
                    new PassField(getLiveDataString(backLabel19), getLiveDataString(backValue19)));
        }
        if (backLabel20.getValue() != null || backValue20.getValue() != null) {
            backs.add(
                    new PassField(getLiveDataString(backLabel20), getLiveDataString(backValue20)));
        }
        pass.setBackFields(backs);
        
        pass.setBackgroundColor(getLiveDataInteger(backgroundColor));
        pass.setForegroundColor(getLiveDataInteger(valueColor));
        pass.setLabelColor(getLiveDataInteger(labelColor));
        
        File passDirectory = PassFileUtils.getNextUnzippedPassDirectory();
        
        Bitmap logoBitmap = logoImage.getValue();
        File logoFile = new File(passDirectory, "logo.png");
        pass.setLogoPath(saveImageToFile(logoBitmap, logoFile));
        
        Bitmap stripBitmap = stripImage.getValue();
        File stripFile = new File(passDirectory, "strip.png");
        pass.setStripPath(saveImageToFile(stripBitmap, stripFile));
        
        Bitmap thumbnailBitmap = thumbnailImage.getValue();
        File thumbnailFile = new File(passDirectory, "thumbnail.png");
        pass.setThumbnailPath(saveImageToFile(thumbnailBitmap, thumbnailFile));
        
        Bitmap iconBitmap = iconImage.getValue();
        File iconFile = new File(passDirectory, "icon.png");
        pass.setIconPath(saveImageToFile(iconBitmap, iconFile));
        
        Bitmap footerBitmap = footerImage.getValue();
        File footerFile = new File(passDirectory, "footer.png");
        pass.setFooterPath(saveImageToFile(footerBitmap, footerFile));
        
        Bitmap backgroundBitmap = backgroundImage.getValue();
        File backgroundFile = new File(passDirectory, "background.png");
        pass.setBackgroundImagePath(saveImageToFile(backgroundBitmap, backgroundFile));
        
        return pass;
    }
    
    private String saveImageToFile(Bitmap image, File file) {
        
        if (image != null) {
            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                image.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                return file.getPath();
            } catch (FileNotFoundException e) {
                Log.e(TAG, "saveImageToFile: File not found", e);
            } catch (IOException e) {
                Log.e(TAG, "saveImageToFile: Error writing to file", e);
            }
        }
        
        return "";
    }
    
    private String getLiveDataString(LiveData<String> stringLiveData) {
        
        return stringLiveData.getValue() == null ? "" : stringLiveData.getValue();
    }
    
    private int getLiveDataInteger(LiveData<Integer> integerLiveData) {
        
        return integerLiveData.getValue() == null ? 0 : integerLiveData.getValue();
    }
    
    // Getters and Setters
    
    public MutableLiveData<PassType> getPassType() {
        
        return passType;
    }
    
    public void setPassType(PassType passType) {
        
        this.passType.postValue(passType);
    }
    
    public MutableLiveData<PassTransitType> getPassTransitType() {
        
        return passTransitType;
    }
    
    public void setPassTransitType(PassTransitType passTransitType) {
        
        this.passTransitType.postValue(passTransitType);
    }
    
    public MutableLiveData<BarcodeFormat> getBarcodeFormat() {
        
        return barcodeFormat;
    }
    
    public void setBarcodeFormat(BarcodeFormat barcodeFormat) {
        
        this.barcodeFormat.postValue(barcodeFormat);
    }
    
    public MutableLiveData<String> getBarcodeMessage() {
        
        return barcodeMessage;
    }
    
    public MutableLiveData<String> getDescription() {
        
        return description;
    }
    
    public MutableLiveData<String> getLogoText() {
        
        return logoText;
    }
    
    public MutableLiveData<String> getHeaderLabel1() {
        
        return headerLabel1;
    }
    
    public MutableLiveData<String> getHeaderValue1() {
        
        return headerValue1;
    }
    
    public MutableLiveData<String> getHeaderLabel2() {
        
        return headerLabel2;
    }
    
    public MutableLiveData<String> getHeaderValue2() {
        
        return headerValue2;
    }
    
    public MutableLiveData<String> getHeaderLabel3() {
        
        return headerLabel3;
    }
    
    public MutableLiveData<String> getHeaderValue3() {
        
        return headerValue3;
    }
    
    public MutableLiveData<String> getPrimaryLabel1() {
        
        return primaryLabel1;
    }
    
    public MutableLiveData<String> getPrimaryValue1() {
        
        return primaryValue1;
    }
    
    public MutableLiveData<String> getPrimaryLabel2() {
        
        return primaryLabel2;
    }
    
    public MutableLiveData<String> getPrimaryValue2() {
        
        return primaryValue2;
    }
    
    public MutableLiveData<String> getSecondaryLabel1() {
        
        return secondaryLabel1;
    }
    
    public MutableLiveData<String> getSecondaryValue1() {
        
        return secondaryValue1;
    }
    
    public MutableLiveData<String> getSecondaryLabel2() {
        
        return secondaryLabel2;
    }
    
    public MutableLiveData<String> getSecondaryValue2() {
        
        return secondaryValue2;
    }
    
    public MutableLiveData<String> getSecondaryLabel3() {
        
        return secondaryLabel3;
    }
    
    public MutableLiveData<String> getSecondaryValue3() {
        
        return secondaryValue3;
    }
    
    public MutableLiveData<String> getSecondaryLabel4() {
        
        return secondaryLabel4;
    }
    
    public MutableLiveData<String> getSecondaryValue4() {
        
        return secondaryValue4;
    }
    
    public MutableLiveData<String> getAuxiliaryLabel1() {
        
        return auxiliaryLabel1;
    }
    
    public MutableLiveData<String> getAuxiliaryValue1() {
        
        return auxiliaryValue1;
    }
    
    public MutableLiveData<String> getAuxiliaryLabel2() {
        
        return auxiliaryLabel2;
    }
    
    public MutableLiveData<String> getAuxiliaryValue2() {
        
        return auxiliaryValue2;
    }
    
    public MutableLiveData<String> getAuxiliaryLabel3() {
        
        return auxiliaryLabel3;
    }
    
    public MutableLiveData<String> getAuxiliaryValue3() {
        
        return auxiliaryValue3;
    }
    
    public MutableLiveData<String> getAuxiliaryLabel4() {
        
        return auxiliaryLabel4;
    }
    
    public MutableLiveData<String> getAuxiliaryValue4() {
        
        return auxiliaryValue4;
    }
    
    public MutableLiveData<String> getAuxiliaryLabel5() {
        
        return auxiliaryLabel5;
    }
    
    public MutableLiveData<String> getAuxiliaryValue5() {
        
        return auxiliaryValue5;
    }
    
    public MutableLiveData<String> getBackLabel1() {
        
        return backLabel1;
    }
    
    public MutableLiveData<String> getBackValue1() {
        
        return backValue1;
    }
    
    public MutableLiveData<String> getBackLabel2() {
        
        return backLabel2;
    }
    
    public MutableLiveData<String> getBackValue2() {
        
        return backValue2;
    }
    
    public MutableLiveData<String> getBackLabel3() {
        
        return backLabel3;
    }
    
    public MutableLiveData<String> getBackValue3() {
        
        return backValue3;
    }
    
    public MutableLiveData<String> getBackLabel4() {
        
        return backLabel4;
    }
    
    public MutableLiveData<String> getBackValue4() {
        
        return backValue4;
    }
    
    public MutableLiveData<String> getBackLabel5() {
        
        return backLabel5;
    }
    
    public MutableLiveData<String> getBackValue5() {
        
        return backValue5;
    }
    
    public MutableLiveData<String> getBackLabel6() {
        
        return backLabel6;
    }
    
    public MutableLiveData<String> getBackValue6() {
        
        return backValue6;
    }
    
    public MutableLiveData<String> getBackLabel7() {
        
        return backLabel7;
    }
    
    public MutableLiveData<String> getBackValue7() {
        
        return backValue7;
    }
    
    public MutableLiveData<String> getBackLabel8() {
        
        return backLabel8;
    }
    
    public MutableLiveData<String> getBackValue8() {
        
        return backValue8;
    }
    
    public MutableLiveData<String> getBackLabel9() {
        
        return backLabel9;
    }
    
    public MutableLiveData<String> getBackValue9() {
        
        return backValue9;
    }
    
    public MutableLiveData<String> getBackLabel10() {
        
        return backLabel10;
    }
    
    public MutableLiveData<String> getBackValue10() {
        
        return backValue10;
    }
    
    public MutableLiveData<String> getBackLabel11() {
        
        return backLabel11;
    }
    
    public MutableLiveData<String> getBackValue11() {
        
        return backValue11;
    }
    
    public MutableLiveData<String> getBackLabel12() {
        
        return backLabel12;
    }
    
    public MutableLiveData<String> getBackValue12() {
        
        return backValue12;
    }
    
    public MutableLiveData<String> getBackLabel13() {
        
        return backLabel13;
    }
    
    public MutableLiveData<String> getBackValue13() {
        
        return backValue13;
    }
    
    public MutableLiveData<String> getBackLabel14() {
        
        return backLabel14;
    }
    
    public MutableLiveData<String> getBackValue14() {
        
        return backValue14;
    }
    
    public MutableLiveData<String> getBackLabel15() {
        
        return backLabel15;
    }
    
    public MutableLiveData<String> getBackValue15() {
        
        return backValue15;
    }
    
    public MutableLiveData<String> getBackLabel16() {
        
        return backLabel16;
    }
    
    public MutableLiveData<String> getBackValue16() {
        
        return backValue16;
    }
    
    public MutableLiveData<String> getBackLabel17() {
        
        return backLabel17;
    }
    
    public MutableLiveData<String> getBackValue17() {
        
        return backValue17;
    }
    
    public MutableLiveData<String> getBackLabel18() {
        
        return backLabel18;
    }
    
    public MutableLiveData<String> getBackValue18() {
        
        return backValue18;
    }
    
    public MutableLiveData<String> getBackLabel19() {
        
        return backLabel19;
    }
    
    public MutableLiveData<String> getBackValue19() {
        
        return backValue19;
    }
    
    public MutableLiveData<String> getBackLabel20() {
        
        return backLabel20;
    }
    
    public MutableLiveData<String> getBackValue20() {
        
        return backValue20;
    }
    
    public MutableLiveData<Integer> getBackgroundColor() {
        
        return backgroundColor;
    }
    
    public MutableLiveData<Integer> getLabelColor() {
        
        return labelColor;
    }
    
    public MutableLiveData<Integer> getValueColor() {
        
        return valueColor;
    }
    
    public MutableLiveData<Bitmap> getLogoImage() {
        
        return logoImage;
    }
    
    public void clearLogoImage() {
        
        this.logoImage.postValue(null);
    }
    
    public MutableLiveData<Bitmap> getStripImage() {
        
        return stripImage;
    }
    
    public void clearStripImage() {
        
        this.stripImage.postValue(null);
    }
    
    public MutableLiveData<Bitmap> getThumbnailImage() {
        
        return thumbnailImage;
    }
    
    public void clearThumbnailImage() {
        
        this.thumbnailImage.postValue(null);
    }
    
    public MutableLiveData<Bitmap> getIconImage() {
        
        return iconImage;
    }
    
    public void clearIconImage() {
        
        this.iconImage.postValue(null);
    }
    
    public MutableLiveData<Bitmap> getFooterImage() {
        
        return footerImage;
    }
    
    public void clearFooterImage() {
        
        this.footerImage.postValue(null);
    }
    
    public MutableLiveData<Bitmap> getBackgroundImage() {
        
        return backgroundImage;
    }
    
    public void clearBackgroundImage() {
        
        this.backgroundImage.postValue(null);
    }
}
