
package com.example.cursos.Modelo

class clsVerRegistrosModelo {

    suspend fun obtenerCarreras(): List<dataCarreras> {
        val listaFinal = mutableListOf(
            dataCarreras(0, "Todas las carreras")
        )

        listaFinal.addAll(RetrofitCliente.apiService.obtenerCarreras())

        return listaFinal
    }

    suspend fun obtenerRegistros(carreraId: Int): dataRespuestaRegistros {
        return RetrofitCliente.apiService.obtenerRegistros(carreraId)
    }
}
