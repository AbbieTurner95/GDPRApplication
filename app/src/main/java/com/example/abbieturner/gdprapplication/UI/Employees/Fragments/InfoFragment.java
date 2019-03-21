package com.example.abbieturner.gdprapplication.UI.Employees.Fragments;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.abbieturner.gdprapplication.R;
import com.example.abbieturner.gdprapplication.UI.Employees.Activitys.ContactUsActivity;
import com.example.abbieturner.gdprapplication.UI.Employees.Activitys.HowDataUsedActivity;
import com.example.abbieturner.gdprapplication.UI.Employees.Activitys.InfoAboutActivity;
import com.example.abbieturner.gdprapplication.UI.Employees.Activitys.InfoFAQActivity;
import com.example.abbieturner.gdprapplication.UI.Employees.Activitys.InfoWhyActivity;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class InfoFragment extends Fragment {

    Unbinder unbinder;

    @BindView(R.id.about_btn)
    Button about_btn;
    @BindView(R.id.contactus_btn)
    Button contactus_btn;
    @BindView(R.id.faqs_btn)
    Button faqs_btn;
    @BindView(R.id.why_btn)
    Button why_btn;
    @BindView(R.id.how_data_used_btn)
    Button how_btn;

    private FirebaseAuth mAuth;

    public InfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        unbinder = ButterKnife.bind(this, view);
        mAuth = FirebaseAuth.getInstance();


        about_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), InfoAboutActivity.class);
                startActivity(intent);
            }
        });

        why_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), InfoWhyActivity.class);
                startActivity(intent);
            }
        });

        how_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HowDataUsedActivity.class);
                startActivity(intent);
            }
        });

        faqs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), InfoFAQActivity.class);
                startActivity(intent);
            }
        });

        contactus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ContactUsActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}