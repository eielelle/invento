package com.example.invento.utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.invento.dao.AuthDao;
import com.example.invento.entities.User;

@Database(entities = {User.class}, version = 2)
public abstract class DB extends RoomDatabase {
    private static DB db_instance;

    public abstract AuthDao authDao();

    public static DB getInstance(Context context) {
        if (db_instance == null) {
            db_instance = Room.databaseBuilder(context.getApplicationContext(), DB.class, "invento_db").fallbackToDestructiveMigration().build();
        }

        return db_instance;
    }
}
