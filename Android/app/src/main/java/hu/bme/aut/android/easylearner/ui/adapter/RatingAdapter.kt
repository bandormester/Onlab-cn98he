package hu.bme.aut.android.easylearner.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.model.Lesson
import hu.bme.aut.android.easylearner.model.Rating

class RatingAdapter(con : Context): RecyclerView.Adapter<RatingAdapter.RatingHolder>() {

    val ratings : MutableList<Rating> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingAdapter.RatingHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_rating, parent, false)
        return RatingHolder(view)
    }

    override fun getItemCount() = ratings.size

    override fun onBindViewHolder(holder: RatingAdapter.RatingHolder, position: Int) {
        val rating = ratings[position]
    }

    inner class RatingHolder(ratingView : View) : RecyclerView.ViewHolder(ratingView){

    }
}