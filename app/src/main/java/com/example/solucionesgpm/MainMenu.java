package com.example.solucionesgpm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.os.Bundle;

public class MainMenu extends AppCompatActivity {
    Button btnVerClase;
    Button btnVerMaestro;
    Button btnAgregarMaestro;
    Button btnAgregarClase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnVerClase = findViewById(R.id.viewClases);
        btnVerMaestro = findViewById(R.id.viewMaestros);
        btnAgregarMaestro = findViewById(R.id.addMaestros);
        btnAgregarClase = findViewById(R.id.addClases);
        btnVerClase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, VerClase.class);
                startActivity(intent);
            }
        });
        btnVerMaestro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenu.this, VerMaestro.class);
                startActivity(intent);
            }
        });
        btnAgregarMaestro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),  AgregarMaestro.class);
                startActivity(intent);
            }
        });
        btnAgregarClase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AgregarClase.class);
                startActivity(intent);
            }
        });
    }
}
