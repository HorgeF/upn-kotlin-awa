package service

import models.cabecera
import models.detalle
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import kotlin.collections.ArrayList
import retrofit2.Call
import retrofit2.Response


interface PostApiService {

    @GET("/api/AwaDetail/1")
    suspend fun getDetalle():ArrayList<detalle>

    @GET("/api/Awa/1")
    suspend fun getCabecera():cabecera

    @POST("/api/Awa")
    fun enviarDatos(@Body datos: Datos): Call<ResponseBody>
}

data class Datos(val id: String)
data class Respuesta(val MSG: String, val IDMSG: Int)
