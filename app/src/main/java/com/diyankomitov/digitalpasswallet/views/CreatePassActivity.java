package com.diyankomitov.digitalpasswallet.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.diyankomitov.digitalpasswallet.R;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassType;
import com.diyankomitov.digitalpasswallet.viewmodel.FormViewModel;
import com.diyankomitov.digitalpasswallet.views.form.CreatePassPagerAdapter;
import com.diyankomitov.digitalpasswallet.views.form.components.CreatePassAuxiliaryFragment;
import com.diyankomitov.digitalpasswallet.views.form.components.CreatePassBackFragment;
import com.diyankomitov.digitalpasswallet.views.form.components.CreatePassGeneralFragment;
import com.diyankomitov.digitalpasswallet.views.form.components.CreatePassHeaderFragment;
import com.diyankomitov.digitalpasswallet.views.form.components.CreatePassImagesFragment;
import com.diyankomitov.digitalpasswallet.views.form.components.CreatePassSecondaryFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.pes.androidmaterialcolorpickerdialog.ColorPicker;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

public class CreatePassActivity extends AppCompatActivity {
    
    public static final String PASS_EXTRA = "PASS_EXTRA";
    
    private static final String TAG = "CreatePassActivity";
    private static final int COLOR_FROM_IMAGE = 1000;
    
    private static boolean removeFocusCanceled = true;
    
    ViewPager createPassViewPager;
    private View focusedView;
    private int moveCounter = 0;
    private FormViewModel formViewModel;
    private boolean isColorPickerFromPalette = true;
    private Drawable currentColorPickerDrawable;
    private View currentColorPickerPreviewView;
    private String preferenceName;
    private CreatePassPagerAdapter pagerAdapter;
    private MutableLiveData<Bitmap> currentImageLiveData;
    private CreatePassImagesFragment imagesFragment;
    private CreatePassHeaderFragment headerFragment;
    private CreatePassSecondaryFragment secondaryFragment;
    private CreatePassAuxiliaryFragment auxiliaryFragment;
    private CreatePassBackFragment backFragment;
    
    public static void setRemoveFocusCanceled(boolean removeFocusCanceled) {
        
        CreatePassActivity.removeFocusCanceled = removeFocusCanceled;
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_pass_activity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        createPassViewPager = findViewById(R.id.create_pass_view_pager);
        initViewPager(createPassViewPager);
        
        TabLayout createPassTabLayout = findViewById(R.id.create_pass_tab_layout);
        createPassTabLayout.setupWithViewPager(createPassViewPager);
        
        formViewModel = ViewModelProviders.of(this).get(FormViewModel.class);
        Log.d(TAG, "onCreate: formviewmodel" + formViewModel);
        
        formViewModel.getPassType().observeForever(new Observer<PassType>() {
            @Override
            public void onChanged(PassType passType) {
                
                if (passType != null) {
                    addRemainingFragmentsToViewPager();
                    formViewModel.getPassType().removeObserver(this);
                }
            }
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        getMenuInflater().inflate(R.menu.create_pass_activity_action_bar_menu, menu);
        
        return true;
    }
    
    private void initViewPager(ViewPager viewPager) {
        
        pagerAdapter = new CreatePassPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new CreatePassGeneralFragment(), "General");
        
        imagesFragment = new CreatePassImagesFragment();
        headerFragment = new CreatePassHeaderFragment();
        secondaryFragment = new CreatePassSecondaryFragment();
        auxiliaryFragment = new CreatePassAuxiliaryFragment();
        backFragment = new CreatePassBackFragment();
        
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(5);
    }
    
    private void addRemainingFragmentsToViewPager() {
        
        pagerAdapter.addFragment(imagesFragment, "Images");
        pagerAdapter.addFragment(headerFragment, "Header");
        pagerAdapter.addFragment(secondaryFragment, "Secondary");
        pagerAdapter.addFragment(auxiliaryFragment, "Auxiliary");
        pagerAdapter.addFragment(backFragment, "Back");
        
        pagerAdapter.notifyDataSetChanged();
    }
    
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moveCounter = 0;
                focusedView = getCurrentFocus();
                
                if (focusedView instanceof EditText) {
                    
                    removeFocusCanceled = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (++moveCounter >= 3) {
                    removeFocusCanceled = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                
                if (!removeFocusCanceled) {
                    focusedView.clearFocus();
                    InputMethodManager imm =
                            (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(focusedView.getWindowToken(), 0);
                }
                break;
        }
        
        return super.dispatchTouchEvent(ev);
    }
    
    public void launchColorPickerDialog(View view) {
        
        MutableLiveData<Integer> colorLiveData;
        
        switch (view.getId()) {
            case R.id.pass_colors_background_input:
                colorLiveData = formViewModel.getBackgroundColor();
                break;
            case R.id.pass_colors_foreground_input:
                colorLiveData = formViewModel.getValueColor();
                break;
            case R.id.pass_colors_label_input:
                colorLiveData = formViewModel.getLabelColor();
                break;
            default:
                return;
        }
        
        int color = colorLiveData.getValue();
        
        ColorPicker colorPicker =
                new ColorPicker(this, Color.red(color), Color.green(color), Color.blue(color));
        
        colorPicker.enableAutoClose();
        colorPicker.setCallback(colorLiveData::postValue);
        
        colorPicker.show();
        
        //        if (currentColorPickerPreviewView == null || !currentColorPickerPreviewView.equals(view)) {
        //            currentColorPickerPreviewView = view;
        //        }
        //
        //        if (isColorPickerFromPalette) {
        //            launchColorPickerFromPalette();
        //        } else {
        //            launchColorPickerFromImage();
        //        }
    }
    
    public void launchBarcodeScanner(View view) {
        
        new IntentIntegrator(this).setBeepEnabled(false)
                                  .setPrompt("Scan Barcode")
                                  .setDesiredBarcodeFormats("QR_CODE", "AZTEC", "PDF_417",
                                                            "CODE_128")
                                  .initiateScan();
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        
        IntentResult scanIntentResult =
                IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        
        if (scanIntentResult != null && scanIntentResult.getContents() != null) {
            
            formViewModel.getBarcodeMessage().postValue(scanIntentResult.getContents());
            formViewModel.getBarcodeFormat()
                         .postValue(BarcodeFormat.valueOf(scanIntentResult.getFormatName()));
        } else if (requestCode == COLOR_FROM_IMAGE && resultCode == RESULT_OK && data != null) {
            //            onColorFromImageIntentResult(data);
        } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE &&
                   resultCode == RESULT_OK) {
            onCropImageIntentResult(data);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    
    private void onCropImageIntentResult(Intent data) {
        
        try {
            Uri resultUri = CropImage.getActivityResult(data).getUri();
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
            currentImageLiveData.postValue(bitmap);
        } catch (IOException e) {
            Log.e(TAG, "onActivityResult: on crop uri not found", e);
        }
    }
    
    public void launchImagePicker(View view) {
        
        int maxWidth;
        int maxHeight;
        
        switch (view.getId()) {
            case R.id.pass_logo_image_input:
                currentImageLiveData = formViewModel.getLogoImage();
                maxWidth = getResources().getDimensionPixelSize(R.dimen.logoMaxWidth);
                maxHeight = getResources().getDimensionPixelSize(R.dimen.logoHeight);
                break;
            case R.id.pass_strip_image_input:
                currentImageLiveData = formViewModel.getStripImage();
                maxWidth = getResources().getDimensionPixelSize(R.dimen.stripMaxWidth);
                maxHeight = formViewModel.getPassType().getValue() == PassType.EVENT_TICKET
                            ? getResources().getDimensionPixelSize(R.dimen.stripShortHeight)
                            : getResources().getDimensionPixelSize(R.dimen.stripTallHeight);
                break;
            case R.id.pass_thumbnail_image_input:
                currentImageLiveData = formViewModel.getThumbnailImage();
                maxWidth = getResources().getDimensionPixelSize(R.dimen.thumbnailSize);
                maxHeight = getResources().getDimensionPixelSize(R.dimen.thumbnailSize);
                break;
            case R.id.pass_icon_image_input:
                currentImageLiveData = formViewModel.getIconImage();
                maxWidth = getResources().getDimensionPixelSize(R.dimen.iconSize);
                maxHeight = getResources().getDimensionPixelSize(R.dimen.iconSize);
                break;
            case R.id.pass_footer_image_input:
                currentImageLiveData = formViewModel.getFooterImage();
                maxWidth = getResources().getDimensionPixelSize(R.dimen.footerMaxWidth);
                maxHeight = getResources().getDimensionPixelSize(R.dimen.footerHeight);
                break;
            case R.id.pass_background_image_input:
                currentImageLiveData = formViewModel.getBackgroundImage();
                maxWidth = getResources().getDimensionPixelSize(R.dimen.backgroundWidth);
                maxHeight = getResources().getDimensionPixelSize(R.dimen.backgroundHeight);
                break;
            default:
                return;
        }
        
        CropImage.activity()
                 .setGuidelines(CropImageView.Guidelines.ON_TOUCH)
                 .setOutputCompressFormat(Bitmap.CompressFormat.PNG)
                 .setMaxCropResultSize(maxWidth, maxHeight)
                 .start(this);
    }
    
    public void onCreateButtonClicked(MenuItem item) {
        
        Intent resultIntent = new Intent();
        resultIntent.putExtra(PASS_EXTRA, formViewModel.getPass());
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
