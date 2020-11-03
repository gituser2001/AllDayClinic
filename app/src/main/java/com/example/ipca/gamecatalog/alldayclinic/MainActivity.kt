package com.example.ipca.gamecatalog.alldayclinic

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ipca.gamecatalog.alldayclinic.databinding.ActivityMainBinding


import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide();
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(binding.editTextUtente.text.toString(),
          binding.editTextPassword.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val intent = Intent(this, Agenda::class.java)
                    intent.putExtra("email", binding.editTextUtente.text.toString())
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                } else {

                }
            }


    }


}
