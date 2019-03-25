package com.diyankomitov.digitalpasswallet.views.pass.components;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

import java.util.List;

import me.grantland.widget.AutofitTextView;

public class PassFieldValueTextView extends AutofitTextView {
    
    private static final String TAG = "PassFieldValueTextView";
    
    private List<PassFieldValueTextView> textGroup;
    private float targetTextSize = Float.MAX_VALUE;
    
    public PassFieldValueTextView(Context context) {
        
        super(context);
    }
    
    public PassFieldValueTextView(Context context, AttributeSet attrs) {
        
        super(context, attrs);
    }
    
    public PassFieldValueTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        
        super(context, attrs, defStyleAttr);
    }
    
    public void setTextGroup(List<PassFieldValueTextView> textGroup) {
        this.textGroup = textGroup;
    }
    
    private void setTargetTextSize(float textSize) {
        targetTextSize = textSize;
        Log.d(TAG, "setTargetTextSize: targetTextSize for " + getText() + " is: " + targetTextSize);
        setMaxTextSize(TypedValue.COMPLEX_UNIT_PX, targetTextSize);
    }
    
    private float getTargetTextSize() {
        return targetTextSize;
    }
    
    @Override
    public void onTextSizeChange(float newTextSize, float oldTextSize) {
        
        super.onTextSizeChange(newTextSize, oldTextSize);
        
        if (textGroup != null) {
    
            for (PassFieldValueTextView textView : textGroup) {
        
                if (newTextSize < textView.getTargetTextSize()) {
                    textView.setTargetTextSize(newTextSize);
                }
            }
        }
    }
}
