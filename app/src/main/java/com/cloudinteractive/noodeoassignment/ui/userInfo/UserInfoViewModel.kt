package com.cloudinteractive.noodeoassignment.ui.userInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cloudinteractive.noodeoassignment.model.RespUpdateTimezone
import com.cloudinteractive.noodeoassignment.network.NetworkRepository
import com.cloudinteractive.noodeoassignment.repository.IRepository
import com.cloudinteractive.noodeoassignment.repository.NetworkResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserInfoViewModel : ViewModel() {

    private val repository: IRepository by lazy { NetworkRepository() }

    private val _updateTimezoneResponse = MutableLiveData<NetworkResponse<RespUpdateTimezone>>()
    val updateTimezoneResponse: LiveData<NetworkResponse<RespUpdateTimezone>>
        get() = _updateTimezoneResponse


    fun updateTimezone(objectId: String, timezone: Int) {
        GlobalScope.launch {
            _updateTimezoneResponse.postValue(repository.updateTimezone(objectId, timezone))
        }
    }
}