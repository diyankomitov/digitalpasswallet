package com.diyankomitov.digitalpasswallet.views.pass.components;

import com.diyankomitov.digitalpasswallet.BR;
import com.diyankomitov.digitalpasswallet.viewmodel.pass.PassFieldViewModel;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

class PassBackViewHolder extends RecyclerView.ViewHolder {
    
    private ViewDataBinding passBackDataBinding;
    
    PassBackViewHolder(ViewDataBinding dataBinding) {
        
        super(dataBinding.getRoot());
        
        this.passBackDataBinding = dataBinding;
    }
    
    void bindBackFieldViewModel(PassFieldViewModel backFieldViewModel) {
        passBackDataBinding.setVariable(BR.backField, backFieldViewModel);
    }
}
