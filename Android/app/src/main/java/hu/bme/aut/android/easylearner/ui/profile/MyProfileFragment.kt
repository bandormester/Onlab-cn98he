package hu.bme.aut.android.easylearner.ui.profile

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.request.RequestOptions
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.model.LearnerProfile
import hu.bme.aut.android.easylearner.retrofit.RetrofitClient
import hu.bme.aut.android.easylearner.ui.adapter.RatingAdapter
import kotlinx.android.synthetic.main.fragment_my_profile.*

class MyProfileFragment(activity : Activity, //var profileDetails = profileDetails
                        var learnerProfile: LearnerProfile
) : Dialog(activity) {


    var myActivity = activity
    var adapter = RatingAdapter(activity.baseContext)


    internal var recyclerView: RecyclerView? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(
        savedInstanceState: Bundle?
    ){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_my_profile)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        adapter.addRatingList(learnerProfile.ratings)

        tvProfileCommunication.text = myActivity.getString(R.string.profile_communication, learnerProfile.communication)
        tvProfileKnowledge.text = myActivity.getString(R.string.profile_knowledge, learnerProfile.knowledge)
        tvProfilePunctiality.text = myActivity.getString(R.string.profile_punctuality, learnerProfile.punctuality)
        tvProfileName.text = learnerProfile.fullName
        tvProfileUsername.text = learnerProfile.userName

      val glideUrl = GlideUrl("http://10.0.2.2:8090/user/pic/"+learnerProfile.picUrl)
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