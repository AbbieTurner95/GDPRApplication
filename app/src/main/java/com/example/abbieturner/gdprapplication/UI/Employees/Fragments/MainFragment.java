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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.abbieturner.gdprapplication.Models.User;
import com.example.abbieturner.gdprapplication.R;
import com.example.abbieturner.gdprapplication.UI.Employees.Activitys.LoginActivity;
import com.example.abbieturner.gdprapplication.ViewModel.MainViewModel;
import com.example.abbieturner.gdprapplication.utils.SharedPref;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

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
    private DatabaseReference mRootRef;
    private User user;

    public MainFragment() {

    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        unbinder = ButterKnife.bind(this, view);

        mAuth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);


        getAdmin();
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
                HashMap hashMap=new HashMap();
                hashMap.put("user_name",new SharedPref(getActivity()).getUserData().getName());
                hashMap.put("email", new SharedPref(getActivity()).getUserData().getEmail());
                hashMap.put("address", new SharedPref(getActivity()).getUserData().getAddress());
                hashMap.put("phone", new SharedPref(getActivity()).getUserData().getPhone());
                hashMap.put("fax", new SharedPref(getActivity()).getUserData().getPhone());
                hashMap.put("ID",new SharedPref(getActivity()).getUserId());


                hashMap.put("profile", new SharedPref(getActivity()).getUserData().getProfile());


                hashMap.put("lang",new SharedPref(getActivity()).getUserData().getLang());
                hashMap.put("medical", new SharedPref(getActivity()).getUserData().getMedical());
                hashMap.put("ethnicity", new SharedPref(getActivity()).getUserData().getEthnicity());
                hashMap.put("workHour", new SharedPref(getActivity()).getUserData().getWorkHour());
                hashMap.put("workPlace", new SharedPref(getActivity()).getUserData().getWorkPlace());
                hashMap.put("admin_token",user.getToken_id());
                mRootRef.child("requests").child(new SharedPref(getActivity()).getUserId()).updateChildren(hashMap, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {

                    }
                });
            }
        });


        log_out_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
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


    private void getAdmin(){
        mRootRef.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("Count " ,""+dataSnapshot.getChildrenCount());
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    if (snapshot.getValue(User.class).isAdmin()){
                        user=snapshot.getValue(User.class);
                        break;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("The read failed: " ,databaseError.getMessage());
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}