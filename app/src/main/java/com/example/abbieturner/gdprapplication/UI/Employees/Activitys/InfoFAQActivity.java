package com.example.abbieturner.gdprapplication.UI.Employees.Activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.abbieturner.gdprapplication.R;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
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

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_faq_activity);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

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

                new LovelyStandardDialog(InfoFAQActivity.this, LovelyStandardDialog.ButtonLayout.VERTICAL)
                        .setTopColorRes(R.color.colorPrimary)
                        .setButtonsColorRes(R.color.colorAccent)
                        .setTitle(R.string.update_string)
                        .setMessage("You can update your data in the profile section of this application. Profile > Update Data")
                        .setNegativeButton(android.R.string.ok, null)
                        .show();
            }
        });


        tech_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new LovelyStandardDialog(InfoFAQActivity.this, LovelyStandardDialog.ButtonLayout.VERTICAL)
                        .setTopColorRes(R.color.colorPrimary)
                        .setButtonsColorRes(R.color.colorAccent)
                        .setMessage("Any issues please contact us. Visit the contact us page.")
                        .setTitle(R.string.tech_string)
                        .setNegativeButton(android.R.string.ok, null)
                        .show();
            }
        });

        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new LovelyStandardDialog(InfoFAQActivity.this, LovelyStandardDialog.ButtonLayout.VERTICAL)
                        .setTopColorRes(R.color.colorPrimary)
                        .setButtonsColorRes(R.color.colorAccent)
                        .setMessage("To request to delete your data please visit profile and select the request to delete data button")
                        .setTitle(R.string.del_req_string)
                        .setNegativeButton(android.R.string.ok, null)
                        .show();
            }
        });


        kept_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new LovelyStandardDialog(InfoFAQActivity.this, LovelyStandardDialog.ButtonLayout.VERTICAL)
                        .setTopColorRes(R.color.colorPrimary)
                        .setButtonsColorRes(R.color.colorAccent)
                        .setMessage("Your data is kept for up to three years after three years it will be deleted. ")
                        .setTitle(R.string.kept_string)
                        .setNegativeButton(android.R.string.ok, null)
                        .show();
            }
        });


        contact_q_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText taskEditText = new EditText(InfoFAQActivity.this);
                AlertDialog dialog = new AlertDialog.Builder(InfoFAQActivity.this)
                        .setTitle("Ask us anything!")
                        .setMessage("We aim to reply within 48 hours.")
                        .setView(taskEditText)
                        .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String text = String.valueOf(taskEditText.getText());
                                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                                emailIntent.setType("text/plain");
                                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Question");
                                emailIntent.putExtra(Intent.EXTRA_TEXT, text);
                                startActivity(Intent.createChooser(emailIntent, "Send email..."));

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
