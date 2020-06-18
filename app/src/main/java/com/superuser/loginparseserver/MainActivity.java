package com.superuser.loginparseserver;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.parse.Parse;

import com.google.android.material.snackbar.Snackbar;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {
    private EditText usernameET;
    private EditText passwordET;
    private String username;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameET = findViewById(R.id.usernameEditText);
        passwordET = findViewById(R.id.passwordEditText);

    }
    public void onClickLogin(View view){
        //code for logging in
        Intent intent = new Intent(this,LandingPageActivity.class);
        username = usernameET.getText().toString();
        password = passwordET.getText().toString();
        UserModels userModel = new UserModels(username, password, "");
        //Pre-Validation of username and password
        if(username.length() == 0){
            usernameET.setError("This can't be empty");
        }
        if(password.length() < 6){
            passwordET.setError("Enter a password atleast 6 characters long");
        }
        else {
            ParseDatabaseHandler databaseHandler = new ParseDatabaseHandler();
           if(databaseHandler.loginUser(userModel, password, intent)) {
               startActivity(intent);
           }
           else{
               createAlert("Error Logging in","Invalid username/password");
           }
        }

    }
    public void onClickRegister(View view){
        //code for registering new user
        startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
    }
    public void onClickForgotPassword(View view){
        //code for recovering password
    }


    private void createAlert(String title, String content){
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(content);
        alertDialogBuilder.create().show();
    }
}