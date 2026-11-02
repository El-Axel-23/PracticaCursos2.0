package com.example.cursos.Vista

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cursos.Adaptadores.RegistroAdaptador
import com.example.cursos.Modelo.dataCarreras
import com.example.cursos.Modelo.dataRegistro
import com.example.cursos.Presentador.clsVerRegistrosPresentador
import com.example.cursos.R
import com.google.android.material.appbar.MaterialToolbar

class VerRegistrosActivity : AppCompatActivity() {
    private lateinit var adaptador: RegistroAdaptador
    private lateinit var presentador: clsVerRegistrosPresentador
    private lateinit var spnCarrera: Spinner
    private lateinit var tvTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activityver_registro)

        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        val btnVolver = findViewById<Button>(R.id.btnVolverCatalogo)
        val rv = findViewById<RecyclerView>(R.id.rvRegistros)

        spnCarrera = findViewById(R.id.spnCarrera)
        tvTotal = findViewById(R.id.tvTotal)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        btnVolver.setOnClickListener {
            finish()
        }

        rv.layoutManager = LinearLayoutManager(this)
        adaptador = RegistroAdaptador()
        rv.adapter = adaptador
        presentador = clsVerRegistrosPresentador(this)

        spnCarrera.onItemSelectedListener =
            object : android.widget.AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                    presentador.seleccionarCarreras(position)
                }

                override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {
                }
            }

        presentador.iniciar()
    }

    fun mostrarCarreras(carreras: List<dataCarreras>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, carreras)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnCarrera.adapter = adapter
    }

    fun mostrarRegistros(lista: List<dataRegistro>, total: Int) {
        adaptador.actualizarLista(lista)
        tvTotal.text = "Total: $total"
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}
