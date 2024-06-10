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

    private val dayOfWeek = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    private val minTemperatures = IntArray(7)
    private val maxTemperatures = IntArray(7)
    private val weatherConditions = Array<String?>(7) { null }

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
            val intent = Intent(this, ViewScreen::class.java)
            intent.putExtra("dayOfWeek", dayOfWeek)
            intent.putExtra("minTemperatures", minTemperatures)
            intent.putExtra("maxTemperatures", maxTemperatures)
            intent.putExtra("weatherConditions", weatherConditions)
            startActivity(intent)
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
        val minText = minNumberEditText.text.toString()
        val maxText = maxNumberEditText.text.toString()

        if (minText.isEmpty() || maxText.isEmpty()) {
            Toast.makeText(this, "Please enter both min and max temperatures", Toast.LENGTH_SHORT).show()
            return
        }

        val minTemp = minText.toIntOrNull()
        val maxTemp = maxText.toIntOrNull()

        if (minTemp == null || maxTemp == null) {
            Toast.makeText(this, "Please enter valid temperature values", Toast.LENGTH_SHORT).show()
            return
        }

        if (minTemp !in -5..135 || maxTemp !in -5..135) {
            Toast.makeText(this, "Temperature values must be between -5 and 135 degrees", Toast.LENGTH_SHORT).show()
            return
        }

        for (i in 0 until 7) {
            minTemperatures[i] = minTemp
            maxTemperatures[i] = maxTemp
        }

        val average = (minTemp + maxTemp) / 2
        Toast.makeText(this, "Average temperature for the week: $average", Toast.LENGTH_SHORT).show()

        // Pass the calculated averages to ViewScreen
        val intent = Intent(this, ViewScreen::class.java)
        intent.putExtra("dayOfWeek", dayOfWeek)
        intent.putExtra("minTemperatures", minTemperatures)
        intent.putExtra("maxTemperatures", maxTemperatures)
        intent.putExtra("weatherConditions", weatherConditions)
        intent.putExtra("averageTemperature", average)
        startActivity(intent)
    }
}
