package com.example.teentrega.api

import com.google.gson.Gson
import okhttp3.RequestBody.Companion.toRequestBody

data class Shipping (
    val id: String?,
    val data_da_solicitacao: String,
    val data_da_entrega: String,
    val produtos: List<String>,
    val id_cliente: String,
    val id_transportador: String
) {
    fun paraJson(): String {
        val dados = mutableMapOf<String, Any>()
        if (this.id != null) {
            dados["id"] = this.id
        }
        dados["data_da_solicitacao"] = this.data_da_solicitacao
        dados["data_da_entrega"] = this.data_da_entrega
        dados["produtos"] = this.produtos
        dados["id_cliente"] = this.id_cliente
        dados["id_transportador"] = this.id_transportador
        return Gson().toJson(dados)
    }
}

class ShippingAPI(baseURL: String, callbacksPerOrigin: CallBackPerOrigin) :
    API(baseURL, callbacksPerOrigin) {
    fun create(data: Shipping) {
        val body = data.paraJson().toRequestBody(JSON)
        return this.call("entregas/", Method.POST, body)
    }

    fun list() {
        return this.call("entregas/", Method.GET, null)
    }

    fun update(data: Shipping) {
        val id = data.id!!
        val body = data.paraJson().toRequestBody(JSON)
        return this.call("entregas/$id", Method.PATCH, body)
    }

    fun delete(id: String) {
        return this.call("entregas/$id", Method.DELETE, null)
    }
}