package com.example.fixcar;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class clientSearchBar extends AppCompatActivity {
    EditText searchText;
    ListView listView;
    AutoCompleteTextView textView;
    DatabaseReference databaseReference;
    DatabaseReference dbRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_search_bar);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fixcar-1b5cf-default-rtdb.firebaseio.com");
        dbRef = databaseReference.child("users");

        listView = (ListView) findViewById(R.id.listData);
        textView = (AutoCompleteTextView) findViewById(R.id.textSearch);

        ValueEventListener event = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                populateSearch(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        databaseReference.child("users").addListenerForSingleValueEvent(event);


    }

    private void populateSearch(DataSnapshot snapshot){
        Log.d("users", "reading data");
        ArrayList<String> names = new ArrayList<>();
        if(snapshot.exists())
        {
            for (DataSnapshot ds:snapshot.getChildren())
            {
                String name = ds.child("username").getValue(String.class);
                names.add(name);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
            textView.setAdapter(adapter);
            textView.setOnItemClickListener((parent, view, position, id) -> {
                String name = textView.getText().toString();
                System.out.print(name);
                searchUser(name);
            });
            
        }
        else
        {
            Log.d("users", "No data found");
            Toast.makeText(clientSearchBar.this, "Nu au fost identificate date!", Toast.LENGTH_SHORT).show();
        }
    }

    private void searchUser(String name) {
        Query query = dbRef.orderByChild(name).equalTo(name);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    ArrayList<String> listusers = new ArrayList<>();
                    for(DataSnapshot ds:snapshot.getChildren()){
                        // User user = new User(ds.child("username").getValue(String.class), ds.child("email").getValue(String.class));
                        listusers.add(ds.child("username").getValue(String.class)+"\n"+ds.child("email").getValue(String.class));
                        System.out.print(listusers.toString());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, listusers);
                    listView.setAdapter(adapter);

                }
                else
                {
                    Log.d("users", "No details found");
                    Toast.makeText(clientSearchBar.this, "Nu au fost identificate detalii!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}