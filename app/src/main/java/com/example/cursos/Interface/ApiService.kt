package com.example.cursos.Interface
import com.example.cursos.Modelo.dataCarreras
import com.example.cursos.Modelo.dataCurso
import com.example.cursos.Modelo.dataDetalleMateria
import com.example.cursos.Modelo.dataRegistro
import com.example.cursos.Modelo.dataRespuestaRegistros
import com.example.cursos.Modelo.datarespuestaAlumno
import com.example.cursos.Modelo.datarespuestaInscripcion
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
interface ApiService {
    @GET("obtener_registros.php")
    suspend fun obtenerRegistros(@Query("carrera_id") carreraId: Int): dataRespuestaRegistros
    @GET("obtener_carreras.php")
    suspend fun obtenerCarreras(): List<dataCarreras>

    @GET("detalle_curso.php")
    suspend fun detalleMateria(@Query("curso_id") cursoId: Int): List<dataDetalleMateria>

    @GET("obtener_cursos.php")
    suspend fun obtenerCursos(@Query("carrera_id") carreraId: Int): List<dataCurso>

    @POST("buscaralumno.php") // Envía la matrícula al servidor para buscar los datos del alumno
    suspend fun obtenerAlumno(@Body datos: datarespuestaAlumno): datarespuestaAlumno

    @POST("confirmarinscripcion.php") // Envía los datos del alumno y del curso para guardar su inscripción
    suspend fun registrarInscripcion(@Body datos: datarespuestaInscripcion): datarespuestaInscripcion
}