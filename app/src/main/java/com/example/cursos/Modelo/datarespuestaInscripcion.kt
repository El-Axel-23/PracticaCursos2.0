package com.example.cursos.Modelo

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
data class datarespuestaInscripcion(val exito: Boolean=false,val mensaje: String=""){

}
