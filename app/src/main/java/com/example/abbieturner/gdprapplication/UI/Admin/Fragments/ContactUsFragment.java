package com.example.abbieturner.gdprapplication.UI.Admin.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.abbieturner.gdprapplication.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ContactUsFragment extends Fragment {

    Unbinder unbinder;

    public ContactUsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
}
