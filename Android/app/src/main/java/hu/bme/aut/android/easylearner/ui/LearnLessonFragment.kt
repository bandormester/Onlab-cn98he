package hu.bme.aut.android.easylearner.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.model.Lesson
import hu.bme.aut.android.easylearner.model.ProfileDetails
import hu.bme.aut.android.easylearner.model.Rating
import hu.bme.aut.android.easylearner.retrofit.RetroLessons
import hu.bme.aut.android.easylearner.retrofit.RetroTest
import hu.bme.aut.android.easylearner.retrofit.RetrofitClient
import hu.bme.aut.android.easylearner.ui.adapter.LessonAdapter
import hu.bme.aut.android.easylearner.ui.adapter.RatingAdapter
import kotlinx.android.synthetic.main.fragment_learn_lesson.*
import kotlinx.android.synthetic.main.fragment_my_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

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
        RetrofitClient.lessonService!!.getLessonsAsStudent().enqueue(object : Callback<List<Lesson>>{
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        adapter = LessonAdapter(activity!!.baseContext)
        recyclerLearnLesson.layoutManager = LinearLayoutManager(activity!!.baseContext)
        btAddLesson.text = getString(R.string.learn_something_else)

        btAddLesson.setOnClickListener {
            val intent = Intent(activity, AddLessonActivity::class.java)
            intent.putExtra("asTeacher", false)
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
        startActivity(intent)
        Toast.makeText(activity, "Lesson clicked "+lesson.teacherName, Toast.LENGTH_LONG).show()
    }

    override fun onProfileClicked(profileId: Int, profileName : String) {
        //Toast.makeText(this.activity, "profile", Toast.LENGTH_LONG).show()
        val ratingAdapter = RatingAdapter(activity!!.baseContext)

        RetrofitClient.buildLessonService()
        RetrofitClient.lessonService!!.getRating(profileId).enqueue(object :
            Callback<List<Rating>> {
            override fun onFailure(call: Call<List<Rating>>, t: Throwable) {
                Log.d("retrofit",t.message)
            }

            override fun onResponse(call: Call<List<Rating>>, response: Response<List<Rating>>) {
                Log.d("retrofit", response.code().toString())
                Log.d("retrofit",response.message())

                val ratingList = response.body()
                if (ratingList != null) {

                    var profileDetails = ProfileDetails(profileName)

                    for(r : Rating in ratingList){
                        profileDetails.comm+=r.communication
                        profileDetails.punc+=r.punctuality
                        profileDetails.know+=r.knowledge
                    }
                    profileDetails.comm /= ratingList.size
                    profileDetails.punc /= ratingList.size
                    profileDetails.know /= ratingList.size

                    profileDetails.comm = Math.round(profileDetails.comm*10)/10.0
                    profileDetails.punc = Math.round(profileDetails.punc*10)/10.0
                    profileDetails.know = Math.round(profileDetails.know*10)/10.0

                    ratingAdapter.addRatingList(ratingList)
                    val dialog = MyProfileFragment(activity as Activity, ratingAdapter, profileDetails)
                    dialog.show()
                }
            }
        })
    }


}