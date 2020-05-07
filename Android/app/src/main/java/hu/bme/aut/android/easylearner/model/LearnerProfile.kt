package hu.bme.aut.android.easylearner.model

import java.io.Serializable


class LearnerProfile : Serializable{
    val picUrl : String = ""
    val fullName : String = ""
    val userName : String = ""
    val punctuality : Double = 0.0
    val knowledge : Double = 0.0
    val communication : Double = 0.0
    val ratings : List<Rating> = mutableListOf()
}