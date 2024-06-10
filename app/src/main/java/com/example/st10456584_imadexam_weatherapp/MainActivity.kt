package com.example.st10456584_imadexam_weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find main button and set click listener
        val mainButton = findViewById<View>(R.id.mainButton)
        mainButton.setOnClickListener {
            // Navigate to the main screen activity
            val intent = Intent(this, MainScreen::class.java)
            startActivity(intent)
        }

        // Find exit button and set click listener
        val exitButton = findViewById<View>(R.id.exitButton)
        exitButton.setOnClickListener {
            // Exit the app
            finish()
        }
    }
 }

