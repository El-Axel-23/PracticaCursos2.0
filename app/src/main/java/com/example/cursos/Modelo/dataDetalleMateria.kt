package com.example.cursos.Modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class dataDetalleMateria(
    @SerialName("id") val id: Int = 0,
    @SerialName("carrera_id") val carreraid: Int = 0,
    @SerialName("codigo") val codigo: String = "",
    @SerialName("nombre") val nombre: String = "",
    @SerialName("descripcion") val descripcion: String = "",
    @SerialName("url_imagen") val urlimagen: String = "",
    @SerialName("salon") val salon: String = "",
    @SerialName("edificio") val edificio: String = "",
    @SerialName("total_horas") val totalhoras: Int = 0,
    @SerialName("dias_atencion") val diasatencion: String = "",
    @SerialName("horario") val horario: String = "",
    @SerialName("cupo_maximo") val cupomaximo: Int = 0
)
{
    override fun toString(): String {
        return nombre
    }
}