package com.example.cursos.Vista

import com.example.cursos.Modelo.dataCarreras
import com.example.cursos.Modelo.dataCurso

interface catalogoCursoVista {
    fun mostrarCursos(listaCursos: List<dataCurso>)
    fun mostrarCarreras(carreras: List<dataCarreras>)
    fun mostrarError(mensaje: String)
    fun irARegistros()
}

