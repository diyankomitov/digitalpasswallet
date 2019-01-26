package com.diyankomitov.digitalpasswallet.views;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.diyankomitov.digitalpasswallet.BR;
import com.diyankomitov.digitalpasswallet.viewmodel.PassViewModel;

public class PassViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding passDataBinding;

    PassViewHolder(ViewDataBinding passDataBinding) {
        super(passDataBinding.getRoot());
        this.passDataBinding = passDataBinding;
    }


    public void bindPassViewModel(PassViewModel passViewModel) {
        passDataBinding.setVariable(BR.passViewModel, passViewModel);
    }

}
