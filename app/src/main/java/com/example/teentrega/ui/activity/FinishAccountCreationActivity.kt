package com.example.teentrega.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.teentrega.R
import com.example.teentrega.databinding.ActivityFinishAcountCreationBinding

class FinishAccountCreationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_acount_creation)

        val binding = ActivityFinishAcountCreationBinding.inflate(layoutInflater)
    }
}