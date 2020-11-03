package com.example.ipca.gamecatalog.alldayclinic

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener


import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide();
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val email = findViewById<TextView>(R.id.editTextUtente)
        val password = findViewById<TextView>(R.id.editTextPassword)
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString()).addOnCompleteListener(this, OnCompleteListener{ task ->
            if(task.isSuccessful){
                Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                val intent = Intent(this, Agenda::class.java)
                startActivity(intent)
                finish()
            }else {
                Toast.makeText(this, "Registration Failed", Toast.LENGTH_LONG).show()
            }
        })


    }


}
