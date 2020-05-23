package com.example.solucionesgpm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ver extends AppCompatActivity {
    Button btnVerClase;
    Button btnVerMaestro;
    Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        btnVerClase = findViewById(R.id.viewClases);
        btnVerMaestro = findViewById(R.id.viewMaestros);
        btnRegresar = findViewById(R.id.regresar_ver);

        btnVerClase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ver.this, VerClase.class);
                startActivity(intent);
            }
        });
        btnVerMaestro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ver.this, VerMaestro.class);
                startActivity(intent);
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Ver.this, MainMenu.class);
                startActivity(intent);
            }
        });
    }
}
