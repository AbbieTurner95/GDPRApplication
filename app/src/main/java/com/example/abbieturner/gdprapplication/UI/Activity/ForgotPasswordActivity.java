package com.example.abbieturner.gdprapplication.UI.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abbieturner.gdprapplication.R;
import com.example.abbieturner.gdprapplication.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class ForgotPasswordActivity extends AppCompatActivity {

    @BindView(R.id.et_mail)
    EditText et_mail;
    @BindView(R.id.btn_reset)
    Button btn_reset;

    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startResetPassword();
            }
        });
    }

    private void startResetPassword() {
        if (Utils.checkEmail(et_mail) && Utils.checkError(et_mail)) {

            progressDialog.setTitle("Updating Password");
            progressDialog.setMessage("Please wait ...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.sendPasswordResetEmail(et_mail.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                Toasty.success(getApplicationContext(), "reset mail send successfully "
                                        , Toast.LENGTH_SHORT, true).show();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class)
                                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                            } else {
                                progressDialog.hide();
                                Toasty.error(getApplicationContext(), "unexpected error happened "
                                        , Toast.LENGTH_SHORT, true).show();
                            }
                        }
                    });
        }
    }
}
