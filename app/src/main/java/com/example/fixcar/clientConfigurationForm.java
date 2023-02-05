package com.example.fixcar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class clientConfigurationForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_configuration_form);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fixcar-1b5cf-default-rtdb.firebaseio.com");
        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();

        final Button imageBtn = findViewById(R.id.imageBtn);
        final Button saveBtn = findViewById(R.id.saveBtn);
        final EditText name = findViewById(R.id.inputName);
        final EditText city = findViewById(R.id.inputCity);
        final EditText address = findViewById(R.id.inputAddress);
        final EditText car = findViewById(R.id.inputCar);
        final EditText phone = findViewById(R.id.inputPhone);
        final EditText email = findViewById(R.id.inputEmail);

        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                assert currentuser != null;
                if (!snapshot.child(currentuser.getUid()).child("client").child("name").getValue().toString().isEmpty()) {
                    name.setText(snapshot.child(currentuser.getUid()).child("client").child("name").getValue().toString());
                } else {
                    name.setText("Nume");
                }

                if (!snapshot.child(currentuser.getUid()).child("client").child("city").getValue().toString().isEmpty()) {
                    city.setText(snapshot.child(currentuser.getUid()).child("client").child("city").getValue().toString());
                } else {
                    city.setText("Oras");
                }

                if (!snapshot.child(currentuser.getUid()).child("client").child("address").getValue().toString().isEmpty()) {
                    address.setText(snapshot.child(currentuser.getUid()).child("client").child("address").getValue().toString());
                } else {
                    address.setText("Adresa");
                }

                if (!snapshot.child(currentuser.getUid()).child("client").child("car").getValue().toString().isEmpty()) {
                    car.setText(snapshot.child(currentuser.getUid()).child("client").child("car").getValue().toString());
                } else {
                    car.setText("Vechime in service");
                }

                if (!snapshot.child(currentuser.getUid()).child("client").child("phone").getValue().toString().isEmpty()) {
                    phone.setText(snapshot.child(currentuser.getUid()).child("client").child("phone").getValue().toString());
                } else {
                    phone.setText("Telefon");
                }

                if (!snapshot.child(currentuser.getUid()).child("client").child("email2").getValue().toString().isEmpty()) {
                    email.setText(snapshot.child(currentuser.getUid()).child("client").child("email2").getValue().toString());
                } else {
                    email.setText("Email");
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nameText = name.getText().toString().trim();
                final String cityText = city.getText().toString().trim();
                final String addressText = address.getText().toString().trim();
                final String experienceText = car.getText().toString().trim();
                final String phoneText = phone.getText().toString().trim();
                final String emailText = email.getText().toString().trim();


                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child("users").child(currentuser.getUid()).child("client").child("name").setValue(nameText);
                        databaseReference.child("users").child(currentuser.getUid()).child("client").child("city").setValue(cityText);
                        databaseReference.child("users").child(currentuser.getUid()).child("client").child("address").setValue(addressText);
                        databaseReference.child("users").child(currentuser.getUid()).child("client").child("car").setValue(experienceText);
                        databaseReference.child("users").child(currentuser.getUid()).child("client").child("phone").setValue(phoneText);
                        databaseReference.child("users").child(currentuser.getUid()).child("client").child("email2").setValue(emailText);
                        Toast.makeText(clientConfigurationForm.this, "Datele au fost salvate cu succes in baza de date!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(clientConfigurationForm.this, clientLandingPage.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

    }


}