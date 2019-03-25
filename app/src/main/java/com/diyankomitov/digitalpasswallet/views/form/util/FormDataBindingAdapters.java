package com.diyankomitov.digitalpasswallet.views.form.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class FormDataBindingAdapters {
    
    @BindingAdapter({"android:src", "placeholder"})
    public static void setImageBitmapOrPlaceholderDrawable(ImageView imageView, Bitmap bitmap,
                                                           Drawable placeholder) {
        
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            imageView.setImageDrawable(placeholder);
        }
    }
}
