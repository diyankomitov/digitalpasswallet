package com.diyankomitov.digitalpasswallet.views;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.widget.ImageView;

public class DataBindingAdapters {

    @BindingAdapter("android:src")
    public static void setImageBitmap(ImageView imageView, Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @BindingAdapter("android:background")
    public static void setCardViewBackgroundBitmap(CardView cardView, Bitmap bitmap) {
        cardView.setBackground(new BitmapDrawable(cardView.getResources(), bitmap));
    }
}
