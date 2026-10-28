package com.example.cursos.Vista

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.cursos.Presentador.clsDetalleMateriaPresentador
import com.example.cursos.Adaptador.GaleriaAdaptador
import com.example.cursos.Modelo.dataDetalleMateria
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
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_materia)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
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

        val id = intent.getIntExtra("MATERIA_ID", 0)
        val nombre = intent.getStringExtra("NOMBRE_MATERIA") ?: ""
        val codigo = intent.getStringExtra("CODIGO_MATERIA") ?: ""
        val descripcion = intent.getStringExtra("DESCRIPCION_MATERIA") ?: "No llego"
        val edificio = intent.getStringExtra("EDIFICIO_MATERIA") ?: ""
        val salon = intent.getStringExtra("SALON_MATERIA") ?: ""
        val totalHoras = intent.getIntExtra("TOTALHORAS_MATERIA", 0)
        val diasAtencion = intent.getStringExtra("DIASATENCION_MATERIA") ?: ""
        val horario = intent.getStringExtra("HORARIO_MATERIA") ?: ""

        txtNombre.text = nombre
        txtCodigo.text = "Código: " + codigo
        txtDescripcion.text = descripcion
        txtEspacio.text = edificio + ", Salón " + salon
        txtTotalHoras.text = totalHoras.toString() + " Horas"
        txtDiasAtencion.text = diasAtencion + " (" + horario + " hrs)"
        presenter = clsDetalleMateriaPresentador(this)
        configurarListeners()

        if (id != 0) {
            presenter.cargarDetalle(id)
        }
    }

    private fun configurarListeners() {
        btnRegresar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                finish()
            }
        })
    }

    fun mostrarImagenes(imagenes: List<dataDetalleMateria>) {
        adapterGaleria.actualizarLista(imagenes)
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}