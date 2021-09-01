package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addTaskButton=findViewById(R.id.buttonADDTASK);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"from onCreate",Toast.LENGTH_LONG).show();
                Intent transferToAddTask=new Intent(MainActivity.this,AddTaske.class);
                startActivity(transferToAddTask);
            }
        });

        Button allTaskButton=findViewById(R.id.buttonAllTaskes);
        allTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transferToAddTask=new Intent(MainActivity.this,AllTaskes.class);
                startActivity(transferToAddTask);
            }
        });

//        Button labButton=findViewById(R.id.finishLab27ButtonId);
//        labButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent transferToDetailsTask=new Intent(MainActivity.this,TaskDetails.class);
//                String taskValue=labButton.getText().toString();
//                transferToDetailsTask.putExtra("taskTitle",taskValue);
//                startActivity(transferToDetailsTask);
//
//
//            }
//        });
//
//        Button codeButton=findViewById(R.id.finishCode27ButtonId);
//        codeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent transferToDetailsTask=new Intent(MainActivity.this,TaskDetails.class);
//                String taskValue=codeButton.getText().toString();
//                transferToDetailsTask.putExtra("taskTitle",taskValue);
//                startActivity(transferToDetailsTask);
//
//
//            }
//        });
//
//        Button readButton=findViewById(R.id.readButton);
//        readButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent transferToDetailsTask=new Intent(MainActivity.this,TaskDetails.class);
//                String taskValue=readButton.getText().toString();
//                transferToDetailsTask.putExtra("taskTitle",taskValue);
//                startActivity(transferToDetailsTask);
//
//
//            }
//        });
        Button goToSettingButton=findViewById(R.id.goToSettingId);
        goToSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transferToDetailsTask=new Intent(MainActivity.this,SettingsPage.class);
                startActivity(transferToDetailsTask);
            }
        });
        ///show hardCopy//lab27//
//        ArrayList<TaskClass> allTasks=new ArrayList<TaskClass>();
//        allTasks.add(new TaskClass("lab assessment","solving the lab at the time","assigned"));
//        allTasks.add(new TaskClass("code assessment","solving the code at the time","in progress"));
//        allTasks.add(new TaskClass("read assessment","solving the read at the time","complete"));
        //get
//        RecyclerView allTaskRecuclerView=findViewById(R.id.taskRecyclerView);
//        allTaskRecuclerView.setLayoutManager(new LinearLayoutManager(this));
//        allTaskRecuclerView.setAdapter(new TaskAdapter(allTasks));

        //show the data from dataBase lab29//

//        DatabaseTask db = Room.databaseBuilder(getApplicationContext(),
////                DatabaseTask.class, "database-name").build();
        DatabaseTask db =  Room.databaseBuilder(getApplicationContext(), DatabaseTask.class, "taskDatabase").allowMainThreadQueries()
                .build();
        TaskDao taskDao = db.taskDao();
        List<TaskClass> task = taskDao.getAll();
        RecyclerView allTaskRecuclerView=findViewById(R.id.taskRecyclerView);
        allTaskRecuclerView.setLayoutManager(new LinearLayoutManager(this));
        allTaskRecuclerView.setAdapter(new TaskAdapter(task));

    }
//
    @Override
    protected void onStart() {
        super.onStart();

        Toast.makeText(getApplicationContext(),"from onStart",Toast.LENGTH_LONG).show();

        //Show user name in Home page
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userNameString=sharedPreferences.getString("userName","user");
        TextView userNameView=findViewById(R.id.viewUserNameId);
        userNameView.setText(userNameString);




    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(),"from onStop",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"from onDestroy",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(),"from onPause",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(),"from onResume",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(),"from onRestart",Toast.LENGTH_LONG).show();

    }
}