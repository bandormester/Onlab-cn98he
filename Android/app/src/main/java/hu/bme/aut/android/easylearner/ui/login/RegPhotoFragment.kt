package hu.bme.aut.android.easylearner.ui.login

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.ResultReceiver
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.fragment_reg_photo.*
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.util.jar.Manifest

class RegPhotoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reg_photo, container, false)
    }

    override fun onStart() {
        super.onStart()

        val learner = (activity as RegisterActivity).newLearner

        btTakePhoto.setOnClickListener {
            askCameraPermission()
        }

        btRegFinish.setOnClickListener {
            val bitmap = (ivRegPhoto.drawable as BitmapDrawable).bitmap
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream)
            val image = stream.toByteArray()

            RetrofitClient.buildLessonService()

            val requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), image)

            RetrofitClient.lessonService!!.addLearner(requestBody, learner.name, learner.idCardNumber, learner.username, learner.password).enqueue(object : Callback<Void>{
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("retrofit",t.message)
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d("retrofit", response.message())
                    Log.d("retrofit",response.code().toString())
                    val intent = Intent(activity, LoginActivity::class.java)
                    startActivity(intent)
                }

            })
        }
    }

    private fun askCameraPermission() {
        if(ContextCompat.checkSelfPermission(activity!!.baseContext, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(activity!!, arrayOf<String>(android.Manifest.permission.CAMERA), 101)
        }
        else{
            openCamera()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val bitmap = data!!.extras!!.get("data") as Bitmap
        ivRegPhoto.setImageBitmap(bitmap)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == 101){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openCamera()
            }
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 0)
    }
}