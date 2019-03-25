package com.example.user.atmbuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class bankActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
/* This is basically customer activity as this will be displayed whern customer will login, all banks and nearst banks are two tabs of it*/
    private TextView textView;
    private AppBarLayout appBarLayout;
    private ViewPager mviewPager;
    SharedPreferences sharedPreferences;
    private RecyclerView mRecyclerView;
    private LinearLayout relativeLayout;
    private Exampleadapter mAdapter;
    private DatabaseReference mDatabase;
    private  RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Exampleitem> exampleList = new ArrayList<>();
    final ArrayList<atm> A = new ArrayList<>();
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank2);
        createExampleList();
        buildRecyclerView();
        sharedPreferences = getApplicationContext().getSharedPreferences("com.example.user.atmbuddy", Context.MODE_PRIVATE);
        Intent intent = getIntent();
        String nm = intent.getStringExtra("cname");
        textView = findViewById(R.id.apptitle);
        textView.setText(nm);
        appBarLayout = findViewById(R.id.appbar);
        relativeLayout = findViewById(R.id.relativelayout);
    }


    public void calculatedist(int p){
        Location locationA = new Location("point A");
        locationA.setLongitude(A.get(p).lng);
        locationA.setLatitude(A.get(p).lat);
        Location locationB = new Location("point B");
        float lat = sharedPreferences.getFloat("lat",(float)20.10);
        float lng = sharedPreferences.getFloat("lng",(float)82.14);
        locationB.setLatitude(lat);
        locationB.setLongitude(lng);
        double dist = locationA.distanceTo(locationB);
        Log.i("distance: ", ""+dist);
        dist = dist/1000;
        String val = String.format("%.2f", dist);
        exampleList.add(new Exampleitem(A.get(p).parent,A.get(p).status,Double.parseDouble(val)));
    }
    public void enter(ArrayList<atm> A){
        Log.i("size: ", Integer.toString(A.size()));
        for(int i = 0;i < A.size(); i++){
            Log.i("values: ",A.get(i).parent+A.get(i).status );
            calculatedist(i);
        }
        mAdapter.notifyDataSetChanged();
    }

    public void createExampleList(){
        databaseReference = FirebaseDatabase.getInstance().getReference("atms");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot nameshot : dataSnapshot.getChildren()){
                    atm u = nameshot.getValue(atm.class);
                    A.add(u);
                }
                enter(A);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                showsnackbar("Error in database operation");
            }
        });
            }



    public void showsnackbar(String msg){
        Snackbar snackbar = Snackbar.make(relativeLayout, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Exampleadapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new Exampleadapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {

                Log.i("lat, lng: ", "google.navigation:q="+A.get(pos).lat+","+A.get(pos).lng);
                Uri gmmIntentUri = Uri.parse("google.navigation:q="+A.get(pos).lat+","+A.get(pos).lng);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        mAdapter.setOnItemLongClickListener(new Exampleadapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int pos) {
                Intent intent = new Intent(getApplicationContext(), saySomething.class);
                intent.putExtra("code",A.get(pos).code);
                intent.putExtra("parent",A.get(pos).parent);
                startActivity(intent);
            }
        });
    }


    public void popup(View view) {
        PopupMenu popup = new PopupMenu(this, view);

        // This activity implements OnMenuItemClickListener
        popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) this);
        popup.inflate(R.menu.logout);
        popup.show();
    }


    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                sharedPreferences.edit().clear().apply();
                startActivity(intent);
                return true;

            default:
                return false;
        }
    }
    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
