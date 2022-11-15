package com.example.newsify.roomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface Userdao {
    @Insert
    void enteruser(UserEntity userEntity);

    @Query("SELECT EXISTS(SELECT * FROM UserEntity Where mobileno=:mobileno)")
    boolean is_taken(String mobileno);

    @Query("SELECT EXISTS (SELECT * FROM UserEntity WHERE mobileno=:mobileno AND password=:password)")
    boolean login(String mobileno,String password);
}
