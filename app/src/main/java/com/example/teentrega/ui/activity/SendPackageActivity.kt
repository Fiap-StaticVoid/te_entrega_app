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
        setContentView(R.layout.activity_send_package)
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
                val intent = Intent(this, FinishAccountCreationActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }
    }
}