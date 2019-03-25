package com.example.user.atmbuddy;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Exampleadapter2  extends RecyclerView.Adapter<Exampleadapter2.ExampleViewHolder> {
    private ArrayList<Exampleitem2> mExampleList;
    private Exampleadapter2.OnItemClickListener mListener;
    private Exampleadapter2.OnItemLongClickListener onItemLongClickListener;
    public interface OnItemLongClickListener{
        void onItemLongClick(int pos);
    }
    public interface OnItemClickListener{
        void onItemClick(int pos);
    }
    public void setOnItemLongClickListener(Exampleadapter2.OnItemLongClickListener longlistener){onItemLongClickListener = longlistener;}
    public void setOnItemClickListener(Exampleadapter2.OnItemClickListener listener){
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public CardView cardView;
        public ExampleViewHolder(View itemView, final Exampleadapter2.OnItemClickListener listener, final OnItemLongClickListener longlistener) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.textView1);
            mTextView2 = itemView.findViewById(R.id.textView2);
            cardView = itemView.findViewById(R.id.relativelayout);
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


    public Exampleadapter2(ArrayList<Exampleitem2> exampleList){
        mExampleList = exampleList;
    }


    @NonNull
    @Override
    public Exampleadapter2.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.exampleitem2, parent, false);
        Exampleadapter2.ExampleViewHolder evh = new Exampleadapter2.ExampleViewHolder(v, mListener, onItemLongClickListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull Exampleadapter2.ExampleViewHolder holder, int i) {
        Exampleitem2 currentItem = mExampleList.get(i);
        holder.mTextView1.setText(currentItem.getCode());
        holder.mTextView2.setText(currentItem.getAddress());
        if(currentItem.getStatus().equals("working"))
        holder.cardView.setCardBackgroundColor(Color.parseColor("#a0ffab"));
        else{
            holder.cardView.setCardBackgroundColor(Color.parseColor("#ffd3d1"));
        }
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}

