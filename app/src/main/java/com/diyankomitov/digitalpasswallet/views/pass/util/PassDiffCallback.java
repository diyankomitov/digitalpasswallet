package com.diyankomitov.digitalpasswallet.views.pass.util;

import com.diyankomitov.digitalpasswallet.viewmodel.pass.PassViewModel;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

public class PassDiffCallback extends DiffUtil.Callback {
    
    private final List<PassViewModel> oldPassViewModelList;
    private final List<PassViewModel> newPassViewModelList;
    
    public PassDiffCallback(List<PassViewModel> oldPassViewModelList,
                            List<PassViewModel> newPassViewModelList) {
        
        this.oldPassViewModelList = oldPassViewModelList;
        this.newPassViewModelList = newPassViewModelList;
    }
    
    @Override
    public int getOldListSize() {
        
        return oldPassViewModelList.size();
    }
    
    @Override
    public int getNewListSize() {
        
        return newPassViewModelList.size();
    }
    
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        
        return oldPassViewModelList.get(oldItemPosition).getPassId() ==
               newPassViewModelList.get(newItemPosition).getPassId();
    }
    
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        
        final PassViewModel oldPassViewModel = oldPassViewModelList.get(oldItemPosition);
        final PassViewModel newPassViewModel = newPassViewModelList.get(newItemPosition);
        
        return oldPassViewModel.equals(newPassViewModel);
    }
}
