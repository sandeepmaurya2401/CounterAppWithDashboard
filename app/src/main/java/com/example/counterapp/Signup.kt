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

class Signup : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        val loginButton2 = findViewById<Button>(R.id.loginButton2)
        val yourname = findViewById<EditText>(R.id.yourName)
        val username = findViewById<EditText>(R.id.signupUsername)
        val passwords = findViewById<EditText>(R.id.signupPassword)
        val repasswords = findViewById<EditText>(R.id.signupRe_Password)
        val resister = findViewById<Button>(R.id.resisterButton)

        loginButton2.setOnClickListener{
            startActivity(Intent(this, Login::class.java))
        }
        resister.setOnClickListener{
            // get text from edit text field
            val email = username.text.toString()
            val name = yourname.text.toString()
            val passwoed = passwords.text.toString()
            val repassword = repasswords.text.toString()

            // check if any field is blank

            if (email.isEmpty()|| name.isEmpty()|| passwoed.isEmpty()|| repassword.isEmpty()){
                Toast.makeText(this, "Please fill all the Details", Toast.LENGTH_LONG).show()
            }else if (passwoed != repassword){
                Toast.makeText(this, "Repeat password must be same", Toast.LENGTH_LONG).show()
            }
            else{
                auth.createUserWithEmailAndPassword(email, passwoed)
                    .addOnCompleteListener(this){ task ->
                        if (task.isSuccessful){
                            Toast.makeText(this, "Registration Successful", Toast.LENGTH_LONG).show()
                            startActivity(Intent(this, Login::class.java))
                            finish()
                        }else{
                            Toast.makeText(this, "Registration Field${task.exception?.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }
            }

        }

    }
}