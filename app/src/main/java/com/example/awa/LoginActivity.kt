package com.example.awa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    private lateinit var btnIniciarSesion: Button
    private lateinit var lblOlvidoclave: TextView
    private lateinit var lblRegistrarse: TextView
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
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        btnIniciarSesion.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
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

}