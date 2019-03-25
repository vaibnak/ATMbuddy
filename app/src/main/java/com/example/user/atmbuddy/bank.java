package com.example.user.atmbuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

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
    String bnkcode;
    RelativeLayout relativeLayout;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
        Intent intent = getIntent();
        relativeLayout = findViewById(R.id.relativelayout);
        sharedPreferences = getApplicationContext().getSharedPreferences("com.example.user.atmbuddy", Context.MODE_PRIVATE);
        editText1 = findViewById(R.id.bnkname);
        editText2 = findViewById(R.id.bnkcode);
        editText3 = findViewById(R.id.bnkaddr);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("banks");

    }

    public boolean check(String bnkname, String  bnkcode, String bnkaddr){
        if(bnkname.isEmpty() || bnkaddr.isEmpty() || bnkcode.isEmpty()){
            return false;
        }
        return true;
    }
    public void showsnackbar(String msg){
        Snackbar snackbar = Snackbar.make(relativeLayout, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void register(View view) {
        bnkname = editText1.getText().toString();
        bnkcode = editText2.getText().toString();
        bnkaddr = editText3.getText().toString();
        if(check(bnkname,bnkcode, bnkaddr)){
            sharedPreferences.edit().putString("bname", bnkname).apply();
            String id = databaseReference.push().getKey();
            bnk b = new bnk(bnkname, bnkcode, bnkaddr);
            databaseReference.child(id).setValue(b);
            Intent intent = new Intent(getApplicationContext(), customerActivity.class);
            intent.putExtra("bname", bnkname);
            startActivity(intent);
        }else{
            showsnackbar("Please enter correct details");
        }

    }
}
