package com.example.cursos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataCurso (
    @SerialName("id")
    val id: Int =0,

    @SerialName("nombre")
    val nombre: String = "",

    @SerialName("salon")
    val salon: String = "",

    @SerialName("url_imagen")
    val imagenNombre: String = "",

    @SerialName("descripcion")
    val descripcion: String =""
)
{
    override fun toString(): String {
        return nombre
    }
}