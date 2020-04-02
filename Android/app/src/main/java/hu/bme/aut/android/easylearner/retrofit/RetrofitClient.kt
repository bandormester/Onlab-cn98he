package hu.bme.aut.android.easylearner.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitClient {
    var lessonService : RetroLessons? = null

    fun buildLessonService(){
        if(lessonService == null)
        lessonService = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8090")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetroLessons::class.java)
    }
}