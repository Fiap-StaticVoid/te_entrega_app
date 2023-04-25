package com.example.teentrega.ui.activity

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import com.example.teentrega.R
import com.example.teentrega.databinding.ActivityCreateAccountBinding
import com.example.teentrega.ui.fragment.CreateAccountFirstFragment
import com.example.teentrega.ui.fragment.CreateAccountSecondFragment

class CreateAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCreateAccountBinding.inflate(layoutInflater)

        val spannable = SpannableString("${getString(R.string.dont_have_account)} ${getString(R.string.create)}")
        spannable.setSpan(
            ForegroundColorSpan(getColor(R.color.text_main)),
            0,20, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        binding.buttonLogin.text = spannable


        val firstFragment = CreateAccountFirstFragment()
        supportFragmentManager.beginTransaction().add(binding.createAccountFragment.id, firstFragment).commit()


        binding.buttonContinue.setOnClickListener {
            val nextFragment = CreateAccountSecondFragment()
            supportFragmentManager.beginTransaction().addToBackStack(null).replace(binding.createAccountFragment.id, nextFragment).commit()
        }

        setContentView(binding.root)
    }
}