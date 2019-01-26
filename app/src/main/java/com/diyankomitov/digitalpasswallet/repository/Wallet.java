package com.diyankomitov.digitalpasswallet.repository;

import android.arch.lifecycle.MutableLiveData;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Wallet {


    private static Wallet walletInstance;

    private List<Pass> passes;

    private Wallet() {
        passes = new ArrayList<>();
    }

    public static Wallet getInstance() {
        if (walletInstance == null) {
            walletInstance = new Wallet();
        }

        return walletInstance;
    }

    public MutableLiveData<List<Pass>> getPasses() {
        loadPasses();

        MutableLiveData<List<Pass>> passData = new MutableLiveData<>();
        passData.setValue(passes);
        return passData;
    }

    private void loadPasses() { //Get from database/room/api/filestorage/whatever

//        loadPass(R.raw.pass1, R.raw.logo1, R.raw.thumbnail1, 0);
//        loadPass(R.raw.pass2, R.raw.logo2, 0, R.raw.strip2);
//        loadPass(R.raw.pass3, R.raw.logo3, 0, R.raw.strip3);
//        loadPass(R.raw.pass4, R.raw.logo4, 0, 0);
//        loadPass(R.raw.pass5, R.raw.logo5, R.raw.thumbnail5, 0);

    }

//    private void loadPass(int fileID, int logoID, int thumbnailID, int stripID) { //TODO: Split into file reader and pass factory, and just make better
//        InputStream inputStream = getResources().openRawResource(fileID);
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
//
//        String line;
//        StringBuilder jsonString = new StringBuilder();
//
//        try {
//
//            while ((line = bufferedReader.readLine()) != null) {
//                jsonString.append(line);
//            }
//        }   catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            JSONObject jsonObject = new JSONObject(jsonString.toString());
//            Pass pass = null;
//
//            if (jsonObject.optJSONObject("boardingPass") != null) {
//                pass = new BoardingCardPass();
//            }
//            else if (jsonObject.optJSONObject("coupon") != null) {
//                pass = new CouponPass();
//            }
//            else if (jsonObject.optJSONObject("eventTicket") != null) {
//                pass = new EventTicketPass();
//            }
//            else if (jsonObject.optJSONObject("generic") != null) {
//                pass = new GenericPass();
//            }
//            else if (jsonObject.optJSONObject("storeCard") != null) {
//                pass = new StoreCardPass();
//            }
//
//            if (pass != null) {
//                PassJSONParser.parse(jsonObject, pass);
//                Bitmap logoBitmap = BitmapFactory.decodeStream(getResources().openRawResource(logoID));
//                pass.setLogo(logoBitmap);
//                if (thumbnailID != 0) {
//                    Bitmap thumbnailBitmap = BitmapFactory.decodeStream(getResources().openRawResource(thumbnailID));
//                    pass.setThumbnail(thumbnailBitmap);
//                }
//                if (stripID != 0) {
//                    Bitmap stripBitmap = BitmapFactory.decodeStream(getResources().openRawResource(stripID));
//                    pass.setStrip(stripBitmap);
//                }
//                passes.add(pass);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//    }

//    private Map<String, List<Pass>> walletMap;
//
//    public Wallet() {
//        walletMap = new HashMap<>();
//    }
//
//    public void addCategory(String name) {
//        walletMap.put(name, new ArrayList<Pass>());
//    }
//
//    public List<Pass> getPassesFromCategory(String name) {
//        if (name.equals("")) {
//            return walletMap.get("default");
//        }
//        return walletMap.get(name);
//    }
//
//    public void addPassToCategory(String categoryName, Pass pass) {
//        walletMap.get(categoryName).add(pass);
//    }
}
