package hu.bme.aut.android.easylearner.retrofit

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestOptions
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    var lessonService : RetroLessons? = null

    fun buildLessonService(){
        if(lessonService == null)
        lessonService = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8090")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(RetroLessons::class.java)
    }

    interface LearnerCallback<T> : Callback<T>{
        override fun onFailure(call: Call<T>, t: Throwable) {
            Log.d("retrofit", t.message)
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            Log.d("retrofit", response.code().toString())
            Log.d("retrofit", response.message())        }
    }

}