package com.example.solucionesgpm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.ArrayList;

public class RecylerAdapter extends RecyclerView.Adapter <RecylerAdapter.MyViewHolder>  {
    private ArrayList<Clase> clases;

    public  RecylerAdapter (ArrayList<Clase> clases){
        this.clases = clases;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
            Creating the text where the info about the classes or the professors
            that is going to be display it.
         */
        TextView txtView =  (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(txtView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        /*
            TODO: Modify the ArrayList parameter to String to receive anything to put on the cards
         */
        holder.CardText.setText(clases.get(position).getName() + "\n" + clases.get(position).getProfessor());
    }

    @Override
    public int getItemCount() {
        return clases.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView CardText;
        public MyViewHolder(TextView itemView) {
            super(itemView);
            CardText = itemView;
        }
    }
}
