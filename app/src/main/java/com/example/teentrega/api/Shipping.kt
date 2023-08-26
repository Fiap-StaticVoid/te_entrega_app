package com.example.teentrega.api

import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

data class Shipping (
    val id: String?,
    val data_da_solicitacao: String,
    val data_da_entrega: String,
    val produtos: List<String>,
    val id_cliente: String,
    val id_transportador: String
)

class ShippingAPI(baseURL: String, callbackPerRoute: MutableMap<String, (value: JSONObject) -> Unit>) :
    API(baseURL, callbackPerRoute) {
    fun create(data: Shipping) {
        val body = data.toString().toRequestBody(JSON)
        return this.call("clientes/", Method.POST, body)
    }

    fun list() {
        return this.call("clientes/", Method.GET, null)
    }

    fun update(data: Shipping) {
        val id = data.id!!
        val body = data.toString().toRequestBody(JSON)
        return this.call("clientes/$id", Method.PATCH, body)
    }

    fun delete(id: String) {
        return this.call("clientes/$id", Method.DELETE, null)
    }
}