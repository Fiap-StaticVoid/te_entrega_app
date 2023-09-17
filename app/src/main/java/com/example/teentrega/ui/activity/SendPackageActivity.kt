package com.example.teentrega.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.teentrega.R
import com.example.teentrega.api.CallBackOrigin
import com.example.teentrega.api.CallBackPerOrigin
import com.example.teentrega.api.ClientAPI
import com.example.teentrega.api.Method
import com.example.teentrega.api.Shipping
import com.example.teentrega.api.ShippingAPI
import com.example.teentrega.databinding.ActivitySendPackageBinding
import com.example.teentrega.utils.Constants
import com.example.teentrega.viewmodel.AccountViewModel
import com.example.teentrega.viewmodel.PackageViewModel
import org.json.JSONObject
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class SendPackageActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private val viewModel: PackageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivitySendPackageBinding.inflate(layoutInflater)

        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHost.id) as NavHostFragment
        navController = navHostFragment.navController

        binding.buttonContinue.text = getString(R.string.finish)

        fun update(info: JSONObject) {
            val intent = Intent(this, FinishSendPackageActivity::class.java)
            this@SendPackageActivity.finish()
            startActivity(intent)
        }

        fun checkInput() {
            binding.buttonContinue.isEnabled = viewModel.cep.value.toString().length == 8 && viewModel.address.value != "" && viewModel.number.value != ""
        }

        viewModel.cep.observe(this) { _ -> checkInput() }
        viewModel.address.observe(this) { _ -> checkInput() }
        viewModel.number.observe(this) { _ -> checkInput() }

        val mutable : CallBackPerOrigin = mutableMapOf()
        mutable[CallBackOrigin("entregas/", Method.POST)] = mutableListOf(::update)

        val shippingAPI = ShippingAPI(Constants.IP, mutable)

        binding.buttonContinue.setOnClickListener { it ->
            val local = LocalDateTime.now()
            val date = OffsetDateTime.of(local, ZoneOffset.UTC)

            shippingAPI.create(Shipping(null, date.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                date.plusDays(7).format(DateTimeFormatter.ISO_OFFSET_DATE_TIME), listOf(), "885f14b6-498e-4525-8250-9424a3a52b3a", "2f2ffb54-89b4-4a98-9f90-d171a5dcba87"))
        }

        setContentView(binding.root)
    }
}