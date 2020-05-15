package hu.bme.aut.android.easylearner.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.model.LearnerProfile
import hu.bme.aut.android.easylearner.model.Lesson
import hu.bme.aut.android.easylearner.retrofit.RetrofitClient
import hu.bme.aut.android.easylearner.ui.adapter.LessonAdapter
import hu.bme.aut.android.easylearner.ui.adapter.RatingAdapter
import kotlinx.android.synthetic.main.my_lessons_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyLessonsFragment : Fragment(), LessonAdapter.OnLessonClickedListener {

    private lateinit var adapter : LessonAdapter
    private lateinit var selectedButton : View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_lessons_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()

        adapter = LessonAdapter(activity!!.baseContext)
        recyclerMyLessons.layoutManager = LinearLayoutManager(activity!!.baseContext)
        RetrofitClient.buildLessonService()
        selectButton(btFreeLessons)
        btFreeLessons.callOnClick()
        swAsTeacher.isSelected = false
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swAsTeacher.callOnClick()

        swAsTeacher.setOnCheckedChangeListener(){ compoundButton: CompoundButton, b: Boolean ->
            if(swAsTeacher.isChecked){
                RetrofitClient.lessonService!!.getFreeLessonsAsTeacher((activity as Drawer).userId).enqueue(object : Callback<List<Lesson>>{
                    override fun onFailure(call: Call<List<Lesson>>, t: Throwable) {
                        Log.d("retrofit", t.message)
                    }

                    override fun onResponse(
                        call: Call<List<Lesson>>,
                        response: Response<List<Lesson>>
                    ) {
                        Log.d("retrofit", response.message())
                        Log.d("retrofit", response.code().toString())

                        val lessonList = response.body()
                        if (lessonList != null) {
                            setupRecyclerView(lessonList)
                        }
                    }
                })
            } else{
                RetrofitClient.lessonService!!.getFreeLessonsAsStudent((activity as Drawer).userId).enqueue(object : Callback<List<Lesson>>{
                    override fun onFailure(call: Call<List<Lesson>>, t: Throwable) {
                        Log.d("retrofit", t.message)
                    }

                    override fun onResponse(
                        call: Call<List<Lesson>>,
                        response: Response<List<Lesson>>
                    ) {
                        Log.d("retrofit", response.message())
                        Log.d("retrofit", response.code().toString())

                        val lessonList = response.body()
                        if (lessonList != null) {
                            setupRecyclerViewAsTeacher(lessonList)
                        }
                    }

                })
            }
        }

        btBookedLessons.setOnClickListener {
            selectButton(it)
            RetrofitClient.lessonService!!.getBookedLessons((activity as Drawer).userId).enqueue(object : Callback<List<Lesson>>{
                override fun onFailure(call: Call<List<Lesson>>, t: Throwable) {
                    Log.d("retrofit", t.message)
                }

                override fun onResponse(
                    call: Call<List<Lesson>>,
                    response: Response<List<Lesson>>
                ) {
                    Log.d("retrofit", response.message())
                    Log.d("retrofit", response.code().toString())

                    val lessonList = response.body()
                    if (lessonList != null) {
                        setupRecyclerView(lessonList)
                    }
                }

            })
        }
        btFreeLessons.setOnClickListener {
            selectButton(it)
            Log.d("retrofit", swAsTeacher.isChecked.toString())
            if(swAsTeacher.isChecked){
                RetrofitClient.lessonService!!.getFreeLessonsAsTeacher((activity as Drawer).userId).enqueue(object : Callback<List<Lesson>>{
                    override fun onFailure(call: Call<List<Lesson>>, t: Throwable) {
                        Log.d("retrofit", t.message)
                    }

                    override fun onResponse(
                        call: Call<List<Lesson>>,
                        response: Response<List<Lesson>>
                    ) {
                        Log.d("retrofit", response.message())
                        Log.d("retrofit", response.code().toString())

                        val lessonList = response.body()
                        if (lessonList != null) {
                            setupRecyclerView(lessonList)
                        }
                    }
                })
            } else{
                RetrofitClient.lessonService!!.getFreeLessonsAsStudent((activity as Drawer).userId).enqueue(object : Callback<List<Lesson>>{
                    override fun onFailure(call: Call<List<Lesson>>, t: Throwable) {
                        Log.d("retrofit", t.message)
                    }

                    override fun onResponse(
                        call: Call<List<Lesson>>,
                        response: Response<List<Lesson>>
                    ) {
                        Log.d("retrofit", response.message())
                        Log.d("retrofit", response.code().toString())

                        val lessonList = response.body()
                        if (lessonList != null) {
                            setupRecyclerViewAsTeacher(lessonList)
                        }
                    }

                })
            }
        }
        btFinishedLessons.setOnClickListener {
            selectButton(it)
            RetrofitClient.lessonService!!.getFinishedLessons((activity as Drawer).userId).enqueue(object : Callback<List<Lesson>>{
                override fun onFailure(call: Call<List<Lesson>>, t: Throwable) {
                    Log.d("retrofit", t.message)
                }

                override fun onResponse(
                    call: Call<List<Lesson>>,
                    response: Response<List<Lesson>>
                ) {
                    Log.d("retrofit", response.message())
                    Log.d("retrofit", response.code().toString())

                    val lessonList = response.body()
                    if (lessonList != null) {
                        setupRecyclerView(lessonList)
                    }
                }

            })
        }
    }

    fun setupRecyclerView(list : List<Lesson>){
        adapter.clear()
        adapter.asTeacher = false
        adapter.addLessonList(list)
        recyclerMyLessons.adapter = adapter
        adapter.listener = this
    }

    fun setupRecyclerViewAsTeacher(list : List<Lesson>){
        adapter.clear()
        adapter.asTeacher = true
        adapter.addLessonList(list)
        recyclerMyLessons.adapter = adapter
        adapter.listener = this
    }

    private fun selectButton(button : View){
        btBookedLessons.background = activity!!.getDrawable(R.drawable.list_tab_button)
        btFreeLessons.background = activity!!.getDrawable(R.drawable.list_tab_button)
        btFinishedLessons.background = activity!!.getDrawable(R.drawable.list_tab_button)
        swAsTeacher.isClickable = false
        swAsTeacher.alpha = 0.5f

        when(button){
            btBookedLessons -> {
                btBookedLessons.background = activity!!.getDrawable(R.drawable.list_tab_button_selected)
                selectedButton = btBookedLessons
            }
            btFinishedLessons ->{
                btFinishedLessons.background = activity!!.getDrawable(R.drawable.list_tab_button_selected)
                selectedButton = btFinishedLessons
            }
            btFreeLessons -> {
                btFreeLessons.background = activity!!.getDrawable(R.drawable.list_tab_button_selected)
                selectedButton = btFreeLessons
                swAsTeacher.isClickable = true
                swAsTeacher.alpha = 1.0f
            }
        }
    }

    override fun onLessonSelected(lesson: Lesson, position: Int) {
        when(selectedButton){
            btBookedLessons -> Toast.makeText(activity, "Long click to cancel lesson!", Toast.LENGTH_LONG).show()
            btFinishedLessons -> Toast.makeText(activity, "Long click to add rate!", Toast.LENGTH_LONG).show()
            btFreeLessons -> Toast.makeText(activity, "Long click to cancel lesson!", Toast.LENGTH_LONG).show()
        }

    }

    override fun onLessonLongClicked(lesson: Lesson, position: Int) {
        when(selectedButton){
            btBookedLessons ->{
                RetrofitClient.lessonService!!.cancelLesson(lesson.id, 22).enqueue(object : Callback<Void>{
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.d("retrofit", t.message)
                    }

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Log.d("retrofit", response.code().toString())
                        Log.d("retrofit", response.message())
                    }

                })
                Toast.makeText(activity, "Bokking cancelled", Toast.LENGTH_LONG).show()
            }
            btFinishedLessons -> {
                var ratedId : Int
                if(lesson.teacherId.equals(22)){
                    ratedId = lesson.studentId
                }else ratedId = lesson.teacherId
                val dialog = AddRateFragment(activity as Activity, ratedId ,lesson.id, lesson.topicName)
                dialog.show()
                Toast.makeText(activity, "Long click to add rate!", Toast.LENGTH_LONG).show()
            }
            btFreeLessons -> {
                RetrofitClient.lessonService!!.cancelLesson(lesson.id, 22).enqueue(object : Callback<Void>{
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.d("retrofit", t.message)
                    }

                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Log.d("retrofit", response.code().toString())
                        Log.d("retrofit", response.message())
                    }

                })

                Toast.makeText(activity, "Long click to cancel lesson!", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onProfileClicked(profileId: Int, profileName : String) {
        val ratingAdapter = RatingAdapter(activity!!.baseContext)

        RetrofitClient.buildLessonService()
        RetrofitClient.lessonService!!.getProfileRating(profileId).enqueue(object : Callback<LearnerProfile>{
            override fun onFailure(call: Call<LearnerProfile>, t: Throwable) {
                Log.d("retrofit",t.message)
            }

            override fun onResponse(
                call: Call<LearnerProfile>,
                response: Response<LearnerProfile>
            ) {
                Log.d("retrofit", response.code().toString())
                Log.d("retrofit",response.message())

                val learnerProfile = response.body()
                if(learnerProfile!=null){
                    val dialog = MyProfileFragment(activity as Activity, learnerProfile)
                    dialog.show()
                }

            }

        })
    }

}