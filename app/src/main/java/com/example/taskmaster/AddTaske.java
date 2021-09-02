package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AddTaske extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_taske);
        //get editText by id
        EditText taskTitleInputAddPage=findViewById(R.id.titelinputAddPageInputId);
        EditText taskBodyInputAddPage=findViewById(R.id.bodyIdInputAdd);
        EditText taskStateInputAddPage=findViewById(R.id.stateInsideAddId);

         //get add button by id
        Button addTaskButton=findViewById(R.id.buttonAdd);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"submitted",Toast.LENGTH_LONG).show();
                ////Room///
                //insert function//
                //assign the user input to instance model
                TaskClass task=new TaskClass(taskTitleInputAddPage.getText().toString(),taskBodyInputAddPage.getText().toString(),taskStateInputAddPage.getText().toString());
                //crate database called taskDatabase
                DatabaseTask db = Room.databaseBuilder(getApplicationContext(),
                        DatabaseTask.class, "taskDatabase").allowMainThreadQueries().build();

                TaskDao taskDao = db.taskDao();
                //insert function from dao
                taskDao.insertAll(task);

                //try to print total////
//                TextView taskTotalAddPage=findViewById(R.id.totalTaskAddid);
//                List<TaskClass> getTask = taskDao.getAll();
//                taskTotalAddPage.setText("Total task: "+getTask.size());

            }
        });


    }
}