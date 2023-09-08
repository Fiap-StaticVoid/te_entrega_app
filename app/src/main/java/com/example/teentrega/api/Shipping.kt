package com.example.teentrega.api

import okhttp3.RequestBody.Companion.toRequestBody

data class Shipping (
    val id: String?,
    val data_da_solicitacao: String,
    val data_da_entrega: String,
    val produtos: List<String>,
    val id_cliente: String,
    val id_transportador: String
)

class ShippingAPI(baseURL: String, callbacksPerOrigin: CallBackPerOrigin) :
    API(baseURL, callbacksPerOrigin) {
    fun create(data: Shipping) {
        val body = data.toString().toRequestBody(JSON)
        return this.call("entregas/", Method.POST, body)
    }

    fun list() {
        return this.call("entregas/", Method.GET, null)
    }

    fun update(data: Shipping) {
        val id = data.id!!
        val body = data.toString().toRequestBody(JSON)
        return this.call("entregas/$id", Method.PATCH, body)
    }

    fun delete(id: String) {
        return this.call("entregas/$id", Method.DELETE, null)
    }
}