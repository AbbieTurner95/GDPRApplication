package com.example.abbieturner.gdprapplication.UI.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.abbieturner.gdprapplication.R;
import com.example.abbieturner.gdprapplication.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

public class ChangeMailDialog extends DialogFragment {

    Unbinder unbinder;
    @BindView(R.id.et_mail)
    TextInputEditText et_mail;
    @BindView(R.id.et_pass)
    TextInputEditText et_pass;
    @BindView(R.id.btn_chg_mail)
    Button btn_chg_mail;

    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private DatabaseReference mRootRef;


    public ChangeMailDialog() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.change_mail_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        progressDialog = new ProgressDialog(getActivity());
        mAuth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();

        btn_chg_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChangingEmail();
            }
        });
        return view;
    }

    private void startChangingEmail() {
        if (Utils.isNetworkAvailable(getActivity())) {
            if (Utils.checkError(et_mail) && Utils.checkError(et_pass) && Utils.checkEmail(et_mail)) {
                progressDialog.setTitle("Changing Email");
                progressDialog.setMessage("Please wait ....");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                AuthCredential credential = EmailAuthProvider.getCredential(mAuth.getCurrentUser().getEmail(),
                        et_pass.getText().toString());

                mAuth.getCurrentUser().reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                    mAuth.getCurrentUser().updateEmail(et_mail.getText().toString())
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        mRootRef.child("users")
                                                                .child(mAuth.getCurrentUser().getUid())
                                                                .child("email").setValue(et_mail.getText().toString())
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if (task.isSuccessful()) {
                                                                            progressDialog.dismiss();
                                                                            ChangeMailDialog.this.dismiss();
                                                                            Toasty.success(getActivity(), "Email Changed Successfully",
                                                                                    Toast.LENGTH_SHORT, true).show();
                                                                        }
                                                                    }
                                                                });
                                                    }
                                                }
                                            });
                                }else{
                                    progressDialog.dismiss();
                                    Toasty.error(getActivity(),
                                            "Authentication Failed",
                                            Toast.LENGTH_SHORT,
                                            true).show();
                                }

                            }
                        });
            }
        } else {
            Toasty.warning(getActivity(), "Check internet connection", Toast.LENGTH_SHORT, true).show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}