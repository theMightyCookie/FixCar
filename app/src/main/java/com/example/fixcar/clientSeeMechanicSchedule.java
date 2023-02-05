package com.example.fixcar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Calendar;

public class clientSeeMechanicSchedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_see_mechanic_schedule);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fixcar-1b5cf-default-rtdb.firebaseio.com");
        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();

        final TextView luni = findViewById(R.id.LuniOre);
        final TextView marti = findViewById(R.id.MartiOre);
        final TextView miercuri = findViewById(R.id.MiercuriOre);
        final TextView joi = findViewById(R.id.JoiOre);
        final TextView vineri = findViewById(R.id.VineriOre);
        final TextView sambata = findViewById(R.id.SambataOre);
        final TextView duminica = findViewById(R.id.DuminicaOre);

        final CalendarView calendar = findViewById(R.id.calendarView);

        final TextView first = findViewById(R.id.first);
        final TextView second = findViewById(R.id.second);
        final TextView third = findViewById(R.id.third);

        final Button toNext = findViewById(R.id.btnToAppointment);

        toNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(clientSeeMechanicSchedule.this, clientMakeAppointments.class));
            }
        });

        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                assert currentuser != null;
                if (!(snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Luni").getValue() == null))
                {
                    luni.setText(snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Luni").child("start").getValue().toString() + "-" + snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Luni").child("stop").getValue().toString());
                }
                else{
                    luni.setText("Liber");
                }

                if (!(snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Marti").getValue() == null))
                {
                    marti.setText(snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Marti").child("start").getValue().toString()  + "-" + snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Marti").child("stop").getValue().toString());
                }
                else{
                    marti.setText("Liber");
                }

                if (!(snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Miercuri").getValue() == null))
                {
                    miercuri.setText(snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Miercuri").child("start").getValue().toString()  + "-" + snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Miercuri").child("stop").getValue().toString());
                }
                else{
                    miercuri.setText("Liber");
                }

                if (!(snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Joi").getValue() == null))
                {
                    joi.setText(snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Joi").child("start").getValue().toString() + "-" + snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Joi").child("stop").getValue().toString());
                }
                else{
                    joi.setText("Liber");
                }

                if (!(snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Vineri").getValue() == null))
                {
                    vineri.setText(snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Vineri").child("start").getValue().toString() + "-" + snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Vineri").child("stop").getValue().toString());
                }
                else{
                    vineri.setText("Liber");
                }

                if (!(snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Sambata").getValue() == null))
                {
                    sambata.setText(snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Sambata").child("start").getValue().toString() + "-" + snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Sambata").child("stop").getValue().toString());
                }
                else{
                    sambata.setText("Liber");
                }

                if (!(snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Duminica").getValue() == null))
                {
                    duminica.setText(snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Duminica").child("start").getValue().toString() + "-" + snapshot.child(currentuser.getUid()).child("mechanic").child("schedule").child("Duminica").child("stop").getValue().toString());
                }
                else{
                    duminica.setText("Liber");
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {

                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

                if (dayOfWeek == 1){
                    first.setText(luni.getText());
                }
                if(dayOfWeek == 2){
                    first.setText(marti.getText());
                }
                if(dayOfWeek == 3){
                    first.setText(miercuri.getText());
                }
                if(dayOfWeek == 4){
                    first.setText(joi.getText());
                }
                if(dayOfWeek == 5){
                    first.setText(vineri.getText());
                }
                if(dayOfWeek == 6){
                    first.setText(sambata.getText());
                }
                if(dayOfWeek == 7){
                    first.setText(duminica.getText());
                }
            }
        });

    }
}