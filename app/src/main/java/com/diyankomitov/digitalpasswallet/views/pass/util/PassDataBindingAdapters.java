package com.diyankomitov.digitalpasswallet.views.pass.util;

import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.request.target.Target;
import com.diyankomitov.digitalpasswallet.R;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassTransitType;

import androidx.annotation.ColorInt;
import androidx.databinding.BindingAdapter;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class PassDataBindingAdapters {
    
    private static final String TAG = "PassDataBindingAdapters";
    
    @BindingAdapter("android:src")
    public static void setImageFromPath(ImageView imageView, String imagePath) {
        
        if (imagePath != null) {
            Glide.with(imageView)
                 .asBitmap()
                 .load(imagePath)
                 .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                 .override(Target.SIZE_ORIGINAL)
                 .transition(BitmapTransitionOptions.withCrossFade())
                 .into(imageView);
        }
    }
    
    @BindingAdapter("android:src")
    public static void setImageBitmap(ImageView imageView, Bitmap bitmap) {
        
        imageView.setImageBitmap(bitmap);
    }
    
    @BindingAdapter("eventTicketBackground")
    public static void setEventTicketBackground(ImageView imageView, String imagePath) {
        
        if (imagePath != null) {
            Glide.with(imageView)
                 .asBitmap()
                 .load(imagePath)
                 .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                 .override(Target.SIZE_ORIGINAL)
                 .transform(new BlurTransformation(50))
                 .transition(BitmapTransitionOptions.withCrossFade())
                 .into(imageView);
        }
    }
    
    @BindingAdapter("color")
    public static void setImageColor(ImageView imageView, @ColorInt int color) {
        
        imageView.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
    }
    
    @BindingAdapter("transitType")
    public static void setTransitTypeImage(ImageView imageView, PassTransitType transitType) {
        
        Log.d(TAG, "setTransitTypeImage() " + transitType);
        if (transitType != null) {
            
            switch (transitType) {
                case AIR:
                    imageView.setImageResource(R.drawable.ic_boarding_pass_plane);
                    break;
                case BOAT:
                    imageView.setImageResource(R.drawable.ic_boarding_pass_boat);
                    break;
                case BUS:
                    imageView.setImageResource(R.drawable.ic_boarding_pass_bus);
                    break;
                case TRAIN:
                    imageView.setImageResource(R.drawable.ic_boarding_pass_train);
                    break;
                case GENERIC:
                default:
                    imageView.setImageResource(R.drawable.ic_boarding_pass_arrow);
            }
        }
    }
}

