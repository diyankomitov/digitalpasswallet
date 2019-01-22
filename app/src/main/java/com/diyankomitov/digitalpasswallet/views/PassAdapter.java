package com.diyankomitov.digitalpasswallet.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.diyankomitov.digitalpasswallet.databinding.BoardingPassDataBinding;
import com.diyankomitov.digitalpasswallet.databinding.CouponDataBinding;
import com.diyankomitov.digitalpasswallet.databinding.EventTicketDataBinding;
import com.diyankomitov.digitalpasswallet.databinding.GenericDataBinding;
import com.diyankomitov.digitalpasswallet.databinding.StoreCardDataBinding;
import com.diyankomitov.digitalpasswallet.models.pass.Pass;

import java.util.List;

public class PassAdapter extends RecyclerView.Adapter<PassViewHolder> {

    private static final int PASS_TYPE_BOARDING_PASS = 0;
    private static final int PASS_TYPE_COUPON = 1;
    private static final int PASS_TYPE_EVENT_TICKET = 2;
    private static final int PASS_TYPE_GENERIC = 3;
    private static final int PASS_TYPE_STORE_CARD = 4;

    private List<Pass> passes;
    private LayoutInflater layoutInflater;

    public PassAdapter(List<Pass> passes) {
        this.passes = passes;
    }


    @NonNull
    @Override
    public PassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        switch (viewType) {
            case PASS_TYPE_BOARDING_PASS:
                BoardingPassDataBinding boardingPassDataBinding = BoardingPassDataBinding.inflate(layoutInflater, parent, false);
                return new PassViewHolder(boardingPassDataBinding);
            case PASS_TYPE_COUPON:
                CouponDataBinding couponDataBinding = CouponDataBinding.inflate(layoutInflater, parent, false);
                return new PassViewHolder(couponDataBinding);
            case PASS_TYPE_EVENT_TICKET:
                EventTicketDataBinding eventTicketDataBinding = EventTicketDataBinding.inflate(layoutInflater, parent, false);
                return new PassViewHolder(eventTicketDataBinding);
            case PASS_TYPE_GENERIC:
                GenericDataBinding genericDataBinding = GenericDataBinding.inflate(layoutInflater, parent, false);
                return new PassViewHolder(genericDataBinding);
            case PASS_TYPE_STORE_CARD:
                StoreCardDataBinding storeCardDataBinding = StoreCardDataBinding.inflate(layoutInflater, parent, false);
                return new PassViewHolder(storeCardDataBinding);
            default:
                GenericDataBinding defaultDataBinding = GenericDataBinding.inflate(layoutInflater, parent, false);
                return new PassViewHolder(defaultDataBinding);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Pass pass = passes.get(position);
        switch (pass.getType()) {
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
        Pass pass = passes.get(position);
        holder.bindPass(pass);
    }

    @Override
    public int getItemCount() {
        return passes.size();
    }
}
