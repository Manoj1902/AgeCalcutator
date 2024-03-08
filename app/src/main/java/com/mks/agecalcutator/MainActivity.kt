package com.mks.agecalcutator

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import java.util.*
import kotlin.math.abs

class MainActivity : AppCompatActivity() {


    private lateinit var etDob : EditText
    private lateinit var etTodayDate : EditText
    private lateinit var btnCalculate : Button
    private lateinit var btnReset : Button
    private lateinit var txtAge : TextView
    private lateinit var cardAge : CardView
    private lateinit var txtYear : TextView
    private lateinit var txtMonth : TextView
    private lateinit var txtDays : TextView

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etDob = findViewById(R.id.etDob)
        etTodayDate = findViewById(R.id.etTodayDate)
        btnCalculate = findViewById(R.id.btnCalculate)
        txtAge = findViewById(R.id.txtAge)
        cardAge = findViewById(R.id.ageCard)
        txtYear = findViewById(R.id.txtYear)
        txtMonth = findViewById(R.id.txtMonth)
        txtDays = findViewById(R.id.txtDay)
        btnReset = findViewById(R.id.btnReset)



        val dob = Calendar.getInstance()
        var dobDate = dob.get(Calendar.DAY_OF_MONTH)
        var dobMonth = dob.get(Calendar.MONTH)
        var dobYear = dob.get(Calendar.YEAR)

        etDob.setOnClickListener {

//            Date Picker
            val calenderDatePicker = DatePickerDialog(this, { view, year, month, dayOfMonth ->
                dobDate = dayOfMonth
                dobMonth = month
                dobYear = year

                etDob.setText("$dobDate/${dobMonth + 1}/$dobYear")
            }, dobDate, dobMonth, dobYear)
            calenderDatePicker.show()
        }

//        Current Date
        val current = Calendar.getInstance()
        val cDate = current.get(Calendar.DAY_OF_MONTH)
        val cMonth = current.get(Calendar.MONTH)
        val cYear = current.get(Calendar.YEAR)

        etTodayDate.setText("$cDate/${cMonth + 1}/$cYear")




        etTodayDate.setOnClickListener{
// TODO:
        }




        btnCalculate.setOnClickListener {
            buttonClicked(cYear, dobYear, cDate, dobDate, cMonth, dobMonth)
        }

        btnReset.setOnClickListener {
            etDob.setText("")
            txtYear.text = ""
            txtMonth.text = ""
            txtDays.text = ""
            cardAge.visibility = View.GONE
            Toast.makeText(this, "Reset Complete", Toast.LENGTH_SHORT).show()
        }

    }

    private fun buttonClicked(
        cYear: Int,
        dobYear: Int,
        cDate: Int,
        dobDate: Int,
        cMonth: Int,
        dobMonth: Int
    ) {
        if ((etDob.text != null)){
            if (etTodayDate.text !=null){

                val year = cYear - dobYear
                val month = cMonth - dobMonth
                val date = cDate - dobDate


//                txtAge.text = "Year: $year / Month: ${abs(month)} / Day: ${abs(date)}"

                txtYear.text = "$year"
                txtMonth.text = "${abs(month)}"
                txtDays.text = "${abs(date) }"

                cardAge.visibility = View.VISIBLE

            }else{
                etTodayDate.error = "Field Cannot be Empty"
            }
        }else{
            etDob.error = "Field Cannot be Empty"
        }
    }
}