package com.example.abbieturner.gdprapplication.UI.Employees.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.abbieturner.gdprapplication.R;
import com.example.abbieturner.gdprapplication.UI.Employees.Activitys.UpdateProfileActivity;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ProfileFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.btn_chg_mail)
    Button btn_chg_mail;
    @BindView(R.id.btn_chg_pass)
    Button btn_chg_pass;
    @BindView(R.id.btn_chg_data)
    Button btn_chg_data;

    private FirebaseAuth mAuth;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        unbinder = ButterKnife.bind(this, view);

        btn_chg_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeMailDialog changeMailDialog = new ChangeMailDialog();
                changeMailDialog.show(getFragmentManager(), "Change Mail Dialog");
            }
        });

        btn_chg_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangePasswordDialog changePasswordDialog = new ChangePasswordDialog();
                changePasswordDialog.show(getFragmentManager(), "Change Password Dialog");
            }
        });

        btn_chg_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UpdateProfileActivity.class));
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onPause() {
        super.onPause();
        mAuth.signOut();
    }
}
