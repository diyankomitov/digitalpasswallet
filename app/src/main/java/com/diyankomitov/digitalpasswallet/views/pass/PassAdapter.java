package com.diyankomitov.digitalpasswallet.views.pass;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.diyankomitov.digitalpasswallet.R;
import com.diyankomitov.digitalpasswallet.models.pass.components.PassType;
import com.diyankomitov.digitalpasswallet.viewmodel.pass.PassViewModel;
import com.diyankomitov.digitalpasswallet.views.pass.util.PassDiffCallback;
import com.diyankomitov.digitalpasswallet.views.pass.util.PassListUpdateCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class PassAdapter extends RecyclerView.Adapter<PassViewHolder> {
    
    private static final String TAG = "PassAdapter";
    
    private static final int BOARDING_PASS_LAYOUT = R.layout.pass_item_boarding_pass;
    private static final int COUPON_LAYOUT = R.layout.pass_item_coupon;
    private static final int EVENT_TICKET_LAYOUT = R.layout.pass_item_event_ticket;
    private static final int GENERIC_LAYOUT = R.layout.pass_item_generic;
    private static final int STORE_CARD_LAYOUT = R.layout.pass_item_store_card;
    private final PassListUpdateCallback passListUpdateCallback;
    
    private List<PassViewModel> passViewModels;
    private LifecycleOwner lifecycleOwner;
    private LayoutInflater layoutInflater;
    
    public PassAdapter(LifecycleOwner lifecycleOwner,
                       PassListUpdateCallback passListUpdateCallback) {
        
        this.lifecycleOwner = lifecycleOwner;
        this.passViewModels = new ArrayList<>();
        this.passListUpdateCallback = passListUpdateCallback;
        passListUpdateCallback.setAdapter(this);
    }
    
    public void updateDataSet(List<PassViewModel> newPassViewModels) {
        
        DiffUtil.DiffResult diffResult =
                DiffUtil.calculateDiff(new PassDiffCallback(passViewModels, newPassViewModels));
        
        passViewModels.clear();
        passViewModels.addAll(newPassViewModels);
        
        Log.d(TAG, "updateDataSet: " + passViewModels.size());
        
        diffResult.dispatchUpdatesTo(passListUpdateCallback);
    }
    
    @NonNull
    @Override
    public PassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        
        ViewDataBinding dataBinding =
                DataBindingUtil.inflate(layoutInflater, viewType, parent, false);
        
        dataBinding.setLifecycleOwner(lifecycleOwner);
        
        return new PassViewHolder(lifecycleOwner, dataBinding);
    }
    
    @Override
    public void onBindViewHolder(@NonNull PassViewHolder holder, int position) {
        
        holder.setBindings(passViewModels.get(position));
    }
    
    @Override
    public int getItemViewType(int position) {
        
        PassType passType = passViewModels.get(position).getPassType();
        
        switch (passType) {
            case BOARDING_PASS:
                return BOARDING_PASS_LAYOUT;
            case COUPON:
                return COUPON_LAYOUT;
            case EVENT_TICKET:
                return EVENT_TICKET_LAYOUT;
            case GENERIC:
            default:
                return GENERIC_LAYOUT;
            case STORE_CARD:
                return STORE_CARD_LAYOUT;
        }
    }
    
    @Override
    public int getItemCount() {
        
        return passViewModels.size();
    }
    
    public void scrollToNewItem(int position) {
    
    }
}
