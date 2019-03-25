package com.example.user.atmbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

public class review extends Fragment {
    DatabaseReference mDatabase;
    private RecyclerView mRecyclerView;
    private Exampleadapter3 mAdapter;
    private  RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Exampleitem3> exampleList = new ArrayList<>();
    final ArrayList<revBank> A = new ArrayList<>();
    View view;
    String bnkname;
    public review() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_review, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        bnkname = getActivity().getIntent().getStringExtra("bname");
        createExampleList();
        buildRecyclerView();
    }

    public void enter(ArrayList<revBank> A){
        for(int i = 0;i < A.size(); i++){
            Log.i("values: ",A.get(i).code+A.get(i).review );
            exampleList.add(new Exampleitem3(A.get(i).code,A.get(i).review));
        }
        mAdapter.notifyDataSetChanged();
    }

    public void createExampleList(){
        exampleList.clear();
        if(mAdapter != null)
            mAdapter.notifyDataSetChanged();
        A.clear();
        mDatabase = FirebaseDatabase.getInstance().getReference("reviews");
        Query query = mDatabase.orderByChild("parent").equalTo(bnkname);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    revBank a = postSnapshot.getValue(revBank.class);
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

    public void buildRecyclerView(){
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new Exampleadapter3(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new Exampleadapter3.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
//                changeItem(pos, "clicked");
            }
        });
        mAdapter.setOnItemLongClickListener(new Exampleadapter3.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int pos) {

            }
        });
    }
}
