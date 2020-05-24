package com.example.solucionesgpm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Manual extends AppCompatActivity {
    private TextView info;
    private Button verInfo;
    private Button agregarInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        info = findViewById(R.id.txtInfo);

        // SCROLL movement to the text if it doesn't fit
        info.setMovementMethod(new ScrollingMovementMethod());

        changeText(R.raw.ver);

        verInfo = findViewById(R.id.verBtn);

        verInfo.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                changeText(R.raw.ver);
            }
        });

        agregarInfo = findViewById(R.id.agregarBtn);
        agregarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeText(R.raw.agregar);
            }
        });


    }

    private void changeText(int path){
        String data = "";

        StringBuilder buffer = new StringBuilder();

        // Get the info from the text files
        InputStream inputStream = this.getResources().openRawResource(path);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        if (inputStream != null){
            try {
                while( (data = reader.readLine()) != null){
                    buffer.append(data + "\n");
                }
                // Sending to the view
                info.setText(buffer);
                inputStream.close();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
