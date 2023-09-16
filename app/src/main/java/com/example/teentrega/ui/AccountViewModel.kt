package com.example.teentrega.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class AccountViewModel : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String>
        get() = _username


    private val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password


    private val _firstName = MutableLiveData<String>()
    val firstName: LiveData<String>
        get() = _firstName


    private val _lastName = MutableLiveData<String>()
    val lastName: LiveData<String>
        get() = _lastName

    init {
        _username.value = ""
        _password.value = ""
        _firstName.value = ""
        _lastName.value = ""
    }

    fun setPassword(pass: String) {
        _password.value = pass
    }

    fun setUsername(name: String) {
        _username.value = name
    }

    fun setFirstName(name: String) {
        _firstName.value = name
    }

    fun setLastName(name: String) {
        _lastName.value = name
    }
}