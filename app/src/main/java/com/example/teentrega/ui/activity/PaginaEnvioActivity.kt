package com.example.teentrega.ui.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.teentrega.R
import com.example.teentrega.common.Httpx
import com.example.teentrega.databinding.ActivityPaginaEnvioBinding
import com.example.teentrega.databinding.FragmentHeaderBinding
import org.json.JSONArray


class PaginaEnvioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagina_envio)
        var binding = ActivityPaginaEnvioBinding.inflate(layoutInflater)
        var fragHeader = FragmentHeaderBinding.inflate(layoutInflater)

        fragHeader.textView.text = "Envio"
        fun atualizar(estados: JSONArray) {
            var estadosString = arrayOf<String>()

            for (i in 0 until estados.length()) {
                estadosString[i] = estados.getJSONObject(i).getString("nome")
            }
            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                this, R.layout.activity_pagina_envio, estadosString
            )
            binding.spinner.adapter = adapter
        }
        val httpx = Httpx(::atualizar)
        httpx.pegar_estados()
    }
}