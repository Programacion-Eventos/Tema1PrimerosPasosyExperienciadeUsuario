package com.example.tema1primerospasosyexperienciadeusuario.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SQLite extends SQLiteOpenHelper {

    private static final String NOMBRE_BASE_DATOS = "nombres.db";
    private static final int VERSION_BASE_DATOS = 1;
    private static final String TABLA_NOMBRES = "nombres";
    private static final String COLUMNA_ID = "id";
    private static final String COLUMNA_NOMBRE = "nombre";

    public SQLite(Context contexto) {
        super(contexto, NOMBRE_BASE_DATOS, null, VERSION_BASE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String crearTabla = "CREATE TABLE " + TABLA_NOMBRES + " (" +
                COLUMNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMNA_NOMBRE + " TEXT)";
        db.execSQL(crearTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_NOMBRES);
        onCreate(db);
    }

    public void insertarNombre(String nombre) {
        SQLiteDatabase db = this.getWritableDatabase();
        String insertar = "INSERT INTO " + TABLA_NOMBRES + " (" + COLUMNA_NOMBRE + ") VALUES ('" + nombre + "')";
        db.execSQL(insertar);
    }

    // Exportar la base de datos a almacenamiento interno
    public void exportarBaseDatosAAlmacenamientoInterno(Context contexto) throws IOException {
        File archivoBaseDatos = contexto.getDatabasePath(NOMBRE_BASE_DATOS);
        File archivoCopia = new File(contexto.getFilesDir(), "copia_" + NOMBRE_BASE_DATOS);

        try (FileInputStream fis = new FileInputStream(archivoBaseDatos);
             FileOutputStream fos = new FileOutputStream(archivoCopia)) {
            byte[] buffer = new byte[1024];
            int longitud;
            while ((longitud = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, longitud);
            }
        }
    }

    // Importar la base de datos desde almacenamiento interno
    public void importarBaseDatosDesdeAlmacenamientoInterno(Context contexto) throws IOException {
        File archivoBaseDatos = contexto.getDatabasePath(NOMBRE_BASE_DATOS);
        File archivoCopia = new File(contexto.getFilesDir(), "copia_" + NOMBRE_BASE_DATOS);

        try (FileInputStream fis = new FileInputStream(archivoCopia);
             FileOutputStream fos = new FileOutputStream(archivoBaseDatos)) {
            byte[] buffer = new byte[1024];
            int longitud;
            while ((longitud = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, longitud);
            }
        }
    }
}