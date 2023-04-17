package com.example.mydailys.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mydailys.model.Task;
import com.example.mydailys.util.TaskRoomDatabase;

import java.util.List;

public class myDailysRepository {
    private final TaskDao taskDao;
    private final LiveData<List<Task>> allTasks;

    public myDailysRepository(Application application) {
        TaskRoomDatabase database = TaskRoomDatabase.getDatabase(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public LiveData<Task> get(long id) {
        return taskDao.get(id);
    }

    public void insertTask(Task task) {
        // insert in background thread instead of UI so it does not lag
        TaskRoomDatabase.databaseWriterExecutor.execute(() -> taskDao.insertTask(task));
    }

    public void updateTask(Task task) {
        // update in background thread instead of UI so it does not lag
        TaskRoomDatabase.databaseWriterExecutor.execute(() -> taskDao.updateTask(task));
    }

    public void deleteTask(Task task) {
        // delete in background thread instead of UI so it does not lag
        TaskRoomDatabase.databaseWriterExecutor.execute(() -> taskDao.deleteTask(task));
    }

    public LiveData<List<Task>> getTasksByUserId(long userId) {
        return taskDao.getTasksByUserId(userId);
    }
}
