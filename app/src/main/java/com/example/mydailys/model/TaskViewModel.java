package com.example.mydailys.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mydailys.data.myDailysRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    public static myDailysRepository repository;
    public final LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new myDailysRepository(application);
        allTasks = repository.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }
    public LiveData<Task> get(long id) {
        return repository.get(id);
    }
    public static void insertTask(Task task) {
        repository.insertTask(task);
    }
    public static void updateTask(Task task) {
        repository.updateTask(task);
    }
    public static void deleteTask(Task task) {
        repository.deleteTask(task);
    }

    public LiveData<List<Task>> getTasksByUserId(long userId) {
        return repository.getTasksByUserId(userId);
    }

}
