package hu.bme.aut.android.easylearner.retrofit

import hu.bme.aut.android.easylearner.model.Lesson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroLessons {

    @GET("/lesson")
    fun getLessons() : Call<List<Lesson>>

}