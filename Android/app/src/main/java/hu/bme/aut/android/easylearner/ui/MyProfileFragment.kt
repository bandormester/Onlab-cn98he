package hu.bme.aut.android.easylearner.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.model.Lesson
import hu.bme.aut.android.easylearner.retrofit.RetrofitClient
import hu.bme.aut.android.easylearner.ui.adapter.LessonAdapter
import kotlinx.android.synthetic.main.fragment_learn_lesson.*
import kotlinx.android.synthetic.main.fragment_my_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyProfileFragment : DialogFragment() {

    private lateinit var adapter : LessonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_my_profile, container, false)

        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        v.setOnClickListener{
            dismiss()
        }

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




        return v;
    }

    fun setupRecyclerView(list : List<Lesson>){
        adapter = LessonAdapter(activity!!.baseContext)
        adapter.clear()
        adapter.asTeacher = true
        Log.d("valami",list.size.toString())
        adapter.addLessonList(list)
        recyclerMyProfile.adapter = adapter
    }
}