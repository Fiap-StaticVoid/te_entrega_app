package com.example.teentrega.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
            Log.i("MAIN_ACTIVITY", "login")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.buttonRegister.setOnClickListener {
            Log.i("MAIN_ACTIVITY", "register")
//            val intent = Intent(this, CreateAccountActivity::class.java)
            val intent = Intent(this, PaginaEnvioActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)

    }
}