package hu.bme.aut.android.easylearner.ui.lesson

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.model.LearnerProfile
import hu.bme.aut.android.easylearner.model.Lesson
import hu.bme.aut.android.easylearner.retrofit.RetrofitClient
import hu.bme.aut.android.easylearner.ui.Drawer
import hu.bme.aut.android.easylearner.ui.adapter.LessonAdapter
import hu.bme.aut.android.easylearner.ui.profile.MyProfileFragment
import kotlinx.android.synthetic.main.fragment_learn_lesson.*
import retrofit2.Call
import retrofit2.Response

class LearnLessonFragment : Fragment(), LessonAdapter.OnLessonClickedListener {

    private lateinit var adapter : LessonAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_learn_lesson, container, false)
    }

    override fun onStart() {
        super.onStart()

        RetrofitClient.buildLessonService()
        RetrofitClient.lessonService!!.getLessonsAsStudent((activity as Drawer).userId)
            .enqueue(object : RetrofitClient.LearnerCallback<List<Lesson>>{
            override fun onResponse(call: Call<List<Lesson>>, response: Response<List<Lesson>>) {
               /* Log.d("retrofit", response.code().toString())
               Log.d("retrofit",response.message())*/
                val lessonList = response.body()
                if (lessonList != null) {
                    setupRecyclerView(lessonList)
                }
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        adapter = LessonAdapter(activity!!.baseContext)
        recyclerLearnLesson.layoutManager = LinearLayoutManager(activity!!.baseContext)
        btAddLesson.text = getString(R.string.learn_something_else)

        btAddLesson.setOnClickListener {
            val intent = Intent(activity, AddLessonActivity::class.java)
            intent.putExtra("asTeacher", false)
            intent.putExtra("userId",(activity as Drawer).userId)
            startActivityForResult(intent, 1)
        }
        super.onActivityCreated(savedInstanceState)
    }

    fun setupRecyclerView(list : List<Lesson>){
        adapter.clear()
        adapter.asTeacher = false
        adapter.addLessonList(list)
        recyclerLearnLesson.adapter = adapter
        adapter.listener = this
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                Toast.makeText(activity,"Lesson created", Toast.LENGTH_LONG).show()
            }
            else Toast.makeText(activity,"Not created", Toast.LENGTH_LONG).show()
        }
    }

    override fun onLessonSelected(lesson: Lesson, position: Int) {
        val intent = Intent(activity, LessonDetailsActivity::class.java)
        intent.putExtra("lesson", lesson)
        intent.putExtra("asTeacher", false)
        intent.putExtra("userid", (activity as Drawer).userId)
        startActivity(intent)
        Toast.makeText(activity, "Lesson clicked "+lesson.teacherName, Toast.LENGTH_LONG).show()
    }

    override fun onProfileClicked(profileId: Int, profileName : String) {

        RetrofitClient.buildLessonService()
        RetrofitClient.lessonService!!.getProfileRating(profileId).enqueue(object : RetrofitClient.LearnerCallback<LearnerProfile>{

            override fun onResponse(
                call: Call<LearnerProfile>,
                response: Response<LearnerProfile>
            ) {
                Log.d("retrofit", response.code().toString())
                Log.d("retrofit",response.message())

                val learnerProfile = response.body()
                if(learnerProfile!=null){
                    val dialog =
                        MyProfileFragment(
                            activity as Activity,
                            learnerProfile
                        )
                    dialog.show()
                }
            }
        })
    }

    override fun onLessonLongClicked(lesson: Lesson, position: Int) {
        Log.d("click", "long clicked")
    }


}