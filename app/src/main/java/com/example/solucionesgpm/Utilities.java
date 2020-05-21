package com.example.solucionesgpm;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Utilities {

    public static void showItems(ArrayList<String> items, RecylerAdapter adapter, RecyclerView recyclerView){
        adapter = new RecylerAdapter(items);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }
}
