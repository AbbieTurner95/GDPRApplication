package com.example.abbieturner.gdprapplication.UI.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.abbieturner.gdprapplication.R;
import com.yarolegovich.lovelydialog.LovelyStandardDialog;

import butterknife.BindView;
import butterknife.ButterKnife;


public class InfoFAQActivity extends AppCompatActivity {

    @BindView(R.id.reqdata_btn)
    Button reqdata_btn;
    @BindView(R.id.update_btn)
    Button update_btn;
    @BindView(R.id.tech_btn)
    Button tech_btn;
    @BindView(R.id.del_btn)
    Button del_btn;
    @BindView(R.id.kept_btn)
    Button kept_btn;

    @BindView(R.id.contact_q_btn)
    Button contact_q_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_faq_activity);
        ButterKnife.bind(this);

        reqdata_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new LovelyStandardDialog(InfoFAQActivity.this, LovelyStandardDialog.ButtonLayout.VERTICAL)
                        .setTopColorRes(R.color.colorPrimary)
                        .setButtonsColorRes(R.color.colorAccent)
                        .setTitle(R.string.request_data)
                        .setMessage("Go back to the homepage and select the 'request data' button, if having any technical problems please contact us")
                        .setNegativeButton(android.R.string.ok, null)
                        .show();

            }
        });

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        tech_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        kept_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        contact_q_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
