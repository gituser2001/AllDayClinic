package com.example.ipca.gamecatalog.alldayclinic

import android.R
import android.R.id
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ipca.gamecatalog.alldayclinic.ui.chat.ChatFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.*


class MainActivity : AppCompatActivity() {
private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar?.hide();
        super.onCreate(savedInstanceState)
        setContentView(com.example.ipca.gamecatalog.alldayclinic.R.layout.activity_main)

        //Variáveis referentes ao login tais como credenciais e o botão
        val email = findViewById<TextView>(com.example.ipca.gamecatalog.alldayclinic.R.id.editTextUtente)
        val password = findViewById<TextView>(com.example.ipca.gamecatalog.alldayclinic.R.id.editTextPassword)
        val btnLogin = findViewById<Button>(com.example.ipca.gamecatalog.alldayclinic.R.id.btn_login)

        //Efetuo do Login
        btnLogin.setOnClickListener { auth = getInstance()
            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener(this) { task ->
                        //Verificação se o login foi bem sucedido
                        if (task.isSuccessful) {
                            val intent = Intent(this, Agenda::class.java)
                            intent.putExtra("email", email.text.toString())
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this@MainActivity, "Email ou palavra-passe incorreta", Toast.LENGTH_SHORT).show()
                        }
                    }
            //token de verificação da sessão
        }
    }
}
