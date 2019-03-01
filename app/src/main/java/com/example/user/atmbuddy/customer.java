package com.example.user.atmbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class customer extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    EditText editText3;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        Intent intent = getIntent();
        editText1 = findViewById(R.id.name);
        editText2 = findViewById(R.id.phn);
        editText3 = findViewById(R.id.addr);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("customer");

    }
}
