package cn.ljpc.wechat.viewmodel

import cn.ljpc.wechat.model.User
import com.airbnb.mvrx.MavericksViewModel

//initialState不需要传输默认值
class UserViewModel(initialState: User) :
    MavericksViewModel<User>(initialState) {

    fun setUsername(username: String) = setState {
        copy(username = username)
    }

    fun setMail(mail: String) = setState {
        copy(mail = mail)
    }

    fun setPassword(password: String) = setState {
        copy(password = password)
    }
}