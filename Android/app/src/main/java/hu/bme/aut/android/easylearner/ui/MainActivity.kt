package hu.bme.aut.android.easylearner.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.model.Lesson
import hu.bme.aut.android.easylearner.retrofit.RetroTest
import hu.bme.aut.android.easylearner.retrofit.RetrofitClient
import hu.bme.aut.android.easylearner.ui.adapter.LessonAdapter
import hu.bme.aut.android.easylearner.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_my_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

public lateinit var retroTest : RetroTest

    lateinit var adapter : LessonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {

            //val intent = Intent(this, LoginActivity::class.java)
            //startActivity(intent)

            RetrofitClient.buildLessonService()
            RetrofitClient.lessonService!!.getLessonsAsTeacher().enqueue(object :
                Callback<List<Lesson>> {
                override fun onFailure(call: Call<List<Lesson>>, t: Throwable) {
                    Log.d("retrofit",t.message)
                }

                override fun onResponse(call: Call<List<Lesson>>, response: Response<List<Lesson>>) {
                    Log.d("retrofit", response.code().toString())
                    Log.d("retrofit",response.message())

                    val lessonList = response.body()
                    if (lessonList != null) {
                        setupRecyclerView(lessonList)
                    }
                }
            })


        }

        button2.setOnClickListener {
            val intent = Intent(this, Drawer::class.java)
            startActivity(intent)
        }
    }

    fun setupRecyclerView(list : List<Lesson>){
        adapter = LessonAdapter(this)
        adapter.clear()
        adapter.asTeacher = true
        Log.d("valami",list.size.toString())
        adapter.addLessonList(list)

        //val dialog = MyProfileFragment(this@MainActivity,adapter)
        //dialog.show()
    }
}
