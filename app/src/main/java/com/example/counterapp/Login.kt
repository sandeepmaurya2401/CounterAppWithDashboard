package com.example.counterapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val gotoSignup = findViewById<Button>(R.id.goToSignupButton)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val email = findViewById<EditText>(R.id.usernameEditText)
        val loginPasswords = findViewById<EditText>(R.id.loginPassword)

        auth = FirebaseAuth.getInstance()

        gotoSignup.setOnClickListener{
            startActivity(Intent(this, Signup::class.java))
        }
        loginButton.setOnClickListener{
            val emails = email.text.toString()
            val password = loginPasswords.text.toString()

            if (emails.isEmpty()||password.isEmpty()){
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_LONG).show()
            }else{
                auth.signInWithEmailAndPassword(emails, password)
                    .addOnCompleteListener{task ->
                        if (task.isSuccessful){
                            Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this, "Login Field${task.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }
    }
}