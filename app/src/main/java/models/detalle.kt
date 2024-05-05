package models

import com.google.gson.annotations.SerializedName

data class detalle (

    @SerializedName("ID")
    var id:Int,
    @SerializedName("CANTIDAD_AWA")
    var cantidad_awa:Double = 0.0,
    @SerializedName("FECHA_REGISTRO")
    var fecha:String,
    @SerializedName("HORA")
    var hora:String
)




