package com.example.teentrega.api

import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException


val JSON: MediaType = "application/json; charset=utf-8".toMediaTypeOrNull()!!
enum class Method {
    GET, POST, PATCH, DELETE
}

data class AuthData (
    val nome_de_usuario: String,
    val senha: String
)
open class API (private val baseURL: String, private var callbackPerRoute: MutableMap<String, (value: JSONObject) -> Unit>) {
    private val client = OkHttpClient()
    private var token: String? = null
    private fun updateResult(call: Call, response: Response, callback: (value: JSONObject) -> Unit) {
        response.use {
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            callback(JSONObject(response.body!!.string()))
        }
    }

    open fun call(route: String, method: Method, body: RequestBody?) {
        val callback = callbackPerRoute[route]!!
        val url = "$baseURL/$route"
        var request = Request.Builder()
            .url(url)
        if (token.toBoolean()) {
            request = request.addHeader(
                "Authorization",
                token!!
            )
        }
        request = when (method) {
            Method.GET -> request
            Method.POST -> request.post(body!!)
            Method.PATCH -> request.patch(body!!)
            Method.DELETE -> request.delete()
        }
        client.newCall(request.build()).enqueue(
            object: Callback {
                override fun onFailure(call: Call, e: IOException) {}

                override fun onResponse(call: Call, response: Response)
                    { updateResult(call, response, callback) }
            }
        )
    }

    fun updateToken(value: JSONObject): Unit{
        token = value.getString("token")
    }
    fun login(data: AuthData) {
        callbackPerRoute["clientes/autenticar"] = ::updateToken
        val body = data.toString().toRequestBody(JSON)
        return this.call("clientes/autenticar", Method.POST, body)
    }
}