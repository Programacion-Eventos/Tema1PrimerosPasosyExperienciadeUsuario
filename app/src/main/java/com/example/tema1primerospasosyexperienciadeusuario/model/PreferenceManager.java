package com.example.tema1primerospasosyexperienciadeusuario.model;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private SharedPreferences sharedPreferences;

    public PreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
    }

    public void saveUserNameAndColor(String name, int color) {
        sharedPreferences.edit().putString("user_name", name).putInt("background_color_" + name, color).apply();
    }

    public String getUserName() {
        return sharedPreferences.getString("user_name", "");
    }

    public int getBackgroundColor(String name) {
        return sharedPreferences.getInt("background_color_" + name, android.graphics.Color.GRAY);
    }
}