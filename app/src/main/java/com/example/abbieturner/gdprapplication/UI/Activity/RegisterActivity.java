package com.example.abbieturner.gdprapplication.UI.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_mail)
    EditText etEmail;
    @BindView(R.id.et_fax)
    EditText etFax;
    @BindView(R.id.et_lang)
    EditText etLang;
    @BindView(R.id.et_ethnicity)
    EditText etEthn;
    @BindView(R.id.et_medical)
    EditText etMedical;
    @BindView(R.id.et_work_hour)
    EditText etWorkHour;
    @BindView(R.id.et_work_place)
    EditText etWorkPlace;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.btn_register)
    Button register;

    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private DatabaseReference mRootRef;

    private HashMap hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        progressDialog = new ProgressDialog(this);
        hashMap = new HashMap();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegister();
            }
        });
    }

    private void startRegister() {
        if (Utils.isNetworkAvailable(this)){
            if (Utils.checkError(etName) &&Utils.checkError(etAddress) &&Utils.checkError(etPhone)
                    &&Utils.checkError(etEmail) &&Utils.checkError(etFax)&&Utils.checkError(etLang)
                    &&Utils.checkError(etEthn)&&Utils.checkError(etMedical)&&Utils.checkError(etWorkHour)
                    &&Utils.checkError(etWorkPlace)&&Utils.checkPhoneSize(etPhone)&&Utils.checkEmail(etEmail)){

                progressDialog.setTitle("Start Registering");
                progressDialog.setMessage("Please wait ...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();


                mAuth.createUserWithEmailAndPassword(etEmail.getText().toString(),
                        etPass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    uploadUserDataToFirebase();
                                }
                            }
                        });
            }
        } else {
            Toasty.warning(this,"Check Internet Connection",Toast.LENGTH_SHORT,true).show();
        }
    }

    private void uploadUserDataToFirebase() {
        hashMap.put("name",etName.getText().toString());
        hashMap.put("email",etEmail.getText().toString());
        hashMap.put("address",etAddress.getText().toString());
        hashMap.put("phone",etPhone.getText().toString());
        hashMap.put("fax",etFax.getText().toString());
        hashMap.put("lang",etLang.getText().toString());
        hashMap.put("medical",etMedical.getText().toString());
        hashMap.put("ethnicity",etEthn.getText().toString());
        hashMap.put("workHour",etWorkHour.getText().toString());
        hashMap.put("workPlace",etWorkPlace.getText().toString());
        hashMap.put("admin",false);

        mRootRef.child("users").push().updateChildren(hashMap, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                progressDialog.dismiss();

                startActivity(new Intent(getApplicationContext(),LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
            }
        });
    }
}
