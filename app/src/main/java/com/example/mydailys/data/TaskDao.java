package com.example.mydailys.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mydailys.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    // CRUD: CREATE READ UPDATE DELETE

    @Insert
    void insertTask(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Query("DELETE FROM task_table")
    void deleteAllTasks();

    @Query("SELECT * FROM task_table")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * FROM task_table WHERE task_table.task_id == :id")
    LiveData<Task> get(long id);
}
