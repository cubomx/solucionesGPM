package com.example.solucionesgpm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AgregarMaestro extends AppCompatActivity {
    EditText mNombre;
    EditText edad;
    EditText clase;
    EditText sexo;
    Button btnAdd;
    TextView titulo;
    DatabaseReference databaseClases;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_maestro);
        databaseClases = FirebaseDatabase.getInstance().getReference();
        mNombre = (EditText) findViewById(R.id.NombreM);
        edad = (EditText) findViewById(R.id.edad);
        clase = (EditText) findViewById(R.id.clases);
        sexo = (EditText) findViewById(R.id.sexo);
        btnAdd = (Button) findViewById(R.id.email_sign_in_button);
        titulo =(TextView) findViewById(R.id.textView1);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMaestro();
            }
        });
    }
    public void addMaestro(){
        String name = mNombre.getText().toString().trim();
        String edadV = edad.getText().toString().trim();
        String claseV = clase.getText().toString().trim();
        String sexoV = sexo.getText().toString().trim();
        if(!name.isEmpty() && !edadV.isEmpty()&& !claseV.isEmpty() && !sexoV.isEmpty()){
            String idMaestro = databaseClases.push().getKey();
            Maestro maestro = new Maestro(idMaestro,edadV,claseV,name,sexoV);
            databaseClases.child(idMaestro).setValue(maestro);
            titulo.setText("maestro agregada");
        }
    }
}
