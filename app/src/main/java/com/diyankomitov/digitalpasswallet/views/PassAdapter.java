package com.diyankomitov.digitalpasswallet.views;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.diyankomitov.digitalpasswallet.databinding.BoardingPassDataBinding;
import com.diyankomitov.digitalpasswallet.databinding.CouponDataBinding;
import com.diyankomitov.digitalpasswallet.databinding.EventTicketDataBinding;
import com.diyankomitov.digitalpasswallet.databinding.GenericDataBinding;
import com.diyankomitov.digitalpasswallet.databinding.StoreCardDataBinding;
import com.diyankomitov.digitalpasswallet.models.pass.util.PassType;
import com.diyankomitov.digitalpasswallet.viewmodel.PassViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

public class PassAdapter extends RecyclerView.Adapter<PassViewHolder> {

    private static final int PASS_TYPE_BOARDING_PASS = 0;
    private static final int PASS_TYPE_COUPON = 1;
    private static final int PASS_TYPE_EVENT_TICKET = 2;
    private static final int PASS_TYPE_GENERIC = 3;
    private static final int PASS_TYPE_STORE_CARD = 4;

    private List<PassViewModel> passViewModels;
    private LifecycleOwner lifecycleOwner;
    private LayoutInflater layoutInflater;

    public PassAdapter(LifecycleOwner lifecycleOwner, List<PassViewModel> passViewModels) {
        this.lifecycleOwner = lifecycleOwner;
        this.passViewModels = passViewModels;
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
        PassType passType = passViewModels.get(position).getPassType().getValue();  //TODO: Evaluate if best way to do
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
        holder.bindPassViewModel(passViewModels.get(position));
    }

    @Override
    public int getItemCount() {
        return passViewModels.size();
    }
}
