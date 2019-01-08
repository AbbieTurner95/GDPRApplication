package com.example.abbieturner.gdprapplication.UI.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.abbieturner.gdprapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoWhyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_why_activity);
        ButterKnife.bind(this);



    }
}
