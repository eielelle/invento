package com.example.invento.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.invento.entities.User;

@Dao
public interface AuthDao {
    @Insert
    long Register(User user);

    @Query("SELECT * FROM user WHERE username = :username LIMIT 1")
    User GetUser(String username);
}
