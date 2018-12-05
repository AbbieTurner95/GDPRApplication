package com.example.abbieturner.gdprapplication.UI.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.abbieturner.gdprapplication.R;
import com.example.abbieturner.gdprapplication.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_mail)
    EditText etMail;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.btn_login)
    Button login;
    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLogin();
            }
        });
    }

    private void startLogin() {
        if (Utils.isNetworkAvailable(this)){
            if (Utils.checkError(etMail) &&Utils.checkError(etPass)&&Utils.checkEmail(etMail)){

                progressDialog.setTitle("Start Login");
                progressDialog.setMessage("please wait ...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                mAuth.signInWithEmailAndPassword(etMail.getText().toString(),etPass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                     if (task.isSuccessful()){
                                         progressDialog.dismiss();
                                         startActivity(new Intent(getApplicationContext(),MainActivity.class)
                                         .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                         finish();
                                     } else {
                                         progressDialog.dismiss();
                                         Toasty.error(context, "Incorrect email or password, try again!", Toast.LENGTH_LONG, true).show();
                                     }
                            }
                        });
            }
        } else {
            Toasty.warning(this,"Check internet connection",Toast.LENGTH_SHORT,true).show();
        }
    }
}
