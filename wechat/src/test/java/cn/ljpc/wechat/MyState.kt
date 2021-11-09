package cn.ljpc.wechat

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.MavericksViewModel

data class MyState(val name: Async<String>) : MavericksState

class MyViewModel(initialState: MyState) : MavericksViewModel<MyState>(initialState) {
}

fun main() {

}