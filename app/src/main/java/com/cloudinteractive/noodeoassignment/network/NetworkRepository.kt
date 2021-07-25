package com.cloudinteractive.noodeoassignment.network

import com.cloudinteractive.noodeoassignment.model.ReqUpdateTimezone
import com.cloudinteractive.noodeoassignment.model.RespError
import com.cloudinteractive.noodeoassignment.model.RespLogin
import com.cloudinteractive.noodeoassignment.model.RespUpdateTimezone
import com.cloudinteractive.noodeoassignment.repository.IRepository
import com.cloudinteractive.noodeoassignment.repository.NetworkResponse
import com.cloudinteractive.noodeoassignment.singleton.UserInfo

class NetworkRepository : IRepository {
    override suspend fun login(
        userName: String,
        password: String
    ): NetworkResponse<RespLogin, RespError> {
        val resp = Client.apiService.login(userName, password)
        if (resp is NetworkResponse.Success) {
            UserInfo.info = resp.body
            Client.sessionToken = resp.body.sessionToken
        }
        return resp
    }

    override suspend fun updateTimezone(
        objectId: String,
        timezone: Int
    ): NetworkResponse<RespUpdateTimezone, RespError> = Client.apiService.updateTimezone(objectId, ReqUpdateTimezone(timezone))

}