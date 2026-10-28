package com.example.cursos.Modelo
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class dataCarreras(val id: Int, val carrera: String){
    override fun toString(): String= carrera

}
@Serializable
data class dataRegistro(val id: Int, val nombre: String,
    @SerialName("url_imagen") val urlImagen: String?=null,
    val cupo: Int, val inscritos: Int)
