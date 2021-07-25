package com.cloudinteractive.noodeoassignment.network

import com.cloudinteractive.noodeoassignment.model.ReqUpdateTimezone
import com.cloudinteractive.noodeoassignment.model.RespError
import com.cloudinteractive.noodeoassignment.model.RespLogin
import com.cloudinteractive.noodeoassignment.model.RespUpdateTimezone
import com.cloudinteractive.noodeoassignment.repository.NetworkResponse
import retrofit2.http.*

interface ApiService {

    @GET("/api/login")
    suspend fun login(
        @Query("username") userName: String,
        @Query("password") password: String
    ): NetworkResponse<RespLogin>

    @PUT("/api/users/{objectId}")
    suspend fun updateTimezone(
        @Path("objectId") objectId: String,
        @Body body: ReqUpdateTimezone
    ): NetworkResponse<RespUpdateTimezone>
}