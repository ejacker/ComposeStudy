package cn.ljpc.wechat.model

import com.airbnb.mvrx.MavericksState

data class LoginState(
    val buttonState: Boolean,
    val loginSuccess: Boolean,
    val feedbackMeg: String
) :
    MavericksState