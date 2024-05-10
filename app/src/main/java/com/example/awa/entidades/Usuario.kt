package com.example.awa.entidades

import com.google.gson.annotations.SerializedName

 class Usuario {

    @SerializedName("ID_USUARIO")
    var idUsuario:Int = 0
    @SerializedName("ALIAS_USUARIO")
    var aliasUsuario:String = ""
    @SerializedName("NOMBRE_USUARIO")
    var nombreUsuario:String = ""
    @SerializedName("EDAD_USUARIO")
    var edadUsuario:Int = 0
    @SerializedName("PESO_USUARIO")
    var pesoUsuario:Double = 0.0
    @SerializedName("CORREO_USUARIO")
    var correoUsuario:String = "Fútbol"
    @SerializedName("DEPORTE_USUARIO")
    var deporteUsuario:Int = 0
    @SerializedName("CELULAR_USUARIO")
    var celularUsuario:String = ""

}