package com.example.user.atmbuddy;

import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class bank extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    EditText editText3;
    Button button;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String addr,bnkname,bnkaddr;
    int bnkcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        Intent intent = getIntent();
        editText1 = findViewById(R.id.bnkname);
        editText2 = findViewById(R.id.bnkcode);
        editText3 = findViewById(R.id.bnkaddr);
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("banks");

    }

    public void register(View view) {
//        bnkname = editText1.getText().toString();
//        bnkcode = Integer.parseInt(editText2.getText().toString());
//        bnkaddr = editText3.getText().toString();
//        String id = databaseReference.push().getKey();
//        bnk b = new bnk(bnkname, bnkcode, bnkaddr);
//        databaseReference.child(id).setValue(b);
        Intent intent = new Intent(getApplicationContext(), customerActivity.class);
        startActivity(intent);

    }
}
