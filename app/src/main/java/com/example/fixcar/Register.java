package com.example.fixcar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;




public class Register extends AppCompatActivity {

    public DatabaseReference databaseReference;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fixcar-1b5cf-default-rtdb.firebaseio.com");

        final EditText username = findViewById(R.id.inputUsername);
        final EditText email = findViewById(R.id.inputEmail);
        final EditText password = findViewById(R.id.inputPassword);
        final EditText passwordConfirm = findViewById(R.id.inputPasswordConfirm);

        final Button registerBtn = findViewById(R.id.registerButton);
        final TextView loginBtn = findViewById(R.id.alreadyHaveAccount);



        registerBtn.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {

                                               // preluarea datelor din EditTexts pentru a fi convertite in variabile de tip String
                                               final String usernameText = username.getText().toString().trim();
                                               final String emailText = email.getText().toString().trim();
                                               final String passwordText = password.getText().toString().trim();
                                               final String passwordConfirmText = passwordConfirm.getText().toString().trim();

                                               if (emailText.isEmpty()) {
                                                   Toast.makeText(Register.this, "Introduceti o adresa de email!", Toast.LENGTH_SHORT).show();
                                                   email.setError("Introduceti o adresa de email!");
                                               }
                                               if (passwordText.isEmpty()) {
                                                   Toast.makeText(Register.this, "Introduceti o parola!", Toast.LENGTH_SHORT).show();
                                                   password.setError("Introduceti o parola!");
                                               }
                                               if (usernameText.isEmpty()) {
                                                   Toast.makeText(Register.this, "Introduceti numele de utilizator!", Toast.LENGTH_SHORT).show();
                                                   username.setError("Introduceti numele de utilizator!");
                                               }
                                               if (passwordConfirmText.isEmpty()) {
                                                   Toast.makeText(Register.this, "Confirmati parola!", Toast.LENGTH_SHORT).show();
                                                   passwordConfirm.setError("Confirmati parola!");
                                               }
                                               if (!passwordText.equals(passwordConfirmText)) {
                                                   Toast.makeText(Register.this, "Parolele nu coincid!", Toast.LENGTH_SHORT).show();
                                               } else {
                                                   mAuth.createUserWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                       @Override
                                                       public void onComplete(@NonNull Task<AuthResult> task) {
                                                           if (task.isSuccessful()) {
                                                               Toast.makeText(Register.this, "Inregistrare efectuata cu succes!", Toast.LENGTH_SHORT).show();
                                                               startActivity(new Intent(Register.this, ProfileChoice.class));

                                                           } else {
                                                               Toast.makeText(Register.this, "Inregistrare efectuata cu succes!", Toast.LENGTH_SHORT).show();
                                                               startActivity(new Intent(Register.this, ProfileChoice.class));
                                                           }
                                                           assert currentuser != null;
                                                           databaseReference.child("users").child(currentuser.getUid()).child("username").setValue(usernameText);
                                                           databaseReference.child("users").child(currentuser.getUid()).child("email").setValue(emailText);
                                                           databaseReference.child("users").child(currentuser.getUid()).child("password").setValue(passwordText);
                                                       }

                                                   });

                                               }

                                           }

                                       });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });


    }


}

