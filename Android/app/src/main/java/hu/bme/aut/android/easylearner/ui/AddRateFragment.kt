package hu.bme.aut.android.easylearner.ui

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.ArrayAdapter
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.add_rating_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddRateFragment(act : Activity, var ratedId : Int, var lessonId : Int, var topicName : String) : Dialog(act) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.add_rating_fragment)

        val spinnerItems = arrayOf(1,2,3,4,5)
        val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, spinnerItems)
        spRateComm.adapter = adapter
        spRateKnow.adapter = adapter
        spRatePunc.adapter = adapter
        spRateComm.setSelection(2)
        spRatePunc.setSelection(2)
        spRateKnow.setSelection(2)

        btAddRate.setOnClickListener {
            RetrofitClient.buildLessonService()
            RetrofitClient.lessonService!!.addRating(lessonId,
                ratedId,
                topicName,
                spRateComm.selectedItem as Int,
                spRateKnow.selectedItem as Int,
                spRatePunc.selectedItem as Int,
                etAddRate.text.toString()).enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("retrofit",t.message)
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d("retrofit", response.code().toString())
                }
            })
            dismiss()
        }
    }
}