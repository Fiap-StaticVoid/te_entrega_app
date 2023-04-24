package com.example.teentrega

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.teentrega.databinding.ActivityCreateAccountBinding

class CreateAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}