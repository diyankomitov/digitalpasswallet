package com.diyankomitov.digitalpasswallet.views;

import android.support.v7.widget.RecyclerView;

import com.diyankomitov.digitalpasswallet.databinding.BoardingPassDataBinding;
import com.diyankomitov.digitalpasswallet.databinding.CouponDataBinding;
import com.diyankomitov.digitalpasswallet.databinding.EventTicketDataBinding;
import com.diyankomitov.digitalpasswallet.databinding.GenericDataBinding;
import com.diyankomitov.digitalpasswallet.databinding.StoreCardDataBinding;
import com.diyankomitov.digitalpasswallet.models.pass.Pass;

public class PassViewHolder extends RecyclerView.ViewHolder {
    //data bindings
    private BoardingPassDataBinding boardingPassDataBinding;
    private CouponDataBinding couponDataBinding;
    private EventTicketDataBinding eventTicketDataBinding;
    private GenericDataBinding genericDataBinding;
    private StoreCardDataBinding storeCardDataBinding;


    PassViewHolder(BoardingPassDataBinding boardingPassDataBinding) {
        super(boardingPassDataBinding.getRoot());
        this.boardingPassDataBinding = boardingPassDataBinding;
    }

    PassViewHolder(CouponDataBinding couponDataBinding) {
        super(couponDataBinding.getRoot());
        this.couponDataBinding = couponDataBinding;
    }

    PassViewHolder(EventTicketDataBinding eventTicketDataBinding) {
        super(eventTicketDataBinding.getRoot());
        this.eventTicketDataBinding = eventTicketDataBinding;
    }

    PassViewHolder(GenericDataBinding genericDataBinding) {
        super(genericDataBinding.getRoot());
        this.genericDataBinding = genericDataBinding;
    }

    PassViewHolder(StoreCardDataBinding storeCardDataBinding) {
        super(storeCardDataBinding.getRoot());
        this.storeCardDataBinding = storeCardDataBinding;
    }


    public void bindPass(Pass pass) {
        if (boardingPassDataBinding != null) {
            boardingPassDataBinding.setPass(pass);
        }
        else if (couponDataBinding != null) {
            couponDataBinding.setPass(pass);
        }
        else if (eventTicketDataBinding != null) {
            eventTicketDataBinding.setPass(pass);
        }
        else if (genericDataBinding != null) {
            genericDataBinding.setPass(pass);
        }
        else if (storeCardDataBinding != null) {
            storeCardDataBinding.setPass(pass);
        }
    }

}
