package com.example.cursos.Vista

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.cursos.Adaptadores.GaleriaAdaptador
import com.example.cursos.Modelo.dataDetalleMateria
import com.example.cursos.Presentador.clsDetalleMateriaPresentador
import com.example.cursos.R

class DetalleMateriaActivity : AppCompatActivity() {

    private lateinit var presenter: clsDetalleMateriaPresentador

    private lateinit var btnRegresar: Button
    private lateinit var vpGaleria: ViewPager2

    private lateinit var txtNombre: TextView
    private lateinit var txtCodigo: TextView
    private lateinit var txtDescripcion: TextView
    private lateinit var txtEspacio: TextView
    private lateinit var txtTotalHoras: TextView
    private lateinit var txtDiasAtencion: TextView

    private lateinit var adapterGaleria: GaleriaAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_materia)

        btnRegresar = findViewById(R.id.btnRegresar)
        vpGaleria = findViewById(R.id.vpGaleria)
        txtNombre = findViewById(R.id.txtdetalleMateria)
        txtCodigo = findViewById(R.id.txtdetalleCodigo)
        txtDescripcion = findViewById(R.id.txtdetalleDescripcion)
        txtEspacio = findViewById(R.id.txtdetalleEspacio)
        txtTotalHoras = findViewById(R.id.txtdetalleTotalHoras)
        txtDiasAtencion = findViewById(R.id.txtdetalleDiasAtencion)

        adapterGaleria = GaleriaAdaptador(emptyList())
        vpGaleria.adapter = adapterGaleria

        btnRegresar.setOnClickListener {
            finish()
        }
        val idMateria = intent.getIntExtra("MATERIA_ID", 0)
        if (idMateria <= 0) {
            mostrarError("No se recibió un ID de curso válido.")
            return
        }
        presenter = clsDetalleMateriaPresentador(this)
        presenter.cargarDetalle(idMateria)
    }

    fun mostrarDetalle(materia: dataDetalleMateria) {
        txtNombre.text = materia.nombre
        txtCodigo.text = "Código: ${materia.codigo}"
        txtDescripcion.text = materia.descripcion
        txtEspacio.text = "${materia.edificio}, Salón ${materia.salon}"
        txtTotalHoras.text = "${materia.totalhoras} Horas"
        txtDiasAtencion.text = "${materia.diasatencion} (${materia.horario})"
    }

    fun mostrarImagenes(imagenes: List<dataDetalleMateria>) {
        adapterGaleria.actualizarLista(imagenes)
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(
            this,
            mensaje,
            Toast.LENGTH_LONG
        ).show()
    }
}