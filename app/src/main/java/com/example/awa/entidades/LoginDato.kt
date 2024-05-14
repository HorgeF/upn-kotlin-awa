package com.example.awa.entidades

import com.google.gson.annotations.SerializedName

class LoginDato {

    @SerializedName("USUARIO")
    var usuario:String = ""

    @SerializedName("CONTRASENIA")
    var contrasenia:String = ""
}