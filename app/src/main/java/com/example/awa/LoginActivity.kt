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
import com.example.awa.entidades.LoginDato
import com.example.awa.entidades.Usuario
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import service.PostApiService
import service.Respuesta

class LoginActivity : AppCompatActivity() {

    private lateinit var btnIniciarSesion: Button
    private lateinit var lblOlvidoclave: TextView
    private lateinit var lblRegistrarse: TextView

    private lateinit var txtUsuario1: EditText
    private lateinit var txtContrasena1: EditText

    val urlBase = "https://api-node-awa1-production.up.railway.app"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        asignarReferencias()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun asignarReferencias(){

        txtUsuario1 = findViewById(R.id.txtUsuario1)
        txtContrasena1 = findViewById(R.id.txtcontrasena1)

        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        btnIniciarSesion.setOnClickListener {
            capturarDatos()
        }

        lblOlvidoclave = findViewById(R.id.lblOlvidoclave)
        lblOlvidoclave.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        lblRegistrarse = findViewById(R.id.lblRegistrarse)
        lblRegistrarse.setOnClickListener {
            val intent = Intent(this,RegistrarUsuarioActivity::class.java)
            startActivity(intent)
        }
    }


    fun capturarDatos(){
        val usu = txtUsuario1.text.toString()
        val clave = txtContrasena1.text.toString()


        var valida = true

        if(usu.isEmpty()){
            valida = false
            txtUsuario1.setError("Ingrese su usuario")
        }
        if(clave.isEmpty()){
            valida = false
            txtContrasena1.setError("Ingrese su clave")
        }



        if(valida){
            val usu1 = LoginDato()
            usu1.usuario = usu
            usu1.contrasenia = clave

            loginMethod(usu1)

        }
    }



    private fun loginMethod(usuar: LoginDato) {

        val retrofit = Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(PostApiService::class.java)
        //val datos = LoginDatos(usuar.usuario,usuar.contrasenia) // Ejemplo de ID

        val call = service.loginUsuario(usuar)

        call.enqueue(
            object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        val body = response.body()?.string()
                        Log.d("===","Respuesta del servidor: $body")

                        val gson = Gson()
                        val respuesta = gson.fromJson(body, Respuesta::class.java)
                        mostrarMensaje(respuesta.MSG)

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

    fun mostrarMensaje(mensaje:String){

        Log.d("===","Mensaje ${mensaje.split("|")[0].toString()}")

        if ((mensaje.split("|")[0].toString()) == "1")
        {
            val ventana = AlertDialog.Builder(this)
            ventana.setTitle("Mensaje Informativo")
            ventana.setMessage(mensaje)
            ventana.setPositiveButton("Aceptar", { dialogInterface: DialogInterface, i:Int->
                val intent = Intent(this,MenuActivity::class.java)
                startActivity(intent)
            })
            ventana.create().show()
        }
        else
        {
            val ventana = AlertDialog.Builder(this)
            ventana.setTitle("BIENVENIDO")
            ventana.setMessage(mensaje)
            ventana.setPositiveButton("Aceptar", null)
            ventana.create().show()
        }



    }

}