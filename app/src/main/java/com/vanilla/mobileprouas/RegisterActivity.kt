package com.vanilla.mobileprouas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {
    private lateinit var fullnameregist: EditText
    private lateinit var emailregist: EditText
    private lateinit var unameregist: EditText
    private lateinit var passregist: EditText
    private lateinit var btnRegister: Button
    private lateinit var loginhere: TextView

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        fullnameregist = findViewById(R.id.fullnameregist)
        emailregist = findViewById(R.id.emailregist)
        unameregist = findViewById(R.id.unameregist)
        passregist = findViewById(R.id.passregist)
        btnRegister = findViewById(R.id.btnRegister)

        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mobileprouas-af91e-default-rtdb.firebaseio.com/")

        btnRegister.setOnClickListener {
            val fullname = fullnameregist.text.toString()
            val email = emailregist.text.toString()
            val username = unameregist.text.toString()
            val password = passregist.text.toString()

            if (fullname.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(applicationContext, "Masih ada Data yang Kosong!", Toast.LENGTH_SHORT).show()
            } else {
                database = FirebaseDatabase.getInstance().getReference("users")
                database.child(username).child("fullname").setValue(fullname)
                database.child(username).child("email").setValue(email)
                database.child(username).child("username").setValue(username)
                database.child(username).child("password").setValue(password)

                Toast.makeText(applicationContext, "Register Berhasil", Toast.LENGTH_SHORT).show()
                val register = Intent(applicationContext, LoginActivity::class.java)
                startActivity(register)
            }
        }

        loginhere.setOnClickListener {
            val login = Intent(applicationContext, LoginActivity::class.java)
            startActivity(login)
        }
    }
}
