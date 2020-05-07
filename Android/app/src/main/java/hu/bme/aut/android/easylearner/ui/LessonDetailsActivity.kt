package hu.bme.aut.android.easylearner.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestOptions
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.model.LearnerProfile
import hu.bme.aut.android.easylearner.model.Lesson
import hu.bme.aut.android.easylearner.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_add_lesson.*
import kotlinx.android.synthetic.main.activity_lesson_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LessonDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_details)

        val lesson = intent.getSerializableExtra("lesson") as Lesson
        val asTeacher = intent.getBooleanExtra("asTeacher", false)
        val ownerId : Int

        if(!asTeacher){
            tvDetailsName.text = lesson.teacherName
            ownerId = lesson.teacherId

            val picUrl = "http://10.0.2.2:8090/user/pic/"+lesson.teacherName.hashCode()
            val glideUrl = GlideUrl(picUrl)
            val option = RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)
            Glide.with(this)
                .load(glideUrl)
                .apply(option)
                .into(ivDetailsProfile)
        }else{
            tvDetailsName.text = lesson.studentName
            ownerId = lesson.studentId

            val picUrl = "http://10.0.2.2:8090/user/pic/"+lesson.studentName.hashCode()
            val glideUrl = GlideUrl(picUrl)
            val option = RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)
            Glide.with(this)
                .load(glideUrl)
                .apply(option)
                .into(ivDetailsProfile)
        }

        tvDetailsLevel.text = lesson.levelName
        Log.d("glide", "levelname:  "+lesson.levelName +"    "+ lesson.topicName)
        when(lesson.levelName){
            "ELEMENTARY" -> ivDetailsLevel.setImageResource(R.mipmap.ic_elementary)
            "HIGH SCHOOL" -> ivDetailsLevel.setImageResource(R.mipmap.ic_highschool)
            "GRADUATED" -> ivDetailsLevel.setImageResource(R.mipmap.ic_graduated)
        }

        tvDetailsTopic.text = lesson.topicName
        when(lesson.topicName){
            "MATH" -> ivDetailsTopic.setImageResource(R.mipmap.ic_math)
            "PHYSICS" -> ivDetailsTopic.setImageResource(R.mipmap.ic_physics)
            "CHEMISTRY" -> ivDetailsTopic.setImageResource(R.mipmap.ic_chemistry)
            "IT" -> ivDetailsTopic.setImageResource(R.mipmap.ic_it)
            "LITERATURE" -> ivDetailsTopic.setImageResource(R.mipmap.ic_literature)
            "BIOLOGY" -> ivDetailsTopic.setImageResource(R.mipmap.ic_biology)
        }

        val date = Date(lesson.startTime)
        tvDetailsDate.text = date.year.toString()+"-"+(date.month+1).toString()+"-"+date.date.toString()+" / "+date.hours.toString()+":"+date.minutes.toString()

        tvDetailsPrice.text = lesson.payment.toString()
        tvDetailsInfo.text = lesson.info

        btBookLesson.setOnClickListener {
            RetrofitClient.buildLessonService()
            RetrofitClient.lessonService!!.bookLessonAsTeacher(lesson.id,
                //user.id
                22  //TODO
            ).enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("retrofit", t.message)
                    Log.d("retrofit", t.localizedMessage)
                    Log.d("retrofit", t.cause.toString())
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d("retrofit", response.code().toString())
                    Log.d("retrofit",response.message())
                }

            })
        }

        ivDetailsProfile.setOnClickListener{
            RetrofitClient.buildLessonService()
            RetrofitClient.lessonService!!.getProfileRating(ownerId).enqueue(object : Callback<LearnerProfile>{
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
                        val dialog = MyProfileFragment(this@LessonDetailsActivity, learnerProfile)
                        dialog.show()
                    }

                }

            })
        }
    }
}
