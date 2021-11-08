package cn.ljpc.wechat.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cn.ljpc.wechat.model.Data
import cn.ljpc.wechat.model.InputParams
import cn.ljpc.wechat.model.User
import cn.ljpc.wechat.util.checkMail
import cn.ljpc.wechat.viewmodel.LoginViewModel
import cn.ljpc.wechat.viewmodel.UserViewModel
import com.airbnb.mvrx.compose.collectAsState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RegisterPage(navController: NavController?) {
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
        Header("注册")
        RegisterForm(navController)
    }
}

@Composable
fun RegisterForm(navController: NavController?) {
    val userViewModel = UserViewModel()
    val loginViewModel = LoginViewModel()
    val userState = userViewModel.collectAsState()
    val passwordState = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.padding(bottom = 24.dp)) {
        Input(
            Modifier.padding(bottom = 16.dp),
            label = "姓名",
            viewModel = userViewModel,
            inputParams = InputParams.USERNAME
        )
        Input(
            Modifier.padding(bottom = 16.dp),
            label = "邮箱",
            viewModel = userViewModel,
            inputParams = InputParams.MAIL
        )
        Input(
            label = "输入密码",
            viewModel = userViewModel,
            inputParams = InputParams.PASSWORD,
            isSecure = true
        )
        Input(
            label = "再次输入密码",
            viewModel = userViewModel,
            password = passwordState,
            inputParams = InputParams.RE_PASSWORD,
            isSecure = true
        )
    }
    Button(
        onClick = {
            if (userState.value.username.trim() == "") {
                loginViewModel.setMessage("用户名不能为空")
                loginViewModel.setLoginSate(true)
                return@Button
            } else {
                for (user in Data.users) {
                    if (user.username == userState.value.username.trim()) {
                        loginViewModel.setMessage("用户名已存在")
                        loginViewModel.setLoginSate(true)
                        return@Button
                    }
                }
            }

            if (userState.value.mail == "") {
                loginViewModel.setMessage("邮箱不能为空")
                loginViewModel.setLoginSate(true)
                return@Button
            } else {
                //验证邮箱的格式是否正确
                if (!checkMail(mail = userState.value.mail)) {
                    loginViewModel.setMessage("邮箱格式有误")
                    loginViewModel.setLoginSate(true)
                    return@Button
                }
            }

            if (passwordState.value == "" || userState.value.password == "") {
                loginViewModel.setMessage("密码不能为空")
                loginViewModel.setLoginSate(true)
                return@Button
            }

            if (userState.value.password != passwordState.value) {
                loginViewModel.setMessage("两次输入的密码需要相同")
                loginViewModel.setLoginSate(true)
                return@Button
            }

            //做完上面的验证，说明所有提交的信息都合法
            //提交信息
            loginViewModel.setButtonState(true)
            userViewModel.viewModelScope.launch {
                Log.d("jie", "正在提交信息")
                delay(3000)
                Data.users.add(
                    User(
                        userState.value.username,
                        userState.value.mail,
                        userState.value.password
                    )
                )
                Log.d("jie", "提交信息成功")
                loginViewModel.setButtonState(false)
                //直接跳转
                navController?.navigate("mainPage")
            }
        },
        modifier = Modifier
            .padding(0.dp, 0.dp, 0.dp, 100.dp)
            .size(200.dp, 50.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0, 201, 87)
        )
    ) {
        Text(text = "注册", color = Color.White)
    }
    TipDialog(title = "提示", loginViewModel = loginViewModel)
    LoadingDialog(loginViewModel = loginViewModel, "注册中...")
}

@Preview(showBackground = true)
@Composable
fun PreViewRegisterPage() {
    LoginPage(null)
}


