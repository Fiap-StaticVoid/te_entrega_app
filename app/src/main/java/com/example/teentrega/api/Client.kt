package com.example.teentrega.api

import okhttp3.RequestBody.Companion.toRequestBody


data class Client (
    val id: String?,
    val nome: String,
    val nome_de_usuario: String,
    val senha: String
)

class ClientAPI(baseURL: String, callbacksPerOrigin: CallBackPerOrigin) :
    API(baseURL, callbacksPerOrigin) {
    fun create(data: Client) {
        val body = data.toString().toRequestBody(JSON)
        return this.call("clientes/", Method.POST, body)
    }

    fun list() {
        return this.call("clientes/", Method.GET, null)
    }

    fun update(data: Client) {
        val id = data.id!!
        val body = data.toString().toRequestBody(JSON)
        return this.call("clientes/$id", Method.PATCH, body)
    }

    fun delete(id: String) {
        return this.call("clientes/$id", Method.DELETE, null)
    }
}