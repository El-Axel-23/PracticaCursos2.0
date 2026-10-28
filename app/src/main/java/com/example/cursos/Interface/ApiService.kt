package com.example.cursos.Interface
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
interface ApiService {

    @GET("obtener_carreras.php")
    suspend fun obtenerCarreras(): List<dataCarreras>

    @GET("obtener_registros.php")
    suspend fun obtenerRegistros(@Query("carrera_id") carreraId: Int): List<dataRegistro>




}