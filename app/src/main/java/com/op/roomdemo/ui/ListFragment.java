package com.op.roomdemo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.op.roomdemo.R;
import com.op.roomdemo.bean.User;
import com.op.roomdemo.databinding.ListFragmentBinding;
import com.op.roomdemo.ui.adapter.UserAdapter;
import com.op.roomdemo.ui.callback.OnItemClickListener;
import com.op.roomdemo.viewmodel.UserViewModel;
import com.op.roomdemo.widget.AlertDialogFragment;

import java.util.List;

public class ListFragment extends Fragment {
    private final String TAG = ListFragment.class.getSimpleName();
    private ListFragmentBinding binding;
    private UserAdapter userAdapter;
    private UserViewModel userViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // TODO: 2020/6/23 migrate to viewmodel
        binding.tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(binding.tvAdd).navigate(R.id.action_add);
            }
        });
        userAdapter = new UserAdapter(requireActivity());
        userAdapter.setOnItemClickListener(new OnItemClickListener<User>() {
            @Override
            public void onItemClick(User u) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("User", u);
                Navigation.findNavController(binding.rvUser).navigate(R.id.action_edit, bundle);
            }

            @Override
            public void onItemLongClick(User u) {
                showDeleteDialog(u);
            }
        });
        binding.rvUser.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.rvUser.setItemAnimator(new DefaultItemAnimator());
        binding.rvUser.setAdapter(userAdapter);

        userViewModel.getAllUsers().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userAdapter.updateUsers(users);
            }
        });

    }

    private void showDeleteDialog(final User user) {
        AlertDialogFragment fragment = new AlertDialogFragment.Builder()
                .setCancelable(true)
                .setContent("是否删除该联系人")
                .setPositiveClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userViewModel.deleteUser(user);
                    }
                })
                .setTitle("请确认")
                .setPositiveText("确定")
                .build();
        fragment.show(getChildFragmentManager(), "delete_fragment");
    }
}
