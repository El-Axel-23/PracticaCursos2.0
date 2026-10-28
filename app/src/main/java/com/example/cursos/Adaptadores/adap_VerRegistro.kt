package com.example.cursos.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cursos.Modelo.dataRegistro
import com.example.cursos.R

class adap_VerRegistro(private var listaRegistros: List<dataRegistro>):
    RecyclerView.Adapter<adap_VerRegistro.RegistroViewHolder>() {

    class RegistroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgCurso: ImageView = itemView.findViewById(R.id.imgCurso)
        val txtCurso: TextView = itemView.findViewById(R.id.txtCurso)
        val pbOcupacion: ProgressBar = itemView.findViewById(R.id.pbOcupacion)
        val txtInscritos: TextView = itemView.findViewById(R.id.txtInscritos)
        val txtCupo: TextView = itemView.findViewById(R.id.txtCupo)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegistroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_registro, parent, false)
        return RegistroViewHolder(view)
    }
    override fun onBindViewHolder(holder: RegistroViewHolder, position: Int) {
        val registro = listaRegistros[position]
        holder.txtCurso.text = registro.nombre
        holder.pbOcupacion.max = registro.cupo
        holder.pbOcupacion.progress = registro.inscritos
        holder.txtInscritos.text = "${registro.inscritos} inscritos"
        holder.txtCupo.text = "/ ${registro.cupo} total"

        val baseUrl = "http://192.168.1.8/cursos_api/"
        Glide.with(holder.itemView.context)
            .load(baseUrl + registro.urlImagen)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.imgCurso)
    }
    override fun getItemCount(): Int = listaRegistros.size

    fun actualizarLista(nuevaLista: List<dataRegistro>) {
        listaRegistros = nuevaLista
        notifyDataSetChanged()
    }






}