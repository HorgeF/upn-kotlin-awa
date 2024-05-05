package models

import com.google.gson.annotations.SerializedName

data class cabecera(

    @SerializedName("USUARIO")
    var usuario:String,
    @SerializedName("CANTIDAD")
    var cantidad_vaso:Double = 0.0,
    @SerializedName("PORCENTAJE")
    var porcentaje:Double,
    @SerializedName("CANTIDAD_TOTAL")
    var cantidad_total:Double = 0.0
)
