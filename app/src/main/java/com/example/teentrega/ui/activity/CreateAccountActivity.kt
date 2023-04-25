package com.example.teentrega.ui.activity

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.teentrega.R
import com.example.teentrega.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCreateAccountBinding.inflate(layoutInflater)

        val spannable = SpannableString("${getString(R.string.dont_have_account)} ${getString(R.string.create)}")
        spannable.setSpan(
            ForegroundColorSpan(getColor(R.color.text_main)),
            0,20, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        binding.buttonLogin.text = spannable

        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        navController = navHostFragment.navController

        binding.buttonContinue.setOnClickListener {

            val nextFragment = when (navController.currentDestination?.id) {
                R.id.first_fragment -> R.id.second_fragment
                else -> null
            }

            nextFragment?.let {
                navController.navigate(it)
            }
        }

        setContentView(binding.root)
    }
}