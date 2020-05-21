package com.example.solucionesgpm;

import android.content.Context;
import android.widget.Toast;

public class Messages {

    public static void askFor(Context ctx, String message){
        Toast toast = Toast.makeText(ctx, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void warnUser(Context ctx, String message){
        Toast toast = Toast.makeText(ctx, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
