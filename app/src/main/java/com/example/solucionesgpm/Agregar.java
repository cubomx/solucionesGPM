package com.example.solucionesgpm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Agregar extends AppCompatActivity {
    Button btnMaestros;
    Button btnClases;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        back = findViewById(R.id.back);
        btnClases =  findViewById(R.id.addClases);
        btnMaestros = findViewById(R.id.addMaestros);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                startActivity(intent);
            }
        });

        btnMaestros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AgregarMaestro.class);
                startActivity(intent);
            }
        });

        btnClases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AgregarClase.class);
                startActivity(intent);
            }
        });
    }
}
