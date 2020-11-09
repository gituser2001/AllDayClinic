package com.example.ipca.gamecatalog.alldayclinic

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.*

class
MainActivity : AppCompatActivity() {
private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide();
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email = findViewById<TextView>(R.id.editTextUtente)
        val password = findViewById<TextView>(R.id.editTextPassword)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener {  auth = getInstance()
            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(this, Agenda::class.java)
                            intent.putExtra("email", email.text.toString())
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@MainActivity, "email ou palavra-passe incorreta", Toast.LENGTH_SHORT).show()
                        }


                    }}

    }

}
