package hu.bme.aut.android.easylearner.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.retrofit.RetroTest
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {

public lateinit var retroTest : RetroTest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val gson = GsonBuilder().setLenient().create()
        val builder = Retrofit.Builder()
            .baseUrl("http://192.168.0.227:8080")
            .addConverterFactory(ScalarsConverterFactory.create())
        val retrofit = builder.build()
        retroTest = retrofit.create(RetroTest::class.java)

        button.setOnClickListener {

            val getHello = retroTest.getHello(""+etHello.text)
            getHello.enqueue(object : Callback<String>{
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("retrofit",t.message)
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    textbox.text = response.body()
                    Log.d("retrofit", response.code().toString())
                    Log.d("retrofit", response.body()?:"")
                }

            })

        }

        button2.setOnClickListener {
            val intent = Intent(this, Drawer::class.java)
            startActivity(intent)
        }
    }
}
