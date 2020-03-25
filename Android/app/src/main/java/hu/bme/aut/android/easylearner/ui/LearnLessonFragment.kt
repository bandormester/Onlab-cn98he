package hu.bme.aut.android.easylearner.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.model.Lesson
import hu.bme.aut.android.easylearner.retrofit.RetroLessons
import hu.bme.aut.android.easylearner.retrofit.RetroTest
import hu.bme.aut.android.easylearner.ui.adapter.LessonAdapter
import kotlinx.android.synthetic.main.fragment_learn_lesson.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class LearnLessonFragment : Fragment() {

    private lateinit var adapter : LessonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_learn_lesson, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        adapter = LessonAdapter(activity!!.baseContext)
        recyclerLearnLesson.layoutManager = LinearLayoutManager(activity!!.baseContext)


        val builder = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8090")
            .addConverterFactory(GsonConverterFactory.create())
        val retrofit = builder.build()
        val retroLessons = retrofit.create(RetroLessons::class.java)
        retroLessons.getLessons().enqueue(object : Callback<List<Lesson>>{
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

        super.onActivityCreated(savedInstanceState)
    }

    fun setupRecyclerView(list : List<Lesson>){
        adapter.clear()
        adapter.addLessonList(list)
        recyclerLearnLesson.adapter = adapter
    }

}