package com.example.st10456584_imadexam_weatherapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainScreen : AppCompatActivity() {

    private lateinit var dayNumberEditText: EditText
    private lateinit var minNumberEditText: EditText
    private lateinit var maxNumberEditText: EditText
    private lateinit var weatherNumberEditText: EditText

    private val day = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    private val minTemperatures = DoubleArray(7)
    private val maxTemperatures = DoubleArray(7)
    private val weatherConditions = arrayOfNulls<String>(7)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        // Initialize EditTexts
        dayNumberEditText = findViewById(R.id.dayText)
        minNumberEditText = findViewById(R.id.minNumber)
        maxNumberEditText = findViewById(R.id.maxNumber)
        weatherNumberEditText = findViewById(R.id.weatherText)

        // Initialize buttons
        val viewButton: Button = findViewById(R.id.ViewButton)
        val clearButton: Button = findViewById(R.id.clearButton)
        val exitButton: Button = findViewById(R.id.exitButton2)
        val averageButton: Button = findViewById(R.id.averagebutton)

        // Set onClickListeners
        viewButton.setOnClickListener {
            // Start activity to view daily weather
            startActivity(Intent(this, ViewScreen::class.java))
        }

        clearButton.setOnClickListener {
            // Clear input fields
            clearInputFields()
        }

        exitButton.setOnClickListener {
            // Close the app
            finish()
        }

        averageButton.setOnClickListener {
            // Calculate and display average temperature
            calculateAndDisplayAverage()
        }
    }

    private fun clearInputFields() {
        dayNumberEditText.text.clear()
        minNumberEditText.text.clear()
        maxNumberEditText.text.clear()
        weatherNumberEditText.text.clear()
    }

    private fun calculateAndDisplayAverage() {
        var sum = 0.0
        var count = 0

        for (i in 0 until 7) {
            val minTemp = minTemperatures[i]
            val maxTemp = maxTemperatures[i]

            if (minTemp != 0.0 && maxTemp != 0.0) {
                sum += (minTemp + maxTemp) / 2
                count++
            }
        }

        if (count > 0) {
            val average = sum / count
            Toast.makeText(this, "Average temperature for the week: $average", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "No data available to calculate average", Toast.LENGTH_SHORT).show()
        }

        // Print weather conditions for each day
        for (i in 0 until 7) {
            val dayOfWeek = day[i]
            val weatherCondition = weatherConditions[i]
            if (weatherCondition != null) {
                println("$dayOfWeek: $weatherCondition")
            }
        }
    }
}
