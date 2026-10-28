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
import com.example.cursos.Presentador.RegistrocursosPresentador
import com.example.cursos.R
import java.util.Calendar

class registrocursos : AppCompatActivity(), RegistrocursosVista{

    private lateinit var txtFechaRegistro: EditText
    private lateinit var txtMatriculaAlumno: EditText
    private lateinit var btnConfirmarInscripcion: Button
    private lateinit var btnCancelar: Button

    private lateinit var presentador: RegistrocursosPresentador
    private val cursoId = 1

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

        presentador= RegistrocursosPresentador(this)

        btnConfirmarInscripcion.setOnClickListener(this::ConfirmarInscripcion)
        btnCancelar.setOnClickListener(this::CancelarRegistro)
        txtFechaRegistro.setOnClickListener(this::mostrarcalendario)
    }
    fun mostrarcalendario(v: View){
        val calendario= Calendar.getInstance()
        val ano=calendario.get(Calendar.YEAR)
        val mes=calendario.get(Calendar.MONTH)
        val dia=calendario.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog=DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, ano: Int, mes: Int, dia: Int) {
                txtFechaRegistro.setText(String.format("%04d-%02d-%02d", ano, mes + 1, dia))
            }
        }, ano, mes, dia)
        datePickerDialog.show()
    }

    fun ConfirmarInscripcion(v: View){
        val matricula=txtMatriculaAlumno.text.toString().trim()
        val fecha=txtFechaRegistro.text.toString().trim()
        presentador.registrarCurso(matricula,cursoId,fecha)
    }
    fun CancelarRegistro(v: View){
        finish()
    }
    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

}

