package hu.bme.aut.android.easylearner.retrofit

import hu.bme.aut.android.easylearner.model.Lesson
import hu.bme.aut.android.easylearner.model.Level
import hu.bme.aut.android.easylearner.model.Topic
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroLessons {

    @GET("/lesson/names")
    fun getLessons() : Call<List<Lesson>>

    @GET("/level")
    fun getLevels() : Call<List<Level>>

    @GET("/topic")
    fun getTopics() : Call<List<Topic>>

}