package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class TaskDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
//        Intent intent=getIntent();
//        String taskTitle=intent.getExtras().getString("taskTitle");
//        TextView taskTitleView =findViewById(R.id.taskTitleId);
//        taskTitleView.setText(taskTitle);
        Intent intent=getIntent();
        //get keys
        String taskTitle=intent.getExtras().getString("taskNameClickListener");
        String taskBody=intent.getExtras().getString("taskBodyClickListener");
        String taskState=intent.getExtras().getString("taskStateClickListener");

        // get taskTitleView by id
        TextView taskTitleView =findViewById(R.id.taskTitleId);
        TextView taskBodyView =findViewById(R.id.taskBodyDetailsPageId);
        TextView taskStateView =findViewById(R.id.taskstatDetailsPageId);
        taskTitleView.setText("taskTitle:  "+taskTitle+" taskBody:  "+taskBody+"   taskState:  "+taskState);
        //set text for each view
        taskTitleView.setText("task title:  "+taskTitle);
        taskBodyView.setText("task body:  "+taskBody);
        taskStateView.setText("task state:  "+taskState);

        // show from database this way did not work with me
//        TaskClass taskClass;
//        DatabaseTask db =  Room.databaseBuilder(getApplicationContext(), DatabaseTask.class, "taskDatabase").allowMainThreadQueries()
//                .build();
        //get doa function
//        TaskDao taskDao = db.taskDao();
        //store the all data from database in array
//        taskDao.loadAllByIds(taskClass.taskId)

    }
}