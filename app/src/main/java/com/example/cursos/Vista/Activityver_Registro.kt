package com.example.cursos.Vista

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cursos.Adaptadores.adap_VerRegistro
import com.example.cursos.Modelo.dataCarreras
import com.example.cursos.Modelo.dataRegistro
import com.example.cursos.Presentador.clsVerRegistrosPresentador
import com.example.cursos.R

class Activityver_Registro : AppCompatActivity() {

    private lateinit var txtTotal: TextView
    private lateinit var rvRegistros: RecyclerView
    private lateinit var btnVolver: Button
    private lateinit var spnCarreras: Spinner
    private lateinit var adaptador: adap_VerRegistro
    private lateinit var Presenter: clsVerRegistrosPresentador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_activityver_registro)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets -> val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtTotal = findViewById(R.id.txtTotal)
        rvRegistros = findViewById(R.id.rvRegistros)
        btnVolver = findViewById(R.id.btnVolverCatalogo)
        spnCarreras = findViewById(R.id.spnCarreras)
        rvRegistros.layoutManager = LinearLayoutManager(this)
        adaptador = adap_VerRegistro(emptyList())
        rvRegistros.adapter = adaptador
        Presenter = clsVerRegistrosPresentador(this)
        ConfiguracionListener()
        Presenter.iniciar()
        btnVolver.setOnClickListener {
            finish()
        }
    }

    private fun ConfiguracionListener() {
        spnCarreras.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Presenter.seleccionarCarreras(position)
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
    }

    fun mostrarCarreras(carreras: List<dataCarreras>) { val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, carreras)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnCarreras.adapter = adapter
    }

    fun mostrarRegistros(registros: List<dataRegistro>, total: Int) {
        adaptador.actualizarLista(registros)
        txtTotal.text = "Total: $total"
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}
