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

    private val dayOfWeek = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    private val minTemperatures = DoubleArray(7)
    private val maxTemperatures = DoubleArray(7)
    private val weatherConditions = arrayOfNulls<String>(7)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_screen)


                // Initialize views
                tableLayout = findViewById(R.id.tableLayout)
                mainScreenButton = findViewById(R.id.mainScreenButton)

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
                for (i in 0 until 7) {
                    val row = TableRow(this)
                    row.addView(createTextView(dayOfWeek[i]))
                    row.addView(createTextView(minTemperatures[i].toString()))
                    row.addView(createTextView(maxTemperatures[i].toString()))
                    row.addView(createTextView(weatherConditions[i] ?: ""))
                    tableLayout.addView(row)
                }
            }

            private fun createTextView(text: String): TextView {
                val textView = TextView(this)
                textView.text = text
                textView.setPadding(16, 16, 16, 16) // Add padding for better readability
                return textView
            }
        }


