package com.op.roomdemo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.op.roomdemo.bean.User;
import com.op.roomdemo.repository.UserRepository;

import java.util.List;

/**
 * offer the liveData and operate db through user repository
 */
public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private final LiveData<List<User>> userList;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        userList = userRepository.getUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return userList;
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }

    public void addUser(String name, int age) {
        userRepository.insert(new User(name, age));
    }
}
