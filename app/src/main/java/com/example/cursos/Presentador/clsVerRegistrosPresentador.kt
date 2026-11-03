
package com.example.cursos.Presentador

import com.example.cursos.Modelo.clsVerRegistrosModelo
import com.example.cursos.Modelo.dataCarreras
import com.example.cursos.Vista.VerRegistrosActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsVerRegistrosPresentador(private val vista: VerRegistrosActivity) {

    private val modelo = clsVerRegistrosModelo()

    private var carrerasLista: List<dataCarreras> = emptyList()

    fun iniciar() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                carrerasLista = modelo.obtenerCarreras()
                vista.mostrarCarreras(carrerasLista)

                seleccionarCarreras(0)

            } catch (e: Exception) {
                vista.mostrarError("Error: " + e.localizedMessage)
            }
        }
    }

    fun seleccionarCarreras(position: Int) {
        if (position >= 0 && position < carrerasLista.size) {
            cargarRegistros(carrerasLista[position].id)
        }
    }

    private fun cargarRegistros(carreraId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = modelo.obtenerRegistros(carreraId)

                vista.mostrarRegistros(respuesta.cursos, respuesta.total)

            } catch (e: Exception) {
                vista.mostrarError(
                    "Error al mostrar registros: " + e.localizedMessage
                )
            }
        }
    }
}
