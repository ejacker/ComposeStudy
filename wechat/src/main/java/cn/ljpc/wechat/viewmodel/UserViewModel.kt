package cn.ljpc.wechat.viewmodel

import cn.ljpc.wechat.model.User
import com.airbnb.mvrx.MavericksViewModel

class UserViewModel(initialState: User = User("", "", "")) :
    MavericksViewModel<User>(initialState) {

    fun setUsername(_username: String) = setState {
        copy(username = _username, password = password, mail = mail)
    }

    fun setMail(_mail: String) = setState {
        copy(username = username, password = password, mail = _mail)
    }

    fun setPassword(_password: String) = setState {
        copy(username = username, password = _password, mail = mail)
    }
}