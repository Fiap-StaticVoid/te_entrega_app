package com.example.teentrega.ui.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.teentrega.R
import com.example.teentrega.common.Httpx
import com.example.teentrega.databinding.ActivitySendPackageBinding
import com.example.teentrega.databinding.FragmentHeaderBinding
import org.json.JSONArray

class SendPackageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_package)
        val binding = ActivitySendPackageBinding.inflate(layoutInflater)
        val fragHeader = FragmentHeaderBinding.inflate(layoutInflater)

        fragHeader.textView.text = "Envio"
        fun update(states: JSONArray) {
            val statesList = arrayOf<String>()

            for (i in 0 until states.length()) {
                statesList[i] = states.getJSONObject(i).getString("nome")
            }
            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                this, R.layout.activity_send_package, statesList
            )
            binding.spinner.adapter = adapter
        }
        val httpx = Httpx(::update)
        httpx.getStates()
    }
}