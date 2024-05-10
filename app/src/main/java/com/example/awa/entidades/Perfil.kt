package com.example.awa.entidades

import com.google.gson.annotations.SerializedName

class Perfil {

    @SerializedName("ID_PERFIL")
    var idPerfil:Int = 0
    @SerializedName("NOMBRE_PERFIL")
    var nombrePerfil:String = ""
}