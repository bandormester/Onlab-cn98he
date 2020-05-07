package hu.bme.aut.android.easylearner.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.model.Rating
import kotlinx.android.synthetic.main.row_rating.view.*

class RatingAdapter(con : Context): RecyclerView.Adapter<RatingAdapter.RatingHolder>() {

    val ratings : MutableList<Rating> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingAdapter.RatingHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_rating, parent, false)
        return RatingHolder(view)
    }

    override fun getItemCount() = ratings.size

    fun addRatingList(r : List<Rating>){
        val size = itemCount
        ratings.addAll(r)
        notifyItemRangeChanged(size, itemCount)
    }

    override fun onBindViewHolder(holder: RatingAdapter.RatingHolder, position: Int) {
        val rating = ratings[position]
        holder.tvRatingDate.text = rating.topicName
        holder.tvRatingText.text = rating.text
    }

    inner class RatingHolder(ratingView : View) : RecyclerView.ViewHolder(ratingView){
        val tvRatingText = ratingView.tvRatingText
        val tvRatingDate = ratingView.tvRatingDate

    }
}