package com.example.mydailys.adapter;

import com.example.mydailys.model.Task;

public interface OnTodoClickListener {
    void onTodoClick(int adapterPosition, Task task);
}