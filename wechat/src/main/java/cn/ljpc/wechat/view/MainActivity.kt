package cn.ljpc.wechat.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cn.ljpc.wechat.ui.FirstPage
import cn.ljpc.wechat.ui.LoginPage
import cn.ljpc.wechat.ui.MainPage
import cn.ljpc.wechat.ui.RegisterPage
import cn.ljpc.wechat.ui.theme.ComposeStudyTheme
import com.airbnb.mvrx.Mavericks

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置系统全屏
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )

        //初始化Mavericks
        Mavericks.initialize(this)

        setContent {
            ComposeStudyTheme {
                Surface(color = MaterialTheme.colors.background) {
                    //使用navigation 路由
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "firstPage") {
                        composable("firstPage") {
                            FirstPage(navController = navController)
                        }
                        composable("loginPage") {
                            LoginPage(navController = navController)
                        }
                        composable("registerPage") {
                            RegisterPage(navController = navController)
                        }
                        composable("mainPage") {
                            MainPage(navController = navController)
                        }
                    }
                }
            }
        }
    }
}