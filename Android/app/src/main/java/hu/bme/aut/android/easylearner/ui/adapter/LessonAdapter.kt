package hu.bme.aut.android.easylearner.ui.adapter

import android.content.Context
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.model.Lesson
import kotlinx.android.synthetic.main.row_lesson.view.*
import java.util.*

class LessonAdapter(con : Context) : RecyclerView.Adapter<LessonAdapter.LessonHolder>(){

    val lessons : MutableList<Lesson> = mutableListOf()
    var listener : OnLessonClickedListener? = null
    val context : Context = con
    var asTeacher : Boolean = false

    interface OnLessonClickedListener{
        fun onLessonSelected(lesson: Lesson, position: Int)
        fun onProfileClicked(profileId: Int, profileName : String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonAdapter.LessonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_lesson, parent, false)
        return LessonHolder(view)
    }

    override fun onBindViewHolder(holder: LessonAdapter.LessonHolder, position: Int) {
        val lesson = lessons[position]
        val date = Date(lesson.startTime)
        holder.lesson = lesson
        holder.position = position
        var picUrl = ""
        if(!asTeacher){
            holder.tvTeacherName.text = lesson.teacherName
            picUrl = "http://10.0.2.2:8090/user/pic/"+lesson.teacherName.hashCode()
        } else{
            holder.tvTeacherName.text = lesson.studentName
            picUrl = "http://10.0.2.2:8090/user/pic/"+lesson.studentName.hashCode()
        }

        holder.ivTeacherPic.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_launcher_background))

        val glideUrl = GlideUrl(picUrl)
        val option = RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)
        //Glide.with(this.context)
        //    .load(glideUrl)
        //    .apply(option)
        //    .into(holder.ivTeacherPic)
        holder.tvLevel.text = lesson.levelName
        holder.tvTopic.text = lesson.topicName
        holder.tvStartDate.text = date.year.toString()+"-"+(date.month+1).toString()+"-"+date.date.toString()+" / "+date.hours.toString()+":"+date.minutes.toString() //TODO
        Log.d("datum", lesson.startTime.toString())
        val paymentText = "${lesson.payment} Ft"
        holder.tvPayment.text = paymentText
        holder.tvRating.text = "5.0" //TODO
        holder.tvInfo.text = lesson.info
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

        val tvTeacherName = lessonView.tvName
        val ivTeacherPic = lessonView.ivTeacherPic
        val tvLevel = lessonView.tvLevel
        val tvTopic = lessonView.tvTopic
        val tvStartDate = lessonView.tvStartDate
        val tvPayment = lessonView.tvPayment
        val tvRating = lessonView.tvRating
        val ivRating = lessonView.ivRating
        val tvInfo = lessonView.tvInfo

        init{
            lessonView.setOnClickListener{
                listener?.onLessonSelected(lesson!!, position!!)
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