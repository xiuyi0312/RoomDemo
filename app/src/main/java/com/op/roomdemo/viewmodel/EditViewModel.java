package com.op.roomdemo.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.op.roomdemo.bean.User;

public class EditViewModel extends ViewModel {
    public final MutableLiveData<String> liveName = new MutableLiveData<>("");
    public final MutableLiveData<String> liveAge = new MutableLiveData<>("18");
    private int id;

    public void setUser(User user) {
        if (user != null) {
            liveName.setValue(user.getName());
            liveAge.setValue(String.valueOf(user.getAge()));
            id = user.getId();
        }
    }

    public User saveUser() {
        User user = new User(liveName.getValue(), Integer.valueOf(liveAge.getValue()));
        user.setId(id);
        return user;
    }
}
