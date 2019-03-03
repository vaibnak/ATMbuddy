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
    private  AddressResultReceiver resultReceiver;

    class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler){
            super(handler);
        }
        @Override
        protected  void onReceiveResult(int resultCode, Bundle resultData) {
            if (resultData == null){
                return;
            }
            double lat  = resultData.getDouble("lat");
            double lng  = resultData.getDouble("lng");
            String id = databaseReference.push().getKey();
            bnk b = new bnk(bnkname, bnkcode, bnkaddr,lat, lng);
            databaseReference.child(id).setValue(b);

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
        setContentView(R.layout.activity_bank);
        resultReceiver = new AddressResultReceiver(new android.os.Handler());
        Intent intent = getIntent();
        editText1 = findViewById(R.id.bnkname);
        editText2 = findViewById(R.id.bnkcode);
        editText3 = findViewById(R.id.bnkaddr);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("banks");

    }

    public void register(View view) {
        bnkname = editText1.getText().toString();
        bnkcode = Integer.parseInt(editText2.getText().toString());
        bnkaddr = editText3.getText().toString();
        addr = bnkaddr;

        startIntentService();
    }
}
