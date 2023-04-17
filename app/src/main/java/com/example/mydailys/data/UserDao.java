package com.example.mydailys.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mydailys.login.User;

@Dao
public interface UserDao {

    // CRUD: CREATE READ UPDATE DELETE

    @Insert
    void insertUser(User user);

    @Query("DELETE FROM user_table")
    void deleteAllUsers();
}
