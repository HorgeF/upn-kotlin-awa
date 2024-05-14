package com.example.awa

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.awa.entidades.Usuario
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import service.Datos
import service.PostApiService
import service.Respuesta

class RegistrarUsuarioActivity : AppCompatActivity() {

    val urlBase = "https://api-node-awa1-production.up.railway.app"

    private lateinit var txtNombresUsuario: EditText
    private lateinit var txtSexoUsuario: EditText
    private lateinit var txtEdadUsuario: EditText
    private lateinit var txtTallaUsuario: EditText
    private lateinit var txtPesoUsuario: EditText
    private lateinit var txtCorreoUsuario: EditText
    private lateinit var txtFechaNacimientoUsuario: EditText
    private lateinit var txtCelularUsuario: EditText
    private lateinit var txtAliasUsuario: EditText
    private lateinit var txtClaveUsuario: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var btnCancelar: Button
    private var modificar = false
    private var id:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar_usuario)
        asignarReferencias()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun asignarReferencias(){
        txtNombresUsuario = findViewById(R.id.txtNombresUsuario)
        txtSexoUsuario = findViewById(R.id.txtSexoUsuario)
        txtTallaUsuario = findViewById(R.id.txtTallaUsuario)
        txtEdadUsuario = findViewById(R.id.txtEdadUsuario)
        txtPesoUsuario = findViewById(R.id.txtPesoUsuario)
        txtCorreoUsuario = findViewById(R.id.txtCorreoUsuario)
        txtFechaNacimientoUsuario = findViewById(R.id.txtFechaNacimientoUsuario)
        txtCelularUsuario = findViewById(R.id.txtCelularUsuario)
        txtAliasUsuario = findViewById(R.id.txtAliasUsuario)
        txtClaveUsuario = findViewById(R.id.txtClaveUsuario)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        btnRegistrar.setOnClickListener {
            capturarDatos()
        }
        btnCancelar = findViewById(R.id.btnCancelar)
        btnCancelar.setOnClickListener {
            val intent = Intent(this,MenuActivity::class.java)
            startActivity(intent)
        }
    }

    fun capturarDatos(){
        val unombre = txtNombresUsuario.text.toString()
        val usexo = txtSexoUsuario.text.toString()
        val utalla = txtTallaUsuario.text.toString()
        val uedad = txtEdadUsuario.text.toString()
        val upeso = txtPesoUsuario.text.toString()
        val ucorreo = txtCorreoUsuario.text.toString()
        val ufecha = txtFechaNacimientoUsuario.text.toString()
        val ucelular = txtCelularUsuario.text.toString()
        val uusuario = txtAliasUsuario.text.toString()
        val uclave = txtClaveUsuario.text.toString()

        var valida = true

        if(unombre.isEmpty()){
            valida = false
            txtNombresUsuario.setError("Nombre completo es obligatorio")
        }
        if(usexo.isEmpty()){
            valida = false
            txtSexoUsuario.setError("EL género es obligatorios")
        }
        if(utalla.isEmpty()){
            valida = false
            txtTallaUsuario.setError("La talla es obligatoria")
        }
        if(uedad.isEmpty()){
            valida = false
            txtEdadUsuario.setError("La edad es obligatoria")
        }
        if(upeso.isEmpty()){
            valida = false
            txtPesoUsuario.setError("El peso es obligatorio")
        }
        if(ucorreo.isEmpty()){
            valida = false
            txtCorreoUsuario.setError("El correo es obligatorio")
        }
        if(ufecha.isEmpty()){
            valida = false
            txtFechaNacimientoUsuario.setError("La fecha es obligatoria")
        }
        if(ucelular.isEmpty()){
            valida = false
            txtCelularUsuario.setError("El celular es obligatorio")
        }
        if(uusuario.isEmpty()){
            valida = false
            txtAliasUsuario.setError("El usuario es obligatorio")
        }
        if(uclave.isEmpty()){
            valida = false
            txtClaveUsuario.setError("La clave es obligatoria")
        }


        if(valida){
            val usu = Usuario()
            usu.usu_nombre = unombre
            usu.usu_sexo = usexo
            usu.usu_talla = utalla.toDouble()
            usu.usu_edad = uedad.toInt()
            usu.usu_peso = upeso.toDouble()
            usu.usu_correo = ucorreo
            usu.usu_fechaNacimiento = ufecha.toString()
            usu.usu_celular = ucelular.toString()
            usu.usuario = uusuario.toString()
            usu.contrasenia = uclave.toString()

            if (modificar)
            {
                usu.idUsuario  = id
                editar(usu)
            }
            else
            {
                registrar(usu)
            }

        }
    }

    fun registrar(usu: Usuario){
         UsuarioRegistrarPost(usu)
    }

    fun editar(usu: Usuario){
        UsuarioEditarPost(usu)
    }

    private fun UsuarioRegistrarPost(usuario:Usuario) {

        val retrofit = Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(PostApiService::class.java)
        //val datos = Datos("1") // Ejemplo de ID

        val call = service.registrarUsuario(usuario)

        call.enqueue(
            object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        val body = response.body()?.string()
                        Log.d("===","Respuesta del servidor: $body")

                        val gson = Gson()
                        val respuesta = gson.fromJson(body, Respuesta::class.java)
                        mostrarMensajeRegistrar(respuesta.MSG)

                    } else {

                        Log.d("===","Error en la respuesta del servidor: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    println("Error al enviar la solicitud POST: ${t.message}")
                    Log.d("===","Error al enviar la solicitud POST: ${t.message}")

                }
            }
        )
    }

    private fun UsuarioEditarPost(usuario:Usuario) {

        val retrofit = Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(PostApiService::class.java)
        //val datos = Datos("1") // Ejemplo de ID

        val call = service.editarUsuario(usuario)

        call.enqueue(
            object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        val body = response.body()?.string()
                        Log.d("===","Respuesta del servidor: $body")

                        val gson = Gson()
                        val respuesta = gson.fromJson(body, Respuesta::class.java)
                        mostrarMensajeEditar(respuesta.MSG)

                    } else {

                        Log.d("===","Error en la respuesta del servidor: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    println("Error al enviar la solicitud POST: ${t.message}")
                    Log.d("===","Error al enviar la solicitud POST: ${t.message}")

                }
            }
        )
    }

    fun mostrarMensajeEditar(mensaje:String){
        val ventana = AlertDialog.Builder(this)
        ventana.setTitle("Mensaje Informativo")
        ventana.setMessage(mensaje)
        ventana.setPositiveButton("Aceptar", { dialogInterface: DialogInterface, i:Int->
            val intent = Intent(this,MenuActivity::class.java)
            startActivity(intent)
        })
        ventana.create().show()
    }

    fun mostrarMensajeRegistrar(mensaje:String){
        val ventana = AlertDialog.Builder(this)
        ventana.setTitle("Mensaje Informativo")
        ventana.setMessage(mensaje)
        ventana.setPositiveButton("Aceptar", { dialogInterface: DialogInterface, i:Int->
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        })
        ventana.create().show()
    }

}