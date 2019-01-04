package com.example.abbieturner.gdprapplication.UI.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.abbieturner.gdprapplication.R;
import com.example.abbieturner.gdprapplication.UI.Activity.ContactUsActivity;
import com.example.abbieturner.gdprapplication.UI.Activity.HowDataUsedActivity;
import com.example.abbieturner.gdprapplication.UI.Activity.InfoAboutActivity;
import com.example.abbieturner.gdprapplication.UI.Activity.InfoFAQActivity;
import com.example.abbieturner.gdprapplication.UI.Activity.InfoWhyActivity;

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

    Context context;

    public InfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        unbinder = ButterKnife.bind(this, view);


        about_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InfoAboutActivity.class);
                startActivity(intent);
            }
        });

        why_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InfoWhyActivity.class);
                startActivity(intent);
            }
        });

        how_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HowDataUsedActivity.class);
                startActivity(intent);
            }
        });

        faqs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InfoFAQActivity.class);
                startActivity(intent);
            }
        });

        contactus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ContactUsActivity.class);
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
}