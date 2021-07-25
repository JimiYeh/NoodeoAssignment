package com.cloudinteractive.noodeoassignment.model

import com.google.gson.annotations.SerializedName

data class RespLogin(
    @SerializedName("ACL")
    val aCL: ACL = ACL(),
    @SerializedName("code")
    val code: String = "",
    @SerializedName("createdAt")
    val createdAt: String = "",
    @SerializedName("isVerifiedReportEmail")
    val isVerifiedReportEmail: Boolean = false,
    @SerializedName("number")
    val number: Int = 0,
    @SerializedName("objectId")
    val objectId: String = "",
    @SerializedName("parameter")
    val parameter: Int = 0,
    @SerializedName("phone")
    val phone: String = "",
    @SerializedName("reportEmail")
    val reportEmail: String = "",
    @SerializedName("sessionToken")
    val sessionToken: String = "",
    @SerializedName("timezone")
    val timezone: Int = 0,
    @SerializedName("updatedAt")
    val updatedAt: String = "",
    @SerializedName("username")
    val username: String = ""
) {
    data class ACL(
        @SerializedName("WkuKfCAdGq")
        val wkuKfCAdGq: WkuKfCAdGq = WkuKfCAdGq()
    ) {
        data class WkuKfCAdGq(
            @SerializedName("read")
            val read: Boolean = false,
            @SerializedName("write")
            val write: Boolean = false
        )
    }
}


/**
{
"objectId": "WkuKfCAdGq",
"username": "test2@qq.com",
"code": "4wtmah5h",
"isVerifiedReportEmail": true,
"reportEmail": "test2@qq.com",
"createdAt": "2019-07-12T07:07:18.027Z",
"updatedAt": "2021-07-25T09:13:03.041Z",
"timezone": 1,
"parameter": 8,
"number": 5,
"phone": "415-369-6666",
"ACL": {
"WkuKfCAdGq": {
"read": true,
"write": true
}
},
"sessionToken": "r:fa2908f91ca605ed9c23a20a133857aa"
}
 **/

