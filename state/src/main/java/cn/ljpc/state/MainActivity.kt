package cn.ljpc.state

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloScreen()
        }
    }
}

@Composable
fun HelloScreen() {
    val clickCountState = remember {
        mutableStateOf(0)
    }

    HelloWorldContent(clickCountState.value) {
        clickCountState.value++
    }
}

@Composable
fun Demo(){

}

@Composable
fun HelloWorldContent(clickCount: Int, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("点击了${clickCount}次")
    }
}

@Preview
@Composable
fun PreviewHelloScreen(){
    HelloScreen()
}