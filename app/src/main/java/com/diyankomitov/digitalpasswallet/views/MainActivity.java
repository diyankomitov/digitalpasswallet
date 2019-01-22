package com.diyankomitov.digitalpasswallet.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.SnapHelper;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.diyankomitov.digitalpasswallet.R;
import com.diyankomitov.digitalpasswallet.models.parser.PassJSONParser;
import com.diyankomitov.digitalpasswallet.models.pass.BoardingCardPass;
import com.diyankomitov.digitalpasswallet.models.pass.CouponPass;
import com.diyankomitov.digitalpasswallet.models.pass.EventTicketPass;
import com.diyankomitov.digitalpasswallet.models.pass.GenericPass;
import com.diyankomitov.digitalpasswallet.models.pass.Pass;
import com.diyankomitov.digitalpasswallet.models.pass.StoreCardPass;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private List<Pass> passes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadPass(R.raw.pass1, R.raw.logo1, R.raw.thumbnail1, 0);
        loadPass(R.raw.pass2, R.raw.logo2, 0, R.raw.strip2);
        loadPass(R.raw.pass3, R.raw.logo3, 0, R.raw.strip3);
        loadPass(R.raw.pass4, R.raw.logo4, 0, 0);
        loadPass(R.raw.pass5, R.raw.logo5, R.raw.thumbnail5, 0);
        initPassRecyclerView();
    }

    private void loadPass(int fileID, int logoID, int thumbnailID, int stripID) { //TODO: Split into file reader and pass factory, and just make better
        InputStream inputStream = getResources().openRawResource(fileID);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

        String line;
        StringBuilder jsonString = new StringBuilder();

        try {

            while ((line = bufferedReader.readLine()) != null) {
                jsonString.append(line);
            }
        }   catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonString.toString());
            Pass pass = null;

            if (jsonObject.optJSONObject("boardingPass") != null) {
                pass = new BoardingCardPass();
            }
            else if (jsonObject.optJSONObject("coupon") != null) {
                pass = new CouponPass();
            }
            else if (jsonObject.optJSONObject("eventTicket") != null) {
                pass = new EventTicketPass();
            }
            else if (jsonObject.optJSONObject("generic") != null) {
                pass = new GenericPass();
            }
            else if (jsonObject.optJSONObject("storeCard") != null) {
                pass = new StoreCardPass();
            }

            if (pass != null) {
                PassJSONParser.parse(jsonObject, pass);
                Bitmap logoBitmap = BitmapFactory.decodeStream(getResources().openRawResource(logoID));
                pass.setLogo(logoBitmap);
                if (thumbnailID != 0) {
                    Bitmap thumbnailBitmap = BitmapFactory.decodeStream(getResources().openRawResource(thumbnailID));
                    pass.setThumbnail(thumbnailBitmap);
                }
                if (stripID != 0) {
                    Bitmap stripBitmap = BitmapFactory.decodeStream(getResources().openRawResource(stripID));
                    pass.setStrip(stripBitmap);
                }
                passes.add(pass);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initPassRecyclerView() {
        LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        RecyclerView passRecyclerView = findViewById(R.id.passRecyclerView);
        passRecyclerView.setLayoutManager(layoutManager);

        passRecyclerView.setHasFixedSize(true);

        PassAdapter passAdapter = new PassAdapter(passes);
        passRecyclerView.setAdapter(passAdapter);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(passRecyclerView);
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
}
