package com.example.cursos.Modelo

import kotlinx.serialization.Serializable

@Serializable
data class datarespuestaAlumno(val exito: Boolean=false,val mensaje: String=""){

}
