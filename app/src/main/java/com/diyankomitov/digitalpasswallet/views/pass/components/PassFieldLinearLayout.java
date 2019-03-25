package com.diyankomitov.digitalpasswallet.views.pass.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class PassFieldLinearLayout extends LinearLayout {
    
    private static final String TAG = "PassFieldLinearLayout";
    
    public PassFieldLinearLayout(Context context) {
        
        super(context);
    }
    
    public PassFieldLinearLayout(Context context, @Nullable AttributeSet attrs) {
        
        super(context, attrs);
    }
    
    public PassFieldLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        
        super(context, attrs, defStyleAttr);
    }
    
    public PassFieldLinearLayout(Context context, AttributeSet attrs, int defStyleAttr,
                                 int defStyleRes) {
        
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        
        TextView label = (TextView) getChildAt(0);
        PassFieldValueTextView value = (PassFieldValueTextView) getChildAt(1);
        
        String labelText = label.getText().toString();
        String valueText = value.getText().toString();
        
        if (labelText.length() == 0 && valueText.length() == 0) {
            setVisibility(View.GONE);
        } else {
            setVisibility(View.VISIBLE);
        }
    }
}
