package com.cloudinteractive.noodeoassignment.model
import com.google.gson.annotations.SerializedName


data class RespUpdateTimezone(
    @SerializedName("role")
    val role: Role = Role(),
    @SerializedName("updatedAt")
    val updatedAt: String = ""
) {
    data class Role(
        @SerializedName("__op")
        val op: String = ""
    )
}