package com.example.user.atmbuddy;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class customer extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    EditText editText3;
    RelativeLayout relativeLayout;
    Button button;
    float lng;
    float lat;
    SharedPreferences sharedPreferences;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fusedLocationProviderClient.getLastLocation()
                        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if(location != null){
                                    lng = (float)location.getLongitude();
                                    lat = (float)location.getLatitude();
                                    sharedPreferences.edit().putFloat("lat",lat).apply();
                                    sharedPreferences.edit().putFloat("lng",lng).apply();
                                    Log.i("longitude, latitude", location.getLatitude()+" "+location.getLongitude());
                                }else{
                                    showsnackbar("Location null, Please enable location service");
                                }
                            }
                        });
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        Intent intent = getIntent();
        editText1 = findViewById(R.id.name);
        editText2 = findViewById(R.id.phn);
        editText3 = findViewById(R.id.addr);
        sharedPreferences = getApplicationContext().getSharedPreferences("com.example.user.atmbuddy", Context.MODE_PRIVATE );
        relativeLayout = findViewById(R.id.relativelayout);
        button = findViewById(R.id.btn_reg);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("customer");
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }else{
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if(location != null){
                                lng = (float)location.getLongitude();
                                lat = (float)location.getLatitude();
                                sharedPreferences.edit().putFloat("lat",lat).apply();
                                sharedPreferences.edit().putFloat("lng",lng).apply();
                                Log.i("longitude, latitude", location.getLatitude()+" "+location.getLongitude());
                            }else{
                                showsnackbar("Location null, Please enable location service");
                            }
                        }
                    });
        }
    }


    public void showsnackbar(String msg){
        Snackbar snackbar = Snackbar.make(relativeLayout, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public boolean check(String name, String phn, String address){
        if(name.isEmpty() || phn.length() != 10 || address.isEmpty()){
            return false;
        }
        return true;
    }




    public void click(View view) {
        String name = editText1.getText().toString();
        String phoneno = editText2.getText().toString();
        String address = editText3.getText().toString();
        if(check(name, phoneno, address)){
            sharedPreferences.edit().putString("cname", name).apply();
            Log.i(name + phoneno + address, " "+lat + lng);
            cst c = new cst(name, phoneno, address, lat, lng);
            String id = databaseReference.push().getKey();
            databaseReference.child(id).setValue(c);
            Intent intent = new Intent(getApplicationContext(), bankActivity.class);
            intent.putExtra("cname",name);
            startActivity(intent);
        }else{
            showsnackbar("Please enter correct details");
        }

    }
}
