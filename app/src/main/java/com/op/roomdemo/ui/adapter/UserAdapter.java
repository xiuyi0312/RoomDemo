package com.op.roomdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.op.roomdemo.R;
import com.op.roomdemo.bean.User;
import com.op.roomdemo.databinding.ItemUserBinding;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserVH> {

    private List<User> userList;
    private Context context;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    public UserAdapter(Context context) {
        this.context = context;
    }

    public void updateUsers(List<User> users) {
        if (userList == null) {
            userList = new ArrayList<>();
        }
        this.userList.clear();
        this.userList.addAll(users == null ? new ArrayList<User>() : users);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserVH(DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.item_user, parent, false).getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull UserVH holder, int position) {
        int pos = holder.getAdapterPosition();
        if(pos != RecyclerView.NO_POSITION) {
            // TODO: 2020/6/23 change to databinding in xml file
            ItemUserBinding binding = DataBindingUtil.getBinding(holder.itemView);
            binding.tvAge.setText(String.valueOf(userList.get(pos).getAge()));
            binding.tvName.setText(userList.get(pos).getName());
            binding.tvNo.setText(String.valueOf(userList.get(pos).getId()));
        }
    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 : userList.size();
    }

    static class UserVH extends RecyclerView.ViewHolder {
        UserVH(@NonNull View itemView) {
            super(itemView);
        }
    }
}
