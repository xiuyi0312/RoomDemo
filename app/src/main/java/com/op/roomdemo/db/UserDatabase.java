package com.op.roomdemo.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.op.roomdemo.bean.User;
import com.op.roomdemo.dao.UserDao;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    private static final String DB_NAME = "UserDatabase.db";
    private static volatile UserDatabase instance;

    public static UserDatabase getInstance(Context context) {
        if(instance == null) {
            synchronized (UserDatabase.class) {
                if(instance == null) {
                    instance = create(context);
                }
            }
        }
        return instance;
    }

    private static UserDatabase create(Context context) {
        return Room.databaseBuilder(context,
                UserDatabase.class, DB_NAME).build();
    }

    public abstract UserDao getUserDao();

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
