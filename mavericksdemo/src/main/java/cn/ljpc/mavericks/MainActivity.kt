package cn.ljpc.mavericks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import cn.ljpc.mavericks.ui.theme.ComposeStudyTheme
import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.compose.collectAsState

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //初始化Mavericks
        Mavericks.initialize(this)
        setContent {
            ComposeStudyTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MyComponent()
                }
            }
        }
    }
}

data class CounterState(val count: Int) : MavericksState

class CounterViewModel(initialState: CounterState = CounterState(0)) :
    MavericksViewModel<CounterState>(initialState) {
    fun incrementCount() = setState { copy(count = count + 1) }
}

@Composable
fun MyComponent() {
    val viewModel = CounterViewModel()
    ShowView(viewModel)
}

@Composable
private fun ShowView(
    viewModel: CounterViewModel
) {
    val state by viewModel.collectAsState()
    //两种不同的写法，获取的state是同一个
//    val count1 = viewModel.collectAsState(CounterState::count)
//    val count2 = viewModel.collectAsState { it.count }
    Column {
        Text(text = "点击按钮${state.count}次")
        Button(onClick = { viewModel.incrementCount() }) {
            Text(text = "点击我试试")
        }
    }
}
