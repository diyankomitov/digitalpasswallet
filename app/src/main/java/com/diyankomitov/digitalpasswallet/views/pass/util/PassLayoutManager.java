package com.diyankomitov.digitalpasswallet.views.pass.util;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PassLayoutManager extends LinearLayoutManager {
    
    private static boolean isScrollEnabled = true;
    private final Context context;
    private int extraLayoutSpace;
    
    public PassLayoutManager(Context context) {
        
        super(context);
        this.context = context;
    
    }
    
    public PassLayoutManager(Context context, int orientation, boolean reverseLayout) {
        
        super(context, orientation, reverseLayout);
        this.context = context;
    }
    
    public PassLayoutManager(Context context, AttributeSet attrs, int defStyleAttr,
                             int defStyleRes) {
        
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    
    }
    
    public static void setScrollEnabled(boolean isScrollEnabled) {
        
        PassLayoutManager.isScrollEnabled = isScrollEnabled;
    }
    
    public void setExtraLayoutSpace(int extraLayoutSpace) {
        
        this.extraLayoutSpace = extraLayoutSpace;
    }
    
    @Override
    public boolean canScrollHorizontally() {
        
        return isScrollEnabled && super.canScrollHorizontally();
    }
    
//    @Override
//    protected int getExtraLayoutSpace(RecyclerView.State state) {
//
//       return context.getResources().getDisplayMetrics().widthPixels;
//    }
}
