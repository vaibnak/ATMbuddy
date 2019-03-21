package com.example.user.atmbuddy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void custact(View view) {
        Intent intent = new Intent(getApplicationContext(), customer.class);
        startActivity(intent);
    }

    public void bnkact(View view) {
        Intent intent = new Intent(getApplicationContext(), bank.class);
        startActivity(intent);
    }
}
