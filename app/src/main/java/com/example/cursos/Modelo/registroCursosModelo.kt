package com.example.cursos.Modelo

class registroCursosModelo {
    suspend fun obtenerAlumno(matricula: String):datarespuestaAlumno{
        return RetrofitCliente.apiService.obtenerAlumno(matricula)
    }
    suspend fun registrarInscripcion(matricula: String,cursoId: Int,fechaRegistro: String):datarespuestaInscripcion{
        return RetrofitCliente.apiService.registrarInscripcion(matricula,cursoId,fechaRegistro)
    }

    fun validarDatos(matricula: String,cursoId: Int,fechaRegistro: String): Boolean{
        if(matricula.isBlank())return false
        if (cursoId<=0)return false
        if (fechaRegistro.isBlank())return false

        return true
    }
}