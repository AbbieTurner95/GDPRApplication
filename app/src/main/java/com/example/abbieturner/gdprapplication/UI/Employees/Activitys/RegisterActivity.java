package com.example.abbieturner.gdprapplication.UI.Employees.Activitys;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.abbieturner.gdprapplication.R;
import com.example.abbieturner.gdprapplication.utils.SharedPref;
import com.example.abbieturner.gdprapplication.utils.Utils;
import com.firebase.ui.auth.AuthUI;
import com.fxn.pix.Pix;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.img_profile)
    ImageView img_profile;
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
    Spinner etLang;
    @BindView(R.id.et_ethnicity)
    Spinner etEthn;

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
    private StorageReference mStorageReference;
    private HashMap hashMap;
    private static Uri photoUri;
    public static final int PIC_IMAGE_PICKER = 19;
    private String token;
    private SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        sharedPref = new SharedPref(this);
        mAuth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mStorageReference = FirebaseStorage.getInstance().getReference();
        progressDialog = new ProgressDialog(this);
        hashMap = new HashMap();

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (task.isSuccessful()){
                    token = task.getResult().getToken();
                    Log.e("REGISTER",token);
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegister();
            }
        });

        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startImagePicker();
            }
        });
    }

    private void startImagePicker() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            Pix.start(RegisterActivity.this, PIC_IMAGE_PICKER);
                        } else if (report.isAnyPermissionPermanentlyDenied()) {
                            Utils.goToImageSettings(RegisterActivity.this);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();
    }

    private void startRegister() {
        if (Utils.isNetworkAvailable(this)) {
            if (Utils.checkError(etName) && Utils.checkError(etAddress) && Utils.checkError(etPhone)
                    && Utils.checkError(etEmail) && Utils.checkError(etFax)
                    && Utils.checkError(etMedical) && Utils.checkError(etWorkHour)
                    && Utils.checkError(etWorkPlace) && Utils.checkPhoneSize(etPhone) && Utils.checkEmail(etEmail)) {

                progressDialog.setTitle("Start Registering");
                progressDialog.setMessage("Please wait ...");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                mAuth.createUserWithEmailAndPassword(etEmail.getText().toString(),
                        etPass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    mAuth.getCurrentUser().sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        uploadUserDataToFirebase();
                                                    }
                                                }
                                            });
                                }
                            }
                        });
            }
        } else {
            Toasty.warning(this, "Check internet connection", Toast.LENGTH_SHORT, true).show();
        }
    }

    private void uploadUserDataToFirebase() {
        if (photoUri != null) {
            final StorageReference spaceRef = mStorageReference.child("users");
            spaceRef.child(mAuth.getCurrentUser().getUid() + ".jpg")
                    .putFile(photoUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            spaceRef.child(mAuth.getCurrentUser().getUid() + ".jpg")
                                    .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    finishRegistration(uri);
                                }
                            });
                        }
                    });
        } else {
            finishRegistration(null);
        }
    }

    private void finishRegistration(Uri downloadUri) {
        hashMap.put("name", etName.getText().toString());
        hashMap.put("email", etEmail.getText().toString());
        hashMap.put("address", etAddress.getText().toString());
        hashMap.put("phone", etPhone.getText().toString());
        hashMap.put("fax", etFax.getText().toString());
        hashMap.put("admin", false);

        if (downloadUri != null) {
            hashMap.put("profile", downloadUri.toString());
        } else {
            hashMap.put("profile", "default");
        }

        hashMap.put("lang", etLang.getSelectedItem().toString());
        hashMap.put("medical", etMedical.getText().toString());
        hashMap.put("ethnicity", etEthn.getSelectedItem().toString());
        hashMap.put("workHour", etWorkHour.getText().toString());
        hashMap.put("workPlace", etWorkPlace.getText().toString());
        hashMap.put("token_id",token);

        sharedPref.setUserId(mAuth.getCurrentUser().getUid());
        mRootRef.child("users").child(mAuth.getCurrentUser().getUid())
                .updateChildren(hashMap, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        progressDialog.dismiss();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                        finish();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == PIC_IMAGE_PICKER) {
            ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
            photoUri = Uri.fromFile(new File(returnValue.get(0)));
            img_profile.setImageURI(photoUri);
        }
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