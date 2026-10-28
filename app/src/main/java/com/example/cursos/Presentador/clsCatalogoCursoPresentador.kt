package com.example.cursos.Presentador

import com.example.cursos.Interface.ApiService
import com.example.cursos.Vista.catalogoCursoVista
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface clsCatalogoCursoPresentador {
    fun cargarCursos(carreraId: Int)
}

class clsCatalogoCursoPresentadorImpl(private val vista: catalogoCursoVista, private val apiService: ApiService) : clsCatalogoCursoPresentador {
    override fun cargarCursos(carreraId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val lista = apiService.obtenerCursos(carreraId)
                withContext(Dispatchers.Main) {
                    vista.mostrarCursos(lista)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    vista.mostrarError("Error al cargar cursos: ${e.localizedMessage}")
                }
            }
        }
    }
}