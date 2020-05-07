package hu.bme.aut.android.easylearner.ui

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestOptions
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.model.Lesson
import hu.bme.aut.android.easylearner.model.ProfileDetails
import hu.bme.aut.android.easylearner.retrofit.RetrofitClient
import hu.bme.aut.android.easylearner.ui.adapter.LessonAdapter
import hu.bme.aut.android.easylearner.ui.adapter.RatingAdapter
import kotlinx.android.synthetic.main.fragment_learn_lesson.*
import kotlinx.android.synthetic.main.fragment_my_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyProfileFragment(activity : Activity, adapter: RatingAdapter, profileDetails: ProfileDetails) : Dialog(activity) {


    var myActivity = activity
    var adapter = adapter
    var profileDetails = profileDetails


    internal var recyclerView: RecyclerView? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(
        savedInstanceState: Bundle?
    ){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_my_profile)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        Log.d("math", profileDetails.comm.toString())

        tvProfileCommunication.text = myActivity.getString(R.string.profile_communication, profileDetails.comm)
        tvProfileKnowledge.text = myActivity.getString(R.string.profile_knowledge, profileDetails.know)
        tvProfilePunctiality.text = myActivity.getString(R.string.profile_punctuality, profileDetails.punc)
        tvProfileName.text = profileDetails.name

        val picUrl = "http://10.0.2.2:8090/user/pic/"+profileDetails.name.hashCode()
        val glideUrl = GlideUrl(picUrl)
        val option = RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)
        Glide.with(this.context)
            .load(glideUrl)
            .apply(option)
            .into(ivProfilePicture)

        recyclerView = recyclerMyProfile
        mLayoutManager = LinearLayoutManager(myActivity)
        recyclerView?.layoutManager = mLayoutManager
        recyclerView?.adapter = adapter

        setCanceledOnTouchOutside(true)
        btDialogDone.setOnClickListener { dismiss() }
    }
}