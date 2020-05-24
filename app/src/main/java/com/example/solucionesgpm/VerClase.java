package com.example.solucionesgpm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class VerClase extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager manager;
    private RecylerAdapter adapter;
    private ArrayList selectedItems;
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
        System.out.println("USER:::" + user_);
        /*
            Making the reference to the user database place
         */
        db = FirebaseDatabase.getInstance().getReference("clases");
        final DatabaseReference ref = db.child(user_).getRef();



        /*
            List of items display
         */
        recyclerView = findViewById(R.id.rview);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        Button btnSelect =  findViewById(R.id.btnSelectClass);
        final Dialog dialog = showDialog(savedInstanceState, ref, this);

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

    }

    private Dialog showDialog(Bundle savedInstanceState, final DatabaseReference ref, final Context ctx){
        selectedItems = new ArrayList();  // Where we track the selected items
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Set the dialog title
        builder.setTitle(R.string.pick_toppings)
                .setMultiChoiceItems(R.array.classes, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                if (isChecked) {
                                    // If the user checked the item, add it to the selected items
                                    selectedItems.add(which);
                                } else if (selectedItems.contains(which)) {
                                    // Else, if the item is already in the array, remove it
                                    selectedItems.remove(Integer.valueOf(which));
                                }
                            }
                        })
                // Set the action buttons
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if (selectedItems.size() == 3){
                            Collections.sort(selectedItems);
                            // Retrieving the data about the classes and showing to the user
                            getClasses(ref, selectedItems, ctx);
                        }
                        else{
                            // Telling the user that exactly 3 options must be selected
                            Messages.askFor(ctx, "Debes seleccionar exactamente 3");
                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });

        return builder.create();
    }



    private void getClasses (DatabaseReference ref, final ArrayList<Integer> opciones, final Context ctx){
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*
                    Getting all the objects from the user (in this case, all the classes)
                 */
                ArrayList<String> clases = new ArrayList<>();
                if (dataSnapshot.exists()){
                    Map<String, Object> data = (Map<String, Object>) dataSnapshot.getValue();
                    for (Map.Entry<String, Object> entry : data.entrySet()){

                        Map info = (Map) entry.getValue();
                        // info.get(<specific key>);

                    /*
                        Getting the values from the options selected by the user
                     */
                        System.out.println(info.toString());
                        StringBuilder stringBuilder = new StringBuilder();
                        for (Integer item : opciones){
                            switch (item){
                                case 0:
                                    System.out.println(info.get("name").toString());
                                    stringBuilder.append(info.get("name").toString() + "\n");
                                    break;
                                case 1:
                                    stringBuilder.append(info.get("level").toString() +  "\n");
                                    break;
                                case 2:
                                    stringBuilder.append(info.get("classroom").toString() + "\n");
                                    break;
                                case 3:
                                    stringBuilder.append(info.get("time").toString()+ "\n");
                                    break;
                                case 4:
                                    stringBuilder.append(info.get("professor").toString() + "\n");
                                    break;
                                default:
                                    System.out.println("\n\nSIII\n\n");
                                    break;
                            }
                        }
                        clases.add(stringBuilder.toString());
                    }

                }

                if (clases.size() == 0){
                    clases.add("No se encontro clase registrada");
                }
                Utilities.showItems(clases, adapter, recyclerView);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Messages.databaseError(ctx, error.toString());
            }
        });
    }
}
