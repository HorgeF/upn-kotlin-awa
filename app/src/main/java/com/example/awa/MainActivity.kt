package com.example.awa

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import kotlinx.coroutines.launch
import models.detalle
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import service.Datos
import service.PostApiService
import retrofit2.Call
import java.io.IOException
import retrofit2.Callback
import retrofit2.Response
import okhttp3.ResponseBody
import service.Respuesta


class MainActivity : AppCompatActivity() {

    val urlBase = "https://awa-webapi20240503164201.azurewebsites.net/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        obtenerDetallesYCabecera()

        val button = findViewById<Button>(R.id.btnTomarAgua)

        button.setOnClickListener {
            enviarSolicitudPost()
            obtenerDetallesYCabecera()

        }
    }

    private fun obtenerDetallesYCabecera() {
        val retrofit = Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(PostApiService::class.java)

        lifecycleScope.launch {
            try {
                val response = service.getDetalle()

                val adapter = DetalleAdapter(this@MainActivity, response)
                findViewById<ListView>(R.id.lvDetalle).adapter = adapter

                val response2 = service.getCabecera()

                val txtporcentaje = findViewById<TextView>(R.id.txtPorcentaje)
                txtporcentaje.text = response2?.porcentaje?.toInt().toString() + "%" ?: "0%"

                val txtDescripcion = findViewById<TextView>(R.id.txtDescripcion)
                txtDescripcion.text = response2?.cantidad_vaso?.toInt().toString() + " / " + response2?.cantidad_total?.toInt().toString() + " ml "    ?: "No hay detalles disponibles"

                val txtUsuario = findViewById<TextView>(R.id.txtUsuario)
                txtUsuario.text = response2?.usuario?.toString()   ?: "???"




            } catch (e: Exception) {
                Log.e("MainActivity", "Error al obtener detalles", e)
            }
        }
    }
    class DetalleAdapter(context: MainActivity, private val detalles: List<detalle>) : ArrayAdapter<detalle>(context, 0, detalles) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var itemView = convertView
            if (itemView == null) {
                itemView = LayoutInflater.from(context).inflate(R.layout.list_item_detalle, parent, false)
            }

            val detalle = detalles[position]
            val textView = itemView!!.findViewById<TextView>(R.id.textView)
            textView.text = detalle.fecha

            return itemView
        }
    }

    private fun enviarSolicitudPost() {

        val retrofit = Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(PostApiService::class.java)
        val datos = Datos("1") // Ejemplo de ID

        val call = service.enviarDatos(datos)

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
        val ventana = AlertDialog.Builder(this)
        ventana.setTitle("Mensaje Informativo")
        ventana.setMessage(mensaje)
        ventana.setPositiveButton("Aceptar", null)
        ventana.create().show()
    }
}
