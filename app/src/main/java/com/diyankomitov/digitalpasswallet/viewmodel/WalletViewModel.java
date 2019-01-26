package com.diyankomitov.digitalpasswallet.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.diyankomitov.digitalpasswallet.models.pass.Pass;
import com.diyankomitov.digitalpasswallet.models.pass.util.PassType;
import com.diyankomitov.digitalpasswallet.repository.Wallet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WalletViewModel extends ViewModel{

    MutableLiveData<List<PassViewModel>> passViewModels;
    Wallet wallet;


    public void init() {
        this.wallet = Wallet.getInstance();
        this.passViewModels = new MutableLiveData<>();

        List<PassViewModel> passViewModels = new ArrayList<>();

        for (Pass pass : Objects.requireNonNull(wallet.getPasses().getValue())) {
            passViewModels.add(new PassViewModel(pass));
        }

        this.passViewModels.setValue(passViewModels);

    }

    public LiveData<List<PassViewModel>> getPassViewModels() {
        return passViewModels;
    }
}
