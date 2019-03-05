package com.example.abbieturner.gdprapplication.UI.Employees.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import com.example.abbieturner.gdprapplication.UI.Employees.Activitys.LoginActivity;
import com.example.abbieturner.gdprapplication.utils.Utils;
import com.firebase.ui.auth.AuthUI;
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

public class ChangePasswordDialog extends DialogFragment {

    Unbinder unbinder;
    @BindView(R.id.et_old_pass)
    TextInputEditText et_old_pass;
    @BindView(R.id.et_new_pass)
    TextInputEditText et_new_pass;
    @BindView(R.id.et_conf_pass)
    TextInputEditText et_conf_pass;
    @BindView(R.id.btn_chg_pass)
    Button btn_chg_pass;
    ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private DatabaseReference mRootRef;

    public ChangePasswordDialog() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.change_password_dialog, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        unbinder = ButterKnife.bind(this, view);
        progressDialog = new ProgressDialog(getActivity());
        mAuth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();

        btn_chg_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startChangingPassword();
            }
        });

        return view;
    }

    private void startChangingPassword() {
        if (Utils.isNetworkAvailable(getActivity())) {
            if (Utils.checkError(et_old_pass) && Utils.checkError(et_new_pass) && Utils.checkError(et_conf_pass)
                    && Utils.checkMatch(et_new_pass, et_conf_pass)) {

                progressDialog.setTitle("Changing Password");
                progressDialog.setMessage("Please wait ....");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                AuthCredential credential = EmailAuthProvider.getCredential(mAuth.getCurrentUser().getEmail(),
                        et_old_pass.getText().toString());
                mAuth.getCurrentUser().reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {

                                    mAuth.getCurrentUser().updatePassword(et_new_pass.getText().toString())
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()){
                                                        progressDialog.dismiss();
                                                        ChangePasswordDialog.this.dismiss();
                                                        Toasty.success(getActivity(),
                                                                "Password Changed Successfully",
                                                                Toast.LENGTH_SHORT,
                                                                true).show();
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
    public void onPause() {
        super.onPause();
        mAuth.signOut();
        AuthUI.getInstance().signOut(getActivity());
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onStop() {
        super.onStop();
        mAuth.signOut();
        AuthUI.getInstance().signOut(getActivity());
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }
}