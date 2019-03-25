package com.example.user.atmbuddy;

import android.content.Intent;
import android.os.Handler;
import android.os.ResultReceiver;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    Button button;
    private  AddressResultReceiver resultReceiver;
    String addr;
    int code;
    String bnkname;
    String status;

    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler){
            super(handler);
        }
        @Override
        protected  void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultData == null){
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
        bnkname = intent.getStringExtra("bname");
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("atms");
        editText1 = findViewById(R.id.code);
        editText2 = findViewById(R.id.status);
        editText3 = findViewById(R.id.addr);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.btn_add);
    }

    public void clickit(View view) {
        textView.setText("adding....");
        code = Integer.parseInt(editText1.getText().toString());
        status = editText2.getText().toString();
        addr = editText3.getText().toString();
        startIntentService();
    }
}
