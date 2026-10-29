package com.example.cursos.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cursos.Modelo.dataCurso
import com.example.cursos.R

class CursoAdaptador(
    private var listaCursos: List<dataCurso>,
    private val onDetalleClick: (dataCurso) -> Unit,
    private val onRegistrarseClick: (dataCurso) -> Unit
) : RecyclerView.Adapter<CursoAdaptador.CursoViewHolder>() {

    class CursoViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val ivCurso: ImageView = itemView.findViewById(R.id.ivCurso)
        val tvTituloCurso: TextView = itemView.findViewById(R.id.tvTituloCurso)
        val tvSalonCurso: TextView = itemView.findViewById(R.id.tvSalonCurso)
        val btnVerDetalle: Button = itemView.findViewById(R.id.btnVerDetalle)
        val btnRegistrarse: Button = itemView.findViewById(R.id.btnRegistrarse)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CursoViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_curso, parent, false)

        return CursoViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CursoViewHolder,
        position: Int
    ) {
        val curso = listaCursos[position]

        holder.tvTituloCurso.text = curso.nombre
        holder.tvSalonCurso.text = "Salón: ${curso.salon}"

        val baseUrl = "http://192.168.1.69/cursos_api/"

        Glide.with(holder.itemView.context)
            .load(baseUrl + curso.imagenNombre)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.ivCurso)

        holder.btnVerDetalle.setOnClickListener {
            onDetalleClick(curso)
        }

        holder.btnRegistrarse.setOnClickListener {
            onRegistrarseClick(curso)
        }
    }

    override fun getItemCount(): Int {
        return listaCursos.size
    }

    fun actualizarLista(nuevaLista: List<dataCurso>) {
        listaCursos = nuevaLista
        notifyDataSetChanged()
    }
}
