package com.example.myapplication

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    var tvselectDate:TextView ?=null
    var tvAgeInMin:TextView ?=null
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       tvselectDate = findViewById(R.id.select)
        tvAgeInMin = findViewById(R.id.age_to_min)
        val btnSelect :Button = findViewById(R.id.button2)
        btnSelect.setOnClickListener() {
          chose()
            }
    }

    fun chose(){
        val cal=Calendar.getInstance()
        val year=cal.get(Calendar.YEAR)
        val month=cal.get(Calendar.MONTH)
        val day=cal.get(Calendar.DAY_OF_MONTH)

       val dpd= DatePickerDialog(this ,
        DatePickerDialog.OnDateSetListener{view,year,month,dayOfMonth ->
            Toast.makeText(this, "year - $year month - ${month +1} Day - $dayOfMonth", Toast.LENGTH_LONG).show()
            val selectDate="$dayOfMonth/${month+1}/$year"
            tvselectDate ?.text =selectDate
            val sdf= SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
            val bDate = sdf.parse(selectDate) // it return Date
            bDate?.let {
                val bDateMili = bDate.time/60000
                val currDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currDateInMili = currDate.time/60000
                currDateInMili?.let {
                    val ageInMin = currDateInMili - bDateMili
                    tvAgeInMin?.text = ageInMin.toString()

                }
            }
        },
        year,month,day )
        dpd.datePicker.maxDate=System.currentTimeMillis() - 86400000
        dpd.show()

    }
}