package cn.ljpc.wechat.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cn.ljpc.wechat.R

@Composable
fun FirstPage(navController: NavController?) {
    Image(
        painter = painterResource(id = R.drawable.wechat_font),
        contentDescription = "首页图片",
        modifier = Modifier.fillMaxSize(1f),
        contentScale = ContentScale.FillBounds,//设置图片的缩放方式，当前是全屏模式
    )
    LoginAndRegisterButton(
        loginClick = {
            navController!!.navigate("loginPage")
        }, registerClick = {
            navController!!.navigate("registerPage")
        }
    )
}

@Composable
fun LoginAndRegisterButton(
    modifier: Modifier = Modifier,
    isShow: Boolean = true,
    loginClick: () -> Unit,
    registerClick: () -> Unit
) {
    if (isShow) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = modifier.fillMaxSize(),
        ) {
            Button(
                onClick = loginClick,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 20.dp)
                    .size(150.dp, 50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                )
            ) {
                Text(text = "登录", color = Color(0, 201, 87))
            }
            Spacer(modifier = Modifier.size(30.dp))
            Button(
                onClick = registerClick,
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 20.dp)
                    .size(150.dp, 50.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0, 201, 87)
                )
            ) {
                Text(text = "注册", color = Color.White)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFirstPage() {
    FirstPage(null)
}