package com.cloudinteractive.noodeoassignment.repository

import com.cloudinteractive.noodeoassignment.model.RespError
import com.cloudinteractive.noodeoassignment.model.RespLogin
import com.cloudinteractive.noodeoassignment.model.RespUpdateTimezone

interface IRepository {

    suspend fun login(userName: String, password: String): NetworkResponse<RespLogin, RespError>

    suspend fun updateTimezone(objectId: String, timezone: Int): NetworkResponse<RespUpdateTimezone, RespError>
}