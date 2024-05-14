package service


import com.example.awa.UsuariosResponse
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

object AppConstantes{
    const val BASE_URL = "https://api-node-awa1-production.up.railway.app"
}

interface WebService {

    @GET("/usuarios")
    suspend fun obtenerUsuarios():Response<UsuariosResponse>

}

object RetrofitClient{
    val webService:WebService by lazy {
        Retrofit.Builder()
            .baseUrl(AppConstantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebService::class.java)
    }
}