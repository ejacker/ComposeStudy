package cn.ljpc.wechat.data

import android.content.Context
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController

//local provider
//预先定义CompositionLocals
val LocalString = compositionLocalOf<String> {
    "hello"
}
//
//val MyNavController = compositionLocalOf<NavController> {
//    NavHostController(null)
//}