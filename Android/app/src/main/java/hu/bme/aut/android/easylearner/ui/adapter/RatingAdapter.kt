package hu.bme.aut.android.easylearner.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.model.Rating
import kotlinx.android.synthetic.main.row_rating.view.*

class RatingAdapter(con : Context): RecyclerView.Adapter<RatingAdapter.RatingHolder>() {

    private val ratings : MutableList<Rating> = mutableListOf()

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
        when(rating.topicId){
            1 -> holder.tvRatingDate.text = "Math"
            2 -> holder.tvRatingDate.text = "Physics"
            3 -> holder.tvRatingDate.text = "Chemistry"
            4 -> holder.tvRatingDate.text = "IT"
            5 -> holder.tvRatingDate.text = "Literature"
            6 -> holder.tvRatingDate.text = "Biology"
        }
        Log.d("retrofit", "proba")
        holder.tvRatingText.text = rating.text
    }

    inner class RatingHolder(ratingView : View) : RecyclerView.ViewHolder(ratingView){
        val tvRatingText: TextView = ratingView.tvRatingText
        val tvRatingDate: TextView = ratingView.tvRatingDate

    }
}