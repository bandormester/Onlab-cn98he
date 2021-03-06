package hu.bme.aut.android.easylearner.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.model.LearnerProfile
import hu.bme.aut.android.easylearner.retrofit.RetrofitClient
import hu.bme.aut.android.easylearner.ui.lecture.LearnLectureFragment
import hu.bme.aut.android.easylearner.ui.lesson.LearnLessonFragment
import hu.bme.aut.android.easylearner.ui.lesson.TeachLessonFragment
import hu.bme.aut.android.easylearner.ui.profile.MyLessonsFragment
import hu.bme.aut.android.easylearner.ui.profile.MyProfileFragment
import kotlinx.android.synthetic.main.activity_drawer.*
import retrofit2.Call
import retrofit2.Response

class Drawer : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

     public var userId : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        setSupportActionBar(toolbar)
        userId = intent.getIntExtra("userid", 1)

        val toggle : ActionBarDrawerToggle = ActionBarDrawerToggle(this, draw_layout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close)
        draw_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        if(savedInstanceState == null){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
            LearnLessonFragment()
        ).commit()
        nav_view.setCheckedItem(R.id.nav_learn_lesson)}
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.nav_learn_lesson -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                LearnLessonFragment()
            ).commit()
            R.id.nav_learn_lecture -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                LearnLectureFragment()
            ).commit()
            R.id.nav_teach_lesson -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                TeachLessonFragment()
            ).commit()
            R.id.nav_my_lessons -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                MyLessonsFragment()
            ).commit()
            R.id.nav_my_profile -> {
                RetrofitClient.buildLessonService()
                RetrofitClient.lessonService!!.getProfileRating(userId).enqueue(object : RetrofitClient.LearnerCallback<LearnerProfile> {
                    override fun onResponse(call: Call<LearnerProfile>, response: Response<LearnerProfile>) {
                        Log.d("retrofit", response.code().toString())
                        Log.d("retrofit",response.message())

                        val learnerProfile = response.body()
                        if(learnerProfile!=null){
                            val dialog =
                                MyProfileFragment(
                                    this@Drawer,
                                    learnerProfile
                                )
                            dialog.show()
                        }
                    }
                })
            }
        }
        draw_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(draw_layout.isDrawerOpen(GravityCompat.START))
            draw_layout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }
}
