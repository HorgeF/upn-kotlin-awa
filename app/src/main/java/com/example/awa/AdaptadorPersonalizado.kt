package com.example.awa

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.awa.entidades.Usuario

class AdaptadorPersonalizado: RecyclerView.Adapter<AdaptadorPersonalizado.MiViewHolder>()  {

    private lateinit var context: Context
    class MiViewHolder(var view: View):RecyclerView.ViewHolder(view) {
        private var nombres = view.findViewById<TextView>(R.id.filaNombres)
        private var celular = view.findViewById<TextView>(R.id.filaCelular)
        private var correo = view.findViewById<TextView>(R.id.filaCorreo)

        var filaEditar = view.findViewById<ImageButton>(R.id.filaEditar)


        fun bindView(usua: Usuario){
            nombres.text = usua.usu_nombre.toString()
            celular .text = usua.usu_celular
            correo.text = usua.usu_correo
        }
    }

    private var lista:ArrayList<Usuario> = ArrayList()
    fun agregarDatos(items: ArrayList<Usuario>){
        this.lista = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MiViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.fila,parent,false)
    )

    override fun onBindViewHolder(holder: AdaptadorPersonalizado.MiViewHolder, position: Int) {
        val usuarioItem = lista[position]
        holder.bindView(usuarioItem)

        holder.filaEditar.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("var_id", lista[position].idUsuario)
            intent.putExtra("var_nombres", lista[position].usu_nombre.toString())
            intent.putExtra("var_celular", lista[position].usu_celular.toString())
            intent.putExtra("var_correo", lista[position].usu_correo.toString())
            context.startActivity(intent)
        }

        /*holder.filaEliminar.setOnClickListener {
            onClickDeleteItem?.invoke(personaItem)
        }*/
    }

    fun contexto(context: Context){
        this.context = context
    }






    override fun getItemCount(): Int {
        return lista.size
    }
}