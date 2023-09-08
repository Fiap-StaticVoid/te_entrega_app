package com.example.teentrega.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.teentrega.R
import com.example.teentrega.api.AuthData
import com.example.teentrega.databinding.ActivityCreateAccountBinding
import com.example.teentrega.ui.fragment.createaccount.CreateAccountFirstFragment

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val viewModel: ItemViewModel by viewModels()
        val binding = ActivityCreateAccountBinding.inflate(layoutInflater)

        this.window.statusBarColor = ContextCompat.getColor(this, R.color.background)

        val spannable = SpannableString("${getString(R.string.already_have_account)} ${getString(R.string.enter)}")
        spannable.setSpan(
            ForegroundColorSpan(getColor(R.color.text_main)),
            0,20, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        binding.buttonLogin.text = spannable
        binding.buttonLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        navController = navHostFragment.navController

        val a: AuthData = AuthData("", "");

        binding.buttonContinue.setOnClickListener { it ->

            if (navController.currentDestination?.id != R.id.create_account_fragment_3) {
                val nextFragment = when (navController.currentDestination?.id) {
                    R.id.create_account_fragment_1 -> R.id.create_account_fragment_2
                    else -> R.id.create_account_fragment_3
                }

                nextFragment.let {
                    navController.navigate(it)
                }

                if (it.id == R.id.create_account_fragment_3) {
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