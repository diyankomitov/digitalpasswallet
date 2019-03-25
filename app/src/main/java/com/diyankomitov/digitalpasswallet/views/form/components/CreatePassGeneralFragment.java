package com.diyankomitov.digitalpasswallet.views.form.components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.diyankomitov.digitalpasswallet.R;
import com.diyankomitov.digitalpasswallet.databinding.CreatePassGeneralFragmentBinding;
import com.diyankomitov.digitalpasswallet.viewmodel.FormViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreatePassGeneralFragment extends Fragment {
    
    private static final String TAG = "CreatePassGeneralFragment";
    private FormViewModel formViewModel;
    
    public CreatePassGeneralFragment() {
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
        
        CreatePassGeneralFragmentBinding binding =
                CreatePassGeneralFragmentBinding.inflate(inflater, container, false);
        binding.setFormViewModel(formViewModel);
        binding.setLifecycleOwner(this);
    
        return binding.getRoot();
    }
}
