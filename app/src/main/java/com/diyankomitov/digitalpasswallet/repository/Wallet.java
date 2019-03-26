package com.diyankomitov.digitalpasswallet.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.diyankomitov.digitalpasswallet.models.pass.Pass;
import com.diyankomitov.digitalpasswallet.models.pass.PassModel;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassType;
import com.diyankomitov.digitalpasswallet.models.room.PassDao;
import com.diyankomitov.digitalpasswallet.models.room.PassDatabase;
import com.diyankomitov.digitalpasswallet.repository.passloader.PassFileLoader;
import com.diyankomitov.digitalpasswallet.repository.passloader.PassJSONParser;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import androidx.lifecycle.LiveData;

public class Wallet {
    
    private static final String TAG = "Wallet";
    
    private static Wallet walletInstance;
    
    private final LiveData<List<Pass>> passesLiveData;
    private final PassDao passDao;
    
    private Wallet(Application application) {
        
        Log.d(TAG, "Wallet() called");
        
        PassDatabase passDatabase = PassDatabase.getInstance(application);
        passDao = passDatabase.passDao();
        passesLiveData = passDao.getAllPasses();
    }
    
    public static synchronized Wallet getInstance(Application application) {
        
        Log.d(TAG, "getInstance() called");
        
        if (walletInstance == null) {
            walletInstance = new Wallet(application);
        }
        
        return walletInstance;
    }
    
    public LiveData<List<Pass>> getPasses() {
        
        Log.d(TAG, "getPasses() called");
        
        return passesLiveData;
    }
    
    public void addPass(InputStream inputStream) {  //TODO: cleanup
        
        
        File pkpassFileCopy = PassFileUtils.getNextPkPassFile();
        try {
            FileUtils.copyInputStreamToFile(inputStream, pkpassFileCopy);
        } catch (IOException e) {
            Log.e(TAG, "addPass: copy of pkpass file could not be created", e);
            return;
        }
        
        File unzippedFileOutputDirectory = PassFileUtils.getNextUnzippedPassDirectory();
        PassFileLoader.unzipPassFile(pkpassFileCopy, unzippedFileOutputDirectory);
        
        
        File jsonFile = PassFileUtils.getJsonFileFromPassDirectory(unzippedFileOutputDirectory);
        String passJsonString = PassFileLoader.loadPassJSON(jsonFile);
        passJsonString = passJsonString.replaceAll("[^ -~]", "");
        
        File passStringsFile = PassFileUtils.getStringsFileFromPassDirectory(unzippedFileOutputDirectory);
        JSONObject stringsJson = null;
        if (passStringsFile.exists()) {
            String passStringsString = "";
            
            passStringsString = PassFileLoader.loadPassJSON(passStringsFile);
            
            passStringsString = StringUtils
                    .removeEnd(passStringsString.replaceAll("=", ":").replaceAll(";", ","), ",");
            passStringsString = "{ " + passStringsString + " }";
            passStringsString = passStringsString.replaceAll("[^ -~]", "");
            try {
                stringsJson = new JSONObject(passStringsString);
            } catch (JSONException e) {
                Log.e(TAG, "addPass: something wrong with strings json", e);
            }
        }
        
        try {
            JSONObject passJson = new JSONObject(passJsonString);
            
            PassType passType = PassJSONParser.parsePassType(passJson);
            
            if (passType != null) {
                Pass pass = new PassModel(passType);
                
                PassJSONParser.parse(passJson, pass, stringsJson);
                
                PassFileLoader.loadPassImagePaths(pass, unzippedFileOutputDirectory);
                
                insert(pass);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    
    public void addPass(Pass pass) {
        insert(pass);
    }
    
    public void deletePass(int passId) {
    
        Log.d(TAG, "deletePass() called with: passId = [" + passId + "]");
        deleteById(passId);
    }
    
    private void insert(Pass pass) {
        
        AsyncTask.execute(() -> passDao.insert(pass));
    }
    
    private void update(Pass pass) {
        
        AsyncTask.execute(() -> passDao.update(pass));
    }
    
    private void delete(Pass pass) {
        
        AsyncTask.execute(() -> passDao.delete(pass));
    }
    
    private void deleteById(int passId) {
    
        Log.d(TAG, "deleteById() called with: passId = [" + passId + "]");
        AsyncTask.execute(() -> passDao.delete(passId));
    }
}
