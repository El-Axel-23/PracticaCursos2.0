package com.example.cursos.Modelo

class clsVerRegistrosModelo {

    suspend fun obtenerCarreras(): List<dataCarreras>{
        val listafinal = mutableListOf(dataCarreras(0,"Todas las carreras"))
        listafinal.addAll(RetrofitCliente.apiService.obtenerCarreras())
        return listafinal
    }
    suspend fun obtenerRegistros(carreraid : Int): List<dataRegistro>{
        return RetrofitCliente.apiService.obtenerRegistros(carreraid)
    }

}