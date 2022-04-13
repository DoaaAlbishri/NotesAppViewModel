package com.example.notesappviewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class SplashScreen : AppCompatActivity() {
    lateinit var imageView : ImageView
    lateinit var textView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        imageView = findViewById(R.id.imageView)
        textView = findViewById(R.id.textView)
        imageView.alpha = 0f
        imageView.animate().setDuration(1500).alpha(1f).withEndAction {
            val i = Intent(
                this, MainActivity::class.java
            )
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }

}