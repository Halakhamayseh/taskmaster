package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskMaster;
import com.amplifyframework.datastore.generated.model.Team;

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

        //get add RadioButton  by id lab33

//        RadioButton RadioButtonFirstTeam=findViewById(R.id.firstTeamIdAdd);
//        RadioButton RadioButtonSecondTeam=findViewById(R.id.towTeamIdAdd);
//        RadioButton RadioButtonThirdTeam=findViewById(R.id.threeTeamIdAdd);
        ////////////////////////////first try to get team name
        //                Team team = Team.builder()
//                        .name""
//                        .build();
//
//                Amplify.API.mutate(
//                        ModelMutation.create(taskMaster),
//                        response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
//                        error -> Log.e("MyAmplifyApp", "Create failed", error)
//                );
        ///////////////////////
         //get add button by id
        Button addTaskButton=findViewById(R.id.buttonAdd);
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"submitted",Toast.LENGTH_LONG).show();

                ////Room///
                //insert function//
                //assign the user input to instance model
//                TaskClass task=new TaskClass(taskTitleInputAddPage.getText().toString(),taskBodyInputAddPage.getText().toString(),taskStateInputAddPage.getText().toString());
                //crate database called taskDatabase
//                DatabaseTask db = Room.databaseBuilder(getApplicationContext(),
//                        DatabaseTask.class, "taskDatabase").allowMainThreadQueries().build();
//
//                TaskDao taskDao = db.taskDao();
                //insert function from dao
//                taskDao.insertAll(task);

                //try to print total////
//                TextView taskTotalAddPage=findViewById(R.id.totalTaskAddid);
//                List<TaskClass> getTask = taskDao.getAll();
//                taskTotalAddPage.setText("Total task: "+getTask.size());

                //lab32
//                TaskMaster taskMaster = TaskMaster.builder()
//                        .taskTitle(taskTitleInputAddPage.getText().toString())
//                        .taskBody(taskBodyInputAddPage.getText().toString())
//                        .teams()
//                        .taskState(taskStateInputAddPage.getText().toString())
//                        .build();
//
//                Amplify.API.mutate(
//                        ModelMutation.create(taskMaster),
//                        response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
//                        error -> Log.e("MyAmplifyApp", "Create failed", error)
//                );

                Intent transferToMainTask=new Intent(AddTaske.this,MainActivity.class);
                startActivity(transferToMainTask);

            }

        });


    }
}