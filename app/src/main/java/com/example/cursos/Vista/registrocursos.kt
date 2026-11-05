package com.example.cursos.Vista

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.cursos.R
import com.example.cursos.Presentador.RegistrocursosPresentador
import java.util.Calendar

class registrocursos : AppCompatActivity(), RegistrocursosVista {
    private lateinit var txtFechaRegistro: EditText
    private lateinit var txtMatriculaAlumno: EditText
    private lateinit var txtCorreoInstitucional: EditText
    private lateinit var btnConfirmarInscripcion: Button
    private lateinit var btnCancelar: Button
    private lateinit var txtNombreCurso: TextView   // ★ nuevo
    private lateinit var presentador: RegistrocursosPresentador

    // El id del curso llega desde el catálogo (ya no es fijo)
    private var cursoId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrocursos)

        // Recibimos el id y el nombre del curso que manda el catálogo
        cursoId = intent.getIntExtra("CURSO_ID", 0)
        val cursoNombre = intent.getStringExtra("CURSO_NOMBRE") ?: ""   // ★ nuevo

        // Si no llegó ningún curso, se cierra la pantalla
        if (cursoId == 0) {
            Toast.makeText(this, "No se seleccionó ningún curso", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        txtFechaRegistro = findViewById(R.id.txtFechaRegistro)
        txtMatriculaAlumno = findViewById(R.id.txtMatriculaAlumno)
        txtCorreoInstitucional = findViewById(R.id.txtCorreoInstitucional)
        btnConfirmarInscripcion = findViewById(R.id.btnConfirmarInscripcion)
        btnCancelar = findViewById(R.id.btnCancelar)
        txtNombreCurso = findViewById(R.id.txtNombreCurso)   // ★ nuevo

        // ★ Mostramos a qué curso se va a inscribir
        if (cursoNombre.isNotBlank()) {
            txtNombreCurso.text = cursoNombre
        }

        presentador = RegistrocursosPresentador(this)

        btnConfirmarInscripcion.setOnClickListener(this::ConfirmarInscripcion)
        btnCancelar.setOnClickListener(this::CancelarRegistro)
        txtFechaRegistro.setOnClickListener(this::mostrarcalendario)
        txtMatriculaAlumno.setOnEditorActionListener(this::buscarAlumno)

        // Limpiar el correo cuando cambie la matrícula
        txtMatriculaAlumno.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                txtCorreoInstitucional.setText("")
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    // Se busca el alumno al ingresar la matrícula y si existe se muestra su correo
    fun buscarAlumno(v: TextView?, accion: Int, evento: KeyEvent?): Boolean {
        if (accion == EditorInfo.IME_ACTION_DONE ||
            accion == EditorInfo.IME_ACTION_SEARCH ||
            (evento != null && evento.keyCode == KeyEvent.KEYCODE_ENTER && evento.action == KeyEvent.ACTION_DOWN)) {
            val matricula = txtMatriculaAlumno.text.toString().trim()
            txtCorreoInstitucional.setText("")
            presentador.buscarAlumno(matricula)
            return true
        }
        return false
    }

    fun mostrarcalendario(v: View) {
        val calendario = Calendar.getInstance()
        val anio = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, anio: Int, mes: Int, dia: Int) {
                txtFechaRegistro.setText(String.format("%04d-%02d-%02d", anio, mes + 1, dia))
            }
        }, anio, mes, dia)
        datePickerDialog.show()
    }

    // Confirma la inscripción al curso que llegó desde el catálogo
    fun ConfirmarInscripcion(v: View) {
        val matricula = txtMatriculaAlumno.text.toString().trim()
        val fecha = txtFechaRegistro.text.toString().trim()
        presentador.registrarCurso(matricula, cursoId, fecha)
    }

    // Cancelamos el registro
    fun CancelarRegistro(v: View) {
        finish()
    }

    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    // Se muestra el correo al ingresar la matrícula
    override fun mostrarCorreo(correo: String) {
        txtCorreoInstitucional.setText(correo)
    }
}