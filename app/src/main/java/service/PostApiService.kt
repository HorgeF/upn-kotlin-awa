package service

import com.example.awa.entidades.LoginDato
import com.example.awa.entidades.Usuario
import models.cabecera
import models.detalle
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import kotlin.collections.ArrayList
import retrofit2.Call


interface PostApiService {

    @GET("/AwaDetail?id=1")
    suspend fun getDetalle():ArrayList<detalle>

    @GET("/Awa?id=1")
    suspend fun getCabecera():cabecera

    @POST("/Awa")
    fun enviarDatos(@Body datos: Datos): Call<ResponseBody>

    @GET("/Usuarios")
    suspend fun listarUsuarios(): ArrayList<Usuario>

    //--------------------------------------------------------

    @POST("/Usuario/Registrar")
    fun registrarUsuario(@Body usuario:Usuario): Call<ResponseBody>

    @POST("/Usuario/Editar")
    fun editarUsuario(@Body usuario: Usuario): Call<ResponseBody>

    @POST("/Login")
    fun loginUsuario(@Body login: LoginDato): Call<ResponseBody>

    @POST("/Login/Reset")
    fun resetPassword(@Body datos: LoginDato): Call<ResponseBody>
}

data class Datos(val id: String)
data class Respuesta(val MSG: String, val IDMSG: Int)
