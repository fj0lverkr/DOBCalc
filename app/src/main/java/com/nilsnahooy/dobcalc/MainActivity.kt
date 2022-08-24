package com.nilsnahooy.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var selectedDateTV: TextView? = null
    private var calculatedMinutesTV: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePickerButton = findViewById<Button>(R.id.select_date_btn)

        datePickerButton.setOnClickListener {
            datePickerClicked()
        }
    }

    private fun datePickerClicked() {
        val cal = Calendar.getInstance()
        val y = cal.get(Calendar.YEAR)
        val m = cal.get(Calendar.MONTH)
        val d = cal.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            { _, year, month, dayOfMonth ->
                //note months start from 0, add 1.
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val selectedCal = Calendar.getInstance()
                selectedCal.set(year, month, dayOfMonth)
                val selectedDate = selectedCal.time
                val selectedDateFormatted = sdf.format(selectedDate)
                val diff = "${(cal.timeInMillis - selectedCal.timeInMillis)/60000}"

                selectedDateTV = findViewById(R.id.selected_date_tv)
                selectedDateTV?.text = selectedDateFormatted

                calculatedMinutesTV = findViewById(R.id.in_minutes_tv)
                calculatedMinutesTV?.text = diff

        }
            , y, m, d).show()
    }
}