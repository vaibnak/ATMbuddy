package com.example.user.atmbuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.ResultReceiver;
import android.provider.ContactsContract;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class addATM extends AppCompatActivity {
    TextView textView;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    RelativeLayout relativeLayout;
    Button button;
    private  AddressResultReceiver resultReceiver;
    String addr;
    String  code;
    String bnkname;
    String status;

    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler){
            super(handler);
        }
        @Override
        protected  void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultData == null){
                showsnackbar("This service is not working properly, try again later");
                return;
            }
            textView.setText("Added");
            double lat = resultData.getDouble("lat");
            double lng = resultData.getDouble("lng");
            String id = databaseReference.push().getKey();
            atm a = new atm(bnkname,code,addr,status, lat, lng);
            databaseReference.child(id).setValue(a);

        }
    }
    protected void startIntentService() {
        Log.i("starting ", "Intent");
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra("receiver", resultReceiver);
        intent.putExtra("address", addr);
        startService(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_atm);
        resultReceiver = new AddressResultReceiver(new android.os.Handler());
        Intent intent = getIntent();
        relativeLayout = findViewById(R.id.relativelayout);
        bnkname = intent.getStringExtra("bname");
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("atms");
        editText1 = findViewById(R.id.code);
        editText2 = findViewById(R.id.status);
        editText3 = findViewById(R.id.addr);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.btn_add);
    }

    public void showsnackbar(String msg){
        Snackbar snackbar = Snackbar.make(relativeLayout, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public boolean check(String code,String status, String address ){
        if(code.isEmpty() || status.isEmpty() || address.isEmpty()){
            return false;
        }
        return true;
    }

    public void clickit(View view) {
        code = editText1.getText().toString();
        status = editText2.getText().toString();
        addr = editText3.getText().toString();
        if(check(code, status, addr)){
            textView.setText("adding....");
            startIntentService();
        }else{
            showsnackbar("Please enter correct details");
        }

    }

    @Override
    public void onBackPressed() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.user.atmbuddy", Context.MODE_PRIVATE);
        Intent intent = new Intent(getApplicationContext(),customerActivity.class);
        intent.putExtra("bname",sharedPreferences.getString("bname",null));
        startActivity(intent);

    }
}
