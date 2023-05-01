package com.example.teentrega.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
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

        val spannable = SpannableString("${getString(R.string.already_have_account)} ${getString(R.string.enter)}")
        spannable.setSpan(
            ForegroundColorSpan(getColor(R.color.text_main)),
            0,20, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        binding.buttonLogin.text = spannable
        binding.buttonLogin.setOnClickListener {
            Log.i("MAIN_ACTIVITY", "login")
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        navController = navHostFragment.navController

        binding.buttonContinue.setOnClickListener { it ->

            if (navController.currentDestination?.id != R.id.fifth_fragment) {
                val nextFragment = when (navController.currentDestination?.id) {
                    R.id.first_fragment -> R.id.second_fragment
                    R.id.second_fragment -> R.id.third_fragment
                    R.id.third_fragment -> R.id.fourth_fragment
                    else -> R.id.fifth_fragment
                }

                nextFragment.let {
                    navController.navigate(it)
                }

                if (it.id == R.id.fifth_fragment) {
                    binding.buttonContinue.text = getString(R.string.finish)
                }
            } else {
                val intent = Intent(this, FinishAcountCreationActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }

        setContentView(binding.root)
    }
}