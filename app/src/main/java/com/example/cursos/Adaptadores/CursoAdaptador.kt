package com.example.cursos.Adaptador

import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.cursos.Modelo.dataCurso
import com.example.cursos.R

class CursoAdaptador(
    private var listaCursos: List<dataCurso>,
    private val onDetalleClick: (dataCurso) -> Unit,
    private val onRegistrarseClick: (dataCurso) -> Unit
) : RecyclerView.Adapter<CursoAdaptador.CursoViewHolder>() {

    companion object {
        private const val BASE_IMG = "http://192.168.1.69/cursos_api/upload/cursos/"
        private const val TAG = "GLIDE"
    }

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

        cargarImagen(holder.ivCurso, curso.imagenNombre)

        holder.btnVerDetalle.setOnClickListener { onDetalleClick(curso) }
        holder.btnRegistrarse.setOnClickListener { onRegistrarseClick(curso) }
    }

    private fun construirUrl(nombre: String?): String? {
        val limpio = nombre?.trim()?.replace("\\", "/")
        if (limpio.isNullOrEmpty()) return null

        // Si ya es una URL completa, se usa tal cual
        if (limpio.startsWith("http://") || limpio.startsWith("https://")) return limpio

        // Si trae carpetas (cursos/ia.jpg, upload/cursos/ia.jpg), se queda solo con el archivo
        val archivo = limpio.substringAfterLast("/")
        return BASE_IMG + Uri.encode(archivo)
    }

    private fun cargarImagen(imageView: ImageView, nombre: String?) {
        val url = construirUrl(nombre)

        if (url == null) {
            Glide.with(imageView.context).clear(imageView)
            imageView.setImageResource(android.R.drawable.ic_menu_gallery)
            return
        }

        Glide.with(imageView.context)
            .load(url)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_report_image)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.e(TAG, "No cargó: $model", e)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean = false
            })
            .into(imageView)
    }

    override fun getItemCount(): Int = listaCursos.size

    fun actualizarLista(nuevaLista: List<dataCurso>) {
        listaCursos = nuevaLista
        notifyDataSetChanged()
    }
}