package com.example.taskmaster;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskMaster;
import com.amplifyframework.datastore.generated.model.Team;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class AddTaske extends AppCompatActivity {

String img="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_taske);


        //get add RadioButton  by id lab33



        RadioButton RadioButtonFirstTeam = findViewById(R.id.firstTeamIdAdd);
        RadioButton RadioButtonSecondTeam = findViewById(R.id.towTeamIdAdd);
        RadioButton RadioButtonThirdTeam = findViewById(R.id.threeTeamIdAdd);

        /// lab33 1. to get  team name data from data base

        List<Team> allTeam = new ArrayList<>();

        Amplify.API.query(
                ModelQuery.list(Team.class),
                response -> {
                    for (Team team : response.getData()) {
                        Log.i("MyAmplifyApp", team.getName());

                        allTeam.add(team);


                        System.out.println("here is the data all team " + allTeam);
                    }
                    Log.i("MyAmplifyApp", "outside the loop");
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );


        //get editText by id
        EditText taskTitleInputAddPage = findViewById(R.id.titelinputAddPageInputId);
        EditText taskBodyInputAddPage = findViewById(R.id.bodyIdInputAdd);
        EditText taskStateInputAddPage = findViewById(R.id.stateInsideAddId);


        ////////////////////////////first try to get team name lab33
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
        Button addTaskButton = findViewById(R.id.buttonAdd);

        Button attacedFile = findViewById(R.id.attacedFilId);
        attacedFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFileFromDevice();
            }
        });
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "submitted", Toast.LENGTH_LONG).show();

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

                //lab33


                String teamName = "";
                if (RadioButtonFirstTeam.isChecked()) {
//                            teamName = "First team";
                    teamName=RadioButtonFirstTeam.getText().toString();

                } else if (RadioButtonSecondTeam.isChecked()) {
//                            teamName = "Tow team";
                    teamName=RadioButtonSecondTeam.getText().toString();

//
                } else if (RadioButtonThirdTeam.isChecked()) {
//                            teamName = "Three team";
                    teamName=RadioButtonThirdTeam.getText().toString();

//
                }
                Team selectedTeam=null;
                for (Team teams : allTeam) {
                    if (teams.getName().equals(teamName)){
                        selectedTeam = teams;
                    }

                }

                //lab32
                TaskMaster taskMaster = TaskMaster.builder()
                        .taskTitle(taskTitleInputAddPage.getText().toString())
                        .taskBody(taskBodyInputAddPage.getText().toString())
                        .teams(selectedTeam)
                        .img(img)
                        .taskState(taskStateInputAddPage.getText().toString())
                        .build();

                Amplify.API.mutate(
                        ModelMutation.create(taskMaster),
                        response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                        error -> Log.e("MyAmplifyApp", "Create failed", error)
                );

                //lab33


                Intent transferToMainTask = new Intent(AddTaske.this, MainActivity.class);
                startActivity(transferToMainTask);

            }

        });


    }

    private void getFileFromDevice() {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");
        chooseFile = Intent.createChooser(chooseFile, "Choose a File");
        startActivityForResult(chooseFile, 1234);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File uploadFile = new File(getApplicationContext().getFilesDir(), "uploadFileCopied");
        try {
            InputStream exampleInputStream = getContentResolver().openInputStream(Uri.fromFile(uploadFile));
             img=data.getData().getPath();
            System.out.println(img);
            Amplify.Storage.uploadInputStream(
                    "img"+img,
                    exampleInputStream,
                    result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                    storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
            );
        }  catch (FileNotFoundException error) {
            Log.e("MyAmplifyApp", "Could not find file to open for input stream.", error);
        }
//        try {
//            InputStream exampleInputStream = getContentResolver().openInputStream(data.getData());
//            OutputStream outputStream= new FileOutputStream(uploadFile);
//            byte [] buff=new byte[1024];
//            int length;
//            while((length=exampleInputStream.read(buff))>0){
//                outputStream.write(buff,0,length);
//            }
//            exampleInputStream.close();
//            outputStream.close();
//            Amplify.Storage.uploadFile(
//                    "attach",
//                    uploadFile,
//                    result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
//                    storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
//            );
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}