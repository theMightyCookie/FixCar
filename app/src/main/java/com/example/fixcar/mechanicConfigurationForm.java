package com.example.fixcar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class mechanicConfigurationForm extends AppCompatActivity {

//    final ImageView imageAvatar = findViewById(R.id.imageAvatar);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_configuration_form);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fixcar-1b5cf-default-rtdb.firebaseio.com");
        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();

        final Button imageBtn = findViewById(R.id.imageBtn);
        final Button saveBtn = findViewById(R.id.saveBtn);
        final EditText name = findViewById(R.id.inputName);
        final EditText city = findViewById(R.id.inputCity);
        final EditText address = findViewById(R.id.inputAddress);
        final EditText experience = findViewById(R.id.inputExperience);
        final EditText phone = findViewById(R.id.inputPhone);
        final EditText email = findViewById(R.id.inputEmail);

        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                assert currentuser != null;
                if (!(snapshot.child(currentuser.getUid()).child("mechanic").child("name").getValue() == null))
                {
                    name.setText(snapshot.child(currentuser.getUid()).child("mechanic").child("name").getValue().toString());
                }
                else{
                    name.setText("Nume");
                }

                if (!(snapshot.child(currentuser.getUid()).child("mechanic").child("city").getValue() == null)){
                    city.setText(snapshot.child(currentuser.getUid()).child("mechanic").child("city").getValue().toString());
                }
                else {
                    city.setText("Oras");
                }

                if(!(snapshot.child(currentuser.getUid()).child("mechanic").child("address").getValue() == null)){
                    address.setText(snapshot.child(currentuser.getUid()).child("mechanic").child("address").getValue().toString());
                }
                else {
                    address.setText("Adresa");
                }

                if (!(snapshot.child(currentuser.getUid()).child("mechanic").child("experience").getValue() == null)){
                    experience.setText(snapshot.child(currentuser.getUid()).child("mechanic").child("experience").getValue().toString());
                }
                else{
                    experience.setText("Vechime in service");
                }

                if(!(snapshot.child(currentuser.getUid()).child("mechanic").child("phone").getValue() == null)){
                    phone.setText(snapshot.child(currentuser.getUid()).child("mechanic").child("phone").getValue().toString());
                }
                else {
                    phone.setText("Telefon");
                }

                if(!(snapshot.child(currentuser.getUid()).child("mechanic").child("email2").getValue() == null)){
                    email.setText(snapshot.child(currentuser.getUid()).child("mechanic").child("email2").getValue().toString());
                }
                else {
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
                final String experienceText = experience.getText().toString().trim();
                final String phoneText = phone.getText().toString().trim();
                final String emailText = email.getText().toString().trim();



                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("name").setValue(nameText);
                        databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("city").setValue(cityText);
                        databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("address").setValue(addressText);
                        databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("experience").setValue(experienceText);
                        databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("phone").setValue(phoneText);
                        databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("email2").setValue(emailText);
                        Toast.makeText(mechanicConfigurationForm.this, "Datele au fost salvate cu succes in baza de date!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(mechanicConfigurationForm.this, mechanicLandingPage.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




            }
        });



    }
}