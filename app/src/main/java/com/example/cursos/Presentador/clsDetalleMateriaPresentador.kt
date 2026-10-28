package com.example.cursos.Presentador

import com.example.practicacursos.Modelo.clsDetalleMateriaModelo
import com.example.practicacursos.Modelo.dataDetalleMateria
import com.example.practicacursos.Vista.DetalleMateriaActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsDetalleMateriaPresentador(private val vista: DetalleMateriaActivity) {
    private val modelo = clsDetalleMateriaModelo()

    fun cargarDetalle(materiaId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val listaImagenes: List<dataDetalleMateria> = modelo.obtenerDetalleMateria(materiaId)
                vista.mostrarImagenes(listaImagenes)
            } catch (e: Exception) {
                vista.mostrarError("Error al cargar detalle: " + e.localizedMessage)
            }
        }
    }
}