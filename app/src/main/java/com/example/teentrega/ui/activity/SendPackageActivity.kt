package com.example.teentrega.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.teentrega.R
import com.example.teentrega.databinding.ActivitySendPackageBinding

class SendPackageActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySendPackageBinding.inflate(layoutInflater)

        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHost.id) as NavHostFragment
        navController = navHostFragment.navController

        binding.buttonContinue.setOnClickListener { it ->
            if (navController.currentDestination?.id != R.id.payment) {
                val nextFragment = when (navController.currentDestination?.id) {
                    R.id.address -> R.id.payment
                    else -> R.id.payment
                }

                nextFragment.let {
                    navController.navigate(it)
                }

                if (it.id == R.id.create_account_fragment_5) {
                    binding.buttonContinue.text = getString(R.string.finish)
                }
            } else {
                val intent = Intent(this, FinishSendPackageActivity::class.java)
                this@SendPackageActivity.finish()
                startActivity(intent)
            }
        }

        setContentView(binding.root)
    }
}