package com.example.abbieturner.gdprapplication.UI.Admin.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abbieturner.gdprapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class RequestsFragment extends Fragment {

    private FirebaseAuth mAuth;

    public RequestsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_requests, container, false);

        mAuth = FirebaseAuth.getInstance();
        return view;
    }


    @Override
    public void onPause() {
        super.onPause();
        mAuth.signOut();
    }
}
