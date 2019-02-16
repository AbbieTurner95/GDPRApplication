package com.example.abbieturner.gdprapplication.UI.Employees.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.abbieturner.gdprapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class InfoAboutActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_about_activity);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuth.signOut();
    }
}
