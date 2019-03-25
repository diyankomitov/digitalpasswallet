package com.diyankomitov.digitalpasswallet.viewmodel.pass;

import android.text.Html;
import android.text.Spanned;

import com.diyankomitov.digitalpasswallet.models.pass.components.PassField;
import com.diyankomitov.digitalpasswallet.viewmodel.pass.util.DateTimeUtil;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import androidx.annotation.NonNull;

public class PassFieldViewModel {
    
    private static final String TAG = "PassFieldViewModel";
    
    private String label;
    private String value;
    
    PassFieldViewModel(@NonNull PassField passField) {
    
        String formattedValue = DateTimeUtil
                .getFormattedString(passField.getValue(), passField.getDateStyle().getFormatStyle(),
                                    passField.getTimeStyle().getFormatStyle(),
                                    passField.isIgnoresTimeZone(), passField.isRelative());
    
        this.label = passField.getLabel();
        this.value = formattedValue;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public String getValue() {
        
        return value;
    }
    
    public Spanned getLabelSpanned() {
        return Html.fromHtml(label, Html.FROM_HTML_MODE_LEGACY);
    }
    public Spanned getValueSpanned() {
        return Html.fromHtml(value, Html.FROM_HTML_MODE_LEGACY);
    }
    
    @Override
    public boolean equals(Object o) {
        
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        
        PassFieldViewModel that = (PassFieldViewModel) o;
        
        return new EqualsBuilder().append(getLabel(), that.getLabel())
                                  .append(getValue(), that.getValue()).isEquals();
    }
    
    @Override
    public int hashCode() {
        
        return new HashCodeBuilder(17, 37).append(getLabel()).append(getValue()).toHashCode();
    }
}
