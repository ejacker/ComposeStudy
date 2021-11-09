package cn.ljpc.wechat.model

import com.airbnb.mvrx.MavericksState

//必须为这些成员 赋默认值
data class LoginState(
    val buttonState: Boolean = false,
    val loginSuccess: Boolean = false,
    val feedbackMeg: String = ""
) :
    MavericksState