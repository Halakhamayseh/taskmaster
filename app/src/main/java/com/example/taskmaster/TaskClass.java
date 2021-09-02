package com.example.taskmaster;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TaskClass {
    @PrimaryKey(autoGenerate = true)
    public int taskId;

    @ColumnInfo(name="taskTitle")
    public String taskTitle;

    @ColumnInfo(name="taskBody")
    public String taskBody;

    @ColumnInfo(name="taskState")
    public String taskState;

    public TaskClass(String taskTitle, String taskBody, String taskState) {
        this.taskTitle = taskTitle;
        this.taskBody = taskBody;
        this.taskState = taskState;
    }
}
