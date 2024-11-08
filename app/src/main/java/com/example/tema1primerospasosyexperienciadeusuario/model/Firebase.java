package com.example.tema1primerospasosyexperienciadeusuario.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase {
    private final DatabaseReference database;

    public Firebase() {
        database = FirebaseDatabase.getInstance("https://taller-e86f1-default-rtdb.europe-west1.firebasedatabase.app/").getReference();
    }

    public void saveNombre(String nombre) {
        String key = database.child("nombres").push().getKey();
        if (key != null) {
            database.child("nombres").child(key).setValue(nombre);
        }
    }

    public void borrarTodosLosNombres() {
        database.child("nombres").removeValue();
    }
}