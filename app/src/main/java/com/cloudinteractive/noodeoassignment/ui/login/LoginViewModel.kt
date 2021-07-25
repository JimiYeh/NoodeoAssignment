package com.cloudinteractive.noodeoassignment.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloudinteractive.noodeoassignment.model.RespLogin
import com.cloudinteractive.noodeoassignment.network.NetworkRepository
import com.cloudinteractive.noodeoassignment.repository.IRepository
import com.cloudinteractive.noodeoassignment.repository.NetworkResponse
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository: IRepository by lazy { NetworkRepository() }

    private val _loginResponse = MutableLiveData<NetworkResponse<RespLogin>>()
    val loginResponse: LiveData<NetworkResponse<RespLogin>>
        get() = _loginResponse


    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginResponse.postValue(repository.login(email, password))
        }
    }
}