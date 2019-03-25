package com.example.user.atmbuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getApplicationContext().getSharedPreferences("com.example.user.atmbuddy", Context.MODE_PRIVATE);
        String bname = sharedPreferences.getString("bname",null);
        String cname = sharedPreferences.getString("cname", null);
        if(bname != null){
            Intent intent = new Intent(getApplicationContext(), customerActivity.class);
            intent.putExtra("bname", bname);
            startActivity(intent);
        }
        if(cname != null){
            Intent intent = new Intent(getApplicationContext(), bankActivity.class);
            intent.putExtra("cname", cname);
            startActivity(intent);
        }

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
