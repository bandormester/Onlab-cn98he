package hu.bme.aut.android.easylearner.model

import android.os.Parcelable
import java.io.Serializable

class Lesson : Serializable{
    val id : Int = 0
    val teacherId : Int = 0
    val studentId : Int = 0
    val teacherName : String = ""
    val studentName : String = ""
    val info : String = ""
    val startTime : Long = 0
    val endTime : Int = 0
    val longitude : String = ""
    val latitude : String = ""
    val payment : Int = 0
    val levelName : String = ""
    val topicName : String = ""
}