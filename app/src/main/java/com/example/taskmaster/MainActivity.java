package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.TaskMaster;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
////////////////////////////////
        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            // Add this line, to include the Auth plugin.
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());

            Amplify.Auth.signInWithWebUI(
                    this,
                    result -> Log.i("AuthQuickStart", result.toString()),
                    error -> Log.e("AuthQuickStart", error.toString())
            );


            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }
//manual auth
//        Amplify.Auth.fetchAuthSession(
//                result -> Log.i("AmplifyQuickstart", result.toString()),
//                error -> Log.e("AmplifyQuickstart", error.toString())
//        );

        ///////////////////////////////////////////////// sould be in singup activity
//        AuthSignUpOptions options = AuthSignUpOptions.builder()
//                .userAttribute(AuthUserAttributeKey.email(), "khamaysehhala95@yahoo.com")
//                .build();
//        Amplify.Auth.signUp("rahma", "Sara@95", options,
//                result -> Log.i("AuthQuickStart", "Result: " + result.toString()),
//                error -> Log.e("AuthQuickStart", "Sign up failed", error)
//        );
//////////////////////////////////confirmSignUp/////////////
//        Amplify.Auth.confirmSignUp(
//                "rahma",
//                "131698",
//                result -> Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete"),
//                error -> Log.e("AuthQuickstart", error.toString())
//        );
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

        //SignOut function
        Button signOut=findViewById(R.id.signOutId);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Amplify.Auth.signOut(
                        () -> Log.i("AuthQuickstart", "Signed out successfully"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );
//                Amplify.Auth.signOut(
//                        AuthSignOutOptions.builder().globalSignOut(true).build(),
//                        () -> Log.i("AuthQuickstart", "Signed out globally"),
//                        error -> Log.e("AuthQuickstart", error.toString())
//                );

            }

        });

        Button goToSettingButton=findViewById(R.id.goToSettingId);
        goToSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transferToDetailsTask=new Intent(MainActivity.this,SettingsPage.class);
                startActivity(transferToDetailsTask);
            }
        });


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



//         show from amplify from log lab32

        //get RecyclerView by id
        RecyclerView allTaskRecuclerView=findViewById(R.id.taskRecyclerView);



        ///handler promise to wait and get data from amplify
        Handler handler =new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                allTaskRecuclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
        //make arraylist to store all data
        ArrayList<TaskMaster> alltaskFromamplify=new ArrayList<TaskMaster>();
//        System.out.println("task array"+alltaskFromamplify);


        ////show teamName in home
        SharedPreferences sharedPreferencest= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String teamNameString=sharedPreferencest.getString("teamName","team name");
        ////////////////////showteamName
        TextView teamNameView=findViewById(R.id.viewteamNameId);
        teamNameView.setText(teamNameString);


        //array allTeam to add filter lab33

        ArrayList<TaskMaster>teams=new ArrayList<TaskMaster>();
        //set layout which it is the main
        allTaskRecuclerView.setLayoutManager(new LinearLayoutManager(this));
        //set Adapter and pass to it the object and edit adapter to fit new model
        allTaskRecuclerView.setAdapter(new TaskAdapter(teams));
        /// use amplify list to get data
        Amplify.API.query(
                ModelQuery.list(TaskMaster.class),
                response -> {
                    ///looping through data to render it
                    for (TaskMaster taskMaster : response.getData()) {
//                        Log.i("MyAmplifyApp", taskMaster.getTaskTitle());
//                        Log.i("MyAmplifyApp", taskMaster.getTaskBody());
//                        Log.i("MyAmplifyApp", taskMaster.getTaskState());

                        ///add new data to array
                        alltaskFromamplify.add(taskMaster);

                        for (int i = 0; i <alltaskFromamplify.size() ; i++) {
                            if(alltaskFromamplify.get(i).getTeams().getName().equals(teamNameString)){
                                teams.add(alltaskFromamplify.get(i));
                            }
                        }
                    }
                    //handel promise and wait to get all data
                    handler.sendEmptyMessage(1);
                    Log.i("MyAmplifyApp", "outsoid the loop");
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

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