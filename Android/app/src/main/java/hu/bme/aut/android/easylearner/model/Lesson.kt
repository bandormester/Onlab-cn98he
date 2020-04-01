package hu.bme.aut.android.easylearner.model

data class Lesson(
    val id : Int = 0,
    val teacherName : String = "",
    val studentName : String = "",
    val info : String = "",
    val startTime : Int = 0,
    val endTime : Int = 0,
    val longitude : String = "",
    val latitude : String = "",
    val payment : Int = 0,
    val levelName : String = "",
    val topicName : String = ""
) {}