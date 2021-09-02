package com.example.taskmaster;

import androidx.room.RoomDatabase;
import androidx.room.Database;

@Database(entities = {TaskClass.class},version=1)
public abstract class DatabaseTask extends RoomDatabase {
    public abstract TaskDao taskDao();
}
