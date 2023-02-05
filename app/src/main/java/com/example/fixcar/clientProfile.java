package com.example.fixcar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.Objects;

public class clientProfile extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fixcar-1b5cf-default-rtdb.firebaseio.com");
    FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_profile);

        final Button logoutBtn = findViewById(R.id.btnLogout);
        final TextView emailOutput = findViewById(R.id.clientEmailOutput);
        final TextView userOutput = findViewById(R.id.clientUserOutput);
        final TextView dataOutput = findViewById(R.id.clientDataOutput);

        emailOutput.setText(currentuser.getEmail());
        dataOutput.setText(new Date(Objects.requireNonNull(currentuser.getMetadata()).getCreationTimestamp()).toString());

        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userOutput.setText(snapshot.child(currentuser.getUid()).child("client").child("name").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(clientProfile.this, Login.class));
    }
}