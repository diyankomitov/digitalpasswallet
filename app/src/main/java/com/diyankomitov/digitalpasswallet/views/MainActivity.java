package com.diyankomitov.digitalpasswallet.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.diyankomitov.digitalpasswallet.R;
import com.diyankomitov.digitalpasswallet.models.pass.Pass;
import com.diyankomitov.digitalpasswallet.viewmodel.WalletViewModel;
import com.diyankomitov.digitalpasswallet.views.pass.PassAdapter;
import com.diyankomitov.digitalpasswallet.views.pass.util.PassLayoutManager;
import com.diyankomitov.digitalpasswallet.views.pass.util.PassListUpdateCallback;
import com.google.android.material.navigation.NavigationView;

import java.io.FileNotFoundException;
import java.io.InputStream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    
    private static final String TAG = "MainActivity";
    
    private static final int ADD_FROM_FILE_REQUEST_CODE = 794;
    private static final int CREATE_FROM_FORM_REQUEST_CODE = 846;
    
    private WalletViewModel walletViewModel;
    private PassAdapter passAdapter;
    
    public MainActivity() {
    
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        
        // toolbar, sidebar, menus, etc
        setContentView(R.layout.main_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
                                          R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        
        //main view/recycler view/pass view
        
        verifyPermissions();
    }
    
    private void verifyPermissions() {
        
        Log.d(TAG, "verifyPermissions()");
        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[0]) ==
            PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[1]) ==
            PackageManager.PERMISSION_GRANTED) {
            initWalletViewModel();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, permissions, 1);
        }
    }
    
    private void initWalletViewModel() {
        
        Log.d(TAG, "initWalletViewModel()");
        walletViewModel = ViewModelProviders.of(this).get(WalletViewModel.class);
        
        initPassRecyclerView();
        
        walletViewModel.getPassViewModels()
                       .observe(this, passViewModels -> passAdapter.updateDataSet(passViewModels));
    }
    
    private void initPassRecyclerView() {
        
        Log.d(TAG, "initPassRecyclerView()");
        PassLayoutManager layoutManager =
                new PassLayoutManager(this, RecyclerView.HORIZONTAL, false);
        
        RecyclerView passRecyclerView = findViewById(R.id.passRecyclerView);
        passRecyclerView.setLayoutManager(layoutManager);
        passRecyclerView.setHasFixedSize(true);
        
        PassListUpdateCallback passListUpdateCallback =
                new PassListUpdateCallback(passRecyclerView);
        
        passAdapter = new PassAdapter(this, passListUpdateCallback);
        passRecyclerView.setAdapter(passAdapter);
        
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(passRecyclerView);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        //noinspection SimplifiableIfStatement
        //        if (id == R.id.action_settings) {
        //            return true;
        //        }
        
        return super.onOptionsItemSelected(item);
    }
    
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        
        if (id == R.id.nav_settings) {
        
        }
        
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    
    public void onAddFromFileButtonClicked(MenuItem item) {
        
        Log.d(TAG, "onAddFromFileButtonClicked()");
        Intent addFileIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        addFileIntent.setType("*/*");
        startActivityForResult(addFileIntent, ADD_FROM_FILE_REQUEST_CODE);
    }
    
    public void onCreateFromFormButtonClicked(MenuItem item) {
        
        Intent createPassFormIntent = new Intent(this, CreatePassActivity.class);
        startActivityForResult(createPassFormIntent, CREATE_FROM_FORM_REQUEST_CODE);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        
        Log.d(TAG, "onActivityResult()");
        
        super.onActivityResult(requestCode, resultCode, data);
        
        if (requestCode == ADD_FROM_FILE_REQUEST_CODE && resultCode == MainActivity.RESULT_OK) {
            
            if (data != null) {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    walletViewModel.addPass(inputStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == CREATE_FROM_FORM_REQUEST_CODE &&
                   resultCode == MainActivity.RESULT_OK) {
            
            if (data != null) {
                Pass pass  = data.getParcelableExtra(CreatePassActivity.PASS_EXTRA);
                walletViewModel.addPass(pass);
            }
        }
    }
    
    @Override
    public void onBackPressed() {
        
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        
        Log.d(TAG, "onRequestPermissionsResult()");
        verifyPermissions();
    }
}
