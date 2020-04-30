package hu.bme.aut.android.easylearner.ui

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import hu.bme.aut.android.easylearner.R
import hu.bme.aut.android.easylearner.model.Level
import hu.bme.aut.android.easylearner.model.Topic
import hu.bme.aut.android.easylearner.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_add_lesson.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class AddLessonActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    private var asTeacher = true
    private lateinit var levels : List<Level>
    private lateinit var topics : List<Topic>
    private var levelNames = mutableListOf<String>()
    private var topicNames = mutableListOf<String>()
    private var chosenDate = Date(Calendar.getInstance().timeInMillis)
    private var year = Calendar.getInstance().get(Calendar.YEAR)
    private var month = Calendar.getInstance().get(Calendar.MONTH)
    private var day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
    private var hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    private var minute = Calendar.getInstance().get(Calendar.MINUTE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_lesson)

        asTeacher = intent.getBooleanExtra("asTeacher", true)

        RetrofitClient.buildLessonService()
        RetrofitClient.lessonService!!.getLevels().enqueue(object : Callback<List<Level>>{
            override fun onFailure(call: Call<List<Level>>, t: Throwable) {
                Log.d("retrofit",t.message)
            }

            override fun onResponse(call: Call<List<Level>>, response: Response<List<Level>>) {
                Log.d("retrofit", response.code().toString())
                levels = response.body()!!
                for(l in levels) levelNames.add(l.name)
                val adapter = ArrayAdapter(this@AddLessonActivity, android.R.layout.simple_spinner_item, levelNames)
                spLevel.adapter = adapter
                spLevel.setSelection(0)
                spLevel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {}

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        Log.d("retrofit", levels[position].toString())
                        when(spLevel.selectedItem){
                            "ELEMENTARY" -> ivLevel.setImageResource(R.mipmap.ic_elementary)
                            "HIGH SCHOOL" -> ivLevel.setImageResource(R.mipmap.ic_highschool)
                            "GRADUATED" -> ivLevel.setImageResource(R.mipmap.ic_graduated)
                        }
                    }

                }
            }

        })

        RetrofitClient.lessonService!!.getTopics().enqueue(object : Callback<List<Topic>>{
            override fun onFailure(call: Call<List<Topic>>, t: Throwable) {
                Log.d("retrofit",t.message)
            }

            override fun onResponse(call: Call<List<Topic>>, response: Response<List<Topic>>) {
                Log.d("retrofit", response.code().toString())
                topics = response.body()!!
                for(t in topics) topicNames.add(t.name)
                val adapter = ArrayAdapter(this@AddLessonActivity, android.R.layout.simple_spinner_item, topicNames)
                spTopic.adapter = adapter
                spTopic.setSelection(0)
                spTopic.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {}

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        Log.d("retrofit", topics[position].toString())
                        when(spTopic.selectedItem){
                            "MATH" -> ivTopic.setImageResource(R.mipmap.ic_math)
                            "PHYSICS" -> ivTopic.setImageResource(R.mipmap.ic_physics)
                            "CHEMISTRY" -> ivTopic.setImageResource(R.mipmap.ic_chemistry)
                            "IT" -> ivTopic.setImageResource(R.mipmap.ic_it)
                            "LITERATURE" -> ivTopic.setImageResource(R.mipmap.ic_literature)
                            "BIOLOGY" -> ivTopic.setImageResource(R.mipmap.ic_biology)
                        }
                    }

                }
            }

        })


        btPickTime.setOnClickListener {
            TimePickerDialog(this, R.style.DateDialog,this, hour, minute, true).show()
        }

        btPickDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this,R.style.DateDialog, this, year, month, day
            )
            datePickerDialog.datePicker.minDate=System.currentTimeMillis()
            datePickerDialog.show()

        }

        btCreateLesson.setOnClickListener {
            Log.d("datum",chosenDate.time.toString())
            Log.d("datum", Date(chosenDate.time).time.toString())
            Log.d("datum",Date(chosenDate.time).date.toString()+"-"+Date(chosenDate.time).hours.toString()+"-"+Date(chosenDate.time).minutes.toString())

            RetrofitClient.buildLessonService()
            RetrofitClient.lessonService!!.addLessonAsTeacher(1,
                tvInfos.text.toString(),
                chosenDate.time,
                Integer.parseInt(etPaymentValue.text.toString()),
                levels[spLevel.selectedItemPosition].id,
                topics[spTopic.selectedItemPosition].id
                ).enqueue(object : Callback<Void>{
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Log.d("retrofit", t.message)
                    Log.d("retrofit", t.localizedMessage)
                    Log.d("retrofit", t.cause.toString())
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Log.d("retrofit", response.code().toString())
                    Log.d("retrofit",response.message())
                }

            })
            val returnIntent = Intent()
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }

    override fun onBackPressed() {
        val returnIntent = Intent()
        setResult(Activity.RESULT_CANCELED, returnIntent)
        super.onBackPressed()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        tvPickDate.text = getString(R.string.date_format, year, month+1, dayOfMonth)
        this.year = year
        this.month = month
        this.day = dayOfMonth
        chosenDate = Date(year, month, dayOfMonth, hour, minute)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        tvPickTime.text = getString(R.string.time_formate, hour, minute)
        this.hour = hourOfDay
        this.minute = minute
        chosenDate = Date(year, month, day, hourOfDay, minute)
    }
}