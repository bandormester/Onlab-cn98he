package hu.bme.aut.android.easylearner.retrofit

import hu.bme.aut.android.easylearner.model.Lesson
import hu.bme.aut.android.easylearner.model.Level
import hu.bme.aut.android.easylearner.model.Topic
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetroLessons {

    @GET("/lesson/names")
    fun getLessons() : Call<List<Lesson>>

    @GET("/level")
    fun getLevels() : Call<List<Level>>

    @GET("/topic")
    fun getTopics() : Call<List<Topic>>

    @POST("/lesson/add/teacher")
    fun addLessonAsTeacher(@Query("idOfTeacher") idOfTeacher: Int,
                           @Query("info") info : String,
                           @Query("paymentValue") paymentValue : Int,
                           @Query("idOfLevel") idOfLevel : Int,
                           @Query("idOfTopic") idOfTopic : Int) : Call<Void>

}