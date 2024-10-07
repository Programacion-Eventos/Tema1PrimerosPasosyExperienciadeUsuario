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

    override fun doInBackground(vararg params: Void?): Void? {
        for (i in 1..100) {
            Thread.sleep(50) // Simular retraso de red
            publishProgress(i)
        }
        return null
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        values[0]?.let { actualizarProgreso(it) }
    }
}