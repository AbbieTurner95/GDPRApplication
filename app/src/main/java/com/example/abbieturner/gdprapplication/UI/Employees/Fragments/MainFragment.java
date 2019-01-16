package com.example.abbieturner.gdprapplication.UI.Employees.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
    Button delte_data_btn;

    private MainViewModel mainViewModel;

    public MainFragment() {

    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);

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
                            emailIntent.putExtra(Intent.EXTRA_TEXT, "Please see all data kept below");
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getProfile());
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getName());
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getAddress());
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getEmail());
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getEthnicity());
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getFax());
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getLang());
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getMedical());
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getPhone());
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getWorkHour());
                            emailIntent.putExtra(Intent.EXTRA_TEXT, user.getWorkPlace());
                            startActivity(emailIntent);
                        }
                    }
                });
            }
        });

        delte_data_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




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
}
