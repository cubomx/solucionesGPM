package com.example.solucionesgpm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AgregarClase extends AppCompatActivity {
    EditText mNombre;
    EditText ciclo;
    EditText nivel;
    EditText salon;
    EditText horario;
    EditText maestro;
    Button btnAdd;
    TextView titulo;
    DatabaseReference databaseClases;
    FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_clase);
        databaseClases = FirebaseDatabase.getInstance().getReference("ver");

        /*
            Getting the credentials from the user
         */
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        /*
            Getting all the data from the from
         */
        mNombre = (EditText) findViewById(R.id.NombreM);
        ciclo = (EditText) findViewById(R.id.ciclo);
        nivel = (EditText) findViewById(R.id.nivel);
        salon = (EditText) findViewById(R.id.salon);
        horario = (EditText) findViewById(R.id.horario);
        maestro = (EditText) findViewById(R.id.maestroClase);
        btnAdd = (Button) findViewById(R.id.btnAddClase);
        titulo =(TextView) findViewById(R.id.txtAgregarClase);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClase();
            }
        });
    }
    public void addClase(){
        /*
            Getting the values of the user input in order to create the Object
         */
        String name = mNombre.getText().toString().trim();
        String cicloV = ciclo.getText().toString().trim();
        String nivelV = nivel.getText().toString().trim();
        String salonV = salon.getText().toString().trim();
        String maestroV = maestro.getText().toString().trim();
        String horarioV = horario.getText().toString().trim();
        if(!name.isEmpty() && !cicloV.isEmpty()&& !nivelV.isEmpty()&& !salonV.isEmpty()&& !horarioV.isEmpty()){
            String user_ = user.getEmail().replace(".",     "_");
            /*
                Making the reference to the user database part
             */
            DatabaseReference ref = databaseClases.child(user_).getRef();
            String idClase = ref.push().getKey();
            Clase clase = new Clase(idClase, salonV, nivelV, name, maestroV, horarioV);
            /*
                Creating the Object in the Firebase Database
             */
            ref.child(idClase).setValue(clase);
            titulo.setText("Clase agregada");
        }
    }
}
