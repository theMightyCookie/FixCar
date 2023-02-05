package com.example.fixcar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class mechanicLandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_landing_page);

        final Button settingsBtn = findViewById(R.id.config);
        final Button reviewsBtn = findViewById(R.id.btnReviews);
        final Button homeBtn = findViewById(R.id.btnHome);
        final Button profileBtn = findViewById(R.id.btnProfile);
        final Button scheduleBtn = findViewById(R.id.btnOrar);
        final Button clientProfileBtn = findViewById(R.id.btnClientProfile);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mechanicLandingPage.this, ProfileChoice.class));
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mechanicLandingPage.this, mechanicProfile.class));
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mechanicLandingPage.this, mechanicConfigurationForm.class));
            }
        });

        reviewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mechanicLandingPage.this, mechanicReviews.class));
            }
        });

        scheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mechanicLandingPage.this, mechanicPlansSchedule.class));
            }
        });

        clientProfileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mechanicLandingPage.this, mechanicViewClients.class));
            }
        });


    }
}