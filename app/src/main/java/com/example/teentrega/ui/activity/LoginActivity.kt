package com.example.teentrega.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.teentrega.R
import com.example.teentrega.api.AuthData
import com.example.teentrega.api.CallBackOrigin
import com.example.teentrega.api.CallBackPerOrigin
import com.example.teentrega.api.ClientAPI
import com.example.teentrega.api.Method
import com.example.teentrega.databinding.ActivityLoginBinding
import com.example.teentrega.utils.Constants
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater)

        fun update(info: Any) {
            if (info !is JSONObject) return
            val sharedPref = getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)

            val editor = sharedPref.edit()
            editor.putString(Constants.BEARER_TOKEN, info.getString("token"))
            editor.apply()

            val intent = Intent(this, DashboardActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        val mutable : CallBackPerOrigin = mutableMapOf()
        mutable[CallBackOrigin("clientes/autenticar", Method.POST)] = mutableListOf(::update)

        val clientAPI = ClientAPI(Constants.IP, mutable)

        this.window.statusBarColor = ContextCompat.getColor(this, R.color.background)

        val spannable = SpannableString("${getString(R.string.dont_have_account)} ${getString(R.string.create)}")
        spannable.setSpan(
            ForegroundColorSpan(getColor(R.color.text_main)),
            0,20, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        binding.buttonLogin.text = spannable

        binding.buttonLogin.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        binding.buttonEnterAccount.setOnClickListener {
            clientAPI.login(AuthData(binding.usernameEdit.text.toString(), binding.passwordEdit.text.toString()))
        }

        setContentView(binding.root)
    }

}