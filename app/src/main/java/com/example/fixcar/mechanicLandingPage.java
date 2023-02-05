package com.example.fixcar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class mechanicLandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_landing_page);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fixcar-1b5cf-default-rtdb.firebaseio.com");
        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();

        final Button settingsBtn = findViewById(R.id.config);
        final Button reviewsBtn = findViewById(R.id.btnReviews);
        final Button homeBtn = findViewById(R.id.btnHome);
        final Button profileBtn = findViewById(R.id.btnProfile);
        final Button scheduleBtn = findViewById(R.id.btnOrar);
        final Button clientProfileBtn = findViewById(R.id.btnClientProfile);
        final CheckedTextView appointments = findViewById(R.id.programari);

        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                assert currentuser != null;
                if (!(snapshot.child(currentuser.getUid()).child("mechanic").child("appointment").getValue() == null))
                {
                    appointments.setText(snapshot.child(currentuser.getUid()).child("mechanic").child("appointment").getValue().toString());
                }
                else{
                    appointments.setText("Nu exista programari!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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