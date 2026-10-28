package com.example.cursos.Modelo

import com.example.cursos.Interface.ApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object RetrofitCliente {

    private const val BASE_URL="http://10.120.107.122/cursos_api/"

    private  val jsonConfig= Json {
        ignoreUnknownKeys=true
        coerceInputValues=true
        isLenient=true

    }
    val apiService: ApiService by lazy {
        val contentType="application/json".toMediaType()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(jsonConfig.asConverterFactory(contentType))
            .build()
            .create(ApiService::class.java)





    }



}