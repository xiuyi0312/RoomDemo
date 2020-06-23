package com.op.roomdemo.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.op.roomdemo.R;
import com.op.roomdemo.bean.User;
import com.op.roomdemo.databinding.ItemUserBinding;
import com.op.roomdemo.ui.callback.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserVH> {

    private List<User> userList;
    private Context context;
    private OnItemClickListener<User> onItemClickListener;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    public UserAdapter(Context context) {
        this.context = context;
    }

    public void setOnItemClickListener(OnItemClickListener<User> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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
        // TODO: 2020/6/23 the variable is layout and item type and can be extracted to an abstract class
        return new UserVH(DataBindingUtil.inflate(LayoutInflater.from(context),
                R.layout.item_user, parent, false).getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull UserVH holder, int position) {
        final int pos = holder.getAdapterPosition();
        if(pos != RecyclerView.NO_POSITION) {
            ItemUserBinding binding = DataBindingUtil.getBinding(holder.itemView);
            binding.setUser(userList.get(pos));
            if(onItemClickListener != null) {
                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(userList.get(pos));
                    }
                });
                binding.getRoot().setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        onItemClickListener.onItemLongClick(userList.get(pos));
                        return true;
                    }
                });
            }
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
