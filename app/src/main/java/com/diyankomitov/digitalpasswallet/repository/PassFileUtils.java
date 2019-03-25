package com.diyankomitov.digitalpasswallet.repository;

import android.os.Environment;
import android.util.Log;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * FileUtils clean directory code provided by Aaron Hall at https://stackoverflow.com/a/26392721/
 */
public class PassFileUtils {
    
    private static final File PASSES_DIRECTORY =
            new File(Environment.getExternalStorageDirectory(), "DigitalPassWallet/passes");
    
    private static final File PKPASS_DIRECTORY = new File(PASSES_DIRECTORY, "pkpass");
    
    private static final File UNZIPPED_DIRECTORY = new File(PASSES_DIRECTORY, "unzipped");
    
    private static final String PKPASS_EXTENSION = ".pkpass";
    
    public static File getNextPkPassFile() {
        
        return new File(PKPASS_DIRECTORY, "pass" + getPkPassFileIndex() + PKPASS_EXTENSION);
    }
    
    private static int getPkPassFileIndex() {
        
        return PKPASS_DIRECTORY.exists() ? PKPASS_DIRECTORY.list().length : 0;
    }
    
    public static File getNextUnzippedPassDirectory() {
        
        File file = new File(UNZIPPED_DIRECTORY, "pass" + getUnzippedPassDirectoryIndex());
        
        if (file.exists()) {
            try {
                FileUtils.cleanDirectory(file);
            } catch (IOException e) {
                Log.e(TAG, "getNextUnzippedPassDirectory: directory could not be cleaned", e);
            }
        } else {
            file.mkdirs();
        }
        
        return file;
    }
    
    private static int getUnzippedPassDirectoryIndex() {
        
        return UNZIPPED_DIRECTORY.exists() ? UNZIPPED_DIRECTORY.list().length : 0;
    }
    
    public static File getJsonFileFromPassDirectory(File passDirectory) {
        
        return new File(passDirectory, "pass.json");
    }
    
    public static File getStringsFileFromPassDirectory(File passDirectory) {
        
        return new File(passDirectory, "en.lproj/pass.strings");
    }
}
