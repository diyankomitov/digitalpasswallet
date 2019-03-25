package com.diyankomitov.digitalpasswallet.views.form.components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diyankomitov.digitalpasswallet.databinding.CreatePassHeaderFragmentBinding;
import com.diyankomitov.digitalpasswallet.viewmodel.FormViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class CreatePassHeaderFragment extends Fragment {
    
    private FormViewModel formViewModel;
    
    public CreatePassHeaderFragment() {
        // Required empty public constructor
    }
    
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        
        formViewModel = ViewModelProviders.of(this.getActivity()).get(FormViewModel.class);
    }
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        
        CreatePassHeaderFragmentBinding binding =
                CreatePassHeaderFragmentBinding.inflate(inflater, container, false);
        binding.setFormViewModel(formViewModel);
        binding.setLifecycleOwner(this);
        
        return binding.getRoot();
    }
}

