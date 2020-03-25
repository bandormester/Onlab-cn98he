package hu.bme.aut.android.easylearner.model

data class Lesson(
    val id : Int = 0,
    val teacherId : Int = 0,
    val studentId : Int = 0,
    val info : String = "",
    val startTime : Int = 0,
    val endTime : Int = 0,
    val longitude : String = "",
    val latitude : String = "",
    val payment : Int = 0,
    val levelId : Int = 0,
    val topicId : Int = 0
) {}