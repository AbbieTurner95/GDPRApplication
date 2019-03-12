package com.example.abbieturner.gdprapplication.UI.Employees.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.abbieturner.gdprapplication.R;
import com.example.abbieturner.gdprapplication.UI.BaseActivity;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.ButterKnife;

public class InfoWhyActivity extends BaseActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_why_activity);
        ButterKnife.bind(this);

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
