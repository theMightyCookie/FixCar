package com.example.fixcar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileChoice extends AppCompatActivity {

    public DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_choice);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fixcar-1b5cf-default-rtdb.firebaseio.com");

        final TextView clientButton = findViewById(R.id.clientMesssge);
        final TextView mechanicButton = findViewById(R.id.mechanicMesssge);

        clientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileChoice.this, clientLandingPage.class));
                // finish();
            }
        });

        mechanicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                assert currentuser != null;
//                databaseReference.child("mechanics").child(currentuser.getUid());
                startActivity(new Intent(ProfileChoice.this, mechanicLandingPage.class));
                // finish();
            }
        });
    }
}