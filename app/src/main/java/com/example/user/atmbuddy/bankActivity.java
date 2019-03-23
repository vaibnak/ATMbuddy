package com.example.user.atmbuddy;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class bankActivity extends AppCompatActivity {
/* This is basically customer activity as this will be displayed whern customer will login, all banks and nearst banks are two tabs of it*/
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager mviewPager;
    private RecyclerView mRecyclerView;
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
        Intent intent = getIntent();
        tabLayout = findViewById(R.id.tablayout);
        appBarLayout = findViewById(R.id.appbar);

    }


    public void calculatedist(int p){
        Location locationA = new Location("point A");
        locationA.setLongitude(A.get(p).lng);
        locationA.setLatitude(A.get(p).lat);
        Location locationB = new Location("point B");
        locationB.setLatitude(22.06);
        locationB.setLongitude(81.68);
        float dist = locationA.distanceTo(locationB);
        Log.i("distance: ", ""+dist);
        dist = dist/1000;
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        float val = Float.parseFloat(decimalFormat.format(dist));
        exampleList.add(new Exampleitem(A.get(p).parent,A.get(p).status,val ));
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
                Log.i("reading ", "finished");
                enter(A);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("error", " data fetching Cancelled: ");
            }
        });
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

                Toast.makeText(bankActivity.this, "clicked: "+ pos, Toast.LENGTH_SHORT).show();
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
}