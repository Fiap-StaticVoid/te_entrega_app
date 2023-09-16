package com.example.teentrega.api

import com.google.gson.Gson
import okhttp3.RequestBody.Companion.toRequestBody

data class Carrier (
    val id: String?,
    val nome: String,
    val placa_veiculo: String,
    val id_cliente: String,
) {
    fun paraJson(): String {
        val dados = mutableMapOf<String, String>()
        if (this.id != null) {
            dados["id"] = this.id
        }
        dados["nome"] = this.nome
        dados["placa_veiculo"] = this.placa_veiculo
        dados["id_cliente"] = this.id_cliente
        return Gson().toJson(dados)
    }
}
class CarrierAPI (baseURL: String, callbacksPerOrigin: CallBackPerOrigin) :
    API(baseURL, callbacksPerOrigin) {
    fun create(data: Carrier) {
        val body = data.paraJson().toRequestBody(JSON)
        return this.call("transportadores/", Method.POST, body)
    }

    fun list() {
        return this.call("transportadores/", Method.GET, null)
    }

    fun update(data: Carrier) {
        val id = data.id!!
        val body = data.paraJson().toRequestBody(JSON)
        return this.call("transportadores/$id", Method.PATCH, body)
    }

    fun delete(id: String) {
        return this.call("transportadores/$id", Method.DELETE, null)
    }
}