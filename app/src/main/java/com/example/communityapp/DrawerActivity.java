package com.example.communityapp;


import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.communityapp.Fragments.Home;
import com.example.communityapp.Fragments.Profile;
import com.example.communityapp.Fragments.Publish;
import com.example.communityapp.databinding.ActivityDrawerBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.navigation.NavigationView;


public class DrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ActivityDrawerBinding binding;
    GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showdp();
        setupdrawer();

    }

    private void setupdrawer() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, new Home());
        fragmentTransaction.commit();

        binding.menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.drawer.openDrawer(Gravity.LEFT);
            }
        });

        binding.navigationView.setNavigationItemSelectedListener(this);

    }

    private void showdp() {
        account = GoogleSignIn.getLastSignedInAccount(this);
        Glide.with(this).load(account.getPhotoUrl()).into(binding.profileIcon);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (item.getItemId() == R.id.nav_home) {
            fragmentTransaction.replace(R.id.frame_layout, new Home());
        } else if (item.getItemId() == R.id.nav_publish) {
            fragmentTransaction.replace(R.id.frame_layout, new Publish());
        } else if (item.getItemId() == R.id.nav_profile) {
            fragmentTransaction.replace(R.id.frame_layout, new Profile());
        } else {
            return false;
        }
        fragmentTransaction.commit();
        binding.drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}