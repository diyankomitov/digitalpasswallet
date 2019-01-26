package com.diyankomitov.digitalpasswallet.views;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.diyankomitov.digitalpasswallet.databinding.BoardingPassDataBinding;
import com.diyankomitov.digitalpasswallet.databinding.CouponDataBinding;
import com.diyankomitov.digitalpasswallet.databinding.EventTicketDataBinding;
import com.diyankomitov.digitalpasswallet.databinding.GenericDataBinding;
import com.diyankomitov.digitalpasswallet.databinding.StoreCardDataBinding;
import com.diyankomitov.digitalpasswallet.models.pass.util.PassType;
import com.diyankomitov.digitalpasswallet.viewmodel.WalletViewModel;

public class PassAdapter extends RecyclerView.Adapter<PassViewHolder> {

    private static final int PASS_TYPE_BOARDING_PASS = 0;
    private static final int PASS_TYPE_COUPON = 1;
    private static final int PASS_TYPE_EVENT_TICKET = 2;
    private static final int PASS_TYPE_GENERIC = 3;
    private static final int PASS_TYPE_STORE_CARD = 4;

//    private List<Pass> passes;
    private WalletViewModel walletViewModel;
    private LifecycleOwner lifecycleOwner;
    private LayoutInflater layoutInflater;

    public PassAdapter(WalletViewModel walletViewModel, LifecycleOwner lifecycleOwner) {
        this.walletViewModel = walletViewModel;
        this.lifecycleOwner = lifecycleOwner;
    }


    @NonNull
    @Override
    public PassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ViewDataBinding dataBinding;

        switch (viewType) {
            case PASS_TYPE_BOARDING_PASS:
                dataBinding = BoardingPassDataBinding.inflate(layoutInflater, parent, false);
                break;
            case PASS_TYPE_COUPON:
                dataBinding = CouponDataBinding.inflate(layoutInflater, parent, false);
                break;
            case PASS_TYPE_EVENT_TICKET:
                dataBinding = EventTicketDataBinding.inflate(layoutInflater, parent, false);
                break;
            case PASS_TYPE_GENERIC:
                dataBinding = GenericDataBinding.inflate(layoutInflater, parent, false);
                break;
            case PASS_TYPE_STORE_CARD:
                dataBinding = StoreCardDataBinding.inflate(layoutInflater, parent, false);
                break;
            default:
                dataBinding = GenericDataBinding.inflate(layoutInflater, parent, false);
                break;
        }

        dataBinding.setLifecycleOwner(lifecycleOwner);

        return new PassViewHolder(dataBinding);
    }

    @Override
    public int getItemViewType(int position) {
        PassType passType = walletViewModel.getPassType(position);
        switch (passType) {
            case BOARDING_PASS:
                return PASS_TYPE_BOARDING_PASS;
            case COUPON:
                return PASS_TYPE_COUPON;
            case EVENT_TICKET:
                return PASS_TYPE_EVENT_TICKET;
            case GENERIC:
                return PASS_TYPE_GENERIC;
            case STORE_CARD:
                return PASS_TYPE_STORE_CARD;
            default:
                return PASS_TYPE_GENERIC;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull PassViewHolder holder, int position) {
        holder.bindPassViewModel(walletViewModel.getPassViewModel(position));
    }

    @Override
    public int getItemCount() {
        return walletViewModel.getPassCount();
    }
}
