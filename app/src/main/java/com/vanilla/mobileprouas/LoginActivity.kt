package com.vanilla.mobileprouas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class LoginActivity : AppCompatActivity() {
    private lateinit var unamelogin: EditText
    private lateinit var passlogin: EditText
    private lateinit var btnLogin: Button
    private lateinit var registernow: TextView

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        unamelogin = findViewById(R.id.unamelogin)
        passlogin = findViewById(R.id.passlogin)
        btnLogin = findViewById(R.id.btnLogin)
        registernow = findViewById(R.id.registernow)

        registernow.setOnClickListener {
            val register = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(register)
        }

        btnLogin.setOnClickListener {
            val username = unamelogin.text.toString()
            val password = passlogin.text.toString()
            database = FirebaseDatabase.getInstance().getReference("users")

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(applicationContext, "Username atau Password Salah!", Toast.LENGTH_SHORT).show()
            } else {
                database.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.child(username).exists()) {
                            if (snapshot.child(username).child("password").getValue(String::class.java) == password) {
                                Toast.makeText(applicationContext, "Login Berhasil", Toast.LENGTH_SHORT).show()

                                val login = Intent(applicationContext, HomeActivity::class.java)
                                startActivity(login)
                            } else {
                                Toast.makeText(applicationContext, "Username atau Password Salah!", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(applicationContext, "Username atau Password belum Terdaftar", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Handle possible errors
                    }
                })
            }
        }
    }
}
