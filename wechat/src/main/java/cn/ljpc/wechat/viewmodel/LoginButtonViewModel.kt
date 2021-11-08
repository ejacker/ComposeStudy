package cn.ljpc.wechat.viewmodel

import cn.ljpc.wechat.model.LoginState
import com.airbnb.mvrx.MavericksViewModel

class LoginViewModel(
    initialState: LoginState = LoginState(
        buttonState = false,
        loginSuccess = false,
        feedbackMeg = ""
    )
) :
    MavericksViewModel<LoginState>(initialState) {

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