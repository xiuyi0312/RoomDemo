package com.op.roomdemo.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.op.roomdemo.bean.User;
import com.op.roomdemo.dao.UserDao;
import com.op.roomdemo.db.UserDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * operation db and offer related methods to viewModel
 */
public class UserRepository {
    private LiveData<List<User>> users;

    public LiveData<List<User>> getUsers() {
        return users;
    }

    private UserDao userDao;

    private ExecutorService dbOperationExecutor = Executors.newFixedThreadPool(2);

    public UserRepository(Context context) {
        userDao = UserDatabase.getInstance(context).getUserDao();
        users = userDao.getAllUsers();
    }

    public void insert(final User... users) {
        // TODO: 2020/6/23 operate db asynchronously
        dbOperationExecutor.submit(new Runnable() {
            @Override
            public void run() {
                userDao.insert(users);
            }
        });
    }

    public void delete(User... users) {

    }

    public void update(User... users) {

    }
}
