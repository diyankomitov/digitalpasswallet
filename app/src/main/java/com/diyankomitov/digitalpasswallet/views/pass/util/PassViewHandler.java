package com.diyankomitov.digitalpasswallet.views.pass.util;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;

import com.diyankomitov.digitalpasswallet.R;
import com.diyankomitov.digitalpasswallet.viewmodel.pass.PassViewModel;

public class PassViewHandler {
    
    private final View cardFront;
    private final View cardBack;
    private final AnimatorSet frontToBackAnimation;
    private final AnimatorSet backToFrontAnimation;
    private final AnimatorListenerAdapter onFlipToBack;
    private final AnimatorListenerAdapter onFlipToFront;
    
    
    public PassViewHandler(View frameLayout) {
        
        Context context = frameLayout.getContext();
        
        frontToBackAnimation = (AnimatorSet) AnimatorInflater
                .loadAnimator(context, R.animator.front_to_back_pass_animation);
        backToFrontAnimation = (AnimatorSet) AnimatorInflater
                .loadAnimator(context, R.animator.back_to_front_pass_animation);
        
        float scale = context.getResources().getDisplayMetrics().density * 8000;
        
        cardFront = frameLayout.findViewById(R.id.card_front);
        cardBack = frameLayout.findViewById(R.id.card_back);
        
        cardFront.setCameraDistance(scale);
        cardBack.setCameraDistance(scale);
        
        onFlipToBack = initOnFlipToBack();
        onFlipToFront = initOnFlipToFront();
    }
    
    public void onClickMoreInfo(View moreInfoButton) {
        
        frontToBackAnimation.addListener(onFlipToBack);
        flipToBack();
    }
    
    public void onClickClose(View closeButton) {
        
        frontToBackAnimation.addListener(onFlipToFront);
        flipToFront();
    }
    
    public void onClickDelete(PassViewModel passViewModel, View closeButton) {
        
        onClickClose(closeButton);
        passViewModel.onClickDelete();
    }
    
    private void flipToBack() {
        
        frontToBackAnimation.setTarget(cardFront);
        backToFrontAnimation.setTarget(cardBack);
        frontToBackAnimation.start();
        backToFrontAnimation.start();
    }
    
    private void flipToFront() {
        
        frontToBackAnimation.setTarget(cardBack);
        backToFrontAnimation.setTarget(cardFront);
        frontToBackAnimation.start();
        backToFrontAnimation.start();
    }
    
    private AnimatorListenerAdapter initOnFlipToBack() {
        
        return new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                
                PassLayoutManager.setScrollEnabled(false);
                enableView(cardBack);
            }
            
            @Override
            public void onAnimationEnd(Animator animation) {
                
                disableView(cardFront);
                frontToBackAnimation.removeListener(this);
            }
        };
    }
    
    private AnimatorListenerAdapter initOnFlipToFront() {
        
        return new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                
                enableView(cardFront);
            }
            
            @Override
            public void onAnimationEnd(Animator animation) {
                
                disableView(cardBack);
                PassLayoutManager.setScrollEnabled(true);
                frontToBackAnimation.removeListener(this);
            }
        };
    }
    
    private void disableView(View view) {
        
        view.setVisibility(View.GONE);
        view.setClickable(false);
        view.setEnabled(false);
    }
    
    private void enableView(View view) {
        
        view.setVisibility(View.VISIBLE);
        view.setClickable(true);
        view.setEnabled(true);
    }
}
