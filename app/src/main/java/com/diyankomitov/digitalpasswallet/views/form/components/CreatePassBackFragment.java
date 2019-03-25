package com.diyankomitov.digitalpasswallet.views.form.components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.diyankomitov.digitalpasswallet.R;
import com.diyankomitov.digitalpasswallet.databinding.CreatePassBackFragmentBinding;
import com.diyankomitov.digitalpasswallet.databinding.CreatePassHeaderFragmentBinding;
import com.diyankomitov.digitalpasswallet.viewmodel.FormViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreatePassBackFragment extends Fragment {
    
    private FormViewModel formViewModel;
    
    public CreatePassBackFragment() {
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
        
        CreatePassBackFragmentBinding binding =
                CreatePassBackFragmentBinding.inflate(inflater, container, false);
        binding.setFormViewModel(formViewModel);
        binding.setLifecycleOwner(this);
        
        return binding.getRoot();
    }
    
    
}
