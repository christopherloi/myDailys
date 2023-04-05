package com.example.mydailys.util;

import androidx.room.TypeConverter;

import com.example.mydailys.model.Priority;

import java.util.Date;

public class Converter {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        // if value is null, then return null, else create new date
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        // if date is null, then return null, else get date time
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static String fromPriority(Priority priority) {
        // pass priority convert to String representation of the enum field
        return priority == null ? null : priority.name();
    }

    @TypeConverter
    public static Priority toPriority(String priority) {
        return priority == null ? null : Priority.valueOf(priority);
    }
}
