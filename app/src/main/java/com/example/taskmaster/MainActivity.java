package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
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

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointManager;
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
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private static PinpointManager pinpointManager;

    public static PinpointManager getPinpointManager(final Context applicationContext) {
        if (pinpointManager == null) {
            final AWSConfiguration awsConfig = new AWSConfiguration(applicationContext);
            AWSMobileClient.getInstance().initialize(applicationContext, awsConfig, new Callback<UserStateDetails>() {
                @Override
                public void onResult(UserStateDetails userStateDetails) {
                    Log.i("INIT", userStateDetails.getUserState().toString());
                }

                @Override
                public void onError(Exception e) {
                    Log.e("INIT", "Initialization error.", e);
                }
            });

            PinpointConfiguration pinpointConfig = new PinpointConfiguration(
                    applicationContext,
                    AWSMobileClient.getInstance(),
                    awsConfig);

            pinpointManager = new PinpointManager(pinpointConfig);

            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull Task<String> task) {
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                                return;
                            }
                            final String token = task.getResult();
                            Log.d(TAG, "Registering push notifications token: " + token);
                            pinpointManager.getNotificationClient().registerDeviceToken(token);
                        }
                    });
        }
        return pinpointManager;
    }

    public void loginFunction(){
        Amplify.Auth.signInWithWebUI(
                this,
                result -> Log.i("AuthQuickStart", result.toString()),
                error -> Log.e("AuthQuickStart", error.toString())
        );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


////////////////////////////////
        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());
            getPinpointManager(getApplicationContext());
//            Amplify.Auth.signInWithWebUI(
//                    this,
//                    result -> Log.i("AuthQuickStart", result.toString()),
//                    error -> Log.e("AuthQuickStart", error.toString())
//            );
            loginFunction();

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

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
                        () -> {
                            loginFunction();
                            Log.i("AuthQuickstart", "Signed out successfully");
                        },
                        error -> Log.e("AuthQuickstart", error.toString())
                );
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