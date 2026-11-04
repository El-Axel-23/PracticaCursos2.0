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
    private lateinit var presentador: RegistrocursosPresentador

    // ID temporal para probar el curso Bases de Datos II
    // Se puede cambiar manualmente o recibir automáticamente desde el catálogo
    private val cursoId = 2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrocursos)
        txtFechaRegistro = findViewById(R.id.txtFechaRegistro)
        txtMatriculaAlumno = findViewById(R.id.txtMatriculaAlumno)
        txtCorreoInstitucional = findViewById(R.id.txtCorreoInstitucional)
        btnConfirmarInscripcion = findViewById(R.id.btnConfirmarInscripcion)
        btnCancelar = findViewById(R.id.btnCancelar)

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
            }
        )
    }
    //se busca el alumno al momento de ingresar la matricula y si existe se muestra
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
            }}, anio, mes, dia)
        datePickerDialog.show()
    }
    //confirma la inscripcion si todos los campos estan correctos
    fun ConfirmarInscripcion(v: View) {
        val matricula = txtMatriculaAlumno.text.toString().trim()
        val fecha = txtFechaRegistro.text.toString().trim()
        presentador.registrarCurso(matricula, cursoId, fecha)
    }
    //cancelamos el registro
    fun CancelarRegistro(v: View) {
        finish()
    }
    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
    //se muestra el correo al momento de ingresar la matricula
    override fun mostrarCorreo(correo: String) {
        txtCorreoInstitucional.setText(correo)
    }
}


