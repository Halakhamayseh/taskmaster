package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addTaskButton=findViewById(R.id.buttonADDTASK);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"from onCreate",Toast.LENGTH_LONG).show();
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
    }
//
    @Override
    protected void onStart() {
        super.onStart();
//        setContentView(R.layout.activity_main);
//        Toast.makeText(getApplicationContext(),"from onStart",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}