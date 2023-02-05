package com.example.fixcar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

import java.util.Objects;

public class clientWriteReview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_write_review);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fixcar-1b5cf-default-rtdb.firebaseio.com");
        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();

        final Button addBtn = findViewById(R.id.btnAdd);
        final EditText review = findViewById(R.id.reviewAdd);

        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                assert currentuser != null;
                if (!(snapshot.child(currentuser.getUid()).child("mechanic").child("review").getValue() == null))
                {
                    review.setText(Objects.requireNonNull(snapshot.child(currentuser.getUid()).child("mechanic").child("review").getValue()).toString());
                }
                else{
                    review.setText("No review");
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String reviewText = review.getText().toString().trim();

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.child("users").child(currentuser.getUid()).child("mechanic").child("review").setValue(reviewText);

                        Toast.makeText(clientWriteReview.this, "Ai adaugat un review cu succes! Multumim!", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




            }
        });



    }
}
