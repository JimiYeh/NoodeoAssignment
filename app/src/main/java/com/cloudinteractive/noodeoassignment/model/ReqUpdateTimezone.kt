package com.cloudinteractive.noodeoassignment.model

import com.google.gson.annotations.SerializedName

data class ReqUpdateTimezone(
    @SerializedName("timezone")
    val timezone: Int
)

