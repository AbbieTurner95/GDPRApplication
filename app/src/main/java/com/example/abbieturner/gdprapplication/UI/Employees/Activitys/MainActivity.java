package com.example.abbieturner.gdprapplication.UI.Employees.Activitys;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.abbieturner.gdprapplication.R;
import com.example.abbieturner.gdprapplication.UI.Employees.Fragments.InfoFragment;
import com.example.abbieturner.gdprapplication.UI.Employees.Fragments.MainFragment;
import com.example.abbieturner.gdprapplication.UI.Employees.Fragments.ProfileFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    private DatabaseReference mRootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();

        fetchData();

        openFragment(new MainFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        openFragment(new MainFragment());
                        return true;
                    case R.id.action_profile:
                        openFragment(new ProfileFragment());
                        return true;
                    case R.id.action_info:
                        openFragment(new InfoFragment());
                        return true;

                } return false;
            }
        });
    }

    private void fetchData() { }

    public void openFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuth.signOut();
    }
}

