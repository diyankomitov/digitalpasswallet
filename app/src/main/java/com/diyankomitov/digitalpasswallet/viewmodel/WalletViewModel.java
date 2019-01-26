package com.diyankomitov.digitalpasswallet.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.diyankomitov.digitalpasswallet.models.pass.Pass;
import com.diyankomitov.digitalpasswallet.models.pass.util.PassType;
import com.diyankomitov.digitalpasswallet.repository.Wallet;

import java.util.List;

public class WalletViewModel extends ViewModel{

    List<PassViewModel> passViewModels;
    MutableLiveData<List<Pass>> passesLiveData;
    Wallet wallet;


    public void init() {
        wallet = Wallet.getInstance();
        passesLiveData = wallet.getPasses();


    }

    public PassViewModel getPassViewModel(int position) {
        return passViewModels.get(position);
    }

    public PassType getPassType(int position) {
        return passesLiveData.getValue().get(position).getType();
    }

    public int getPassCount() {
        return passViewModels.size();
    }
}
