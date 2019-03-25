package com.diyankomitov.digitalpasswallet.viewmodel;

import android.app.Application;
import android.util.Log;

import com.diyankomitov.digitalpasswallet.models.pass.Pass;
import com.diyankomitov.digitalpasswallet.repository.Wallet;
import com.diyankomitov.digitalpasswallet.viewmodel.pass.PassViewModel;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class WalletViewModel extends AndroidViewModel {
    
    private static final String TAG = "WalletViewModel";
    
    private Wallet wallet;
    private MutableLiveData<List<PassViewModel>> passViewModelsLiveData;
    private Observer<List<Pass>> passesObserver;
    private LiveData<List<Pass>> passesLiveData;
    
    public WalletViewModel(@NonNull Application application) {
        
        super(application);
        
        Log.d(TAG, "WalletViewModel() called with: application = [" + application + "]");
        
        this.wallet = Wallet.getInstance(application);
        
        if (passViewModelsLiveData == null) {
            initPassViewModels();
        }
    }
    
    public LiveData<List<PassViewModel>> getPassViewModels() {
        
        Log.d(TAG, "getPassViewModels() called");
        return passViewModelsLiveData;
    }
    
    public void addPass(InputStream inputStream) {
        
        Log.d(TAG, "addPass() called with: inputStream = [" + inputStream + "]");
        
        wallet.addPass(inputStream);
    }
    
    public void addPass(Pass pass) {
        wallet.addPass(pass);
    }
    
    public void deletePass(int passId) {
    
        Log.d(TAG, "deletePass() called with: passId = [" + passId + "]");
        wallet.deletePass(passId);
    }
    
    private void initPassViewModels() {
        
        Log.d(TAG, "initPassViewModels() called");
        
        passesLiveData = wallet.getPasses();
        
        passViewModelsLiveData = new MutableLiveData<>();
        onPassesLiveDataChanged(passesLiveData.getValue()); // Run once to initialize
        
        passesObserver = this::onPassesLiveDataChanged;     // Run on change
        passesLiveData.observeForever(passesObserver);
    }
    
    private void onPassesLiveDataChanged(List<Pass> passes) {
        
        Log.d(TAG, "onPassesLiveDataChanged() called with: passes = [" + passes + "]");
        
        List<PassViewModel> passViewModels = new ArrayList<>();
        
        if (passes != null) {
            for (Pass pass : passes) {
                passViewModels.add(new PassViewModel(pass, this));
            }
        }
    
        Log.d(TAG, "onPassesLiveDataChanged: passViewModels size " + passViewModels.size());
        
        passViewModelsLiveData.postValue(passViewModels);
    }
    
    @Override
    protected void onCleared() {
        
        super.onCleared();
        
        passesLiveData.removeObserver(passesObserver);
    }
    
}
