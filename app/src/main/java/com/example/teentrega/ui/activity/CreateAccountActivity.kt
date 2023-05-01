package com.example.teentrega.ui.activity

import android.content.Intent
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

        binding.buttonContinue.setOnClickListener { it ->

            if (navController.currentDestination?.id != R.id.create_account_fragment_5) {
                val nextFragment = when (navController.currentDestination?.id) {
                    R.id.create_account_fragment_1 -> R.id.create_account_fragment_2
                    R.id.create_account_fragment_2 -> R.id.create_account_fragment_3
                    R.id.create_account_fragment_3 -> R.id.create_account_fragment_4
                    else -> R.id.create_account_fragment_5
                }

                nextFragment.let {
                    navController.navigate(it)
                }

                if (it.id == R.id.create_account_fragment_5) {
                    binding.buttonContinue.text = getString(R.string.finish)
                }
            } else {
                val intent = Intent(this, FinishAccountCreationActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }

        setContentView(binding.root)
    }
}