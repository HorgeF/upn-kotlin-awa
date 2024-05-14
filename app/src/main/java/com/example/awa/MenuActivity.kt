package com.example.awa

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {

    private lateinit var ivInicioMenu: ImageView
    private lateinit var ivUsuarioMenu: ImageView
    private lateinit var ivReportesMenu: ImageView
    private lateinit var ivConfiguracionMenu: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        asignarReferencias()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun asignarReferencias(){
        ivInicioMenu = findViewById(R.id.ivInicioMenu)
        ivInicioMenu.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        ivUsuarioMenu = findViewById(R.id.ivUsuarioMenu)
        ivUsuarioMenu.setOnClickListener {
            val intent = Intent(this,ListarUsuarioActivity::class.java)
            startActivity(intent)
        }
        ivReportesMenu = findViewById(R.id.ivReportesMenu)
        ivReportesMenu.setOnClickListener {
            val intent = Intent(this,MenuActivity::class.java)
            startActivity(intent)
        }
        ivConfiguracionMenu = findViewById(R.id.ivConfiguracionMenu)
        ivConfiguracionMenu.setOnClickListener {
            val intent = Intent(this,MenuActivity::class.java)
            startActivity(intent)
        }
    }
}