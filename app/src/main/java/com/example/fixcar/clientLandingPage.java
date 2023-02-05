package com.example.fixcar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class clientLandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_langing_page);

        final Button settingsBtn = findViewById(R.id.config);
        final Button homeBtn = findViewById(R.id.btnHome);
        final Button profileBtn = findViewById(R.id.btnProfile);
        final Button reviewBtn = findViewById(R.id.btnReviewsWriter);
        final Button searchMechanicBtn = findViewById(R.id.btnSearchMechanic);
        final Button seeAppointmentsBtn = findViewById(R.id.btnProgramari);
        final Button mechanicSchedule = findViewById(R.id.seeMechanicSchedule);

        final TextView accepted = findViewById(R.id.programariAcceptate);
        final TextView waiting = findViewById(R.id.programariWaiting);

        mechanicSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(clientLandingPage.this, clientSeeMechanicSchedule.class));
            }
        });


        seeAppointmentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(clientLandingPage.this, clientSeeAppointments.class));
            }
        });

        searchMechanicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(clientLandingPage.this, clientSearchBar.class));
            }
        });

        reviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(clientLandingPage.this, clientWriteReview.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(clientLandingPage.this, ProfileChoice.class));
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(clientLandingPage.this, clientProfile.class));
            }
        });

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(clientLandingPage.this, clientConfigurationForm.class));
            }
        });


    }
}