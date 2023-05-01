package com.example.teentrega.ui.activity

import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.teentrega.R
import com.example.teentrega.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val binding = ActivityDashboardBinding.inflate(layoutInflater)

        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHost.id) as NavHostFragment
        navController = navHostFragment.navController

        binding.buttonSend.setOnClickListener {

            if (navController.currentDestination?.id == R.id.send_fragment) {
                val buttonContext = ContextThemeWrapper(binding.buttonSend.context, R.style.Button_WithLine_Active)
                navController.navigate(R.id.tracking_fragment)
            } else if (navController.currentDestination?.id == R.id.tracking_fragment) {
                navController.navigate(R.id.send_fragment)
            }
        }
    }
}