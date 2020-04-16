package hu.bme.aut.android.easylearner.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btLogin.setOnClickListener {
            val pw  = etPassword.text.toString().toByteArray()
            val codedpw = Base64.encodeToString(pw,Base64.NO_WRAP)
            val uname = etUsername.text.toString()
            RetrofitClient.buildLessonService()
            RetrofitClient.lessonService!!.tryLogin(uname,codedpw).enqueue(object : Callback<String>{
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("retrofit", t.message)
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    Log.d("retrofit", response.message())
                    Log.d("retrofit", response.code().toString())
                    if(response.isSuccessful)
                    Log.d("retrofit", response.body()?:"null")
                }

            })
        }
    }
}
