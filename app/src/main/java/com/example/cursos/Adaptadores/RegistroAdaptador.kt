package com.example.cursos.Adaptadores

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
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

class RegistroAdaptador(private var lista: List<dataRegistro> = emptyList()) : RecyclerView.Adapter<RegistroAdaptador.VH>() {
    val urlBase = "http://192.168.137.18/cursos_api/"
    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val img: ImageView = v.findViewById(R.id.imgCurso)
        val nombre: TextView = v.findViewById(R.id.tvNombre)
        val barra: ProgressBar = v.findViewById(R.id.pbCupo)
        val inscritos: TextView = v.findViewById(R.id.tvInscritos)
        val total: TextView = v.findViewById(R.id.tvTotalCupo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_registro, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(h: VH, position: Int) {
        val r = lista[position]
        h.nombre.text = r.nombre
        h.inscritos.text = "${r.inscritos} inscritos"
        h.total.text = "/ ${r.cupo} total"
        h.barra.max = if (r.cupo > 0) r.cupo else 1
        h.barra.progress = r.inscritos.coerceAtMost(h.barra.max)
        val color = if (r.inscritos >= r.cupo) "#2E9B4F" else "#1560BD"
        h.barra.progressTintList = ColorStateList.valueOf(Color.parseColor(color))
        val imagen = r.urlImagen

        if (!imagen.isNullOrEmpty()) {
            val urlFinal = urlBase + imagen
            Log.d("IMG", urlFinal)
            Glide.with(h.img.context)
                .load(urlFinal)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.stat_notify_error)
                .into(h.img)
        } else {
            h.img.setImageResource(android.R.drawable.ic_menu_gallery)
        }
    }

    override fun getItemCount(): Int = lista.size

    fun actualizarLista(nueva: List<dataRegistro>) {
        lista = nueva
        notifyDataSetChanged()
    }
}