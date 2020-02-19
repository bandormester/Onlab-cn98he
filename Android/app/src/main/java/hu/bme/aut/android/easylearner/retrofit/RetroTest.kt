package hu.bme.aut.android.easylearner.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface RetroTest {
    @GET("/hello")
    fun getHello(@Query("name") name: String): Call<String>

    @GET("/bello")
    fun getBello(@Header("name") name: String): Call<String>

}