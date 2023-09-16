package com.example.teentrega.api

import com.google.gson.Gson
import okhttp3.RequestBody.Companion.toRequestBody


data class Client (
    val id: String?,
    val nome: String,
    val nome_de_usuario: String,
    val senha: String
) {
    fun paraJson(): String {
        val dados = mutableMapOf<String, String>()
        if (this.id != null) {
            dados["id"] = this.id
        }
        dados["nome"] = this.nome
        dados["nome_de_usuario"] = this.nome_de_usuario
        dados["senha"] = this.senha
        return Gson().toJson(dados)
    }
}

class ClientAPI(baseURL: String, callbacksPerOrigin: CallBackPerOrigin) :
    API(baseURL, callbacksPerOrigin) {
    fun create(data: Client) {
        val body = data.paraJson().toRequestBody(JSON)
        return this.call("clientes/", Method.POST, body)
    }

    fun list() {
        return this.call("clientes/", Method.GET, null)
    }

    fun update(data: Client) {
        val id = data.id!!
        val body = data.paraJson().toRequestBody(JSON)
        return this.call("clientes/$id", Method.PATCH, body)
    }

    fun delete(id: String) {
        return this.call("clientes/$id", Method.DELETE, null)
    }
}