package com.example.tema1primerospasosyexperienciadeusuario.ui.theme

import android.os.AsyncTask


class SegundoPlano(
    private val actualizarProgreso: (Int) -> Unit,
    private val tareaFinalizada: () -> Unit
) : AsyncTask<Void, Int, Void>()  {

    override fun onPreExecute() {
        super.onPreExecute()
        // Inicializar elementos de la UI si es necesario
    }
}