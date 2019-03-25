package com.diyankomitov.digitalpasswallet.views.pass;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

import com.diyankomitov.digitalpasswallet.BR;
import com.diyankomitov.digitalpasswallet.R;
import com.diyankomitov.digitalpasswallet.viewmodel.pass.PassViewModel;
import com.diyankomitov.digitalpasswallet.views.pass.components.PassBackAdapter;
import com.diyankomitov.digitalpasswallet.views.pass.components.PassFieldValueTextView;
import com.diyankomitov.digitalpasswallet.views.pass.util.PassViewHandler;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PassViewHolder extends RecyclerView.ViewHolder {
    
    private final View rootView;
    private LifecycleOwner lifecycleOwner;
    private ViewDataBinding passDataBinding;
    private PassBackAdapter passBackAdapter;
    
    PassViewHolder(LifecycleOwner lifecycleOwner, ViewDataBinding passDataBinding) {
        
        super(passDataBinding.getRoot());
        this.lifecycleOwner = lifecycleOwner;
        this.passDataBinding = passDataBinding;
        this.rootView = passDataBinding.getRoot();
        
        PassFieldValueTextView header1 = rootView.findViewById(R.id.header_value_1);
        PassFieldValueTextView header2 = rootView.findViewById(R.id.header_value_2);
        PassFieldValueTextView header3 = rootView.findViewById(R.id.header_value_3);
        
        List<PassFieldValueTextView> headerGroup = new ArrayList<>();
        headerGroup.add(header1);
        headerGroup.add(header2);
        headerGroup.add(header3);
        
        for (PassFieldValueTextView header : headerGroup) {
            header.setTextGroup(headerGroup);
        }
        
        PassFieldValueTextView secondary1 = rootView.findViewById(R.id.secondary_value_1);
        PassFieldValueTextView secondary2 = rootView.findViewById(R.id.secondary_value_2);
        PassFieldValueTextView secondary3 = rootView.findViewById(R.id.secondary_value_3);
        PassFieldValueTextView secondary4 = rootView.findViewById(R.id.secondary_value_4);
    
        PassFieldValueTextView auxiliary1 = rootView.findViewById(R.id.auxiliary_value_1);
        PassFieldValueTextView auxiliary2 = rootView.findViewById(R.id.auxiliary_value_2);
        PassFieldValueTextView auxiliary3 = rootView.findViewById(R.id.auxiliary_value_3);
        PassFieldValueTextView auxiliary4 = rootView.findViewById(R.id.auxiliary_value_4);
        PassFieldValueTextView auxiliary5 = rootView.findViewById(R.id.auxiliary_value_5);
    
        List<PassFieldValueTextView> secondaryAndAuxiliaryGroup = new ArrayList<>();
        secondaryAndAuxiliaryGroup.add(secondary1);
        secondaryAndAuxiliaryGroup.add(secondary2);
        secondaryAndAuxiliaryGroup.add(secondary3);
        secondaryAndAuxiliaryGroup.add(secondary4);
        
        secondaryAndAuxiliaryGroup.add(auxiliary1);
        secondaryAndAuxiliaryGroup.add(auxiliary2);
        secondaryAndAuxiliaryGroup.add(auxiliary3);
        secondaryAndAuxiliaryGroup.add(auxiliary4);
        if (auxiliary5 != null) {
            secondaryAndAuxiliaryGroup.add(auxiliary5);
        }
        
        for (PassFieldValueTextView textView : secondaryAndAuxiliaryGroup) {
            textView.setTextGroup(secondaryAndAuxiliaryGroup);
        }
        
        initBackRecyclerView();
    }
    
    private void initBackRecyclerView() {
        
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(rootView.getContext(), RecyclerView.VERTICAL, false);
        
        RecyclerView passRecyclerView = rootView.findViewById(R.id.passBackRecyclerView);
        passRecyclerView.setLayoutManager(layoutManager);
        passRecyclerView.setHasFixedSize(true);
        
        passBackAdapter = new PassBackAdapter(lifecycleOwner);
        passRecyclerView.setAdapter(passBackAdapter);
    }
    
    public void setBindings(PassViewModel passViewModel) {
        
        passDataBinding.setVariable(BR.passViewModel, passViewModel);
        passDataBinding.setVariable(BR.passViewHandler, new PassViewHandler(rootView));
        
        passBackAdapter.setBackFields(passViewModel.getBackFields());
        
        animatePassLoad();
    }
    
    private void animatePassLoad() {
        
        View cardFront = rootView.findViewById(R.id.card_front);
        View progress_bar = rootView.findViewById(R.id.progress_bar);
        
        cardFront.setAlpha(0f);
        cardFront.setVisibility(View.GONE);
        progress_bar.setVisibility(View.VISIBLE);

        
        cardFront.animate().alpha(1f).setDuration(500).setStartDelay(500)
                 .setListener(new AnimatorListenerAdapter() {
                     @Override
                     public void onAnimationStart(Animator animation) {
                         progress_bar.setVisibility(View.GONE);
                         cardFront.setVisibility(View.VISIBLE);
                     }
                 });
    }
}
