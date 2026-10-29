package com.example.cursos.Vista

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cursos.Adaptador.CursoAdaptador
import com.example.cursos.Modelo.RetrofitCliente
import com.example.cursos.Modelo.dataCurso
import com.example.cursos.Presentador.clsCatalogoCursoPresentador
import com.example.cursos.Presentador.clsCatalogoCursoPresentadorImpl
import com.example.cursos.R
import android.util.Log
import com.example.cursos.Vista.registrocursos

class CatalogoCurso : AppCompatActivity(), catalogoCursoVista {

    private lateinit var adaptador: CursoAdaptador
    private lateinit var presentador: clsCatalogoCursoPresentador
    private lateinit var rvCursos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo_curso)

        rvCursos = findViewById(R.id.rvCursos)
        rvCursos.layoutManager = LinearLayoutManager(this)

        adaptador = CursoAdaptador(
            listaCursos = emptyList(),

            onDetalleClick = { curso ->
                Log.d("DETALLE_CURSO", "ID del curso: ${curso.id}")

                try {
                    val intent = Intent(
                        this,
                        DetalleMateriaActivity::class.java
                    )

                    intent.putExtra("MATERIA_ID", curso.id)
                    startActivity(intent)

                } catch (e: Exception) {
                    Log.e("DETALLE_CURSO", "Error al abrir el detalle", e)

                    Toast.makeText(
                        this,
                        "No se pudo abrir el detalle: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            },

            onRegistrarseClick = { curso ->

                val intent = Intent(
                    this,
                    registrocursos::class.java
                )

                intent.putExtra("CURSO_ID", curso.id)
                intent.putExtra("CURSO_NOMBRE", curso.nombre)

                startActivity(intent)
            }
        )

        rvCursos.adapter = adaptador

        val apiService = RetrofitCliente.apiService

        presentador = clsCatalogoCursoPresentadorImpl(
            this,
            apiService
        )

        presentador.cargarCursos(carreraId = 1)
    }

    override fun mostrarCursos(listaCursos: List<dataCurso>) {
        adaptador.actualizarLista(listaCursos)
    }

    override fun mostrarError(mensaje: String) {
        Toast.makeText(
            this,
            mensaje,
            Toast.LENGTH_LONG
        ).show()
    }
}