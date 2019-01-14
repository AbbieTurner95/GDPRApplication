package com.example.abbieturner.gdprapplication.UI.Activity.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.abbieturner.gdprapplication.Models.User;
import com.example.abbieturner.gdprapplication.R;
import com.example.abbieturner.gdprapplication.UI.Activity.Admin.Adapters.EmployeeAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminHomeActivity extends AppCompatActivity implements EmployeeAdapter.UserClickListener {

    @BindView(R.id.employees_rv)
    RecyclerView employee_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        EmployeeAdapter employeeAdapter = new  EmployeeAdapter(this, this);

        employee_rv.setLayoutManager(linearLayout);
        employee_rv.setAdapter(employeeAdapter);

    }

    @Override
    public void onEmployeeItemClick(User user) {

        Intent intent = new Intent(this, EmployeeDataActivity.class);
        startActivity(intent);
    }
}
