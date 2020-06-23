package com.op.roomdemo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.op.roomdemo.R;
import com.op.roomdemo.databinding.DetailFragmentBinding;
import com.op.roomdemo.viewmodel.EditViewModel;
import com.op.roomdemo.viewmodel.UserViewModel;

public class DetailFragment extends Fragment {

    private DetailFragmentBinding binding;

    private UserViewModel userViewModel;
    private EditViewModel editViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        editViewModel = new ViewModelProvider(this).get(EditViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false);
        binding.setVm(editViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 2020/6/23 save the new user and then popup
                userViewModel.addUser(editViewModel.liveName.getValue(), Integer.valueOf(
                        editViewModel.liveAge.getValue()));
                Navigation.findNavController(binding.btnSave).navigateUp();
            }
        });
    }
}
