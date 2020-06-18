package com.superuser.loginparseserver;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegistrationActivity extends AppCompatActivity {
    private EditText usernameET;
    private EditText emailET;
    private EditText passwordET;
    private EditText confirmPasswordET;
    private String username;
    private String password;
    private String email;
    private String confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        usernameET = findViewById(R.id.usernameEditText);
        emailET = findViewById(R.id.emailEditText);
        passwordET = findViewById(R.id.passwordEditText);
        confirmPasswordET = findViewById(R.id.confirmPasswordEditText);
    }



    public void onRegistrationClick(View view){
        Intent intent = new Intent(this,LandingPageActivity.class);
        username = usernameET.getText().toString();
        email = emailET.getText().toString();
        password = passwordET.getText().toString();
        confirmPassword = confirmPasswordET.getText().toString();
        UserModels userModel = new UserModels(username, password, email);
        if(validateForm()){
            ParseDatabaseHandler databaseHandler = new ParseDatabaseHandler();
            if(databaseHandler.addNewUser(userModel, password)){
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                finish();
            }
            else {
                createAlert("Error","The user already exists.");
            }
        }
    }
    private boolean validateForm(){
        if(username.length() == 0){
            usernameET.setError("This can't be empty");
            return false;
        }
        if(!(email.contains("@") && email.contains("."))){
            emailET.setError("Invalid Email Address");
            return false;
        }
        if(password.length() < 6){
            passwordET.setError("Enter a password atleast 6 characters long");
            return false;
        }
        if(confirmPassword.length() < 6){
            confirmPasswordET.setError("Enter a password atleast 6 characters long");
            return false;
        }
        if(password.equals(confirmPassword) == false){
            confirmPasswordET.setError("Both Passwords do not match "+password+" "+confirmPassword);
            return false;
        }
        return true;
    }
    private void createAlert(String title, String content){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(content);
        alertDialogBuilder.create().show();
    }


}