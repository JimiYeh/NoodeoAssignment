package com.cloudinteractive.noodeoassignment.repository

import com.cloudinteractive.noodeoassignment.model.RespLogin
import com.cloudinteractive.noodeoassignment.model.RespUpdateTimezone

interface IRepository {

    suspend fun login(userName: String, password: String): NetworkResponse<RespLogin>

    suspend fun updateTimezone(objectId: String, timezone: Int): NetworkResponse<RespUpdateTimezone>
}