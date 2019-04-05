package com.example.abbieturner.gdprapplication.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.abbieturner.gdprapplication.Models.User;
import com.example.abbieturner.gdprapplication.utils.SharedPref;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainViewModel extends AndroidViewModel {

    private FirebaseAuth mAuth;
    private DatabaseReference mRootRef;
    private SharedPref sharedPref;

    private MutableLiveData<User> userLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mAuth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();
        sharedPref = new SharedPref(application.getApplicationContext());
    }

    private void fetchData() {
        mRootRef.keepSynced(true);
        mRootRef.child("users").child(sharedPref.getUserId())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        User user = dataSnapshot.getValue(User.class);
                        sharedPref.setUserData(user);
                        userLiveData.setValue(user);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
    }

    public MutableLiveData<User> getUserLiveData() {
        if (userLiveData == null) {
            userLiveData = new MutableLiveData<>();
            fetchData();
        }
        return userLiveData;
    }
}