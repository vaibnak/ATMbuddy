package com.example.user.atmbuddy;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class FetchAddressIntentService extends IntentService {
    Geocoder geocoder = new Geocoder(this, Locale.getDefault());
    protected ResultReceiver receiver;
    public FetchAddressIntentService() {
        super("fetchAddressintentService");
    }
    private void deliverResultToReceiver(int resultCode, double lat, double lng) {
        Bundle bundle = new Bundle();
        bundle.putDouble("lat", lat);
        bundle.putDouble("lng", lng);
        receiver.send(resultCode, bundle);
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if(intent == null){
            return;
        }
        Log.i("intent is ", "called ");
        receiver = intent.getParcelableExtra("receiver");
        String errorMessage = "";
        String  addr = intent.getStringExtra("address");
        Log.i("from fetch: ","this is"+ addr);
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocationName(addr, 1);
        }catch (IOException ioException) {
            errorMessage = "service not available";
            Log.i("error", errorMessage);
        }catch (IllegalArgumentException arg){
            errorMessage = "illegal arguments";
            Log.i("error", errorMessage);
        }

        if(addresses == null || addresses.size() == 0){
            if(errorMessage.isEmpty()){
                errorMessage = "no address found";
                Log.i("error", errorMessage);
            }

        }else{
            Address address = addresses.get(0);
            double lat = address.getLatitude();
            double lng = address.getLongitude();
            Log.i("address", lat+" and "+ lng+" ");
            deliverResultToReceiver(1, lat, lng);
        }

    }
}
