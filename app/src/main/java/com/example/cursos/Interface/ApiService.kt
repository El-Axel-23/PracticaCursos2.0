package com.example.cursos.Interface
import com.example.cursos.Modelo.dataCarreras
import com.example.cursos.Modelo.dataCurso
import com.example.cursos.Modelo.dataDetalleMateria
import com.example.cursos.Modelo.dataRegistro
import com.example.cursos.Modelo.datarespuestaAlumno
import com.example.cursos.Modelo.datarespuestaInscripcion
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
interface ApiService {

    @GET("obtener_carreras.php")
    suspend fun obtenerCarreras(): List<dataCarreras>

    @GET("obtener_registros.php")
    suspend fun obtenerRegistros(@Query("carrera_id") carreraId: Int): List<dataRegistro>

    @GET("detalle_curso.php")
    suspend fun detalleMateria(@Query("curso_id") cursoId: Int): List<dataDetalleMateria>

    @GET("obtener_cursos.php")
    suspend fun obtenerCursos(@Query("carrera_id") carreraId: Int): List<dataCurso>

    @FormUrlEncoded
    @POST("obtener_alumno.php")
    suspend fun obtenerAlumno(@Field("matricula")matricula: String): datarespuestaAlumno

    @FormUrlEncoded
    @POST("registrar_cursos.php")
    suspend fun registrarInscripcion(@Field("matricula")matricula: String,
                                     @Field("curso_id")cursoId: Int,
                                     @Field("fecha_registro")fechaRegistro: String): datarespuestaInscripcion
}