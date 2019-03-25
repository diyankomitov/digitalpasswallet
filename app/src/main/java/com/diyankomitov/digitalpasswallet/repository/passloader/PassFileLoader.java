package com.diyankomitov.digitalpasswallet.repository.passloader;

import android.util.Log;

import com.diyankomitov.digitalpasswallet.models.pass.Pass;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PassFileLoader {
    
    private static final String TAG = "PassFileLoader";
    
    public static void unzipPassFile(File inputFile, File outputDirectory) {
        
        Log.d(TAG,
              "unzipPassFile() called with: inputFile = [" + inputFile + "], outputDirectory = [" +
              outputDirectory + "]");
        
        try {
            ZipFile zipFile = new ZipFile(inputFile);
            zipFile.extractAll(outputDirectory.getPath());
        } catch (ZipException e) {
            Log.e(TAG, "unzipPassFile: file could not be unzipped", e);
        }
    }
    
    public static String loadPassJSON(File jsonFile) {
        
        Log.d(TAG, "loadPassJSON() called with: jsonFile = [" + jsonFile + "]");
        
        StringBuilder jsonString = new StringBuilder();
        
        try {
            InputStream inputStream = new FileInputStream(jsonFile);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonString.append(line);
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, "loadPassJSON: JSON file not found", e);
        } catch (IOException e) {
            Log.e(TAG, "loadPassJSON: Something went wrong with loading JSON string", e);
        }
        
        return jsonString.toString();
    }
    
    public static void loadPassImagePaths(Pass pass, File imageDirectory) {
        
        Log.d(TAG, "loadPassImagePaths() called with: pass = [" + pass + "], imageDirectory = [" +
                   imageDirectory + "]");
        
        String imageDirectoryPath = imageDirectory.getPath();
        
        pass.setBackgroundImagePath(loadPassImagePath(imageDirectoryPath + "/background"));
        pass.setFooterPath(loadPassImagePath(imageDirectoryPath + "/footer"));
        pass.setIconPath(loadPassImagePath(imageDirectoryPath + "/icon"));
        pass.setLogoPath(loadPassImagePath(imageDirectoryPath + "/logo"));
        pass.setStripPath(loadPassImagePath(imageDirectoryPath + "/strip"));
        pass.setThumbnailPath(loadPassImagePath(imageDirectoryPath + "/thumbnail"));
    }
    
    private static String loadPassImagePath(String path) {
    
        Log.d(TAG, "loadPassImagePath() called with: path = [" + path + "]");
        
        File imageFile = new File(path + ".png");
        File imageFile2x = new File(path + "@2x" + ".png");
        File imageFile3x = new File(path + "@3x" + ".png");
        
        if (imageFile3x.exists()) {
            return imageFile3x.getPath();
        } else if (imageFile2x.exists()) {
            return imageFile2x.getPath();
        } else if (imageFile.exists()) {
            return imageFile.getPath();
        } else {
            return null;
        }
    }
}
