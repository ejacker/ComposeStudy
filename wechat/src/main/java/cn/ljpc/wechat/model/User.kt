package cn.ljpc.wechat.model

import com.airbnb.mvrx.MavericksState

//必须为这些成员 赋默认值
data class User(val username: String = "", val mail: String = "", val password: String = "") :
    MavericksState