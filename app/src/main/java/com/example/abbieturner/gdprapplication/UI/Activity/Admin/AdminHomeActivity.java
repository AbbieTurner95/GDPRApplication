package com.example.abbieturner.gdprapplication.UI.Activity.Admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.abbieturner.gdprapplication.Models.User;
import com.example.abbieturner.gdprapplication.R;
import com.example.abbieturner.gdprapplication.UI.Activity.Admin.Adapters.UserAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminHomeActivity extends AppCompatActivity implements UserAdapter.UserClickListener {

    @BindView(R.id.employees_rv)
    RecyclerView employee_rv;

    private DatabaseReference mRootRef;
    private UserAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        ButterKnife.bind(this);

        mRootRef = FirebaseDatabase.getInstance().getReference().child("users");

        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        employee_rv.setLayoutManager(linearLayout);

        getUsers(mRootRef);
    }

    private void getUsers(DatabaseReference mRootRef) {
        FirebaseRecyclerOptions<User> options = new FirebaseRecyclerOptions.Builder<User>()
                .setQuery(mRootRef, User.class)
                .build();

        mAdapter = new UserAdapter(options, this);
        mAdapter.setHasStableIds(true);
        employee_rv.setAdapter(mAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

    @Override
    public void onEmployeeItemClick(User user) {

        Intent intent = new Intent(this, EmployeeDataActivity.class);
        startActivity(intent);
    }
}
