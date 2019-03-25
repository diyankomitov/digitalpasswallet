package com.diyankomitov.digitalpasswallet.views.form.components;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.diyankomitov.digitalpasswallet.R;
import com.diyankomitov.digitalpasswallet.views.CreatePassActivity;
import com.google.android.material.textfield.TextInputEditText;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

public class CreatePassEditText extends TextInputEditText {
    
    private static final String TAG = "CreatePassEditText";
    private Drawable clearButton;
    
    public CreatePassEditText(Context context) {
        
        super(context);
        init();
    }
    
    public CreatePassEditText(Context context, AttributeSet attrs) {
        
        super(context, attrs);
        init();
    }
    
    public CreatePassEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        
        super(context, attrs, defStyleAttr);
        init();
    }
    
    private void init() {
        
        clearButton = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear);
        clearButton.setTint(
                ContextCompat.getColor(getContext(), R.color.colorLabelOnLightBackground));
        setClearButton(false);
    }
    
    private void setClearButton(boolean isClearButtonVisible) {
        
        setCompoundDrawablesWithIntrinsicBounds(null, null,
                                                isClearButtonVisible ? clearButton : null, null);
    }
    
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        
        setClearButton(lengthAfter > 0);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                CreatePassActivity.setRemoveFocusCanceled(true);
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: clicked on edittext ");
                if (isInsideClearButton(event)) {
                    Log.d(TAG, "onTouchEvent: click was inside");
                    setText(null);
                }
                this.performClick();
                break;
        }
        
        return super.onTouchEvent(event);
    }
    
    private boolean isInsideClearButton(MotionEvent event) {
        
        Drawable clearButton = getCompoundDrawables()[2];
        return clearButton != null &&
               event.getX() >= (getRight() - getCompoundDrawables()[2].getBounds().width()) &&
               event.getX() <= getRight();
    }
    
    @Override
    public boolean performClick() {
        
        return super.performClick();
    }
}
