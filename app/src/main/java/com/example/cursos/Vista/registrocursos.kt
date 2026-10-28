package com.example.cursos.Vista

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cursos.R
import java.util.Calendar

class registrocursos : AppCompatActivity(){

    private lateinit var txtFechaRegistro: EditText
    private lateinit var txtMatriculaAlumno: EditText
    private lateinit var btnConfirmarInscripcion: Button
    private lateinit var btnCancelar: Button



    private val cursoId=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrocursos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtFechaRegistro=findViewById(R.id.txtFechaRegistro)
        txtMatriculaAlumno=findViewById(R.id.txtMatriculaAlumno)
        btnConfirmarInscripcion=findViewById(R.id.btnConfirmarInscripcion)
        btnCancelar=findViewById(R.id.btnCancelar)



        btnConfirmarInscripcion.setOnClickListener(this::confirmarInscripcion)
        btnCancelar.setOnClickListener (this::cancelarRegistro)
        txtFechaRegistro.setOnClickListener (this::mostrarCalendario)
    }
    fun mostrarCalendario(v: View){
        val calendario= Calendar.getInstance()
        val ano=calendario.get(Calendar.YEAR)
        val mes=calendario.get(Calendar.MONDAY)
        val dia=calendario.get(Calendar.DAY_OF_MONTH)


    }

    fun confirmarInscripcion(v: View){
        val matricula=txtMatriculaAlumno.text.toString().trim()
        val fecha=txtFechaRegistro.text.toString().trim()

    }
    fun cancelarRegistro(v: View){
        finish()
    }


}