package com.example.user.atmbuddy;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Exampleadapter extends RecyclerView.Adapter<Exampleadapter.ExampleViewHolder> {
    private  ArrayList<Exampleitem> mExampleList;
    private OnItemClickListener mListener;
    private OnItemLongClickListener onItemLongClickListener;
    public interface OnItemLongClickListener{
        void onItemLongClick(int pos);
    }
    public interface OnItemClickListener{
        void onItemClick(int pos);
    }
    public void setOnItemLongClickListener(OnItemLongClickListener longlistener){onItemLongClickListener = longlistener;}
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView3;
        public TextView mTextView1;
        public TextView mTextView2;
        public ExampleViewHolder(View itemView, final OnItemClickListener listener, final OnItemLongClickListener longlistener) {
            super(itemView);
            mTextView3 = itemView.findViewById(R.id.textView3);
            mTextView1 = itemView.findViewById(R.id.textView1);
            mTextView2 = itemView.findViewById(R.id.textView2);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(longlistener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            longlistener.onItemLongClick(position);
                            return true;
                        }
                    }
                    return false;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                       int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


    public Exampleadapter(ArrayList<Exampleitem> exampleList){
        mExampleList = exampleList;
    }


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener, onItemLongClickListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull Exampleadapter.ExampleViewHolder holder, int i) {
        Exampleitem currentItem = mExampleList.get(i);
        holder.mTextView1.setText(currentItem.getName());
        holder.mTextView2.setText(currentItem.getStatus());
        holder.mTextView3.setText(Double.toString(currentItem.getDist())+"km");

        }


    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
