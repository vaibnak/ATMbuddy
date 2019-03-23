package com.example.user.atmbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class myATMS extends Fragment {
    DatabaseReference mDatabase;
    private RecyclerView mRecyclerView;
    private Exampleadapter2 mAdapter;
    private  RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Exampleitem2> exampleList = new ArrayList<>();
    final ArrayList<atm> A = new ArrayList<>();
    View view;
    FloatingActionButton floatingActionButton;
    public myATMS() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_myatm, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        floatingActionButton = view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                A.clear();
                Intent intent = new Intent(getActivity(),addATM.class);
                startActivity(intent);

            }
        });
        createExampleList();
        buildRecyclerView();
    }
    public void enter(ArrayList<atm> A){
        for(int i = 0;i < A.size(); i++){
            Log.i("values: ",A.get(i).parent+A.get(i).status );
            exampleList.add(new Exampleitem2(A.get(i).code,A.get(i).address));
        }
        mAdapter.notifyDataSetChanged();
    }

    public void createExampleList(){
        exampleList.clear();
        if(mAdapter != null)
        mAdapter.notifyDataSetChanged();
        A.clear();
        mDatabase = FirebaseDatabase.getInstance().getReference("atms");
        Query query = mDatabase.orderByChild("parent").equalTo("SBI");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    atm a = postSnapshot.getValue(atm.class);
                    A.add(a);
//                    Log.i("item added", "ya");
                }
                enter(A);
            }

                @Override
                public void onCancelled (DatabaseError databaseError){
                    Log.i("error", " data fetching Cancelled: ");
                }
            });
        }

//        exampleList.add(new Exampleitem2( "Line 1", "Line 2"));
//        exampleList.add(new Exampleitem2( "Line 3", "Line 4"));
//        exampleList.add(new Exampleitem2("Line 5", "Line 6"));

    public void buildRecyclerView(){
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new Exampleadapter2(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new Exampleadapter2.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
//                changeItem(pos, "clicked");
            }
        });
        mAdapter.setOnItemLongClickListener(new Exampleadapter2.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int pos) {
                int cd = A.get(pos).code;
                Log.i("cd: ", ""+cd);
                Query query = FirebaseDatabase.getInstance().getReference("atms").orderByChild("code").equalTo(cd);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                            atm u = singleSnapshot.getValue(atm.class);
                            String val;
                            Log.i("status: " ,u.status );
                            if(u.status.equals("working")){
                                val = "not working";
                            }else{
                                val = "working";
                            }
                            Log.i("found","it");
                            singleSnapshot.getRef().child("status").setValue(val);
                            //signout on workdone
                        }
                        createExampleList();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.i("msg: ", "error in database");
                    }
                });

            }
        });
    }
}
