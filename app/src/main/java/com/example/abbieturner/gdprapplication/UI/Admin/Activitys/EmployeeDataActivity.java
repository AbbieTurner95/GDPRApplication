package com.example.abbieturner.gdprapplication.UI.Admin.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abbieturner.gdprapplication.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmployeeDataActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    String emp_name, emp_address, emp_email, emp_ethn, emp_fax, emp_lang, emp_med, emp_phone, emp_wh, emp_wp, emp_pp;

    @BindView(R.id.user_image)
    ImageView user_image;
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.user_address)
    TextView user_address;
    @BindView(R.id.user_fax)
    TextView user_fax;
    @BindView(R.id.user_number)
    TextView user_number;
    @BindView(R.id.user_email)
    TextView user_email;
    @BindView(R.id.user_lang)
    TextView user_lang;
    @BindView(R.id.user_workplace)
    TextView user_workplace;
    @BindView(R.id.user_workhours)
    TextView user_workhours;
    @BindView(R.id.user_medicalcond)
    TextView user_medicalcond;
    @BindView(R.id.user_ethnicity)
    TextView user_ethnicity;


    @BindView(R.id.delete_data_btn)
    Button delete_data_btn;
    @BindView(R.id.contact_emp_btn)
    Button contact_emp_btn;
    @BindView(R.id.notification_btn)
    Button notifiation_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_data);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();

        if (intent != null) {
            emp_name = intent.getStringExtra("emp_name");
            emp_address = intent.getStringExtra("emp_address");
            emp_email = intent.getStringExtra("emp_email");
            emp_ethn = intent.getStringExtra("emp_ethn");
            emp_fax = intent.getStringExtra("emp_fax");
            emp_lang = intent.getStringExtra("emp_lang");
            emp_med = intent.getStringExtra("emp_med");
            emp_phone = intent.getStringExtra("emp_phone");
            emp_wh = intent.getStringExtra("emp_wh");
            emp_wp = intent.getStringExtra("emp_wp");
            emp_pp = intent.getStringExtra("emp_pp");
        }

        //user_image.setImageBitmap(emp_pp);
        user_name.setText(emp_name);
        user_address.setText(emp_address);
        user_email.setText(emp_email);
        user_ethnicity.setText(emp_ethn);
        user_fax.setText(emp_fax);
        user_lang.setText(emp_lang);
        user_medicalcond.setText(emp_med);
        user_workhours.setText(emp_wh);
        user_workplace.setText(emp_wp);
        user_number.setText(emp_phone);


    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuth.signOut();
    }
}

