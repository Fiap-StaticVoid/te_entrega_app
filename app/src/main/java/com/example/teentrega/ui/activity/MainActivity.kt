package com.example.teentrega.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.teentrega.R
import com.example.teentrega.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        val spannable = SpannableString("${getString(R.string.already_have_account)} ${getString(R.string.enter)}")
        spannable.setSpan(ForegroundColorSpan(getColor(R.color.text_main)),
            0,19, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        binding.buttonLogin.text = spannable

        binding.buttonLogin.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        this.window.statusBarColor = ContextCompat.getColor(this, R.color.background)

        binding.buttonRegister.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)

    }
}