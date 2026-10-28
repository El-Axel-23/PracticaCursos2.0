package com.example.cursos.Presentador

import com.example.cursos.Modelo.clsVerRegistrosModelo
import com.example.cursos.Modelo.dataCarreras
import com.example.cursos.Vista.Activityver_Registro
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsVerRegistrosPresentador(private val vista: Activityver_Registro) {

    private val modelo = clsVerRegistrosModelo()
    private var carrerasLista: List<dataCarreras> = emptyList()

    fun iniciar(){
        CoroutineScope(Dispatchers.Main).launch{
            try{
                carrerasLista = modelo.obtenerCarreras()
                vista.mostrarCarreras(carrerasLista)
            }catch (e:Exception){
                vista.mostrarError("Error: "+e.localizedMessage)
            }
        }
    }
    fun seleccionarCarreras(position: Int){
        if(position>=0 && position<carrerasLista.size){
            cargoRegistros(carrerasLista[position].id)
        }
    }
    private fun cargoRegistros (carreraid: Int){
        CoroutineScope(Dispatchers.Main).launch{
            try {
                val registros = modelo.obtenerRegistros(carreraid)
                val totalInscritos = registros.sumOf { it.inscritos }
                vista.mostrarRegistros(registros,totalInscritos)
            }catch (e: Exception){
                vista.mostrarError("Error al mostrar registros: "+e.localizedMessage)
            }
        }
    }


}