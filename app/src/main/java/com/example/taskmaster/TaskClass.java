package com.example.taskmaster;

public class TaskClass {
    public String taskTitle;
    public String taskBody;
    public String taskState;

    public TaskClass(String taskTitle, String taskBody, String taskState) {
        this.taskTitle = taskTitle;
        this.taskBody = taskBody;
        this.taskState = taskState;
    }
}
