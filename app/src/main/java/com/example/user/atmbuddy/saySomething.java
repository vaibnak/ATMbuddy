package com.example.user.atmbuddy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class saySomething extends AppCompatActivity {
    DatabaseReference databaseReference;
    RelativeLayout relativeLayout;
    String code;
    String parent;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_say_something);
        Intent intent = getIntent();
        relativeLayout = findViewById(R.id.relativelayout);
        code = intent.getStringExtra("code");
        parent = intent.getStringExtra("parent");
        editText = findViewById(R.id.edt);
    }


    public void showsnackbar(String msg){
        Snackbar snackbar = Snackbar.make(relativeLayout, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void submit(View view) {
        String rev = editText.getText().toString();
        if(rev.isEmpty()){
            showsnackbar("Dont keep the review empty");
            return;
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("reviews");
        String id = databaseReference.push().getKey();
        revBank r = new revBank(code, parent, rev);
        databaseReference.child(id).setValue(r).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
               if(task.isSuccessful()){
                   showsnackbar("Your complaint has been succesfully registered");
               }else{
                   showsnackbar("Unable to register your complaint please register afterwards");
               }
            }
        });



    }
}
