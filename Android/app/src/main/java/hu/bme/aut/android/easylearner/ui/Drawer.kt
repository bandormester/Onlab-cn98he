package hu.bme.aut.android.easylearner.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import hu.bme.aut.android.easylearner.R
import kotlinx.android.synthetic.main.activity_drawer.*

class Drawer : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)

        setSupportActionBar(toolbar)


        val toggle : ActionBarDrawerToggle = ActionBarDrawerToggle(this, draw_layout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close)
        draw_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        if(savedInstanceState == null){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, LearnLessonFragment()).commit()
        nav_view.setCheckedItem(R.id.nav_learn_lesson)}
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.nav_learn_lesson -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, LearnLessonFragment()).commit()
            R.id.nav_learn_lecture -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, LearnLectureFragment()).commit()
            R.id.nav_teach_lesson -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container, TeachLessonFragment()).commit()
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
