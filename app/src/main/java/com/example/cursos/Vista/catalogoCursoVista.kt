package com.example.cursos.Vista

import com.example.cursos.Modelo.dataCurso

interface catalogoCursoVista {
    fun mostrarCursos(listaCursos: List<dataCurso>)
    fun mostrarError(mensaje: String)
}