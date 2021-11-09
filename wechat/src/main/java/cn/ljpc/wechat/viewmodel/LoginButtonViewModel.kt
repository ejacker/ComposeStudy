package cn.ljpc.wechat.viewmodel

import cn.ljpc.wechat.model.LoginState
import com.airbnb.mvrx.MavericksViewModel

//initialState不需要传输默认值
class LoginViewModel(initialState: LoginState) : MavericksViewModel<LoginState>(initialState) {

    fun setButtonState(state: Boolean) = setState {
        copy(buttonState = state)
    }

    fun setLoginSate(state: Boolean) = setState {
        copy(loginSuccess = state)
    }

    fun setMessage(state: String) = setState {
        copy(feedbackMeg = state)
    }
}