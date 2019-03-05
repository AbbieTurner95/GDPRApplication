package com.example.abbieturner.gdprapplication.UI.Employees.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.abbieturner.gdprapplication.Models.User;
import com.example.abbieturner.gdprapplication.R;
import com.example.abbieturner.gdprapplication.ViewModel.MainViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.iv_user_profile)
    ImageView iv_user_profile;
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    @BindView(R.id.request_btn)
    Button request_data;
    @BindView(R.id.delete_data_btn)
    Button delete_data_btn;
    @BindView(R.id.log_out_btn)
    Button log_out_btn;

    private FirebaseAuth mAuth;
    private MainViewModel mainViewModel;

    public MainFragment() {

    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        unbinder = ButterKnife.bind(this, view);

        mAuth = FirebaseAuth.getInstance();

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        request_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.getUserLiveData().observe(MainFragment.this, new Observer<User>() {
                    @Override
                    public void onChanged(@Nullable User user) {
                        if (user != null) {
                            Intent emailIntent = new Intent(Intent.ACTION_SEND);
                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Personal Data");
                            emailIntent.putExtra(Intent.EXTRA_TEXT, "Please see all data we keep on yourself below ; ");
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getProfile() + "/n");
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getName() + "/n");
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getAddress() + "/n");
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getEmail() + "/n");
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getEthnicity() + "/n");
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getFax() + "/n");
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getLang() + "/n");
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getMedical() + "/n");
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getPhone() + "/n");
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getWorkHour() + "/n");
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getWorkPlace());
                            startActivity(Intent.createChooser(emailIntent, "Email:"));
                        }
                    }
                });
            }
        });

        delete_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete data req send to admin
            }
        });


        log_out_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });

        mainViewModel.getUserLiveData().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (user != null) {
                    if (!user.getProfile().equals("default")) {
                        Picasso.get().load(user.getProfile()).into(iv_user_profile);
                    } else {
                        TextDrawable drawable = TextDrawable.builder()
                                .buildRect(String.valueOf(user.getName().charAt(0)), Color.BLUE);
                        iv_user_profile.setImageDrawable(drawable);
                    }
                    tv_user_name.setText(user.getName());
                }
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
