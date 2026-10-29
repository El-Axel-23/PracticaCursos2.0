
package com.example.cursos.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cursos.Modelo.dataDetalleMateria
import com.example.cursos.R

class GaleriaAdaptador(
    private var listaImagenes: List<dataDetalleMateria>
) : RecyclerView.Adapter<GaleriaAdaptador.GaleriaViewHolder>() {

    class GaleriaViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val imgGaleria: ImageView =
            itemView.findViewById(R.id.imgGaleria)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GaleriaViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_galeria, parent, false)

        return GaleriaViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: GaleriaViewHolder,
        position: Int
    ) {
        val imagen = listaImagenes[position]

        val baseUrl = "http://192.168.1.69/cursos_api/"
        val rutaImagen = imagen.urlimagen.trim()

        val urlCompleta = if (rutaImagen.startsWith("http")) {
            rutaImagen
        } else {
            baseUrl + rutaImagen.trimStart('/')
        }

        Glide.with(holder.itemView.context)
            .load(urlCompleta)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.imgGaleria)
    }

    override fun getItemCount(): Int {
        return listaImagenes.size
    }

    fun actualizarLista(nuevaLista: List<dataDetalleMateria>) {
        listaImagenes = nuevaLista
        notifyDataSetChanged()
    }
}
