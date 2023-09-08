package com.example.teentrega.api

import okhttp3.RequestBody.Companion.toRequestBody

data class Carrier (
    val id: String?,
    val nome: String,
    val placa_veiculo: String,
    val id_cliente: String,
)
class CarrierAPI (baseURL: String, callbacksPerOrigin: CallBackPerOrigin) :
    API(baseURL, callbacksPerOrigin) {
    fun create(data: Carrier) {
        val body = data.toString().toRequestBody(JSON)
        return this.call("transportadores/", Method.POST, body)
    }

    fun list() {
        return this.call("transportadores/", Method.GET, null)
    }

    fun update(data: Carrier) {
        val id = data.id!!
        val body = data.toString().toRequestBody(JSON)
        return this.call("transportadores/$id", Method.PATCH, body)
    }

    fun delete(id: String) {
        return this.call("transportadores/$id", Method.DELETE, null)
    }
}