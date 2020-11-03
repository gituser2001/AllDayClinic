package com.example.ipca.gamecatalog.alldayclinic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide();
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val handler = Handler()
        handler.postDelayed({ mostrarMainActivity() }, 3000)
    }

    private fun mostrarMainActivity() {
        val intent = Intent(this@SplashScreen, MainActivity::class.java)
        startActivity(intent)
        finish()

    }
}