package com.example.solucionesgpm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_maestro);
        databaseClases = FirebaseDatabase.getInstance().getReference("maestros");

        /*
            Getting the credentials from the user
         */
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        /*
            Getting all the data from the from
         */
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
        /*
            Getting the values of the user input in order to create the Object
         */
        String name = mNombre.getText().toString().trim();
        String edadV = edad.getText().toString().trim();
        String claseV = clase.getText().toString().trim();
        String sexoV = sexo.getText().toString().trim();
        if(!name.isEmpty() && !edadV.isEmpty()&& !claseV.isEmpty() && !sexoV.isEmpty()){
            String user_ = user.getEmail().replace(".",     "_");
            /*
                Making the reference to the user database part
             */
            DatabaseReference ref = databaseClases.child(user_).getRef();
            String idMaestro = ref.push().getKey();
            Maestro maestro = new Maestro(idMaestro, edadV, claseV, name, sexoV);
            /*
                Creating the Object in the Firebase Database
             */
            ref.child(idMaestro).setValue(maestro);
            titulo.setText("Maestro agregado");
        }
    }
}
