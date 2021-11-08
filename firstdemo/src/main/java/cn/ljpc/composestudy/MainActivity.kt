package cn.ljpc.composestudy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cn.ljpc.composestudy.ui.theme.ComposeStudyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeStudyTheme {
                MyContent()
            }
        }
    }
}

/**
 *      val mutableState = remember { mutableStateOf(default) }
var value by remember { mutableStateOf(default) }
val (value, setValue) = remember { mutableStateOf(default) }
 */
@Composable
fun MyContent() {

    val clickBtn = remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        //一行，horizontalArrangement，verticalAlignment
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 10.dp, 0.dp, 0.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { clickBtn.value = true },
                shape = CircleShape,
            ) {
                //添加图标
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = null,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                //添加间隔
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text("打开对话框")
            }

            Button(onClick = { clickBtn.value = true }
            ) {
                Text("打开对话框")
            }
        }

        val userName = remember {
            mutableStateOf("")
        }

        val password = remember {
            mutableStateOf("")
        }

        val passwordHidden = remember {
            mutableStateOf(true)
        }

        //文本框
        TextField(value = userName.value,
            onValueChange = { userName.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            shape = CircleShape,
            singleLine = true,
            placeholder = { Text("请输入用户名") },
            label = { Text(text = "用户名") },
//            leadingIcon = { Icon(Icons.Filled.AccountBox, null) },
            trailingIcon = { Text(text = "@gmail.com") }
        )

        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            shape = CircleShape,
            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordHidden.value = !passwordHidden.value
                    }
                ) {
                    Icon(Icons.Filled.Face, null)
                }
            },
            singleLine = true,
            label = { Text(text = "密码") },
            visualTransformation = if (passwordHidden.value) PasswordVisualTransformation() else VisualTransformation.None
        )
    }

    ShowDialog(clickBtn)
}

@Composable
private fun ShowDialog(clickBtn: MutableState<Boolean>) {
    if (clickBtn.value) {
        AlertDialog(
            onDismissRequest = {
                // 当用户点击对话框以外的地方或者按下系统返回键将会执行的代码
                clickBtn.value = false
            },
            title = {
                Text(
                    text = "开启位置服务",
                    fontWeight = FontWeight.W700,
                    style = MaterialTheme.typography.h6
                )
            },
            text = {
                Text(
                    text = "这将意味着，我们会给您提供精准的位置服务，并且您将接受关于您订阅的位置信息",
                    fontSize = 16.sp
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        clickBtn.value = false
                    },
                ) {
                    Text(
                        "确认",
                        fontWeight = FontWeight.W700,
                        style = MaterialTheme.typography.button
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        clickBtn.value = false
                    }
                ) {
                    Text(
                        "取消",
                        fontWeight = FontWeight.W700,
                        style = MaterialTheme.typography.button
                    )
                }
            }
        )
    }
}

@Composable
fun Other() {
    val passwordHidden = remember {
        mutableStateOf(true)
    }

    val userName = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }
    Column {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "用户名",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(5.dp, 0.dp, 0.dp, 0.dp)
            )
            Spacer(Modifier.padding(horizontal = 8.dp))
            TextField(
                value = userName.value,
                onValueChange = { userName.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                singleLine = true,
                placeholder = { Text("请输入用户名") },
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "密码    ",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(5.dp, 0.dp, 0.dp, 0.dp)
            )
            Spacer(Modifier.padding(horizontal = 8.dp))
            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordHidden.value = !passwordHidden.value
                        }
                    ) {
                        Icon(Icons.Filled.Face, null)
                    }
                },
                singleLine = true,
                placeholder = { Text(text = "请输入密码", textAlign = TextAlign.Center) },
                visualTransformation = if (passwordHidden.value) PasswordVisualTransformation() else VisualTransformation.None
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeStudyTheme {
        MyContent()
    }
}