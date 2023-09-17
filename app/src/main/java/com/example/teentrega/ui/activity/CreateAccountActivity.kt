package com.example.teentrega.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.teentrega.R
import com.example.teentrega.api.AuthData
import com.example.teentrega.api.CallBackOrigin
import com.example.teentrega.api.CallBackPerOrigin
import com.example.teentrega.api.Client
import com.example.teentrega.api.ClientAPI
import com.example.teentrega.api.Method
import com.example.teentrega.databinding.ActivityCreateAccountBinding
import com.example.teentrega.viewmodel.AccountViewModel
import com.example.teentrega.utils.Constants
import org.json.JSONObject

class CreateAccountActivity : AppCompatActivity(), LifecycleOwner {

    private lateinit var navController: NavController
    private val viewModel: AccountViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityCreateAccountBinding.inflate(layoutInflater)

        this.window.statusBarColor = ContextCompat.getColor(this, R.color.background)

        fun login(info: JSONObject) {
            val sharedPref = getPreferences(Context.MODE_PRIVATE)
            with (sharedPref.edit()) {
                putString("BearerToken", info.getString("token"))
                apply()
            }

            val intent = Intent(this, FinishAccountCreationActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        fun onCreate(info: JSONObject) {
            val mutable : CallBackPerOrigin = mutableMapOf()
            mutable[CallBackOrigin("clientes/autenticar", Method.POST)] = mutableListOf(::login)

            val clientAPI = ClientAPI(Constants.IP, mutable)

            clientAPI.login(AuthData(viewModel.username.value.toString(), viewModel.password.value.toString()))
        }

        val mutable : CallBackPerOrigin = mutableMapOf()
        mutable[CallBackOrigin("clientes/", Method.POST)] = mutableListOf(::onCreate)

        val clientAPI = ClientAPI(Constants.IP, mutable)

        fun checkInput() {
            binding.buttonContinue.isEnabled = when (navController.currentDestination?.id) {
                R.id.create_account_fragment_1 -> {
                    viewModel.username.value != ""
                }
                R.id.create_account_fragment_2 -> {
                    viewModel.password.value != ""
                }
                else -> {
                    viewModel.firstName.value != "" && viewModel.lastName.value != ""
                }
            }
        }

        viewModel.username.observe(this) { _ -> checkInput() }
        viewModel.password.observe(this) { _ -> checkInput() }
        viewModel.firstName.observe(this) { _ -> checkInput() }
        viewModel.lastName.observe(this) { _ -> checkInput() }

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

        binding.buttonContinue.setOnClickListener { it ->
            val currentId = navController.currentDestination?.id

            if (currentId != R.id.create_account_fragment_3) {
                val nextFragment = when (currentId) {
                    R.id.create_account_fragment_1 -> R.id.create_account_fragment_2
                    else -> R.id.create_account_fragment_3
                }

                nextFragment.let {
                    navController.navigate(it)
                    checkInput()
                }

                if (currentId == R.id.create_account_fragment_2) {
                    binding.buttonContinue.text = getString(R.string.finish)
                }
            } else {
                binding.buttonContinue.text = getString(R.string.finish)

                clientAPI.create(Client(null,
                    (viewModel.firstName.value.toString() + " " + viewModel.lastName.value.toString()),
                    viewModel.username.value.toString(),
                    viewModel.password.value.toString()))
            }
        }

        setContentView(binding.root)
    }
}