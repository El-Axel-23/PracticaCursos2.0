package com.example.cursos.Vista

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
            onDetalleClick = { curso -> },
            onRegistrarseClick = { curso -> }
        )
        rvCursos.adapter = adaptador

        val apiService = RetrofitCliente.apiService
        presentador = clsCatalogoCursoPresentadorImpl(this, apiService)

        presentador.cargarCursos(carreraId = 1)
    }

    override fun mostrarCursos(listaCursos: List<dataCurso>) {
        adaptador.actualizarLista(listaCursos)
    }

    override fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}