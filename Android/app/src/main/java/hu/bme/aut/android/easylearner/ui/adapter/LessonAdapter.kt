package hu.bme.aut.android.easylearner.ui.adapter

import android.content.Context
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.model.Lesson
import kotlinx.android.synthetic.main.row_lesson.view.*

class LessonAdapter(con : Context) : RecyclerView.Adapter<LessonAdapter.LessonHolder>(){

    val lessons : MutableList<Lesson> = mutableListOf()
    val listener : OnLessonClickedListener? = null
    val context : Context = con

    interface OnLessonClickedListener{
        fun onLessonSelected(lesson: Lesson, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonAdapter.LessonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_lesson, parent, false)
        return LessonHolder(view)
    }

    override fun onBindViewHolder(holder: LessonAdapter.LessonHolder, position: Int) {
        val lesson = lessons[position]
        holder.lesson = lesson
        holder.position = position
        holder.tvTeacherName.text = lesson.teacherName
        holder.ivTeacherPic.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_launcher_background))
       // Log.d("retrofit", lesson.level)
        Log.d("retrofit", "asdasd")
        holder.tvLevel.text = lesson.levelName
        holder.tvTopic.text = lesson.topicName
        holder.tvStartDate.text = lesson.startTime.toString() //TODO
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

    }

}