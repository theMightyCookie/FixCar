package com.example.fixcar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fixcar-1b5cf-default-rtdb.firebaseio.com");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();
        final EditText email = findViewById(R.id.inputEmail);
        final EditText password = findViewById(R.id.inputPassword);
        final Button loginBtn = findViewById(R.id.btnLogin);
        final TextView registerBtn = findViewById(R.id.textViewSignUp);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailText = email.getText().toString().trim();
                final String passwordText = password.getText().toString().trim();

                if (emailText.isEmpty()){
                    Toast.makeText(Login.this, "Introduceti o adresa de email!", Toast.LENGTH_SHORT).show();
                    email.setError("Introduceti o adresa de email!");
                }
                if (passwordText.isEmpty()){
                    Toast.makeText(Login.this, "Introduceti o parola!", Toast.LENGTH_SHORT).show();
                    password.setError("Introduceti o parola!");
                }
                else{
                    mAuth.signInWithEmailAndPassword(emailText, passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Login.this, "Autentificare reusita!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login.this, ProfileChoice.class));
                            }
                            else{
                                Toast.makeText(Login.this, "Autentificare esuata" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

        // deschide activitatea pentru inregistrare
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }


}