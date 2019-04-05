package com.room.model;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface MyDao {

    @Insert
    void addUser(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT id FROM Users WHERE user_name = :name AND user_password = :password")
    int getId(String name, String password);


    @Query("SELECT * FROM Users WHERE id = :id")
    User getUser(int id);


}
