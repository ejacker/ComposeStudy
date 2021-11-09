package cn.ljpc.mvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import cn.ljpc.mvi.ui.theme.ComposeStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStudyTheme {
                Surface(color = MaterialTheme.colors.background) {



                }
            }
        }
    }
}