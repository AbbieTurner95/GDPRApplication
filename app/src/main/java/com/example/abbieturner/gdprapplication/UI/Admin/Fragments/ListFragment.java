package com.example.abbieturner.gdprapplication.UI.Admin.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abbieturner.gdprapplication.Models.User;
import com.example.abbieturner.gdprapplication.R;
import com.example.abbieturner.gdprapplication.UI.Admin.Activitys.EmployeeDataActivity;
import com.example.abbieturner.gdprapplication.UI.Admin.Adapters.UserAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ListFragment extends Fragment implements UserAdapter.UserClickListener {

    @BindView(R.id.employees_rv)
    RecyclerView employee_rv;

    private DatabaseReference mRootRef;
    private UserAdapter mAdapter;
    Unbinder unbinder;
    Context context;


    public ListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        unbinder = ButterKnife.bind(this, view);

        mRootRef = FirebaseDatabase.getInstance().getReference().child("users");

        LinearLayoutManager linearLayout = new LinearLayoutManager(context);
        employee_rv.setLayoutManager(linearLayout);

        getUsers(mRootRef);

        return view;
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
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

    @Override
    public void onEmployeeItemClick(User user) {
        String name = "emp_name";
        String address = "emp_address";
        String email = "emp_email";
        String ethnicity = "emp_ethn";
        String fax = "emp_fax";
        String lang = "emp_lang";
        String medical = "emp_med";
        String phone = "emp_phone";
        String workHour = "emp_wh";
        String workPlace = "emp_wp";
        String profile = "emp_pp";

        Intent intent = new Intent(context, EmployeeDataActivity.class);
        intent.putExtra(name, user.getName());
        intent.putExtra(address, user.getAddress());
        intent.putExtra(email, user.getEmail());
        intent.putExtra(ethnicity, user.getEthnicity());
        intent.putExtra(fax, user.getFax());
        intent.putExtra(lang, user.getLang());
        intent.putExtra(phone, user.getPhone());
        intent.putExtra(workHour, user.getWorkHour());
        intent.putExtra(workPlace, user.getWorkPlace());
        intent.putExtra(profile, user.getProfile());
        intent.putExtra(medical, user.getMedical());

        startActivity(intent);
    }
}
