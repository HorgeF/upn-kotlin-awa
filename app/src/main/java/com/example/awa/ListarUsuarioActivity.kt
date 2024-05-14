package com.example.awa

import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.awa.entidades.Usuario
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import service.PostApiService
import service.RetrofitClient


class ListarUsuarioActivity : AppCompatActivity() {

    val urlBase = "https://api-node-awa1-production.up.railway.app"
    private lateinit var btnRegresar: FloatingActionButton
    private lateinit var rvUsuarios: RecyclerView
    private var adaptador:AdaptadorPersonalizado = AdaptadorPersonalizado()
    var listaUsuarios = arrayListOf<Usuario>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_usuario)
        asignarReferencias()
        obtenerUsuarios()
    }

    fun asignarReferencias(){
        btnRegresar = findViewById(R.id.btnRegresar)
        btnRegresar.setOnClickListener {
            val intent = Intent(this,MenuActivity::class.java)
            startActivity(intent)
        }
        //personaDAO = PersonaDAO(this)

        rvUsuarios = findViewById(R.id.rvUsuarios)
        rvUsuarios.layoutManager = LinearLayoutManager(this)
        rvUsuarios.adapter = adaptador

    }

    fun mostrarUsuarios(){
        adaptador.agregarDatos(listaUsuarios)

        rvUsuarios.layoutManager = LinearLayoutManager(this)
        rvUsuarios.adapter = adaptador
    }

    fun obtenerUsuarios(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitClient.webService.obtenerUsuarios()
            runOnUiThread {
                if(call.isSuccessful){
                    listaUsuarios = call.body()!!.listaUsuarios
                    mostrarUsuarios()
                }else{
                    Log.d("===", "error")
                }
            }
        }
    }






}