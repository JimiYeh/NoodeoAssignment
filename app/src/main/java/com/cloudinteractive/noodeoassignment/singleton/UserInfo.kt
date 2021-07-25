package com.cloudinteractive.noodeoassignment.singleton

import com.cloudinteractive.noodeoassignment.model.RespLogin

class UserInfo {
    companion object {
        var info: RespLogin? = null
    }
}