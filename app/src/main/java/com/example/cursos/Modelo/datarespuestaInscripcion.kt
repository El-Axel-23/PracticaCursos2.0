package com.example.cursos.Modelo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable // Recibe el resultado de la inscripción realizada en la base de datos
data class datarespuestaInscripcion(
    @SerialName("exito") val exito: Boolean = false,
    @SerialName("mensaje") val mensaje: String = "",
    @SerialName("matricula") val matricula: String = "",
    @SerialName("curso_id") val curso_id: Int = 0,
    @SerialName("fecha_registro") val fecha_registro: String = "",
    @SerialName("inscripcion_id") val inscripcion_id: Int = 0
)