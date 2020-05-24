package hu.bme.aut.android.easylearner.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestOptions
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.model.LearnerProfile
import hu.bme.aut.android.easylearner.model.Lesson
import hu.bme.aut.android.easylearner.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.row_lesson.view.*
import retrofit2.Call
import retrofit2.Response
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

class LessonAdapter(con : Context) : RecyclerView.Adapter<LessonAdapter.LessonHolder>(){

    private val lessons : MutableList<Lesson> = mutableListOf()
    var listener : OnLessonClickedListener? = null
    val context : Context = con
    var asTeacher : Boolean = false

    interface OnLessonClickedListener{
        fun onLessonSelected(lesson: Lesson, position: Int)
        fun onProfileClicked(profileId: Int, profileName : String)
        fun onLessonLongClicked(lesson: Lesson, position : Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonAdapter.LessonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_lesson, parent, false)
        return LessonHolder(view)
    }

    override fun onBindViewHolder(holder: LessonAdapter.LessonHolder, position: Int) {
        val lesson = lessons[position]
        val date = Date(lesson.startTime)
        val ownerId : Int
        holder.lesson = lesson
        holder.position = position
        var picUrl = ""
        if(!asTeacher){
            holder.tvTeacherName.text = lesson.teacherName
            picUrl = "http://10.0.2.2:8090/user/pic/"+lesson.teacherName.hashCode()
            ownerId = lesson.teacherId
        } else{
            holder.tvTeacherName.text = lesson.studentName
            picUrl = "http://10.0.2.2:8090/user/pic/"+lesson.studentName.hashCode()
            ownerId = lesson.studentId
        }

        holder.ivTeacherPic.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_launcher_background))

        val glideUrl = GlideUrl(picUrl)
        val option = RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)
       Glide.with(this.context)
           .load(glideUrl)
           .apply(option)
           .into(holder.ivTeacherPic)
        holder.tvLevel.text = lesson.levelName
        holder.tvTopic.text = lesson.topicName
        holder.tvStartDate.text = date.year.toString()+"-"+(date.month+1).toString()+"-"+date.date.toString()+" / "+date.hours.toString()+":"+date.minutes.toString()
        Log.d("datum", lesson.startTime.toString())
        val paymentText = "${lesson.payment} Ft"
        holder.tvPayment.text = paymentText
        holder.tvRating.text = "5.0" //TODO
        holder.tvInfo.text = lesson.info

        RetrofitClient.buildLessonService()
        RetrofitClient.lessonService!!.getProfileRating(ownerId).enqueue(object :
            RetrofitClient.LearnerCallback<LearnerProfile> {

            override fun onResponse(
                call: Call<LearnerProfile>,
                response: Response<LearnerProfile>
            ) {
                Log.d("retrofit", response.code().toString())
                Log.d("retrofit",response.message())

                val learnerProfile = response.body()
                var dec : BigDecimal? = null
                if(learnerProfile!=null) {
                    val rating =
                        (((learnerProfile.communication + learnerProfile.knowledge + learnerProfile.punctuality)/3)*10)/10.0
                    dec = BigDecimal(rating).setScale(1, RoundingMode.HALF_EVEN)
                }
                holder.tvRating.text = dec.toString()


            }
        })
    }

    override fun getItemCount() = lessons.size

    fun clear(){
        val size = itemCount
        lessons.clear()
        notifyItemRangeChanged(size,0)
    }

    fun addLessonList(l : List<Lesson>){
        val size = itemCount
        lessons.addAll(l)
        notifyItemRangeChanged(size, itemCount)
    }

    inner class LessonHolder(lessonView : View) : RecyclerView.ViewHolder(lessonView){
        var lesson : Lesson? = null
        var position : Int? = null

        val tvTeacherName: TextView = lessonView.tvName
        val ivTeacherPic : ImageView = lessonView.ivTeacherPic
        val tvLevel: TextView = lessonView.tvLevel
        val tvTopic: TextView = lessonView.tvTopic
        val tvStartDate: TextView = lessonView.tvStartDate
        val tvPayment: TextView = lessonView.tvPayment
        val tvRating: TextView = lessonView.tvRating
        val ivRating: ImageView = lessonView.ivRating
        val tvInfo: TextView = lessonView.tvInfo

        init{
            lessonView.setOnClickListener{
                listener?.onLessonSelected(lesson!!, position!!)
            }
            lessonView.setOnLongClickListener {
                listener?.onLessonLongClicked(lesson!!, position!!)
                return@setOnLongClickListener true
            }
            if(asTeacher){
                ivTeacherPic.setOnClickListener{
                    listener?.onProfileClicked(lesson!!.studentId, lesson!!.studentName)
                }

                tvTeacherName.setOnClickListener{
                    listener?.onProfileClicked(lesson!!.studentId, lesson!!.studentName)
                }

                ivRating.setOnClickListener{
                    listener?.onProfileClicked(lesson!!.studentId, lesson!!.studentName)
                }
            }else{
                ivTeacherPic.setOnClickListener{
                    listener?.onProfileClicked(lesson!!.teacherId, lesson!!.teacherName)
                }

                tvTeacherName.setOnClickListener{
                    listener?.onProfileClicked(lesson!!.teacherId, lesson!!.teacherName)
                }

                ivRating.setOnClickListener{
                    listener?.onProfileClicked(lesson!!.teacherId, lesson!!.teacherName)
                }
            }

        }

    }

}