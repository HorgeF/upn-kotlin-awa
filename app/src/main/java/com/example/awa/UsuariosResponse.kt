package com.example.awa

import com.example.awa.entidades.Usuario
import com.google.gson.annotations.SerializedName

data class UsuariosResponse (
    @SerializedName("listaUsuarios") var listaUsuarios:ArrayList<Usuario>
)