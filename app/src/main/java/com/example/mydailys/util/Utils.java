package com.example.mydailys.util;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.mydailys.model.Priority;
import com.example.mydailys.model.Task;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyPattern("EEE, MMM d");
        return simpleDateFormat.format(date);
    }

    public static void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static int priorityColor(Task task) {
        int color = 0;
        if (task.getPriority() == Priority.HIGH) {
            color = Color.argb(200, 200, 0, 0);
        } else if (task.getPriority() == Priority.MEDIUM) {
            color = Color.argb(200, 200, 200, 0);
        } else if (task.getPriority() == Priority.LOW) {
            color = Color.argb(200, 0, 200, 0);
        }
        return color;
    }
}
