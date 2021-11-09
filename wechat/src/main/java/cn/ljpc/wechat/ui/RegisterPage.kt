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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cn.ljpc.wechat.model.Data
import cn.ljpc.wechat.model.User
import cn.ljpc.wechat.util.checkMail
import cn.ljpc.wechat.viewmodel.LoginViewModel
import cn.ljpc.wechat.viewmodel.UserLoginViewModel
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
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
        val userLoginViewModel: UserLoginViewModel = mavericksViewModel()
        val loginViewModel: LoginViewModel = mavericksViewModel()
        val loginState = loginViewModel.collectAsState()
        Header("注册")
        RegisterForm(userLoginViewModel, loginViewModel, navController)
        TipDialog(
            title = "提示",
            contextMsg = loginState.value.feedbackMeg,
            isShow = loginState.value.loginSuccess,
            onDismissRequest = {
                loginViewModel.setLoginSate(false)
            },
            onConfirmButtonClick = {
                loginViewModel.setLoginSate(false)
            }
        )
        LoadingDialog(
            "注册中",
            isShow = loginState.value.buttonState
        )
    }
}

@Composable
fun RegisterForm(
    userLoginViewModel: UserLoginViewModel,
    loginViewModel: LoginViewModel,
    navController: NavController?
) {
    val userLoginState = userLoginViewModel.collectAsState()
    Column(modifier = Modifier.padding(bottom = 24.dp)) {
        Input(
            Modifier.padding(bottom = 16.dp),
            label = "姓名",
            value = userLoginState.value.username,
            valueChange = {
                userLoginViewModel.setUsername(it)
            }
        )
        Input(
            Modifier.padding(bottom = 16.dp),
            label = "邮箱",
            value = userLoginState.value.mail,
            valueChange = {
                userLoginViewModel.setMail(it)
            }
        )
        Input(
            label = "输入密码",
            value = userLoginState.value.password,
            valueChange = {
                userLoginViewModel.setPassword(it)
            },
            isSecure = true
        )
        Input(
            label = "再次输入密码",
            value = userLoginState.value.password,
            valueChange = {
                userLoginViewModel.setPassword(it)
            },
            isSecure = true
        )
    }
    Button(
        onClick = {
            if (userLoginState.value.username.trim() == "") {
                loginViewModel.setMessage("用户名不能为空")
                loginViewModel.setLoginSate(true)
                return@Button
            } else {
                for (user in Data.users) {
                    if (user.username == userLoginState.value.username.trim()) {
                        loginViewModel.setMessage("用户名已存在")
                        loginViewModel.setLoginSate(true)
                        return@Button
                    }
                }
            }

            if (userLoginState.value.mail == "") {
                loginViewModel.setMessage("邮箱不能为空")
                loginViewModel.setLoginSate(true)
                return@Button
            } else {
                //验证邮箱的格式是否正确
                if (!checkMail(mail = userLoginState.value.mail)) {
                    loginViewModel.setMessage("邮箱格式有误")
                    loginViewModel.setLoginSate(true)
                    return@Button
                }
            }

            if (userLoginState.value.repassword == "" || userLoginState.value.password == "") {
                loginViewModel.setMessage("密码不能为空")
                loginViewModel.setLoginSate(true)
                return@Button
            }

            if (userLoginState.value.password != userLoginState.value.repassword) {
                loginViewModel.setMessage("两次输入的密码需要相同")
                loginViewModel.setLoginSate(true)
                return@Button
            }

            //做完上面的验证，说明所有提交的信息都合法
            //提交信息
            loginViewModel.setButtonState(true)
            userLoginViewModel.viewModelScope.launch {
                Log.d("jie", "正在提交信息")
                delay(3000)
                Data.users.add(
                    User(
                        userLoginState.value.username,
                        userLoginState.value.mail,
                        userLoginState.value.password
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
}

