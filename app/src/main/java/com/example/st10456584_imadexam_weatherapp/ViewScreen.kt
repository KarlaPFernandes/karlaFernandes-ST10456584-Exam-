package com.example.st10456584_imadexam_weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TextView
import android.widget.TableRow
import android.widget.Button

class ViewScreen : AppCompatActivity() {

    private lateinit var tableLayout: TableLayout
    private lateinit var mainScreenButton: Button

    private lateinit var dayOfWeek: Array<String>
    private lateinit var minTemperatures: IntArray
    private lateinit var maxTemperatures: IntArray
    private lateinit var weatherConditions: Array<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_screen)

        // Initialize views
        tableLayout = findViewById(R.id.tableLayout)
        mainScreenButton = findViewById(R.id.mainScreenButton)

        // Get data from intent
        val extras = intent.extras
        if (extras != null) {
            dayOfWeek = extras.getStringArray("dayOfWeek") ?: arrayOf()
            minTemperatures = extras.getIntArray("minTemperatures") ?: IntArray(0)
            maxTemperatures = extras.getIntArray("maxTemperatures") ?: IntArray(0)
            weatherConditions = extras.getStringArray("weatherConditions") ?: arrayOfNulls(7)
        }

        // Populate table
        populateTable()

        // Set onClickListener for mainScreenButton
        mainScreenButton.setOnClickListener {
            // Navigate back to MainActivity
            finish()
        }
    }

    private fun populateTable() {
        // Clear existing views
        tableLayout.removeAllViews()

        // Add header row
        val headerRow = TableRow(this)
        headerRow.addView(createTextView("Day"))
        headerRow.addView(createTextView("Min"))
        headerRow.addView(createTextView("Max"))
        headerRow.addView(createTextView("Weather Condition"))
        tableLayout.addView(headerRow)

        // Add data rows
        for (i in dayOfWeek.indices) {
            val row = TableRow(this)
            row.addView(createTextView(dayOfWeek[i]))
            row.addView(createTextView(minTemperatures[i].toString()))
            row.addView(createTextView(maxTemperatures[i].toString()))
            row.addView(createTextView(weatherConditions[i] ?: ""))
            tableLayout.addView(row)
        }

        // Calculate and add average row
        val averageRow = TableRow(this)
        averageRow.addView(createTextView("Average"))
        averageRow.addView(createTextView(calculateAverageMin().toString()))
        averageRow.addView(createTextView(calculateAverageMax().toString()))
        averageRow.addView(createTextView(calculateAverageWeather()))
        tableLayout.addView(averageRow)
    }

    private fun createTextView(text: String): TextView {
        val textView = TextView(this)
        textView.text = text
        textView.setPadding(16, 16, 16, 16) // Add padding for better readability
        return textView
    }

    private fun calculateAverageMin(): Int {
        return minTemperatures.average().toInt()
    }

    private fun calculateAverageMax(): Int {
        return maxTemperatures.average().toInt()
    }

    private fun calculateAverageWeather(): String {
        val weatherMap = weatherConditions.groupingBy { it }.eachCount()
        return weatherMap.maxByOrNull { it.value }?.key ?: ""
    }
}
