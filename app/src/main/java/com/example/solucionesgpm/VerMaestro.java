package com.example.solucionesgpm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class VerMaestro extends AppCompatActivity {
    private RecyclerView lista;
    private RecyclerView.LayoutManager manager;
    DatabaseReference db;

    private void getTeachers(){
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                DataSnapshot data = dataSnapshot;
                System.out.println(data.getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println("Failed to read value.");
            }
        });
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.activity_ver_maestro);
        lista = findViewById(R.id.lista_maestros);
        manager = new LinearLayoutManager(this);
        lista.setLayoutManager(manager);
        getTeachers();
    }
}
