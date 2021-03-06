package com.example.abbieturner.gdprapplication.UI.Admin.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abbieturner.gdprapplication.Models.User;
import com.example.abbieturner.gdprapplication.R;
import com.example.abbieturner.gdprapplication.UI.Admin.Activitys.EmployeeDataActivity;
import com.example.abbieturner.gdprapplication.UI.Admin.Adapters.UserAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RequestsFragment extends Fragment implements UserAdapter.UserClickListener {

    private FirebaseAuth mAuth;
    private DatabaseReference mRootRef;
    private UserAdapter mAdapter;
    Unbinder unbinder;
    public String token = "token";
    Context context;
    @BindView(R.id.deleteUsersList)
    RecyclerView recyclerView;


    public RequestsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_requests, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        unbinder = ButterKnife.bind(this, view);

        mAuth = FirebaseAuth.getInstance();

        mRootRef = FirebaseDatabase.getInstance().getReference().child("requests");

        getUserRequests(mRootRef);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        return view;
    }

    private void getUserRequests(DatabaseReference mRootRef) {
        FirebaseRecyclerOptions<User> options = new FirebaseRecyclerOptions.Builder<User>()
                .setQuery(mRootRef, User.class)
                .build();

        mAdapter = new UserAdapter(options, this);
        recyclerView.setAdapter(mAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        mAdapter.startListening();
    }

    @Override
    public void onPause() {
        super.onPause();
        mAdapter.stopListening();
    }

    @Override
    public void onStop() {
        super.onStop();
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
        String userId = "id";
        String profile = "emp_pp";
        String admin = "admin";
        String inRequestBool = "inRequest";

        Intent intent = new Intent(getActivity(), EmployeeDataActivity.class);
        intent.putExtra(token, user.getToken_id());
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
        Log.e("USER_ID", user.getID() + "");
        intent.putExtra(userId, user.getID());
        intent.putExtra(admin, user.isAdmin());

        intent.putExtra(inRequestBool, true);
        startActivity(intent);
    }
}