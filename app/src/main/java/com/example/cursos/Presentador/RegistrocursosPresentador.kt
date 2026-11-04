package com.example.cursos.Presentador
import com.example.cursos.Modelo.registroCursosModelo
import com.example.cursos.Vista.RegistrocursosVista
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrocursosPresentador(private val vista: RegistrocursosVista) {
    private val modelo = registroCursosModelo()
    // Busca al alumno y envía su correo institucional a la vista
    fun buscarAlumno(matricula: String) {
        if (matricula.isBlank()) {
            vista.mostrarCorreo("")
            vista.mostrarMensaje("Ingresa la matrícula")
            return
        }
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val alumno = withContext(Dispatchers.IO) {
                    modelo.obtenerAlumno(matricula)
                }
                if (alumno.exito) {
                    vista.mostrarCorreo(alumno.correo_institucional)
                } else {
                    vista.mostrarCorreo("")
                    vista.mostrarMensaje(alumno.mensaje)
                }
            } catch (e: Exception) {
                vista.mostrarCorreo("")
                vista.mostrarMensaje("Error al buscar alumno: " + (e.localizedMessage ?: "Error desconocido"))
            }
        }
    }

    // Valida los datos y realiza el proceso de inscripción al curso
    fun registrarCurso(matricula: String, cursoId: Int, fechaRegistro: String) {
        if (!modelo.validarDatos(matricula, cursoId, fechaRegistro)) {
            vista.mostrarMensaje("Completa todos los datos correctamente")
            return
        }
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val alumno = withContext(Dispatchers.IO) {
                    modelo.obtenerAlumno(matricula)
                }
                if (!alumno.exito) {
                    vista.mostrarCorreo("")
                    vista.mostrarMensaje(alumno.mensaje)
                    return@launch
                }
                vista.mostrarCorreo(alumno.correo_institucional)
                val respuesta = withContext(Dispatchers.IO) {
                    modelo.registrarInscripcion(matricula, cursoId, fechaRegistro)
                }
                vista.mostrarMensaje(respuesta.mensaje)
            } catch (e: Exception) {
                vista.mostrarMensaje("Error al registrarse: " + (e.localizedMessage ?: "Error desconocido"))
            }
        }
    }
}
