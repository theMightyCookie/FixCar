package com.example.fixcar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class mechanicPlansSchedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_plans_schedule);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fixcar-1b5cf-default-rtdb.firebaseio.com");
        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();

        final CheckBox luni = findViewById(R.id.luni);
        final CheckBox marti = findViewById(R.id.marti);
        final CheckBox miercuri = findViewById(R.id.miercuri);
        final CheckBox joi = findViewById(R.id.joi);
        final CheckBox vineri = findViewById(R.id.vineri);
        final CheckBox sambata = findViewById(R.id.sambata);
        final CheckBox duminica = findViewById(R.id.duminca);

        final EditText luniStart = findViewById(R.id.luniStart);
        final EditText luniStop = findViewById(R.id.luniStop);
        final EditText martiStart = findViewById(R.id.martiStart);
        final EditText martiStop = findViewById(R.id.martiStop);
        final EditText miercuriStart = findViewById(R.id.miercuriStart);
        final EditText miercuriStop = findViewById(R.id.miercuriStop);
        final EditText joiStart = findViewById(R.id.joiStart);
        final EditText joiStop = findViewById(R.id.joiStop);
        final EditText vineriStart = findViewById(R.id.vineriStart);
        final EditText vineriStop = findViewById(R.id.vineriStop);
        final EditText sambataStart = findViewById(R.id.sambataStart);
        final EditText sambataStop = findViewById(R.id.sambataStop);
        final EditText duminicaStart = findViewById(R.id.duminicaStart);
        final EditText duminicaStop = findViewById(R.id.dumincaStop);

        final Button saveBtn = findViewById(R.id.btnSave);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String luniStartText = luniStart.getText().toString().trim();
                final String luniStopText = luniStop.getText().toString().trim();
                final String martiStartText = martiStart.getText().toString().trim();
                final String martiStopText = martiStop.getText().toString().trim();
                final String miercuriStartText = miercuriStart.getText().toString().trim();
                final String miercuriStopText = miercuriStop.getText().toString().trim();
                final String joiStartText = joiStart.getText().toString().trim();
                final String joiStopText = joiStop.getText().toString().trim();
                final String vineriStartText = vineriStart.getText().toString().trim();
                final String vineriStopText = vineriStop.getText().toString().trim();
                final String sambataStartText = sambataStart.getText().toString().trim();
                final String sambataStopText = sambataStop.getText().toString().trim();
                final String duminicaStartText = duminicaStart.getText().toString().trim();
                final String duminicaStopText = duminicaStop.getText().toString().trim();

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (luni.isChecked()){
                            if (!luniStartText.isEmpty() && !luniStopText.isEmpty()){
                                assert currentuser != null;
                                databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("schedule").child("Luni").child("start").setValue(luniStartText);
                                databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("schedule").child("Luni").child("stop").setValue(luniStopText);
                            }
                            else{
                                Toast.makeText(mechanicPlansSchedule.this, "Introduceti intervalul orar in care lucrati!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        if (marti.isChecked()){
                            if (!martiStartText.isEmpty() && !martiStopText.isEmpty()){
                                assert currentuser != null;
                                databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("schedule").child("Marti").child("start").setValue(martiStartText);
                                databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("schedule").child("Marti").child("stop").setValue(martiStopText);
                            }
                            else{
                                Toast.makeText(mechanicPlansSchedule.this, "Introduceti intervalul orar in care lucrati!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        if (miercuri.isChecked()){
                            if (!miercuriStartText.isEmpty() && !miercuriStopText.isEmpty()){
                                assert currentuser != null;
                                databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("schedule").child("Miercuri").child("start").setValue(miercuriStartText);
                                databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("schedule").child("Miercuri").child("stop").setValue(miercuriStopText);
                            }
                            else{
                                Toast.makeText(mechanicPlansSchedule.this, "Introduceti intervalul orar in care lucrati!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        if (joi.isChecked()){
                            if (!joiStartText.isEmpty() && !joiStopText.isEmpty()){
                                assert currentuser != null;
                                databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("schedule").child("Joi").child("start").setValue(joiStartText);
                                databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("schedule").child("Joi").child("stop").setValue(joiStopText);
                            }
                            else{
                                Toast.makeText(mechanicPlansSchedule.this, "Introduceti intervalul orar in care lucrati!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        if (vineri.isChecked()){
                            if (!vineriStartText.isEmpty() && !vineriStopText.isEmpty()){
                                assert currentuser != null;
                                databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("schedule").child("Vineri").child("start").setValue(vineriStartText);
                                databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("schedule").child("Vineri").child("stop").setValue(vineriStopText);
                            }
                            else{
                                Toast.makeText(mechanicPlansSchedule.this, "Introduceti intervalul orar in care lucrati!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        if (sambata.isChecked()){
                            if (!sambataStartText.isEmpty() && !sambataStopText.isEmpty()){
                                assert currentuser != null;
                                databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("schedule").child("Sambata").child("start").setValue(sambataStartText);
                                databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("schedule").child("Sambata").child("stop").setValue(sambataStopText);
                            }
                            else{
                                Toast.makeText(mechanicPlansSchedule.this, "Introduceti intervalul orar in care lucrati!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        if (duminica.isChecked()){
                            if (!duminicaStartText.isEmpty() && !duminicaStopText.isEmpty()){
                                assert currentuser != null;
                                databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("schedule").child("Duminica").child("start").setValue(duminicaStartText);
                                databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("schedule").child("Duminica").child("stop").setValue(duminicaStopText);
                            }
                            else{
                                Toast.makeText(mechanicPlansSchedule.this, "Introduceti intervalul orar in care lucrati!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        Toast.makeText(mechanicPlansSchedule.this, "Datele au fost salvate cu succes in baza de date!", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(mechanicConfigurationForm.this, mechanicLandingPage.class));
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




            }
        });

    }
}