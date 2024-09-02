package com.example.counterapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : AppCompatActivity() {

    lateinit var countertxt: TextView
    lateinit var viewModel: MainViewModel
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        countertxt = findViewById<TextView>(R.id.counterText)
        val incrementButton = findViewById<Button>(R.id.incrementButton)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setText()


    incrementButton.setOnClickListener{
    viewModel.increment()
    setText()
}
    }

    fun setText() {
        //val countertxt = findViewById<TextView>(R.id.counterText)
        //val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        countertxt.text = viewModel.count.toString()
    }
}