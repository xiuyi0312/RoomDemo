package com.op.roomdemo.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.op.roomdemo.bean.User;

import java.util.List;

/**
 * pretty easy way to combine db with LiveData
 */
@Dao
public interface UserDao {
    @Query("select * from user")
    LiveData<List<User>> getAllUsers();

    @Query("select * from user where age=:age")
    LiveData<List<User>> getUsersUnderAge(int age);

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User... users);
}
