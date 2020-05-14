package hu.bme.aut.android.easylearner.retrofit

import hu.bme.aut.android.easylearner.model.*
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface RetroLessons {

    @GET("/lesson/names")
    fun getLessons() : Call<List<Lesson>>

    @GET("/lesson/names/student")
    fun getLessonsAsStudent() : Call<List<Lesson>>

    @GET("/lesson/names/teacher")
    fun getLessonsAsTeacher() : Call<List<Lesson>>

    @GET("/level")
    fun getLevels() : Call<List<Level>>

    @GET("/topic")
    fun getTopics() : Call<List<Topic>>

    @POST("/lesson/add/teacher")
    fun addLessonAsTeacher(@Query("idOfTeacher") idOfTeacher: Int,
                           @Query("info") info : String,
                           @Query("startTime") startTime : Long,
                           @Query("paymentValue") paymentValue : Int,
                           @Query("idOfLevel") idOfLevel : Int,
                           @Query("idOfTopic") idOfTopic : Int) : Call<Void>

    @POST("/lesson/add/student")
    fun addLessonAsStudent(@Query("idOfStudent") idOfTeacher: Int,
                           @Query("info") info : String,
                           @Query("startTime") startTime : Long,
                           @Query("paymentValue") paymentValue : Int,
                           @Query("idOfLevel") idOfLevel : Int,
                           @Query("idOfTopic") idOfTopic : Int) : Call<Void>

    @GET("/user/login")
    fun tryLogin(@Query("username") username: String,
                 @Query("password") password: String) : Call<String>

    @POST("/user/register")
    fun addLearner(@Body pic : RequestBody,
                   @Query("name") name : String,
                   @Query("idCardNumber") idCardNumber : String,
                   @Query("username") username : String,
                   @Query("password") password : String) : Call<Void>

    @PUT("/lesson/book/student")
    fun bookLessonAsTeacher(@Query("lessonId") lessonId : Int,
                            @Query("studentId") studentId : Int) : Call<Void>

    @GET("/rating/{id}")
    fun getRating(@Path("id") id : Int) : Call<List<Rating>>

    @GET("/rating/profile/{id}")
    fun getProfileRating(@Path("id") id : Int) : Call<LearnerProfile>

    @GET("/lesson/my/booked/{id}")
    fun getBookedLessons(@Path("id") id : Int) : Call<List<Lesson>>

    @PUT("/lesson/cancel/{lessonId}")
    fun cancelLesson(@Path("lessonId") lessonId : Int,
                     @Query("cancellerId") cancellerId : Int) : Call<Void>
}