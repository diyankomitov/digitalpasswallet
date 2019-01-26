package com.diyankomitov.digitalpasswallet.views;

import androidx.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import androidx.cardview.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

public class DataBindingAdapters {

    @BindingAdapter("imageBitmap")
    public static void setImageBitmap(ImageView imageView, Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    @BindingAdapter("backgroundBitmap")
    public static void setBackgroundBitmap(CardView cardView, Bitmap bitmap) {
        cardView.setBackground(new BitmapDrawable(cardView.getResources(), bitmap));
    }

    @BindingAdapter("backgroundColor")
    public static void setBackgroundColor(CardView cardView, Integer color) {
        if (color != null) {
            cardView.setCardBackgroundColor(color);
        }
    }

    @BindingAdapter("textColor")
    public static void setTextColor(TextView textView, Integer color) {
        if (color != null) {
            textView.setTextColor(color);
        }
    }
}
