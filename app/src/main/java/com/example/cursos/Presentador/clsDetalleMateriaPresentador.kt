package com.example.cursos.Presentador

import com.example.cursos.Modelo.clsDetalleMateriaModelo
import com.example.cursos.Modelo.dataDetalleMateria
import com.example.cursos.Vista.DetalleMateriaActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsDetalleMateriaPresentador(private val vista: DetalleMateriaActivity) {

    private val modelo = clsDetalleMateriaModelo()
    fun cargarDetalle(materiaId: Int) {
        CoroutineScope(Dispatchers.Main).launch {

            try {
                val listaDetalle = modelo.obtenerDetalleMateria(materiaId)
                val materia = listaDetalle.firstOrNull()
                if (materia != null) {
                    vista.mostrarDetalle(materia)
                    vista.mostrarImagenes(listaDetalle)
                } else { vista.mostrarError("No se encontró información del curso.")
                }

            } catch (e: Exception) {
                vista.mostrarError("Error al cargar el detalle: " + (e.localizedMessage ?: "Error desconocido"))
            }
        }
    }
}