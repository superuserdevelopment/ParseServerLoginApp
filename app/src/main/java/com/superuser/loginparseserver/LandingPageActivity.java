package com.superuser.loginparseserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LandingPageActivity extends AppCompatActivity {
    TextView usernameTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        usernameTV = findViewById(R.id.usernameTextView);
        ParseDatabaseHandler databaseHandler = new ParseDatabaseHandler();
        usernameTV.setText(databaseHandler.getCurrentUser());
        //RegistrationActivity.getInstance().finish();
    }

    public void onLogout(View view){
        ParseDatabaseHandler databaseHandler = new ParseDatabaseHandler();
        databaseHandler.Logout();
        finish();
    }
}