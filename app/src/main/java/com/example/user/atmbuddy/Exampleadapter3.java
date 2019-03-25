package com.example.user.atmbuddy;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Exampleadapter3 extends RecyclerView.Adapter<Exampleadapter3.ExampleViewHolder> {
    private ArrayList<Exampleitem3> mExampleList;
    private Exampleadapter3.OnItemClickListener mListener;
    private Exampleadapter3.OnItemLongClickListener onItemLongClickListener;
    public interface OnItemLongClickListener{
        void onItemLongClick(int pos);
    }
    public interface OnItemClickListener{
        void onItemClick(int pos);
    }
    public void setOnItemLongClickListener(Exampleadapter3.OnItemLongClickListener longlistener){onItemLongClickListener = longlistener;}
    public void setOnItemClickListener(Exampleadapter3.OnItemClickListener listener){
        mListener = listener;
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public ExampleViewHolder(View itemView, final Exampleadapter3.OnItemClickListener listener, final Exampleadapter3.OnItemLongClickListener longlistener) {
            super(itemView);
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


    public Exampleadapter3(ArrayList<Exampleitem3> exampleList){
        mExampleList = exampleList;
    }


    @NonNull
    @Override
    public Exampleadapter3.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item3, parent, false);
        Exampleadapter3.ExampleViewHolder evh = new Exampleadapter3.ExampleViewHolder(v, mListener, onItemLongClickListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull Exampleadapter3.ExampleViewHolder holder, int i) {
        Exampleitem3 currentItem = mExampleList.get(i);
        holder.mTextView1.setText(currentItem.getCode());
        holder.mTextView2.setText(currentItem.getMsg());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
