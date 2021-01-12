package com.example.ipca.gamecatalog.alldayclinic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        supportActionBar?.hide();
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val handler = Handler()

        val currentUser = Firebase.auth.currentUser

        if (currentUser != null) {

            handler.postDelayed({ mostrarAgenda() }, 3000)
        }
        else {
            handler.postDelayed({ mostrarMainActivity() }, 3000)
        }
    }

    private fun mostrarMainActivity() {
        val intent = Intent(this@SplashScreen, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        finish()

    }

    private fun mostrarAgenda() {
        val intent = Intent(this@SplashScreen, Agenda::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        finish()
    }
}