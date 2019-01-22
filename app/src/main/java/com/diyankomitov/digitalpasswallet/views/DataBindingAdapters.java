package com.diyankomitov.digitalpasswallet.views;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.widget.ImageView;

public class DataBindingAdapters {

    @BindingAdapter("android:src")
    public static void setImageBitmap(ImageView imageView, Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

}
