package hu.bme.aut.android.easylearner.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.retrofit.RetroTest
import hu.bme.aut.android.easylearner.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

public lateinit var retroTest : RetroTest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {

            //val intent = Intent(this, LoginActivity::class.java)
            //startActivity(intent)

            val dialog = MyProfileFragment()

            dialog.show(supportFragmentManager, "mydialog")
        }

        button2.setOnClickListener {
            val intent = Intent(this, Drawer::class.java)
            startActivity(intent)
        }
    }
}
