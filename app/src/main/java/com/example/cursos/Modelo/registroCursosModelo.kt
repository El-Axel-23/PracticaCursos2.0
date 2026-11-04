package com.example.cursos.Modelo
class registroCursosModelo {
    // Consulta la API para obtener la información del alumno mediante su matrícula
    suspend fun obtenerAlumno(matricula: String): datarespuestaAlumno {
        val datos = datarespuestaAlumno(matricula = matricula)
        return RetrofitCliente.apiService.obtenerAlumno(datos)
    }

    // Envía los datos de inscripción a la API para guardarlos en la base de datos
    suspend fun registrarInscripcion(matricula: String, cursoId: Int, fechaRegistro: String): datarespuestaInscripcion {
        val datos = datarespuestaInscripcion(matricula = matricula, curso_id = cursoId, fecha_registro = fechaRegistro)
        return RetrofitCliente.apiService.registrarInscripcion(datos)
    }

    // Verifica que la matrícula, el curso y la fecha sean válidos
    fun validarDatos(matricula: String, cursoId: Int, fechaRegistro: String): Boolean {
        if (matricula.isBlank()) return false
        if (cursoId <= 0) return false
        if (fechaRegistro.isBlank()) return false
        return true
    }
}