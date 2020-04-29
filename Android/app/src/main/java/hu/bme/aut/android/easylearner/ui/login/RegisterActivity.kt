package hu.bme.aut.android.easylearner.ui.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.model.Learner
import hu.bme.aut.android.easylearner.ui.LearnLessonFragment
import kotlinx.android.synthetic.main.fragment_reg_infos.*

class RegisterActivity : AppCompatActivity() {

    lateinit var newLearner : Learner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportFragmentManager.beginTransaction().replace(R.id.reg_fragment_container, RegInfosFragment()).commit()
    }

    override fun onBackPressed() {
        val returnIntent = Intent()
        setResult(Activity.RESULT_CANCELED, returnIntent)
        super.onBackPressed()
    }

    fun toPhotoFragment(){
        newLearner = Learner(etRegFullname.text.toString(),
            etRegIdcard.text.toString(),
            "",
            etRegUsername.text.toString(),
            Base64.encodeToString(etRegPassword.text.toString().toByteArray(), Base64.NO_WRAP))
        supportFragmentManager.beginTransaction().replace(R.id.reg_fragment_container, RegPhotoFragment()).commit()
    }
}
