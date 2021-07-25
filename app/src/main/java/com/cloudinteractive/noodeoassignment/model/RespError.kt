package com.cloudinteractive.noodeoassignment.model

import com.google.gson.annotations.SerializedName


data class RespError(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("error")
    val error: String = ""
)

