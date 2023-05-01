package com.example.teentrega.common

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray
import java.io.IOException


class Httpx(private val callback: (value: JSONArray) -> Unit) {

    private fun updateResult(call: Call, response: Response) {
        response.use {
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            this.callback(JSONArray(response.body!!.string()))
        }
    }

    private fun run(url: String) {

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(
            object: Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.i("Erro na requisicão: ", e.toString())
                }

                override fun onResponse(call: Call, response: Response) = updateResult(call, response)
            }
        )
    }
    fun pegar_estados() {
        this.run("https://servicodados.ibge.gov.br/api/v1/localidades/estados")
    }
    fun pegar_cidades(estado: String) {
        this.run("https://servicodados.ibge.gov.br/api/v1/localidades/estados/${estado}/distritos")
    }
}