package hu.bme.aut.android.easylearner.model

data class Rating(
    val text : String = "",
    val communication : Int = 0,
    val punctuality : Int = 0,
    val knowledge : Int = 0,
    val teacherId : Int = 0,
    val topicId : Int = 0
){}