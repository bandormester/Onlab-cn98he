package hu.bme.aut.android.easylearner.model

data class Level(
    val id : Int = 0,
    val name : String = ""
) {
    override fun toString(): String {
        return this.name
    }
}