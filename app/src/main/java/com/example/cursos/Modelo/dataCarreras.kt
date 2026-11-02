package com.example.cursos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCarreras(val id: Int, val carrera: String) {
    override fun toString(): String = carrera
}

@Serializable
data class dataRegistro(
    val id: Int,
    val nombre: String,
    val inscritos: Int,
    @SerialName("cupo_maximo") val cupo: Int,
@SerialName("url_imagen") val urlImagen: String?
)

@Serializable
data class dataRespuestaRegistros(
    val total: Int,
    val cursos: List<dataRegistro>
)