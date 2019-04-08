package com.example.abbieturner.gdprapplication.UI.Admin.Activitys;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.abbieturner.gdprapplication.R;
import com.example.abbieturner.gdprapplication.UI.BaseActivity;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmployeeDataActivity extends BaseActivity {


    private FirebaseAuth mAuth;

    String emp_name;
    String emp_address;
    String emp_email;
    String emp_ethn;
    String emp_fax;
    String emp_lang;
    String emp_med;
    String emp_phone;
    String emp_wh;
    String emp_wp;
    String emp_pp;
    String emp_id;
    String token;
    boolean inRequestBool;
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
    @BindView(R.id.delete_emp_btn)
    Button delete_emp_btn;
    @BindView(R.id.notification_btn)
    Button notification_btn;
    @BindView(R.id.contact_emp_btn)
    Button contact_emp_btn;
    private DatabaseReference mRootRef;
    private HashMap hashMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_data);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        hashMap = new HashMap();

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
            emp_id = intent.getStringExtra("user_id");
            token = intent.getStringExtra("token");
            inRequestBool = intent.getBooleanExtra("isRequest", false);
        }

        //user_image.setImageBitmap(emp_pp);
        user_name.setText(emp_name);
        user_address.setText(emp_address);
        user_email.setText(emp_email);
        user_ethnicity.setText(emp_ethn);
        user_fax.setText(emp_fax);
        user_lang.setText(String.valueOf(emp_lang));
        user_medicalcond.setText(emp_med);
        user_workhours.setText(emp_wh);
        user_workplace.setText(emp_wp);
        user_number.setText(emp_phone);

        delete_emp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inRequestBool == true) {
                    mRootRef.child("requests").child(emp_id).removeValue();
                    mRootRef.child("users").child(emp_id).removeValue();
                } else if (inRequestBool == false) {
                    mRootRef.child("users").child(emp_id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(getApplicationContext(), AdminHomeActivity.class));
                                finish();
                            }
                        }
                    });
                }
            }
        });


        notification_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hashMap.put("name", emp_name);
                hashMap.put("token", token);
                mRootRef.child("updates").child(emp_id)
                        .updateChildren(hashMap, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                                Toast.makeText(EmployeeDataActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });


        contact_emp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String number = emp_phone;
                final String email = emp_email;
                new MaterialStyledDialog.Builder(EmployeeDataActivity.this)
                        .setHeaderDrawable(R.drawable.emailpic)
                        .setHeaderColor(R.color.defaultTextColor)
                        .setHeaderScaleType(ImageView.ScaleType.FIT_CENTER)
                        .setPositiveText("Email")
                        .setNegativeText("Phone")
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:" + number));
                                startActivity(intent);
                            }
                        })
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                        "mailto", email, null));
                                emailIntent.putExtra(Intent.EXTRA_TEXT, "");
                                startActivity(Intent.createChooser(emailIntent, "Sending email..."));
                            }
                        }).show();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}