package cn.ljpc.wechat.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cn.ljpc.wechat.model.Data
import cn.ljpc.wechat.model.InputParams
import cn.ljpc.wechat.viewmodel.LoginViewModel
import cn.ljpc.wechat.viewmodel.UserViewModel
import com.airbnb.mvrx.compose.collectAsState
import kotlinx.coroutines.*
import cn.ljpc.wechat.ui.TipDialog as TipDialog

@Composable
fun LoginPage(navController: NavController?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(horizontal = 24.dp)
            .padding(bottom = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Header("登录")
        val loginViewModel = LoginViewModel()
        val userViewModel = UserViewModel()
        LoginForm(loginViewModel, userViewModel, navController = navController)
        LoadingDialog(loginViewModel, "登录中")
        TipDialog("提示", loginViewModel)
    }
}

@Composable
fun LoginForm(
    loginViewModel: LoginViewModel,
    userViewModel: UserViewModel,
    navController: NavController?
) {
    val userState = userViewModel.collectAsState()
    Column(modifier = Modifier.padding(bottom = 24.dp)) {
        Input(
            Modifier.padding(bottom = 16.dp),
            label = "用户名",
            viewModel = userViewModel,
            inputParams = InputParams.USERNAME
        )
        Input(
            label = "密码",
            isSecure = true,
            viewModel = userViewModel,
            inputParams = InputParams.PASSWORD
        )
    }
    Button(
        onClick = {
            if (userState.value.username.trim() != "" && userState.value.password != "") {
                loginViewModel.setButtonState(true)
                loginViewModel.viewModelScope.launch {
                    Log.d("jie", "正在后台验证信息")
                    delay(3000)
                    for (user in Data.users) {
                        if (userState.value.username.trim() == user.username
                            && userState.value.password == user.password
                        ) {
                            Log.d("jie", "验证信息完成")
                            //跳转到主界面
                            navController!!.navigate("mainPage")
                            loginViewModel.setButtonState(false)
                            return@launch
                        }
                    }
                    loginViewModel.setMessage("用户名或者密码错误")
                    loginViewModel.setButtonState(false)
                    loginViewModel.setLoginSate(true)
                }
            } else {
                loginViewModel.setMessage("用户名或者密码不能为空")
                loginViewModel.setLoginSate(true)
            }
        },
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 100.dp)
            .size(200.dp, 50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0, 201, 87)
        )
    ) {
        Text(text = "登录", color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun PreViewLoginPage() {
    LoginPage(null)
}