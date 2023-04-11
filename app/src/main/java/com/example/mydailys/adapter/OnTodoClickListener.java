package com.example.mydailys.adapter;

import com.example.mydailys.model.Task;

public interface OnTodoClickListener {
    void onTodoClick(Task task);
    void onTodoRadioButtonClick(Task task);
}