package com.diyankomitov.digitalpasswallet.views.pass.util;

import com.diyankomitov.digitalpasswallet.views.pass.PassAdapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView;

public class PassListUpdateCallback implements ListUpdateCallback {
    
    private PassAdapter passAdapter;
    private RecyclerView recyclerView;
    
    public PassListUpdateCallback(RecyclerView recyclerView) {
        
        this.recyclerView = recyclerView;
    }
    
    @Override
    public void onInserted(int position, int count) {
        
        passAdapter.notifyItemRangeInserted(position, count);
        
        recyclerView.smoothScrollToPosition(position);
    }
    
    @Override
    public void onRemoved(int position, int count) {
        
        passAdapter.notifyItemRangeRemoved(position, count);
    }
    
    @Override
    public void onMoved(int fromPosition, int toPosition) {
        
        passAdapter.notifyItemMoved(fromPosition, toPosition);
    }
    
    @Override
    public void onChanged(int position, int count, @Nullable Object payload) {
        
        passAdapter.notifyItemRangeChanged(position, count, payload);
    }
    
    public void setAdapter(PassAdapter passAdapter) {
        
        this.passAdapter = passAdapter;
    }
}
