package com.example.cursos.Modelo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable // Recibe la respuesta del servidor con los datos del alumno encontrado
data class datarespuestaAlumno(@SerialName("exito") val exito: Boolean = false,
                               @SerialName("mensaje") val mensaje: String = "",
                               @SerialName("matricula") val matricula: String = "",
                               @SerialName("nombrealumno") val nombrealumno: String = "",
                               @SerialName("correo_institucional") val correo_institucional: String = ""
)
