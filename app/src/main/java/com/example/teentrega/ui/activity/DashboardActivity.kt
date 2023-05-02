package com.example.teentrega.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.teentrega.R
import com.example.teentrega.databinding.ActivityDashboardBinding
import com.example.teentrega.ui.fragment.dashboard.DashboardSendFragment
import com.example.teentrega.ui.fragment.dashboard.DashboardTrackingFragment

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.window.statusBarColor = ContextCompat.getColor(this, R.color.background)

        val extras = intent.extras

        if (extras != null) {
            if (extras.getBoolean("sendPackage")) {
                val intent = Intent(this, SendPackageActivity::class.java)
                startActivity(intent)
            }
        }

        val binding = ActivityDashboardBinding.inflate(layoutInflater)
        replaceWith(DashboardSendFragment())

        binding.navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.send -> replaceWith(DashboardSendFragment())
                R.id.track -> replaceWith(DashboardTrackingFragment())
            }
            return@setOnItemSelectedListener true
        }

        setContentView(binding.root)
    }

    private fun replaceWith(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.nav_host, fragment).commit()
    }
}