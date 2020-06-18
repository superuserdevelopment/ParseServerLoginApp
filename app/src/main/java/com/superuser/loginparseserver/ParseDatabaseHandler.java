package com.superuser.loginparseserver;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.concurrent.Future;

public class ParseDatabaseHandler extends Application {
    private UserModels user;
    private Intent intent;
    @Override
    public void onCreate() {
        super.onCreate();
        //Creating a link to the database
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.aws_app_id))
                // if defined
                .clientKey(getString(R.string.aws_client_key))
                .server(getString(R.string.aws_server_url))
                .build()
        );
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
    public boolean addNewUser(UserModels userModel, String password){
        ParseUser currentUser = ParseUser.getCurrentUser();
        if(true) {
            ParseUser user1 = new ParseUser();
            user1.setUsername(userModel.username);
            user1.setPassword(password);
            user1.setEmail(userModel.email);
            try {
                user1.signUp();
            }
            catch (Exception e){
                System.out.println("Registration Unsuccessful");
                return false;
            }
        }
        return true;
    }
    public boolean loginUser(UserModels userModel, final String password, Intent intentPass){
        user = new UserModels(userModel.username,password,"");
        intent = intentPass;
        try {
            ParseUser.logIn(userModel.username, password);
        }
        catch (Exception e){
            System.out.println("Login Unsuccessful");
            return false;
        }
        System.out.print("Executed");
        return true;
    }
    public String getCurrentUser(){
        ParseUser user = ParseUser.getCurrentUser();
        return user.getUsername();
    }

    public void Logout(){
        ParseUser user = ParseUser.getCurrentUser();
        user.logOut();
    }
}
