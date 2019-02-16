package com.example.abbieturner.gdprapplication.UI.Admin.Activitys;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.abbieturner.gdprapplication.R;
import com.example.abbieturner.gdprapplication.UI.Admin.Fragments.ContactUsFragment;
import com.example.abbieturner.gdprapplication.UI.Admin.Fragments.RequestsFragment;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdminHomeActivity extends AppCompatActivity {

    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        openFragment(new ListFragment());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_emp:
                        openFragment(new ListFragment());
                        return true;
                    case R.id.action_req:
                        openFragment(new RequestsFragment());
                        return true;
                    case R.id.action_cont:
                        openFragment(new ContactUsFragment());
                        return true;
                }
                return false;
            }
        });
    }

    public void openFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAuth.signOut();
    }
}
