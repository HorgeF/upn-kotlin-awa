package com.example.awa.entidades

import com.google.gson.annotations.SerializedName

class Usuario {

    @SerializedName("ID")
    var idUsuario:Int = 0

    @SerializedName("USUARIO")
    var usuario:String = ""

    @SerializedName("CONTRASENIA")
    var contrasenia:String = ""

    @SerializedName("USU_NOMBRE")
    var usu_nombre:String = ""

    @SerializedName("USU_SEXO")
    var usu_sexo:String = ""

    @SerializedName("USU_FECHA_NACIMIENTO")
    var usu_fechaNacimiento: String = ""

    @SerializedName("USU_EDAD")
    var usu_edad:Int = 0

    @SerializedName("USU_PESO")
    var usu_peso:Double = 0.0

    @SerializedName("USU_TALLA")
    var usu_talla:Double = 0.0

    @SerializedName("USU_CORREO")
    var usu_correo:String = ""

    @SerializedName("USU_CELULAR")
    var usu_celular:String = ""



}