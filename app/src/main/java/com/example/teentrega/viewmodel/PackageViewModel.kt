package com.example.teentrega.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PackageViewModel : ViewModel() {
    private val _cep = MutableLiveData<String>()
    val cep: LiveData<String>
        get() = _cep

    private val _address = MutableLiveData<String>()
    val address: LiveData<String>
        get() = _address


    private val _number = MutableLiveData<String>()
    val number: LiveData<String>
        get() = _number

    init {
        _cep.value = ""
        _address.value = ""
        _number.value = ""
    }

    fun setAddress(pass: String) {
        _address.value = pass
    }

    fun setCEP(name: String) {
        _cep.value = name
    }

    fun setNumber(name: String) {
        _number.value = name
    }
}