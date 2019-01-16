package com.example.abbieturner.gdprapplication.UI.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.abbieturner.gdprapplication.R;

import butterknife.ButterKnife;

public class EmployeeDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_data);
        ButterKnife.bind(this);
    }
}
