package com.example.tema1primerospasosyexperienciadeusuario;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Call setPersistenceEnabled before any other Firebase operation
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}