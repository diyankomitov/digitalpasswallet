package com.diyankomitov.digitalpasswallet.views.pass.components;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.diyankomitov.digitalpasswallet.databinding.PassBackFieldDataBinding;
import com.diyankomitov.digitalpasswallet.viewmodel.pass.PassFieldViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

public class PassBackAdapter extends RecyclerView.Adapter<PassBackViewHolder> {
    
    private LifecycleOwner lifecycleOwner;
    private List<PassFieldViewModel> backFields;
    private LayoutInflater layoutInflater;
    
    public PassBackAdapter(LifecycleOwner lifecycleOwner) {
        
        this.lifecycleOwner = lifecycleOwner;
        this.backFields = new ArrayList<>();
    }
    
    public void setBackFields(List<PassFieldViewModel> backFields) {
        
        this.backFields = backFields;
        
        notifyDataSetChanged();
    }
    
    @NonNull
    @Override
    public PassBackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
    
        ViewDataBinding dataBinding = PassBackFieldDataBinding
                .inflate(layoutInflater, parent, false);
        
        dataBinding.setLifecycleOwner(lifecycleOwner);
        
        return new PassBackViewHolder(dataBinding);
    }
    
    @Override
    public void onBindViewHolder(@NonNull PassBackViewHolder holder, int position) {
        holder.bindBackFieldViewModel(backFields.get(position));
    }
    
    @Override
    public int getItemCount() {
        
        return backFields.size();
    }
}
