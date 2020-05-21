package com.example.solucionesgpm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class VerClase extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private RecylerAdapter adapter;
    DatabaseReference db;
    FirebaseAuth mAuth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_clase);

        /*
            Getting the credentials from the current user
         */
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        String user_ = user.getEmail().replace(".",     "_");

        /*
            Making the reference to the user database place
         */
        db = FirebaseDatabase.getInstance().getReference("clases");
        DatabaseReference ref = db.child(user_).getRef();

        recyclerView = findViewById(R.id.rview);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);


        /*
            This ArrayList is the 3 button selection of what the user wants to see
            on the screen
            TODO: it shouldn't be done by code (ask the user)
         */
        ArrayList<String> opciones = new ArrayList<>();
        opciones.add("name");
        opciones.add("professor");
        getClasses(ref, opciones);
    }

    private void showClases(ArrayList<Clase> clases){
        /*
            Adding the cards to the recyclerView to show all the info related to the classes
         */
        adapter = new RecylerAdapter(clases);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void getClasses (DatabaseReference ref, final ArrayList<String> opciones){
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*
                    Getting all the objects from the user (in this case, all the classes)
                 */
                Map<String, Object> data = (Map<String, Object>) dataSnapshot.getValue();


                ArrayList<Clase> clases = new ArrayList<>();
                for (Map.Entry<String, Object> entry : data.entrySet()){
                    Clase clase = new Clase(null, null, null, null, null, null);
                    Map info = (Map) entry.getValue();
                    // info.get(<specific key>);

                    /*
                        Getting the values from the options selected by the user
                     */
                    for (String item : opciones){
                        switch (item){
                            case "claseId":
                                clase.setclaseId(info.get(item).toString());
                                break;
                            case "classroom":
                                clase.setClassroom(info.get(item).toString());
                                break;
                            case "level":
                                clase.setLevel(info.get(item).toString());
                                break;
                            case "name":
                                clase.setName(info.get(item).toString());
                                break;
                            case "professor":
                                clase.setProfessor(info.get(item).toString());
                                break;
                            case "time":
                                clase.setTime(info.get(item).toString());
                                break;

                        }
                    }
                    clases.add(clase);
                }
                showClases(clases);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                System.out.println("ERROR: ");
            }
        });
    }
}
