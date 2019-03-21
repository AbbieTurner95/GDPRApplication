package com.example.abbieturner.gdprapplication.UI.Employees.Activitys;

import android.os.Bundle;

import com.example.abbieturner.gdprapplication.R;
import com.example.abbieturner.gdprapplication.UI.BaseActivity;
import com.google.firebase.auth.FirebaseAuth;

public class InfoAboutActivity extends BaseActivity {

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
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
