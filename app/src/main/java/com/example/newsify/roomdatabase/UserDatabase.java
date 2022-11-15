package com.example.newsify.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public static final String dbName = "userdb";

    private static final String DB_NAME = "userdb";
    private static UserDatabase getInstance;

    public static synchronized UserDatabase getDb(Context context) {
        if (getInstance == null) {
            getInstance = Room.databaseBuilder(context, UserDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return getInstance;
    }
    public abstract Userdao userDao();
}
