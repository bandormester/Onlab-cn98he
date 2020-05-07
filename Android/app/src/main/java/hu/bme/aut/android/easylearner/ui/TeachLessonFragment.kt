package hu.bme.aut.android.easylearner.ui

import android.app.Activity
import android.app.Dialog
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
import hu.bme.aut.android.easylearner.model.ProfileDetails
import hu.bme.aut.android.easylearner.model.Rating
import hu.bme.aut.android.easylearner.retrofit.RetrofitClient
import hu.bme.aut.android.easylearner.ui.adapter.LessonAdapter
import hu.bme.aut.android.easylearner.ui.adapter.RatingAdapter
import kotlinx.android.synthetic.main.fragment_learn_lesson.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeachLessonFragment : Fragment(), LessonAdapter.OnLessonClickedListener {

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        adapter = LessonAdapter(activity!!.baseContext)
        recyclerLearnLesson.layoutManager = LinearLayoutManager(activity!!.baseContext)
        btAddLesson.text = getString(R.string.teach_something_else)

        btAddLesson.setOnClickListener {
            val intent = Intent(activity, AddLessonActivity::class.java)
            intent.putExtra("asTeacher", true)
            startActivityForResult(intent, 1)
        }
        super.onActivityCreated(savedInstanceState)
    }

    fun setupRecyclerView(list : List<Lesson>){
        adapter.clear()
        adapter.asTeacher = true
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
        intent.putExtra("asTeacher", true)
        startActivity(intent)
        Toast.makeText(activity, "Lesson clicked "+lesson.teacherName, Toast.LENGTH_LONG).show()
    }

    override fun onProfileClicked(profileId: Int, profileName : String) {
        //Toast.makeText(this.activity, "profile", Toast.LENGTH_LONG).show()
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
        //RetrofitClient.lessonService!!.getRating(profileId).enqueue(object :
        //    Callback<List<Rating>> {
        //    override fun onFailure(call: Call<List<Rating>>, t: Throwable) {
        //        Log.d("retrofit",t.message)
        //    }
//
        //    override fun onResponse(call: Call<List<Rating>>, response: Response<List<Rating>>) {
        //        Log.d("retrofit", response.code().toString())
        //        Log.d("retrofit",response.message())
//
        //        val ratingList = response.body()
        //        if (ratingList != null) {
        //            var profileDetails = ProfileDetails(profileName)
//
        //            for(r : Rating in ratingList){
        //                profileDetails.comm+=r.communication
        //                profileDetails.punc+=r.punctuality
        //                profileDetails.know+=r.knowledge
        //            }
        //            profileDetails.comm /= ratingList.size
        //            profileDetails.punc /= ratingList.size
        //            profileDetails.know /= ratingList.size
//
        //            profileDetails.comm = Math.round(profileDetails.comm*10)/10.0
        //            profileDetails.punc = Math.round(profileDetails.punc*10)/10.0
        //            profileDetails.know = Math.round(profileDetails.know*10)/10.0
//
        //            ratingAdapter.addRatingList(ratingList)
        //            val dialog = MyProfileFragment(activity as Activity, ratingAdapter, profileDetails)
        //            dialog.show()
        //        }
        //    }
        //})
    }



}