package hu.bme.aut.android.easylearner.ui.lesson

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
import hu.bme.aut.android.easylearner.ui.profile.MyProfileFragment
import kotlinx.android.synthetic.main.activity_lesson_details.*
import retrofit2.Call
import retrofit2.Response
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

class LessonDetailsActivity : AppCompatActivity() {

    private var learnerProfile : LearnerProfile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_details)

        val userid = intent.getIntExtra("userid",0)
        val lesson = intent.getSerializableExtra("lesson") as Lesson
        val asTeacher = intent.getBooleanExtra("asTeacher", false)
        val ownerId : Int
        var picName : Int = 0

        if(!asTeacher){
            tvDetailsName.text = lesson.teacherName
            ownerId = lesson.teacherId
            picName = lesson.teacherName.hashCode()
        }else{
            tvDetailsName.text = lesson.studentName
            ownerId = lesson.studentId
            picName = lesson.studentName.hashCode()
        }
        val picUrl = "http://10.0.2.2:8090/user/pic/"+picName
        val glideUrl = GlideUrl(picUrl)
        val option = RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)
        Glide.with(this)
            .load(glideUrl)
            .apply(option)
            .into(ivDetailsProfile)

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

        RetrofitClient.buildLessonService()
        RetrofitClient.lessonService!!.getProfileRating(ownerId).enqueue(object : RetrofitClient.LearnerCallback<LearnerProfile>{

            override fun onResponse(
                call: Call<LearnerProfile>,
                response: Response<LearnerProfile>
            ) {
                Log.d("retrofit", response.code().toString())
                Log.d("retrofit",response.message())

                learnerProfile = response.body()
                var dec : BigDecimal? = null
                if(learnerProfile!=null) {
                    val rating =
                        (((learnerProfile!!.communication + learnerProfile!!.knowledge + learnerProfile!!.punctuality)/3)*10)/10.0
                    dec = BigDecimal(rating).setScale(2, RoundingMode.HALF_EVEN)
                }
                tvDetailsRating.text = dec.toString()

            }

        })

        val date = Date(lesson.startTime)
        tvDetailsDate.text = date.year.toString()+"-"+(date.month+1).toString()+"-"+date.date.toString()+" / "+date.hours.toString()+":"+date.minutes.toString()

        tvDetailsPrice.text = lesson.payment.toString()
        tvDetailsInfo.text = lesson.info

        btBookLesson.setOnClickListener {
            RetrofitClient.buildLessonService()
            if(asTeacher) {
                RetrofitClient.lessonService!!.bookLessonAsTeacher(
                    lesson.id,
                    userid
                ).enqueue(object : RetrofitClient.LearnerCallback<Void> {
                })
            }else {
                RetrofitClient.lessonService!!.bookLessonAsStudent(
                    lesson.id,
                    userid
                ).enqueue(object : RetrofitClient.LearnerCallback<Void> {
                })
            }
        }

        ivDetailsProfile.setOnClickListener{
            if(learnerProfile!=null){
                val dialog =
                    MyProfileFragment(
                        this@LessonDetailsActivity,
                        learnerProfile!!
                    )
                dialog.show()
            }
        }
    }
}
