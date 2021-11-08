package cn.ljpc.wechat.model

import com.airbnb.mvrx.MavericksState

//成员必须是val
data class User(val username: String, val mail: String, val password: String) : MavericksState