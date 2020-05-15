package hu.bme.aut.android.easylearner.ui.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.retrofit.RetrofitClient
import hu.bme.aut.android.easylearner.ui.Drawer
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btLogin.setOnClickListener {
            val pw  = etPassword.text.toString().toByteArray()
            val codedpw = Base64.encodeToString(pw,Base64.NO_WRAP)
            val uname = etUsername.text.toString()
            RetrofitClient.buildLessonService()
            RetrofitClient.lessonService!!.tryLogin(uname,codedpw).enqueue(object : Callback<Int>{
                override fun onFailure(call: Call<Int>, t: Throwable) {
                    Log.d("retrofit", t.message)
                }

                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                    Log.d("retrofit", response.message())
                    Log.d("retrofit", response.code().toString())
                    if(response.code()==200){
                        val intent = Intent(this@LoginActivity, Drawer::class.java)
                        intent.putExtra("userid", response.body())
                        startActivity(intent)
                    }
                    Log.d("retrofit", response.body().toString())
                }

            })
        }

        btRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivityForResult(intent, 2)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 2){
            if(resultCode == Activity.RESULT_OK){
                Toast.makeText(this.baseContext,"Registered", Toast.LENGTH_LONG).show()
            }
            else Toast.makeText(this.baseContext,"Cancelled", Toast.LENGTH_LONG).show()
        }
    }
}
