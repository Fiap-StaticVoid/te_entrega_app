package com.example.teentrega.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.example.teentrega.R
import com.example.teentrega.databinding.ActivityFinishSendPackageBinding
import com.example.teentrega.viewmodel.AccountViewModel

class FinishSendPackageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityFinishSendPackageBinding.inflate(layoutInflater)

        this.window.statusBarColor = ContextCompat.getColor(this, R.color.background)

        binding.buttonHomepage.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("sendPackage", false)
            startActivity(intent)
        }

        binding.buttonSendPackage.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("sendPackage", true)
            startActivity(intent)
        }
        setContentView(binding.root)
    }
}